package com.arsene.service.impl;

import com.arsene.service.AttributeService;
import com.arsene.domain.Attribute;
import com.arsene.repository.AttributeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Attribute.
 */
@Service
@Transactional
public class AttributeServiceImpl implements AttributeService {

    private final Logger log = LoggerFactory.getLogger(AttributeServiceImpl.class);

    private final AttributeRepository attributeRepository;

    public AttributeServiceImpl(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    /**
     * Save a attribute.
     *
     * @param attribute the entity to save
     * @return the persisted entity
     */
    @Override
    public Attribute save(Attribute attribute) {
        log.debug("Request to save Attribute : {}", attribute);
        return attributeRepository.save(attribute);
    }

    /**
     * Get all the attributes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Attribute> findAll() {
        log.debug("Request to get all Attributes");
        return attributeRepository.findAll();
    }


    /**
     * Get one attribute by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Attribute> findOne(Long id) {
        log.debug("Request to get Attribute : {}", id);
        return attributeRepository.findById(id);
    }

    /**
     * Delete the attribute by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Attribute : {}", id);
        attributeRepository.deleteById(id);
    }
}
