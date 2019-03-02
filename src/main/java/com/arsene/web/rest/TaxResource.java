package com.arsene.web.rest;
import com.arsene.domain.Tax;
import com.arsene.service.TaxService;
import com.arsene.web.rest.errors.BadRequestAlertException;
import com.arsene.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Tax.
 */
@RestController
@RequestMapping("/api")
public class TaxResource {

    private final Logger log = LoggerFactory.getLogger(TaxResource.class);

    private static final String ENTITY_NAME = "tax";

    private final TaxService taxService;

    public TaxResource(TaxService taxService) {
        this.taxService = taxService;
    }

    /**
     * POST  /taxes : Create a new tax.
     *
     * @param tax the tax to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tax, or with status 400 (Bad Request) if the tax has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/taxes")
    public ResponseEntity<Tax> createTax(@Valid @RequestBody Tax tax) throws URISyntaxException {
        log.debug("REST request to save Tax : {}", tax);
        if (tax.getId() != null) {
            throw new BadRequestAlertException("A new tax cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Tax result = taxService.save(tax);
        return ResponseEntity.created(new URI("/api/taxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /taxes : Updates an existing tax.
     *
     * @param tax the tax to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tax,
     * or with status 400 (Bad Request) if the tax is not valid,
     * or with status 500 (Internal Server Error) if the tax couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/taxes")
    public ResponseEntity<Tax> updateTax(@Valid @RequestBody Tax tax) throws URISyntaxException {
        log.debug("REST request to update Tax : {}", tax);
        if (tax.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Tax result = taxService.save(tax);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tax.getId().toString()))
            .body(result);
    }

    /**
     * GET  /taxes : get all the taxes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of taxes in body
     */
    @GetMapping("/taxes")
    public List<Tax> getAllTaxes() {
        log.debug("REST request to get all Taxes");
        return taxService.findAll();
    }

    /**
     * GET  /taxes/:id : get the "id" tax.
     *
     * @param id the id of the tax to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tax, or with status 404 (Not Found)
     */
    @GetMapping("/taxes/{id}")
    public ResponseEntity<Tax> getTax(@PathVariable Long id) {
        log.debug("REST request to get Tax : {}", id);
        Optional<Tax> tax = taxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tax);
    }

    /**
     * DELETE  /taxes/:id : delete the "id" tax.
     *
     * @param id the id of the tax to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/taxes/{id}")
    public ResponseEntity<Void> deleteTax(@PathVariable Long id) {
        log.debug("REST request to delete Tax : {}", id);
        taxService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
