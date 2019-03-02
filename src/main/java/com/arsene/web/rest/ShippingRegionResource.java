package com.arsene.web.rest;
import com.arsene.domain.ShippingRegion;
import com.arsene.service.ShippingRegionService;
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
 * REST controller for managing ShippingRegion.
 */
@RestController
@RequestMapping("/api")
public class ShippingRegionResource {

    private final Logger log = LoggerFactory.getLogger(ShippingRegionResource.class);

    private static final String ENTITY_NAME = "shippingRegion";

    private final ShippingRegionService shippingRegionService;

    public ShippingRegionResource(ShippingRegionService shippingRegionService) {
        this.shippingRegionService = shippingRegionService;
    }

    /**
     * POST  /shipping-regions : Create a new shippingRegion.
     *
     * @param shippingRegion the shippingRegion to create
     * @return the ResponseEntity with status 201 (Created) and with body the new shippingRegion, or with status 400 (Bad Request) if the shippingRegion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/shipping-regions")
    public ResponseEntity<ShippingRegion> createShippingRegion(@RequestBody ShippingRegion shippingRegion) throws URISyntaxException {
        log.debug("REST request to save ShippingRegion : {}", shippingRegion);
        if (shippingRegion.getId() != null) {
            throw new BadRequestAlertException("A new shippingRegion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShippingRegion result = shippingRegionService.save(shippingRegion);
        return ResponseEntity.created(new URI("/api/shipping-regions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /shipping-regions : Updates an existing shippingRegion.
     *
     * @param shippingRegion the shippingRegion to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated shippingRegion,
     * or with status 400 (Bad Request) if the shippingRegion is not valid,
     * or with status 500 (Internal Server Error) if the shippingRegion couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/shipping-regions")
    public ResponseEntity<ShippingRegion> updateShippingRegion(@RequestBody ShippingRegion shippingRegion) throws URISyntaxException {
        log.debug("REST request to update ShippingRegion : {}", shippingRegion);
        if (shippingRegion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShippingRegion result = shippingRegionService.save(shippingRegion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, shippingRegion.getId().toString()))
            .body(result);
    }

    /**
     * GET  /shipping-regions : get all the shippingRegions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of shippingRegions in body
     */
    @GetMapping("/shipping-regions")
    public List<ShippingRegion> getAllShippingRegions() {
        log.debug("REST request to get all ShippingRegions");
        return shippingRegionService.findAll();
    }

    /**
     * GET  /shipping-regions/:id : get the "id" shippingRegion.
     *
     * @param id the id of the shippingRegion to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the shippingRegion, or with status 404 (Not Found)
     */
    @GetMapping("/shipping-regions/{id}")
    public ResponseEntity<ShippingRegion> getShippingRegion(@PathVariable Long id) {
        log.debug("REST request to get ShippingRegion : {}", id);
        Optional<ShippingRegion> shippingRegion = shippingRegionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shippingRegion);
    }

    /**
     * DELETE  /shipping-regions/:id : delete the "id" shippingRegion.
     *
     * @param id the id of the shippingRegion to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/shipping-regions/{id}")
    public ResponseEntity<Void> deleteShippingRegion(@PathVariable Long id) {
        log.debug("REST request to delete ShippingRegion : {}", id);
        shippingRegionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
