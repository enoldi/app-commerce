package com.arsene.service;

import com.arsene.domain.ShippingRegion;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ShippingRegion.
 */
public interface ShippingRegionService {

    /**
     * Save a shippingRegion.
     *
     * @param shippingRegion the entity to save
     * @return the persisted entity
     */
    ShippingRegion save(ShippingRegion shippingRegion);

    /**
     * Get all the shippingRegions.
     *
     * @return the list of entities
     */
    List<ShippingRegion> findAll();


    /**
     * Get the "id" shippingRegion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ShippingRegion> findOne(Long id);

    /**
     * Delete the "id" shippingRegion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
