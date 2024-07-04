package com.sofrecom.zones.repositories;



import com.sofrecom.zones.entities.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ZoneRepo extends JpaRepository <Zone,Integer> {
    Zone findByName(String name);
}
