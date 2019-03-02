package com.arsene.repository;

import com.arsene.domain.ShippingRegion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ShippingRegion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShippingRegionRepository extends JpaRepository<ShippingRegion, Long> {

}
