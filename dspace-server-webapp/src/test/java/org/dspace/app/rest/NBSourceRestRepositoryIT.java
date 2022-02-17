/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.rest;

import static org.dspace.app.rest.matcher.NBSourceMatcher.matchNBSourceEntry;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dspace.app.rest.test.AbstractControllerIntegrationTest;
import org.dspace.builder.CollectionBuilder;
import org.dspace.builder.CommunityBuilder;
import org.dspace.builder.ItemBuilder;
import org.dspace.builder.NBEventBuilder;
import org.dspace.content.Collection;
import org.dspace.content.Item;
import org.dspace.content.NBEvent;
import org.dspace.services.ConfigurationService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Integration tests for {@link NBSourceRestRepository}.
 *
 * @author Luca Giamminonni (luca.giamminonni at 4science.it)
 *
 */
public class NBSourceRestRepositoryIT extends AbstractControllerIntegrationTest {

    @Autowired
    private ConfigurationService configurationService;

    private Item target;

    @Before
    public void setup() {

        context.turnOffAuthorisationSystem();

        parentCommunity = CommunityBuilder.createCommunity(context)
            .withTitle("Community")
            .build();

        Collection collection = CollectionBuilder.createCollection(context, parentCommunity)
            .withName("Collection")
            .build();

        target = ItemBuilder.createItem(context, collection)
            .withTitle("Item")
            .build();

        context.restoreAuthSystemState();

        configurationService.setProperty("nbevent.sources",
            new String[] { "openaire", "test-source", "test-source-2" });

    }

    @Test
    public void testFindAll() throws Exception {

        context.turnOffAuthorisationSystem();

        createEvent("openaire", "TOPIC/OPENAIRE/1", "Title 1");
        createEvent("openaire", "TOPIC/OPENAIRE/2", "Title 2");
        createEvent("openaire", "TOPIC/OPENAIRE/2", "Title 3");

        createEvent("test-source", "TOPIC/TEST/1", "Title 4");
        createEvent("test-source", "TOPIC/TEST/1", "Title 5");

        context.restoreAuthSystemState();

        String authToken = getAuthToken(admin.getEmail(), password);
        getClient(authToken).perform(get("/api/integration/nbsources"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$._embedded.nbsources", contains(
                matchNBSourceEntry("openaire", 3),
                matchNBSourceEntry("test-source", 2),
                matchNBSourceEntry("test-source-2", 0))))
            .andExpect(jsonPath("$.page.size", is(20)))
            .andExpect(jsonPath("$.page.totalElements", is(3)));

    }

    @Test
    public void testFindAllForbidden() throws Exception {

        context.turnOffAuthorisationSystem();

        createEvent("openaire", "TOPIC/OPENAIRE/1", "Title 1");
        createEvent("test-source", "TOPIC/TEST/1", "Title 4");

        context.restoreAuthSystemState();

        String token = getAuthToken(eperson.getEmail(), password);
        getClient(token).perform(get("/api/integration/nbsources"))
            .andExpect(status().isForbidden());

    }

    @Test
    public void testFindAllUnauthorized() throws Exception {

        context.turnOffAuthorisationSystem();

        createEvent("openaire", "TOPIC/OPENAIRE/1", "Title 1");
        createEvent("test-source", "TOPIC/TEST/1", "Title 4");

        context.restoreAuthSystemState();

        getClient().perform(get("/api/integration/nbsources"))
            .andExpect(status().isUnauthorized());

    }

    @Test
    public void testFindOne() throws Exception {

        context.turnOffAuthorisationSystem();

        createEvent("openaire", "TOPIC/OPENAIRE/1", "Title 1");
        createEvent("openaire", "TOPIC/OPENAIRE/2", "Title 2");
        createEvent("openaire", "TOPIC/OPENAIRE/2", "Title 3");

        createEvent("test-source", "TOPIC/TEST/1", "Title 4");
        createEvent("test-source", "TOPIC/TEST/1", "Title 5");

        context.restoreAuthSystemState();

        String authToken = getAuthToken(admin.getEmail(), password);
        getClient(authToken).perform(get("/api/integration/nbsources/openaire"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$", matchNBSourceEntry("openaire", 3)));

        getClient(authToken).perform(get("/api/integration/nbsources/test-source"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$", matchNBSourceEntry("test-source", 2)));

        getClient(authToken).perform(get("/api/integration/nbsources/test-source-2"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$", matchNBSourceEntry("test-source-2", 0)));

        getClient(authToken).perform(get("/api/integration/nbsources/unknown-test-source"))
            .andExpect(status().isNotFound());

    }

    @Test
    public void testFindOneForbidden() throws Exception {

        context.turnOffAuthorisationSystem();

        createEvent("openaire", "TOPIC/OPENAIRE/1", "Title 1");
        createEvent("test-source", "TOPIC/TEST/1", "Title 4");

        context.restoreAuthSystemState();

        String token = getAuthToken(eperson.getEmail(), password);
        getClient(token).perform(get("/api/integration/nbsources/openaire"))
            .andExpect(status().isForbidden());

    }

    @Test
    public void testFindOneUnauthorized() throws Exception {

        context.turnOffAuthorisationSystem();

        createEvent("openaire", "TOPIC/OPENAIRE/1", "Title 1");
        createEvent("test-source", "TOPIC/TEST/1", "Title 4");

        context.restoreAuthSystemState();

        getClient().perform(get("/api/integration/nbsources/openaire"))
            .andExpect(status().isUnauthorized());

    }

    private NBEvent createEvent(String source, String topic, String title) {
        return NBEventBuilder.createTarget(context, target)
            .withSource(source)
            .withTopic(topic)
            .withTitle(title)
            .build();
    }

}
