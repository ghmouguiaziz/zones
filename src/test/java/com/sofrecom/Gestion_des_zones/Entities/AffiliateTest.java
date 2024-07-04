package com.sofrecom.Gestion_des_zones.Entities;

import com.sofrecom.zones.entities.Affiliate;
import com.sofrecom.zones.entities.DataCenter;
import com.sofrecom.zones.entities.Zone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AffiliateTest {

    private Affiliate affiliate;

    @BeforeEach
    void setUp() {
        affiliate = new Affiliate();
    }

    @Test
    void getIdAffiliate() {
        int id = 1;
        affiliate.setIdAffiliate(id);
        assertEquals(id, affiliate.getIdAffiliate());
    }

    @Test
    void getName() {
        String name = "Affiliate Name";
        affiliate.setName(name);
        assertEquals(name, affiliate.getName());
    }

    @Test
    void getFullName() {
        String fullName = "Affiliate Full Name";
        affiliate.setFullName(fullName);
        assertEquals(fullName, affiliate.getFullName());
    }

    @Test
    void getZone() {
        Zone zone = new Zone();
        affiliate.setZone(zone);
        assertEquals(zone, affiliate.getZone());
    }

    @Test
    void getDataCenters() {

        DataCenter dataCenter1 = new DataCenter();
        DataCenter dataCenter2 = new DataCenter();
        List<DataCenter> datacenters= List.of(dataCenter1,dataCenter2);


        affiliate.setDataCenters(datacenters);

        assertEquals(2, affiliate.getDataCenters().size());
    }


}
