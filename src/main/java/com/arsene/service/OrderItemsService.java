package com.arsene.service;

import com.arsene.domain.OrderItems;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing OrderItems.
 */
public interface OrderItemsService {

    /**
     * Save a orderItems.
     *
     * @param orderItems the entity to save
     * @return the persisted entity
     */
    OrderItems save(OrderItems orderItems);

    /**
     * Get all the orderItems.
     *
     * @return the list of entities
     */
    List<OrderItems> findAll();


    /**
     * Get the "id" orderItems.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OrderItems> findOne(Long id);

    /**
     * Delete the "id" orderItems.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
