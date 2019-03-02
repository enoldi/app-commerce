package com.arsene.service.impl;

import com.arsene.service.AttributeValueService;
import com.arsene.domain.AttributeValue;
import com.arsene.repository.AttributeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing AttributeValue.
 */
@Service
@Transactional
public class AttributeValueServiceImpl implements AttributeValueService {

    private final Logger log = LoggerFactory.getLogger(AttributeValueServiceImpl.class);

    private final AttributeValueRepository attributeValueRepository;

    public AttributeValueServiceImpl(AttributeValueRepository attributeValueRepository) {
        this.attributeValueRepository = attributeValueRepository;
    }

    /**
     * Save a attributeValue.
     *
     * @param attributeValue the entity to save
     * @return the persisted entity
     */
    @Override
    public AttributeValue save(AttributeValue attributeValue) {
        log.debug("Request to save AttributeValue : {}", attributeValue);
        return attributeValueRepository.save(attributeValue);
    }

    /**
     * Get all the attributeValues.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AttributeValue> findAll() {
        log.debug("Request to get all AttributeValues");
        return attributeValueRepository.findAll();
    }


    /**
     * Get one attributeValue by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AttributeValue> findOne(Long id) {
        log.debug("Request to get AttributeValue : {}", id);
        return attributeValueRepository.findById(id);
    }

    /**
     * Delete the attributeValue by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AttributeValue : {}", id);
        attributeValueRepository.deleteById(id);
    }
}
