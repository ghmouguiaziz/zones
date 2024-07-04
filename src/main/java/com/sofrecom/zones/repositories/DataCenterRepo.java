package com.sofrecom.zones.repositories;

;
import com.sofrecom.zones.entities.DataCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataCenterRepo extends JpaRepository<DataCenter,Integer> {
   DataCenter findByName(String DataCenterName);
}
