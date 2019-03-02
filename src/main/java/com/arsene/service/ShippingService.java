package com.arsene.service;

import com.arsene.domain.Shipping;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Shipping.
 */
public interface ShippingService {

    /**
     * Save a shipping.
     *
     * @param shipping the entity to save
     * @return the persisted entity
     */
    Shipping save(Shipping shipping);

    /**
     * Get all the shippings.
     *
     * @return the list of entities
     */
    List<Shipping> findAll();


    /**
     * Get the "id" shipping.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Shipping> findOne(Long id);

    /**
     * Delete the "id" shipping.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
