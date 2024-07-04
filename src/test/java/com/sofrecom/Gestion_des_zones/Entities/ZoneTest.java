package com.sofrecom.Gestion_des_zones.Entities;

import com.sofrecom.zones.entities.Affiliate;
import com.sofrecom.zones.entities.Zone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZoneTest {

    private Zone zone;

    @BeforeEach
    void setup(){
        zone= new Zone();
    }
    @Test
    void getIdZone() {
        int id =1;
        zone.setIdZone(id);
        assertEquals(id,zone.getIdZone());
    }

    @Test
    void getName() {
        String name= "name";
        zone.setName(name);
        assertEquals(name,zone.getName());
    }

    @Test
    void getAffiliates() {
        Affiliate affiliate1= new Affiliate();
        Affiliate affiliate2= new Affiliate();
        List<Affiliate> affiliates= List.of(affiliate2,affiliate1);

        zone.setAffiliates(affiliates);
        assertEquals(affiliates,zone.getAffiliates());
    }


}
