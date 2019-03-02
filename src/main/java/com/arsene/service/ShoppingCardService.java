package com.arsene.service;

import com.arsene.domain.ShoppingCard;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ShoppingCard.
 */
public interface ShoppingCardService {

    /**
     * Save a shoppingCard.
     *
     * @param shoppingCard the entity to save
     * @return the persisted entity
     */
    ShoppingCard save(ShoppingCard shoppingCard);

    /**
     * Get all the shoppingCards.
     *
     * @return the list of entities
     */
    List<ShoppingCard> findAll();


    /**
     * Get the "id" shoppingCard.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ShoppingCard> findOne(Long id);

    /**
     * Delete the "id" shoppingCard.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
