package com.arsene.service.impl;

import com.arsene.service.ShoppingCardService;
import com.arsene.domain.ShoppingCard;
import com.arsene.repository.ShoppingCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing ShoppingCard.
 */
@Service
@Transactional
public class ShoppingCardServiceImpl implements ShoppingCardService {

    private final Logger log = LoggerFactory.getLogger(ShoppingCardServiceImpl.class);

    private final ShoppingCardRepository shoppingCardRepository;

    public ShoppingCardServiceImpl(ShoppingCardRepository shoppingCardRepository) {
        this.shoppingCardRepository = shoppingCardRepository;
    }

    /**
     * Save a shoppingCard.
     *
     * @param shoppingCard the entity to save
     * @return the persisted entity
     */
    @Override
    public ShoppingCard save(ShoppingCard shoppingCard) {
        log.debug("Request to save ShoppingCard : {}", shoppingCard);
        return shoppingCardRepository.save(shoppingCard);
    }

    /**
     * Get all the shoppingCards.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ShoppingCard> findAll() {
        log.debug("Request to get all ShoppingCards");
        return shoppingCardRepository.findAll();
    }


    /**
     * Get one shoppingCard by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ShoppingCard> findOne(Long id) {
        log.debug("Request to get ShoppingCard : {}", id);
        return shoppingCardRepository.findById(id);
    }

    /**
     * Delete the shoppingCard by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ShoppingCard : {}", id);
        shoppingCardRepository.deleteById(id);
    }
}
