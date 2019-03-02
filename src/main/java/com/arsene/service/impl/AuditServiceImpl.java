package com.arsene.service.impl;

import com.arsene.service.AuditService;
import com.arsene.domain.Audit;
import com.arsene.repository.AuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Audit.
 */
@Service
@Transactional
public class AuditServiceImpl implements AuditService {

    private final Logger log = LoggerFactory.getLogger(AuditServiceImpl.class);

    private final AuditRepository auditRepository;

    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    /**
     * Save a audit.
     *
     * @param audit the entity to save
     * @return the persisted entity
     */
    @Override
    public Audit save(Audit audit) {
        log.debug("Request to save Audit : {}", audit);
        return auditRepository.save(audit);
    }

    /**
     * Get all the audits.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Audit> findAll() {
        log.debug("Request to get all Audits");
        return auditRepository.findAll();
    }


    /**
     * Get one audit by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Audit> findOne(Long id) {
        log.debug("Request to get Audit : {}", id);
        return auditRepository.findById(id);
    }

    /**
     * Delete the audit by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Audit : {}", id);
        auditRepository.deleteById(id);
    }
}
