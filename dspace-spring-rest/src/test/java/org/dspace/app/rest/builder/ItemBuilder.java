/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.rest.builder;

import org.dspace.content.Collection;
import org.dspace.content.DCDate;
import org.dspace.content.Item;
import org.dspace.content.MetadataSchema;
import org.dspace.content.WorkspaceItem;
import org.dspace.content.service.DSpaceObjectService;
import org.dspace.core.Context;
import org.dspace.eperson.Group;

/**
 * Builder to construct Item objects
 *
 * @author Tom Desair (tom dot desair at atmire dot com)
 * @author Raf Ponsaerts (raf dot ponsaerts at atmire dot com)
 */
public class ItemBuilder extends AbstractDSpaceObjectBuilder<Item> {

    private WorkspaceItem workspaceItem;
    private Item item;
    private Group readerGroup = null;

    protected ItemBuilder(Context context) {
        super(context);
    }

    public static ItemBuilder createItem(final Context context, final Collection col) {
        ItemBuilder builder = new ItemBuilder(context);
        return builder.create(context, col);
    }

    private ItemBuilder create(final Context context, final Collection col) {
        this.context = context;

        try {
            workspaceItem = workspaceItemService.create(context, col, false);
            item = workspaceItem.getItem();
        } catch (Exception e) {
            return handleException(e);
        }

        return this;
    }

    public ItemBuilder withTitle(final String title) {
        return setMetadataSingleValue(item, MetadataSchema.DC_SCHEMA, "title", null, title);
    }

    public ItemBuilder withIssueDate(final String issueDate) {
        return addMetadataValue(item, MetadataSchema.DC_SCHEMA, "date", "issued", new DCDate(issueDate).toString());
    }

    public ItemBuilder withAuthor(final String authorName) {
        return addMetadataValue(item, MetadataSchema.DC_SCHEMA, "contributor", "author", authorName);
    }

    public ItemBuilder withSubject(final String subject) {
        return addMetadataValue(item, MetadataSchema.DC_SCHEMA, "subject", null, subject);
    }

    public ItemBuilder makePrivate() {
        item.setDiscoverable(false);
        return this;
    }

    public ItemBuilder withEmbargoPeriod(String embargoPeriod) {
        return setEmbargo(embargoPeriod, item);
    }

    public ItemBuilder withReaderGroup(Group group) {
        readerGroup = group;
        return this;
    }

    @Override
    public Item build() {
        try {
            installItemService.installItem(context, workspaceItem);
            itemService.update(context, item);

            //Check if we need to make this item private. This has to be done after item install.
            if (readerGroup != null) {
                setOnlyReadPermission(workspaceItem.getItem(), readerGroup, null);
            }

            context.dispatchEvents();

            indexingService.commit();
            return item;
        } catch (Exception e) {
            return handleException(e);
        }
    }

    protected void cleanup() throws Exception {
        delete(item);
    }

    @Override
    protected DSpaceObjectService<Item> getService() {
        return itemService;
    }

}
