package com.arsene.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.arsene.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.arsene.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.arsene.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsene.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsene.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.arsene.domain.Product.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsene.domain.Product.class.getName() + ".categories", jcacheConfiguration);
            cm.createCache(com.arsene.domain.Product.class.getName() + ".attributes", jcacheConfiguration);
            cm.createCache(com.arsene.domain.OrderItems.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsene.domain.OrderItems.class.getName() + ".products", jcacheConfiguration);
            cm.createCache(com.arsene.domain.Department.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsene.domain.Category.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsene.domain.Category.class.getName() + ".products", jcacheConfiguration);
            cm.createCache(com.arsene.domain.Attribute.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsene.domain.Attribute.class.getName() + ".products", jcacheConfiguration);
            cm.createCache(com.arsene.domain.AttributeValue.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsene.domain.ShoppingCard.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsene.domain.ShoppingCard.class.getName() + ".products", jcacheConfiguration);
            cm.createCache(com.arsene.domain.Orders.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsene.domain.ShippingRegion.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsene.domain.Customer.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsene.domain.Customer.class.getName() + ".shippingRegions", jcacheConfiguration);
            cm.createCache(com.arsene.domain.Shipping.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsene.domain.Shipping.class.getName() + ".shippingRegions", jcacheConfiguration);
            cm.createCache(com.arsene.domain.Tax.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsene.domain.Review.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsene.domain.Audit.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
