package com.arsene.service;

import com.arsene.domain.Audit;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Audit.
 */
public interface AuditService {

    /**
     * Save a audit.
     *
     * @param audit the entity to save
     * @return the persisted entity
     */
    Audit save(Audit audit);

    /**
     * Get all the audits.
     *
     * @return the list of entities
     */
    List<Audit> findAll();


    /**
     * Get the "id" audit.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Audit> findOne(Long id);

    /**
     * Delete the "id" audit.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
