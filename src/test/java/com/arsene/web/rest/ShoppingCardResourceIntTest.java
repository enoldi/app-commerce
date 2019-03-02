package com.arsene.web.rest;

import com.arsene.AppcommerceApp;

import com.arsene.domain.ShoppingCard;
import com.arsene.repository.ShoppingCardRepository;
import com.arsene.service.ShoppingCardService;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.arsene.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ShoppingCardResource REST controller.
 *
 * @see ShoppingCardResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppcommerceApp.class)
public class ShoppingCardResourceIntTest {

    private static final Integer DEFAULT_ITEM_ID = 1;
    private static final Integer UPDATED_ITEM_ID = 2;

    private static final String DEFAULT_ATTRIBUTES = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTES = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final Boolean DEFAULT_BUY_NOW = false;
    private static final Boolean UPDATED_BUY_NOW = true;

    private static final LocalDate DEFAULT_ADDED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ADDED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_SALARY = 1L;
    private static final Long UPDATED_SALARY = 2L;

    @Autowired
    private ShoppingCardRepository shoppingCardRepository;

    @Autowired
    private ShoppingCardService shoppingCardService;

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

    private MockMvc restShoppingCardMockMvc;

    private ShoppingCard shoppingCard;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ShoppingCardResource shoppingCardResource = new ShoppingCardResource(shoppingCardService);
        this.restShoppingCardMockMvc = MockMvcBuilders.standaloneSetup(shoppingCardResource)
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
    public static ShoppingCard createEntity(EntityManager em) {
        ShoppingCard shoppingCard = new ShoppingCard()
            .itemId(DEFAULT_ITEM_ID)
            .attributes(DEFAULT_ATTRIBUTES)
            .quantity(DEFAULT_QUANTITY)
            .buyNow(DEFAULT_BUY_NOW)
            .addedOn(DEFAULT_ADDED_ON)
            .salary(DEFAULT_SALARY);
        return shoppingCard;
    }

    @Before
    public void initTest() {
        shoppingCard = createEntity(em);
    }

