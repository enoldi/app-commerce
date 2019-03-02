package com.arsene.service;

import com.arsene.domain.AttributeValue;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AttributeValue.
 */
public interface AttributeValueService {

    /**
     * Save a attributeValue.
     *
     * @param attributeValue the entity to save
     * @return the persisted entity
     */
    AttributeValue save(AttributeValue attributeValue);

    /**
     * Get all the attributeValues.
     *
     * @return the list of entities
     */
    List<AttributeValue> findAll();


    /**
     * Get the "id" attributeValue.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AttributeValue> findOne(Long id);

    /**
     * Delete the "id" attributeValue.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
