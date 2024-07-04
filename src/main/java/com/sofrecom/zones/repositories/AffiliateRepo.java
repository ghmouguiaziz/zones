package com.sofrecom.zones.repositories;


import com.sofrecom.zones.entities.Affiliate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliateRepo extends JpaRepository<Affiliate,Integer> {
    Affiliate findByName(String name);
}
