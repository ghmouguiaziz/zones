package com.sofrecom.Gestion_des_zones.Services;

import com.sofrecom.zones.entities.Affiliate;
import com.sofrecom.zones.entities.Zone;
import com.sofrecom.zones.repositories.AffiliateRepo;
import com.sofrecom.zones.repositories.ZoneRepo;
import com.sofrecom.zones.services.AffiliateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AffiliateServiceTest {
    @Mock
    AffiliateRepo affiliateRepo;
    @Mock
    ZoneRepo zoneRepo;


    @InjectMocks
    AffiliateService affiliateService;

    @Test
    void addAffiliate() {
        Affiliate affiliateToAdd = new Affiliate();
        int zoneId = 1;
        Zone zone = new Zone();
        zone.setIdZone(zoneId);

        when(zoneRepo.findById(zoneId)).thenReturn(Optional.of(zone));
        when(affiliateRepo.save(affiliateToAdd)).thenReturn(affiliateToAdd);

        String result = affiliateService.addAffiliate(affiliateToAdd, zoneId);

        assertEquals("added", result);
        verify(zoneRepo, times(1)).findById(zoneId);
        verify(affiliateRepo, times(1)).save(affiliateToAdd);
    }

    @Test
    void updateAffiliate() {

        // Créer une instance d'affilié avec un ID spécifique
        int affiliateId = 1;
        Affiliate existingAffiliate = new Affiliate();
        existingAffiliate.setIdAffiliate(affiliateId);

        // Simulation de findById pour retourner l'instance d'affilié créée
        when(affiliateRepo.findById(affiliateId)).thenReturn(Optional.of(existingAffiliate));

        // Simulation de saveAndFlush en utilisant le même instance d'affilié
        when(affiliateRepo.saveAndFlush(existingAffiliate)).thenReturn(existingAffiliate);

        // Créer une nouvelle instance d'affilié avec des données mises à jour
        Affiliate updatedAffiliate = new Affiliate();
        updatedAffiliate.setIdAffiliate(affiliateId);
        updatedAffiliate.setName("New Name");
        updatedAffiliate.setFullName("New Full Name");

        // Appel de la méthode à tester
        String result = affiliateService.updateAffiliate(updatedAffiliate, affiliateId);

        // Vérifier que la méthode a été appelée avec les bons paramètres
        assertEquals("updated", result);
        verify(affiliateRepo, times(1)).findById(affiliateId);
        verify(affiliateRepo, times(1)).saveAndFlush(existingAffiliate);
    }

    @Test
    void updateZoneAffiliate() {

        int affiliateId = 1;
        int newZoneId = 2;

        Affiliate existingAffiliate = new Affiliate();
        existingAffiliate.setIdAffiliate(affiliateId);

        Zone newZone = new Zone();
        newZone.setIdZone(newZoneId);

        when(affiliateRepo.findById(affiliateId)).thenReturn(Optional.of(existingAffiliate));
        when(zoneRepo.findById(newZoneId)).thenReturn(Optional.of(newZone));

        String result = affiliateService.updateZoneAffiliate(affiliateId, newZoneId);

        assertEquals("updated", result);
        verify(affiliateRepo, times(1)).findById(affiliateId);
        verify(zoneRepo, times(1)).findById(newZoneId);
        verify(affiliateRepo, times(1)).save(existingAffiliate);
    }

    @Test
    void deleteAffiliate() {

        int affiliateId = 1;

        String result = affiliateService.deleteAffiliate(affiliateId);

        assertEquals("deleted", result);
        verify(affiliateRepo, times(1)).deleteById(affiliateId);
    }

    @Test
    void getAllAffiliate() {
        List<Affiliate> affiliates = new ArrayList<>();

        when(affiliateRepo.findAll()).thenReturn(affiliates);

        List<Affiliate> result = affiliateService.getAllAffiliate();

        assertEquals(affiliates, result);
        verify(affiliateRepo, times(1)).findAll();
    }

    @Test
    void getAffiliateById() {
        int affiliateId = 1;
        Affiliate affiliate = new Affiliate();

        when(affiliateRepo.findById(affiliateId)).thenReturn(Optional.of(affiliate));

        Affiliate result = affiliateService.getAffiliateById(affiliateId);

        assertEquals(affiliate, result);
        verify(affiliateRepo, times(1)).findById(affiliateId);
    }

    @Test
    void getAllAffiliateByZone() {
        String zoneName = "Test Zone";
        Zone zone = new Zone();
        zone.setName(zoneName);

        List<Affiliate> affiliates = new ArrayList<>();
        affiliates.add(new Affiliate());
        zone.setAffiliates(affiliates);

        when(zoneRepo.findByName(zoneName)).thenReturn(zone);

        List<Affiliate> result = affiliateService.getAllAffiliateByZone(zoneName);

        assertEquals(affiliates, result);
        verify(zoneRepo, times(1)).findByName(zoneName);
    }
}
