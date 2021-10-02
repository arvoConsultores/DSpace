/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.rest.iiif.service.util;

import org.dspace.app.rest.iiif.service.CacheEvictService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Exposes the Spring managed cache evict service to the event consumer.
 */
@Component
public class CacheEvictBeanLocator implements ApplicationContextAware {

    private static ApplicationContext context;

    private static final String CACHE_SERVICE = "cacheEvictService";

    @Override
    public void setApplicationContext(ApplicationContext appContext)
        throws BeansException {
        context = appContext;

    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static CacheEvictService getCacheEvictService() {
        return (CacheEvictService) context.getBean(CACHE_SERVICE);
    }

}
