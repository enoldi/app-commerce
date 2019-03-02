package com.arsene.service;

import com.arsene.domain.Attribute;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Attribute.
 */
public interface AttributeService {

    /**
     * Save a attribute.
     *
     * @param attribute the entity to save
     * @return the persisted entity
     */
    Attribute save(Attribute attribute);

    /**
     * Get all the attributes.
     *
     * @return the list of entities
     */
    List<Attribute> findAll();


    /**
     * Get the "id" attribute.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Attribute> findOne(Long id);

    /**
     * Delete the "id" attribute.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
