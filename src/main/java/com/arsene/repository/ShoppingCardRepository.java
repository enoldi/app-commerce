package com.arsene.repository;

import com.arsene.domain.ShoppingCard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ShoppingCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShoppingCardRepository extends JpaRepository<ShoppingCard, Long> {

}
