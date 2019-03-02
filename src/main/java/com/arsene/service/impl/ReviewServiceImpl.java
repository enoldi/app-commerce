package com.arsene.service.impl;

import com.arsene.service.ReviewService;
import com.arsene.domain.Review;
import com.arsene.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Review.
 */
@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Save a review.
     *
     * @param review the entity to save
     * @return the persisted entity
     */
    @Override
    public Review save(Review review) {
        log.debug("Request to save Review : {}", review);
        return reviewRepository.save(review);
    }

    /**
     * Get all the reviews.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Review> findAll() {
        log.debug("Request to get all Reviews");
        return reviewRepository.findAll();
    }


    /**
     * Get one review by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Review> findOne(Long id) {
        log.debug("Request to get Review : {}", id);
        return reviewRepository.findById(id);
    }

    /**
     * Delete the review by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Review : {}", id);
        reviewRepository.deleteById(id);
    }
}
