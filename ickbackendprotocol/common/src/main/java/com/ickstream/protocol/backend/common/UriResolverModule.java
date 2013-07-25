/*
 * Copyright (C) 2013 ickStream GmbH
 * All rights reserved
 */

package com.ickstream.protocol.backend.common;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * Injection module that provides instances of {@link UriResolver}
 * <p/>
 * This module is automatically loaded by {@link AbstractInjectModule}
 */
public class UriResolverModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    public UriResolver createUrlResolver() {
        return new UriResolver();
    }
}
