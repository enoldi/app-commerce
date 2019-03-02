package com.arsene.web.rest;
import com.arsene.domain.ShoppingCard;
import com.arsene.service.ShoppingCardService;
import com.arsene.web.rest.errors.BadRequestAlertException;
import com.arsene.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ShoppingCard.
 */
@RestController
@RequestMapping("/api")
public class ShoppingCardResource {

    private final Logger log = LoggerFactory.getLogger(ShoppingCardResource.class);

    private static final String ENTITY_NAME = "shoppingCard";

    private final ShoppingCardService shoppingCardService;

    public ShoppingCardResource(ShoppingCardService shoppingCardService) {
        this.shoppingCardService = shoppingCardService;
    }

    /**
     * POST  /shopping-cards : Create a new shoppingCard.
     *
     * @param shoppingCard the shoppingCard to create
     * @return the ResponseEntity with status 201 (Created) and with body the new shoppingCard, or with status 400 (Bad Request) if the shoppingCard has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/shopping-cards")
    public ResponseEntity<ShoppingCard> createShoppingCard(@RequestBody ShoppingCard shoppingCard) throws URISyntaxException {
        log.debug("REST request to save ShoppingCard : {}", shoppingCard);
        if (shoppingCard.getId() != null) {
            throw new BadRequestAlertException("A new shoppingCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShoppingCard result = shoppingCardService.save(shoppingCard);
        return ResponseEntity.created(new URI("/api/shopping-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /shopping-cards : Updates an existing shoppingCard.
     *
     * @param shoppingCard the shoppingCard to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated shoppingCard,
     * or with status 400 (Bad Request) if the shoppingCard is not valid,
     * or with status 500 (Internal Server Error) if the shoppingCard couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/shopping-cards")
    public ResponseEntity<ShoppingCard> updateShoppingCard(@RequestBody ShoppingCard shoppingCard) throws URISyntaxException {
        log.debug("REST request to update ShoppingCard : {}", shoppingCard);
        if (shoppingCard.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShoppingCard result = shoppingCardService.save(shoppingCard);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, shoppingCard.getId().toString()))
            .body(result);
    }

    /**
     * GET  /shopping-cards : get all the shoppingCards.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of shoppingCards in body
     */
    @GetMapping("/shopping-cards")
    public List<ShoppingCard> getAllShoppingCards() {
        log.debug("REST request to get all ShoppingCards");
        return shoppingCardService.findAll();
    }

    /**
     * GET  /shopping-cards/:id : get the "id" shoppingCard.
     *
     * @param id the id of the shoppingCard to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the shoppingCard, or with status 404 (Not Found)
     */
    @GetMapping("/shopping-cards/{id}")
    public ResponseEntity<ShoppingCard> getShoppingCard(@PathVariable Long id) {
        log.debug("REST request to get ShoppingCard : {}", id);
        Optional<ShoppingCard> shoppingCard = shoppingCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shoppingCard);
    }

    /**
     * DELETE  /shopping-cards/:id : delete the "id" shoppingCard.
     *
     * @param id the id of the shoppingCard to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/shopping-cards/{id}")
    public ResponseEntity<Void> deleteShoppingCard(@PathVariable Long id) {
        log.debug("REST request to delete ShoppingCard : {}", id);
        shoppingCardService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