    @Test
    @Transactional
    public void createShoppingCard() throws Exception {
        int databaseSizeBeforeCreate = shoppingCardRepository.findAll().size();

        // Create the ShoppingCard
        restShoppingCardMockMvc.perform(post("/api/shopping-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoppingCard)))
            .andExpect(status().isCreated());

        // Validate the ShoppingCard in the database
        List<ShoppingCard> shoppingCardList = shoppingCardRepository.findAll();
        assertThat(shoppingCardList).hasSize(databaseSizeBeforeCreate + 1);
        ShoppingCard testShoppingCard = shoppingCardList.get(shoppingCardList.size() - 1);
        assertThat(testShoppingCard.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testShoppingCard.getAttributes()).isEqualTo(DEFAULT_ATTRIBUTES);
        assertThat(testShoppingCard.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testShoppingCard.isBuyNow()).isEqualTo(DEFAULT_BUY_NOW);
        assertThat(testShoppingCard.getAddedOn()).isEqualTo(DEFAULT_ADDED_ON);
        assertThat(testShoppingCard.getSalary()).isEqualTo(DEFAULT_SALARY);
    }

    @Test
    @Transactional
    public void createShoppingCardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shoppingCardRepository.findAll().size();

        // Create the ShoppingCard with an existing ID
        shoppingCard.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShoppingCardMockMvc.perform(post("/api/shopping-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoppingCard)))
            .andExpect(status().isBadRequest());

        // Validate the ShoppingCard in the database
        List<ShoppingCard> shoppingCardList = shoppingCardRepository.findAll();
        assertThat(shoppingCardList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllShoppingCards() throws Exception {
        // Initialize the database
        shoppingCardRepository.saveAndFlush(shoppingCard);

        // Get all the shoppingCardList
        restShoppingCardMockMvc.perform(get("/api/shopping-cards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shoppingCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID)))
            .andExpect(jsonPath("$.[*].attributes").value(hasItem(DEFAULT_ATTRIBUTES.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].buyNow").value(hasItem(DEFAULT_BUY_NOW.booleanValue())))
            .andExpect(jsonPath("$.[*].addedOn").value(hasItem(DEFAULT_ADDED_ON.toString())))
            .andExpect(jsonPath("$.[*].salary").value(hasItem(DEFAULT_SALARY.intValue())));
    }
    
    @Test
    @Transactional
    public void getShoppingCard() throws Exception {
        // Initialize the database
        shoppingCardRepository.saveAndFlush(shoppingCard);

        // Get the shoppingCard
        restShoppingCardMockMvc.perform(get("/api/shopping-cards/{id}", shoppingCard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(shoppingCard.getId().intValue()))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID))
            .andExpect(jsonPath("$.attributes").value(DEFAULT_ATTRIBUTES.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.buyNow").value(DEFAULT_BUY_NOW.booleanValue()))
            .andExpect(jsonPath("$.addedOn").value(DEFAULT_ADDED_ON.toString()))
            .andExpect(jsonPath("$.salary").value(DEFAULT_SALARY.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingShoppingCard() throws Exception {
        // Get the shoppingCard
        restShoppingCardMockMvc.perform(get("/api/shopping-cards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShoppingCard() throws Exception {
        // Initialize the database
        shoppingCardService.save(shoppingCard);

        int databaseSizeBeforeUpdate = shoppingCardRepository.findAll().size();

        // Update the shoppingCard
        ShoppingCard updatedShoppingCard = shoppingCardRepository.findById(shoppingCard.getId()).get();
        // Disconnect from session so that the updates on updatedShoppingCard are not directly saved in db
        em.detach(updatedShoppingCard);
        updatedShoppingCard
            .itemId(UPDATED_ITEM_ID)
            .attributes(UPDATED_ATTRIBUTES)
            .quantity(UPDATED_QUANTITY)
            .buyNow(UPDATED_BUY_NOW)
            .addedOn(UPDATED_ADDED_ON)
            .salary(UPDATED_SALARY);

        restShoppingCardMockMvc.perform(put("/api/shopping-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedShoppingCard)))
            .andExpect(status().isOk());

        // Validate the ShoppingCard in the database
        List<ShoppingCard> shoppingCardList = shoppingCardRepository.findAll();
        assertThat(shoppingCardList).hasSize(databaseSizeBeforeUpdate);
        ShoppingCard testShoppingCard = shoppingCardList.get(shoppingCardList.size() - 1);
        assertThat(testShoppingCard.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testShoppingCard.getAttributes()).isEqualTo(UPDATED_ATTRIBUTES);
        assertThat(testShoppingCard.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testShoppingCard.isBuyNow()).isEqualTo(UPDATED_BUY_NOW);
        assertThat(testShoppingCard.getAddedOn()).isEqualTo(UPDATED_ADDED_ON);
        assertThat(testShoppingCard.getSalary()).isEqualTo(UPDATED_SALARY);
    }

    @Test
    @Transactional
    public void updateNonExistingShoppingCard() throws Exception {
        int databaseSizeBeforeUpdate = shoppingCardRepository.findAll().size();

        // Create the ShoppingCard

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShoppingCardMockMvc.perform(put("/api/shopping-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoppingCard)))
            .andExpect(status().isBadRequest());

        // Validate the ShoppingCard in the database
        List<ShoppingCard> shoppingCardList = shoppingCardRepository.findAll();
        assertThat(shoppingCardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShoppingCard() throws Exception {
        // Initialize the database
        shoppingCardService.save(shoppingCard);

        int databaseSizeBeforeDelete = shoppingCardRepository.findAll().size();

        // Delete the shoppingCard
        restShoppingCardMockMvc.perform(delete("/api/shopping-cards/{id}", shoppingCard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ShoppingCard> shoppingCardList = shoppingCardRepository.findAll();
        assertThat(shoppingCardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShoppingCard.class);
        ShoppingCard shoppingCard1 = new ShoppingCard();
        shoppingCard1.setId(1L);
        ShoppingCard shoppingCard2 = new ShoppingCard();
        shoppingCard2.setId(shoppingCard1.getId());
        assertThat(shoppingCard1).isEqualTo(shoppingCard2);
        shoppingCard2.setId(2L);
        assertThat(shoppingCard1).isNotEqualTo(shoppingCard2);
        shoppingCard1.setId(null);
        assertThat(shoppingCard1).isNotEqualTo(shoppingCard2);
    }
}
