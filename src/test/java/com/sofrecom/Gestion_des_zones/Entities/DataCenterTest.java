package com.sofrecom.Gestion_des_zones.Entities;

import com.sofrecom.zones.entities.Affiliate;
import com.sofrecom.zones.entities.DataCenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataCenterTest {

    private DataCenter dataCenter;



    @BeforeEach
    void setup(){
        dataCenter= new DataCenter();
    }
    @Test
    void getAndSetIdDataCenter() {
        int id= 1;
        dataCenter.setIdDataCenter(id);
        assertEquals(id,dataCenter.getIdDataCenter());
    }

    @Test
    void getAndSetName() {
        String name = "name";
        dataCenter.setName(name);
        assertEquals(name,dataCenter.getName());
    }

    @Test
    void getAndSetAffiliate() {
        Affiliate affiliate= new Affiliate();
        dataCenter.setAffiliate(affiliate);
        assertEquals(affiliate,dataCenter.getAffiliate());
    }


}
