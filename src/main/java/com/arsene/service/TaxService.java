package com.arsene.service;

import com.arsene.domain.Tax;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Tax.
 */
public interface TaxService {

    /**
     * Save a tax.
     *
     * @param tax the entity to save
     * @return the persisted entity
     */
    Tax save(Tax tax);

    /**
     * Get all the taxes.
     *
     * @return the list of entities
     */
    List<Tax> findAll();


    /**
     * Get the "id" tax.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Tax> findOne(Long id);

    /**
     * Delete the "id" tax.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
