package com.sofrecom.Gestion_des_zones.Services;

import com.sofrecom.zones.entities.Affiliate;
import com.sofrecom.zones.entities.DataCenter;
import com.sofrecom.zones.repositories.AffiliateRepo;
import com.sofrecom.zones.repositories.DataCenterRepo;
import com.sofrecom.zones.services.DataCenterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataCenterServiceTest {
    @Mock
    DataCenterRepo dataCenterRepo;
    @Mock
    AffiliateRepo affiliateRepo;


    @InjectMocks
    DataCenterService dataCenterService;
    @Test
    void addDataCenter() {
        DataCenter dataCenterToAdd = new DataCenter();
        int AffiliateId = 1;
        Affiliate affiliate = new Affiliate();
        affiliate.setIdAffiliate(AffiliateId);

        when(affiliateRepo.findById(AffiliateId)).thenReturn(Optional.of(affiliate));
        when(dataCenterRepo.save(dataCenterToAdd)).thenReturn(dataCenterToAdd);

        DataCenter result = dataCenterService.addDataCenter(dataCenterToAdd, AffiliateId);

        assertNotNull(result);
        verify(affiliateRepo, times(1)).findById(AffiliateId);
        verify(dataCenterRepo, times(1)).save(dataCenterToAdd);
    }

    @Test
    void updateDataCenter() {

        int dataCenterId = 1;
        DataCenter existingdataCenter = new DataCenter();
        existingdataCenter.setIdDataCenter(dataCenterId);


        when(dataCenterRepo.findById(dataCenterId)).thenReturn(Optional.of(existingdataCenter));


        when(dataCenterRepo.saveAndFlush(existingdataCenter)).thenReturn(existingdataCenter);


        DataCenter updatedDataCenter = new DataCenter();
        updatedDataCenter.setIdDataCenter(dataCenterId);
        updatedDataCenter.setName("New Name");


        String result = dataCenterService.updateDataCenter(updatedDataCenter, dataCenterId);


        assertEquals("updated", result);
        verify(dataCenterRepo, times(1)).findById(dataCenterId);
        verify(dataCenterRepo, times(1)).saveAndFlush(existingdataCenter);
    }

    @Test
    void updateAffiliateDataCenter() {
        int dataCenterId = 1;
        int newaffiliateId = 2;

        DataCenter existingDataCenter = new DataCenter();
        existingDataCenter.setIdDataCenter(dataCenterId);

        Affiliate newAffiliate = new Affiliate();
        newAffiliate.setIdAffiliate(newaffiliateId);

        when(dataCenterRepo.findById(dataCenterId)).thenReturn(Optional.of(existingDataCenter));
        when(affiliateRepo.findById(newaffiliateId)).thenReturn(Optional.of(newAffiliate));

        String result = dataCenterService.updateAffiliateDataCenter(dataCenterId, newaffiliateId);

        assertEquals("updated", result);
        verify(dataCenterRepo, times(1)).findById(dataCenterId);
        verify(affiliateRepo, times(1)).findById(newaffiliateId);
        verify(dataCenterRepo, times(1)).save(existingDataCenter);
    }

    @Test
    void deleteDataCenter() {
        int dataCenterId = 1;

        String result = dataCenterService.deleteDataCenter(dataCenterId);

        assertEquals("deleted", result);
        verify(dataCenterRepo, times(1)).deleteById(dataCenterId);
    }

    @Test
    void getAllDataCenter() {
        List<DataCenter> dataCenters = new ArrayList<>();

        when(dataCenterRepo.findAll()).thenReturn(dataCenters);

        List<DataCenter> result = dataCenterService.getAllDataCenter();

        assertEquals(dataCenters, result);
        verify(dataCenterRepo, times(1)).findAll();
    }

    @Test
    void getDataCenterById() {
        int dataCenterId = 1;
        DataCenter dataCenter = new DataCenter();

        when(dataCenterRepo.findById(dataCenterId)).thenReturn(Optional.of(dataCenter));

        DataCenter result = dataCenterService.getDataCenterById(dataCenterId);

        assertEquals(dataCenter, result);
        verify(dataCenterRepo, times(1)).findById(dataCenterId);
    }


    @Test
    void getAllDataCenterByAffiliate() {
        String affiliateName = "Test Affiliate";
        Affiliate affiliate = new Affiliate();
        affiliate.setName(affiliateName);

        List<DataCenter> dataCenters = new ArrayList<>();
        dataCenters.add(new DataCenter());
        affiliate.setDataCenters(dataCenters);

        when(affiliateRepo.findByName(affiliateName)).thenReturn(affiliate);

        List<DataCenter> result = dataCenterService.getAllDataCenterByAffiliate(affiliateName);

        assertEquals(dataCenters, result);
        verify(affiliateRepo, times(1)).findByName(affiliateName);
    }
}
