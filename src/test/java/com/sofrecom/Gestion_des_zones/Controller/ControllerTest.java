package com.sofrecom.Gestion_des_zones.Controller;

import com.sofrecom.zones.entities.Affiliate;
import com.sofrecom.zones.entities.DataCenter;
import com.sofrecom.zones.entities.Zone;
import com.sofrecom.zones.services.IAffiliateService;
import com.sofrecom.zones.services.IDataCenterService;
import com.sofrecom.zones.services.IZoneService;
import com.sofrecom.zones.controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class ControllerTest {

    @Mock
    private IZoneService zoneService;

    @Mock
    private IAffiliateService affiliateService;

    @Mock
    private IDataCenterService dataCenterService;

    @InjectMocks
    private Controller controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addZone() {
        Zone zone = new Zone();
        when(zoneService.addZone(any(Zone.class))).thenReturn("Zone added successfully");
        String result = controller.addZone(zone);
        assertEquals("Zone added successfully", result);
    }

    @Test
    void updateZone() {
        Zone zone = new Zone();
        int id = 1;
        when(zoneService.updateZone(any(Zone.class), eq(id))).thenReturn("Zone updated successfully");
        String result = controller.updateZone(zone, id);
        assertEquals("Zone updated successfully", result);
    }

    @Test
    void deleteZone() {
        int id = 1;
        when(zoneService.deleteZone(id)).thenReturn("Zone deleted successfully");
        String result = controller.deleteZone(id);
        assertEquals("Zone deleted successfully", result);
    }

    @Test
    void getAllZone() {
        List<Zone> zones = Collections.singletonList(new Zone());
        when(zoneService.getAllZone()).thenReturn(zones);
        List<Zone> result = controller.getAllZone();
        assertEquals(zones, result);
    }

    @Test
    void getZoneById() {
        int id = 1;
        Zone zone = new Zone();
        when(zoneService.getZoneById(id)).thenReturn(zone);
        Zone result = controller.getZoneById(id);
        assertEquals(zone, result);
    }

    @Test
    void addAffiliate() {
        Affiliate affiliate = new Affiliate();
        int zoneId = 1;
        when(affiliateService.addAffiliate(any(Affiliate.class), eq(zoneId))).thenReturn("Affiliate added successfully");
        String result = controller.addAffiliate(affiliate, zoneId);
        assertEquals("Affiliate added successfully", result);
    }

    @Test
    void updateAffiliate() {
        Affiliate affiliate = new Affiliate();
        int id = 1;
        when(affiliateService.updateAffiliate(any(Affiliate.class), eq(id))).thenReturn("Affiliate updated successfully");
        String result = controller.updateAffiliate(affiliate, id);
        assertEquals("Affiliate updated successfully", result);
    }

    @Test
    void testUpdateAffiliate() {
        int affiliateId = 1;
        int zoneId = 2;
        when(affiliateService.updateZoneAffiliate(affiliateId, zoneId)).thenReturn("Affiliate updated with new zone successfully");
        String result = controller.updateAffiliate(affiliateId, zoneId);
        assertEquals("Affiliate updated with new zone successfully", result);
    }

    @Test
    void deleteAffiliate() {
        int id = 1;
        when(affiliateService.deleteAffiliate(id)).thenReturn("Affiliate deleted successfully");
        String result = controller.deleteAffiliate(id);
        assertEquals("Affiliate deleted successfully", result);
    }

    @Test
    void getAllAffiliate() {
        List<Affiliate> affiliates = Collections.singletonList(new Affiliate());
        when(affiliateService.getAllAffiliate()).thenReturn(affiliates);
        List<Affiliate> result = controller.getAllAffiliate();
        assertEquals(affiliates, result);
    }

    @Test
    void getAffiliateById() {
        int id = 1;
        Affiliate affiliate = new Affiliate();
        when(affiliateService.getAffiliateById(id)).thenReturn(affiliate);
        Affiliate result = controller.getAffiliateById(id);
        assertEquals(affiliate, result);
    }

    @Test
    void getAllAffiliateByZone() {
        String zoneName = "Test Zone";
        List<Affiliate> affiliates = Collections.singletonList(new Affiliate());
        when(affiliateService.getAllAffiliateByZone(zoneName)).thenReturn(affiliates);
        List<Affiliate> result = controller.getAllAffiliateByZone(zoneName);
        assertEquals(affiliates, result);
    }

    /*@Test
    void addDataCenter() {
        DataCenter dataCenter = new DataCenter();
        int id = 1;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJSelR3T01VWDhWTEJCczNlOUVsTlJtc2JrWk1oQWpUWVVWaUFxUDdvd0NrIn0.eyJleHAiOjE3MTE1MjgwMzIsImlhdCI6MTcxMTUyNzczMiwianRpIjoiYjgwZmU0MzgtNmViZC00NmFhLWI0MjItZGY2MDllMjBhODYwIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9TcHJpbmdCb290RGVtbyIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiIxMGMxMDRmZC04NmZhLTRkNGYtOTMzNS0yNmVlNWZkMjhmNWUiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJsb2dpbiIsInNlc3Npb25fc3RhdGUiOiI5MWU4OWQ2Yy1lMTIzLTQ1OGQtYjYxYS0wY2ZlNDRlMWY3Y2MiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbInJlYWxtX3VzZXIiLCJvZmZsaW5lX2FjY2VzcyIsImRlZmF1bHQtcm9sZXMtc3ByaW5nYm9vdGRlbW8iLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImxvZ2luIjp7InJvbGVzIjpbInVzZXIiXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIGVtYWlsIHByb2ZpbGUiLCJzaWQiOiI5MWU4OWQ2Yy1lMTIzLTQ1OGQtYjYxYS0wY2ZlNDRlMWY3Y2MiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlcnVzZXIiLCJnaXZlbl9uYW1lIjoiIiwiZmFtaWx5X25hbWUiOiIiLCJlbWFpbCI6InVzZXJ1c2VyQGdtYWlsLmNvbSJ9.MA8woBOkVBXWN8ZMVfxHlubLLTEkVWs_dZ9JL9dn-C3TRwQ26kW_6wvZ5509UcQD882j2xP_v4dwB_ZFuW_M6GQhLPM0VBBM6rDg8HLG8Mkfvt0o66cdhnh--aYH61ZBFubSHhx3L9tbag1zDxmgpCD_lalLRvQFXibD800lRLZ-0BGuHqcZoyE-0iEiby36geuZjEm5V5LCMPx7BqQvqJw2-eeTzLv24Y8fdRMkdwNWvS6EODPW2Q1FmEEC45GcwNL1kqXaENlRO76zU2h156ux9uPHnK5bDDlvbPB0hci8X2KuIJyP7qzswmtpTG_MDCT5GfngwnFVeCziDnOy1Q");
        DataCenter savedDataCenter = new DataCenter();
        savedDataCenter.setIdDataCenter(id);
        when(dataCenterService.addDataCenter(eq(dataCenter), eq(id))).thenReturn(savedDataCenter);
        when(dataCenterService.updateAffiliateDataCenter(eq(id), eq(id))).thenReturn("DataCenter added successfully");
        String result = controller.addDataCenter(dataCenter, id, headers);
        assertEquals("First Microservice: DataCenter added successfully, Second Microservice: added, Third Microservice: added", result);
    }*/

    @Test
    void updateDataCenter() {
        DataCenter dataCenter = new DataCenter();
        int id = 1;
        when(dataCenterService.updateDataCenter(dataCenter, id)).thenReturn("DataCenter updated successfully");
        String result = controller.updateDataCenter(dataCenter, id);
        assertEquals("DataCenter updated successfully", result);
    }

    @Test
    void testUpdateAffiliateDataCenter() {
        int dataCenterId = 1;
        int affiliateId = 2;
        when(dataCenterService.updateAffiliateDataCenter(dataCenterId, affiliateId)).thenReturn("Affiliate updated with new data center successfully");
        String result = controller.updateAffiliateDataCenter(dataCenterId, affiliateId);
        assertEquals("Affiliate updated with new data center successfully", result);
    }

    @Test
    void deleteDataCenter() {
        int id = 1;
        when(dataCenterService.deleteDataCenter(id)).thenReturn("DataCenter deleted successfully");
        String result = controller.deleteDataCenter(id);
        assertEquals("DataCenter deleted successfully", result);
    }

    @Test
    void getDataCenterById() {
        int id = 1;
        DataCenter dataCenter = new DataCenter();
        when(dataCenterService.getDataCenterById(id)).thenReturn(dataCenter);
        DataCenter result = controller.getDataCenterById(id);
        assertEquals(dataCenter, result);
    }
    @Test
    void getAllDataCenterByAffiliate() {
        String zoneAff = "Test Aff";
        List<DataCenter> dataCenters = Collections.singletonList(new DataCenter());
        when(dataCenterService.getAllDataCenterByAffiliate(zoneAff)).thenReturn(dataCenters);
        List<DataCenter> result = controller.getAllDataCenterByAffiliate(zoneAff);
        assertEquals(dataCenters, result);
    }
    @Test
    void getAllDataCenter() {
        List<DataCenter> dataCenters = Collections.singletonList(new DataCenter());
        when(dataCenterService.getAllDataCenter()).thenReturn(dataCenters);
        List<DataCenter> result = controller.getAllDataCenter();
        assertEquals(dataCenters, result);
    }
}


