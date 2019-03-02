package com.arsene.web.rest;

import com.arsene.AppcommerceApp;

import com.arsene.domain.ShippingRegion;
import com.arsene.repository.ShippingRegionRepository;
import com.arsene.service.ShippingRegionService;
import com.arsene.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.arsene.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ShippingRegionResource REST controller.
 *
 * @see ShippingRegionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppcommerceApp.class)
public class ShippingRegionResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ShippingRegionRepository shippingRegionRepository;

    @Autowired
    private ShippingRegionService shippingRegionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restShippingRegionMockMvc;

    private ShippingRegion shippingRegion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ShippingRegionResource shippingRegionResource = new ShippingRegionResource(shippingRegionService);
        this.restShippingRegionMockMvc = MockMvcBuilders.standaloneSetup(shippingRegionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShippingRegion createEntity(EntityManager em) {
        ShippingRegion shippingRegion = new ShippingRegion()
            .name(DEFAULT_NAME);
        return shippingRegion;
    }

    @Before
    public void initTest() {
        shippingRegion = createEntity(em);
    }

    @Test
    @Transactional
    public void createShippingRegion() throws Exception {
        int databaseSizeBeforeCreate = shippingRegionRepository.findAll().size();

        // Create the ShippingRegion
        restShippingRegionMockMvc.perform(post("/api/shipping-regions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shippingRegion)))
            .andExpect(status().isCreated());

        // Validate the ShippingRegion in the database
        List<ShippingRegion> shippingRegionList = shippingRegionRepository.findAll();
        assertThat(shippingRegionList).hasSize(databaseSizeBeforeCreate + 1);
        ShippingRegion testShippingRegion = shippingRegionList.get(shippingRegionList.size() - 1);
        assertThat(testShippingRegion.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createShippingRegionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shippingRegionRepository.findAll().size();

        // Create the ShippingRegion with an existing ID
        shippingRegion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShippingRegionMockMvc.perform(post("/api/shipping-regions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shippingRegion)))
            .andExpect(status().isBadRequest());

        // Validate the ShippingRegion in the database
        List<ShippingRegion> shippingRegionList = shippingRegionRepository.findAll();
        assertThat(shippingRegionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllShippingRegions() throws Exception {
        // Initialize the database
        shippingRegionRepository.saveAndFlush(shippingRegion);

        // Get all the shippingRegionList
        restShippingRegionMockMvc.perform(get("/api/shipping-regions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shippingRegion.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getShippingRegion() throws Exception {
        // Initialize the database
        shippingRegionRepository.saveAndFlush(shippingRegion);

        // Get the shippingRegion
        restShippingRegionMockMvc.perform(get("/api/shipping-regions/{id}", shippingRegion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(shippingRegion.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingShippingRegion() throws Exception {
        // Get the shippingRegion
        restShippingRegionMockMvc.perform(get("/api/shipping-regions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShippingRegion() throws Exception {
        // Initialize the database
        shippingRegionService.save(shippingRegion);

        int databaseSizeBeforeUpdate = shippingRegionRepository.findAll().size();

        // Update the shippingRegion
        ShippingRegion updatedShippingRegion = shippingRegionRepository.findById(shippingRegion.getId()).get();
        // Disconnect from session so that the updates on updatedShippingRegion are not directly saved in db
        em.detach(updatedShippingRegion);
        updatedShippingRegion
            .name(UPDATED_NAME);

        restShippingRegionMockMvc.perform(put("/api/shipping-regions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedShippingRegion)))
            .andExpect(status().isOk());

        // Validate the ShippingRegion in the database
        List<ShippingRegion> shippingRegionList = shippingRegionRepository.findAll();
        assertThat(shippingRegionList).hasSize(databaseSizeBeforeUpdate);
        ShippingRegion testShippingRegion = shippingRegionList.get(shippingRegionList.size() - 1);
        assertThat(testShippingRegion.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingShippingRegion() throws Exception {
        int databaseSizeBeforeUpdate = shippingRegionRepository.findAll().size();

        // Create the ShippingRegion

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShippingRegionMockMvc.perform(put("/api/shipping-regions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shippingRegion)))
            .andExpect(status().isBadRequest());

        // Validate the ShippingRegion in the database
        List<ShippingRegion> shippingRegionList = shippingRegionRepository.findAll();
        assertThat(shippingRegionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShippingRegion() throws Exception {
        // Initialize the database
        shippingRegionService.save(shippingRegion);

        int databaseSizeBeforeDelete = shippingRegionRepository.findAll().size();

        // Delete the shippingRegion
        restShippingRegionMockMvc.perform(delete("/api/shipping-regions/{id}", shippingRegion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ShippingRegion> shippingRegionList = shippingRegionRepository.findAll();
        assertThat(shippingRegionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShippingRegion.class);
        ShippingRegion shippingRegion1 = new ShippingRegion();
        shippingRegion1.setId(1L);
        ShippingRegion shippingRegion2 = new ShippingRegion();
        shippingRegion2.setId(shippingRegion1.getId());
        assertThat(shippingRegion1).isEqualTo(shippingRegion2);
        shippingRegion2.setId(2L);
        assertThat(shippingRegion1).isNotEqualTo(shippingRegion2);
        shippingRegion1.setId(null);
        assertThat(shippingRegion1).isNotEqualTo(shippingRegion2);
    }
}
