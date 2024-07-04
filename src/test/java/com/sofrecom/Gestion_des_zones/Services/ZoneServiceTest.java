package com.sofrecom.Gestion_des_zones.Services;

import com.sofrecom.zones.entities.Zone;
import com.sofrecom.zones.repositories.ZoneRepo;
import com.sofrecom.zones.services.ZoneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ZoneServiceTest {
    @Mock
    ZoneRepo zoneRepo;

    @InjectMocks
    ZoneService zoneService;

    @Test
    void addZone() {
        Zone zoneToAdd = new Zone();
        when(zoneRepo.save(zoneToAdd)).thenReturn(zoneToAdd);

        String result = zoneService.addZone(zoneToAdd);

        assertEquals("Added", result);
        verify(zoneRepo, times(1)).save(zoneToAdd);
    }

    @Test
    void updateZone() {

        int zoneId = 1;
        Zone existingZone = new Zone();
        existingZone.setIdZone(zoneId);
        existingZone.setName("Old Name");

        Zone updatedZone = new Zone();
        updatedZone.setIdZone(zoneId);
        updatedZone.setName("New Name");


        when(zoneRepo.findById(zoneId)).thenReturn(Optional.of(existingZone));


        doReturn(updatedZone).when(zoneRepo).saveAndFlush(any(Zone.class));


        String result = zoneService.updateZone(updatedZone, zoneId);


        assertEquals("updated", result);
        verify(zoneRepo, times(1)).findById(zoneId);
        verify(zoneRepo, times(1)).saveAndFlush(any(Zone.class));
    }

    @Test
    void deleteZone() {
        int zoneId = 1;

        String result = zoneService.deleteZone(zoneId);

        assertEquals("deleted", result);
        verify(zoneRepo, times(1)).deleteById(zoneId);
    }

    @Test
    void getAllZone() {
        Zone zone1 = new Zone();
        Zone zone2 = new Zone();
        List<Zone> zones = List.of(zone1, zone2);

        when(zoneRepo.findAll()).thenReturn(zones);

        List<Zone> result = zoneService.getAllZone();

        assertEquals(zones, result);
        verify(zoneRepo, times(1)).findAll();
    }

    @Test
    void getZoneById() {
        int zoneId = 1;
        Zone zone = new Zone();

        when(zoneRepo.findById(zoneId)).thenReturn(Optional.of(zone));

        Zone result = zoneService.getZoneById(zoneId);

        assertEquals(zone, result);
        verify(zoneRepo, times(1)).findById(zoneId);
    }
}
