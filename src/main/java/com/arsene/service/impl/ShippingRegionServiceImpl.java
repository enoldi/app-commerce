package com.arsene.service.impl;

import com.arsene.service.ShippingRegionService;
import com.arsene.domain.ShippingRegion;
import com.arsene.repository.ShippingRegionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing ShippingRegion.
 */
@Service
@Transactional
public class ShippingRegionServiceImpl implements ShippingRegionService {

    private final Logger log = LoggerFactory.getLogger(ShippingRegionServiceImpl.class);

    private final ShippingRegionRepository shippingRegionRepository;

    public ShippingRegionServiceImpl(ShippingRegionRepository shippingRegionRepository) {
        this.shippingRegionRepository = shippingRegionRepository;
    }

    /**
     * Save a shippingRegion.
     *
     * @param shippingRegion the entity to save
     * @return the persisted entity
     */
    @Override
    public ShippingRegion save(ShippingRegion shippingRegion) {
        log.debug("Request to save ShippingRegion : {}", shippingRegion);
        return shippingRegionRepository.save(shippingRegion);
    }

    /**
     * Get all the shippingRegions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ShippingRegion> findAll() {
        log.debug("Request to get all ShippingRegions");
        return shippingRegionRepository.findAll();
    }


    /**
     * Get one shippingRegion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ShippingRegion> findOne(Long id) {
        log.debug("Request to get ShippingRegion : {}", id);
        return shippingRegionRepository.findById(id);
    }

    /**
     * Delete the shippingRegion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ShippingRegion : {}", id);
        shippingRegionRepository.deleteById(id);
    }
}
