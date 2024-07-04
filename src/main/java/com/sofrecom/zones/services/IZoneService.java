package com.sofrecom.zones.services;


import com.sofrecom.zones.entities.Zone;

import java.util.List;

public interface IZoneService {
    public String addZone(Zone z ) ;
    public String updateZone(Zone z ,int id);
    public String deleteZone(int id);
    public List<Zone> getAllZone();
    public Zone getZoneById(int id);
}
