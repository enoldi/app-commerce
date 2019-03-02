package com.arsene.web.rest;
import com.arsene.domain.Orders;
import com.arsene.service.OrdersService;
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
 * REST controller for managing Orders.
 */
@RestController
@RequestMapping("/api")
public class OrdersResource {

    private final Logger log = LoggerFactory.getLogger(OrdersResource.class);

    private static final String ENTITY_NAME = "orders";

    private final OrdersService ordersService;

    public OrdersResource(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    /**
     * POST  /orders : Create a new orders.
     *
     * @param orders the orders to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orders, or with status 400 (Bad Request) if the orders has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/orders")
    public ResponseEntity<Orders> createOrders(@Valid @RequestBody Orders orders) throws URISyntaxException {
        log.debug("REST request to save Orders : {}", orders);
        if (orders.getId() != null) {
            throw new BadRequestAlertException("A new orders cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Orders result = ordersService.save(orders);
        return ResponseEntity.created(new URI("/api/orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /orders : Updates an existing orders.
     *
     * @param orders the orders to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orders,
     * or with status 400 (Bad Request) if the orders is not valid,
     * or with status 500 (Internal Server Error) if the orders couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/orders")
    public ResponseEntity<Orders> updateOrders(@Valid @RequestBody Orders orders) throws URISyntaxException {
        log.debug("REST request to update Orders : {}", orders);
        if (orders.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Orders result = ordersService.save(orders);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orders.getId().toString()))
            .body(result);
    }

    /**
     * GET  /orders : get all the orders.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of orders in body
     */
    @GetMapping("/orders")
    public List<Orders> getAllOrders() {
        log.debug("REST request to get all Orders");
        return ordersService.findAll();
    }

    /**
     * GET  /orders/:id : get the "id" orders.
     *
     * @param id the id of the orders to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orders, or with status 404 (Not Found)
     */
    @GetMapping("/orders/{id}")
    public ResponseEntity<Orders> getOrders(@PathVariable Long id) {
        log.debug("REST request to get Orders : {}", id);
        Optional<Orders> orders = ordersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orders);
    }

    /**
     * DELETE  /orders/:id : delete the "id" orders.
     *
     * @param id the id of the orders to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrders(@PathVariable Long id) {
        log.debug("REST request to delete Orders : {}", id);
        ordersService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
