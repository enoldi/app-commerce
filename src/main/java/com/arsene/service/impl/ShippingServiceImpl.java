package com.arsene.service.impl;

import com.arsene.service.ShippingService;
import com.arsene.domain.Shipping;
import com.arsene.repository.ShippingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Shipping.
 */
@Service
@Transactional
public class ShippingServiceImpl implements ShippingService {

    private final Logger log = LoggerFactory.getLogger(ShippingServiceImpl.class);

    private final ShippingRepository shippingRepository;

    public ShippingServiceImpl(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    /**
     * Save a shipping.
     *
     * @param shipping the entity to save
     * @return the persisted entity
     */
    @Override
    public Shipping save(Shipping shipping) {
        log.debug("Request to save Shipping : {}", shipping);
        return shippingRepository.save(shipping);
    }

    /**
     * Get all the shippings.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Shipping> findAll() {
        log.debug("Request to get all Shippings");
        return shippingRepository.findAll();
    }


    /**
     * Get one shipping by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Shipping> findOne(Long id) {
        log.debug("Request to get Shipping : {}", id);
        return shippingRepository.findById(id);
    }

    /**
     * Delete the shipping by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Shipping : {}", id);
        shippingRepository.deleteById(id);
    }
}
