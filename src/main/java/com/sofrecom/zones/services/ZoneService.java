package com.sofrecom.zones.services;


import com.sofrecom.zones.entities.Zone;
import com.sofrecom.zones.repositories.ZoneRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class ZoneService implements IZoneService {


    private final ZoneRepo zonerepo;
    @Override
    public String addZone(Zone z) {
        zonerepo.save(z);
        return "Added";
    }
    @Override
    public String updateZone(Zone z ,int id) {

        Zone z1 = zonerepo.findById(id).get();
        z1.setName(z.getName());
         zonerepo.saveAndFlush(z1);
        return "updated";
    }
    @Override
    public String deleteZone(int id) {

        zonerepo.deleteById(id);
        return "deleted";
    }
    @Override
    public List<Zone> getAllZone() {

        return zonerepo.findAll();
    }
    @Override
    public Zone getZoneById(int id) {

        return zonerepo.findById(id).orElse(null);
    }




}
