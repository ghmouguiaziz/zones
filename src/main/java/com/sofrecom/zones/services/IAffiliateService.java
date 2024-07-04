package com.sofrecom.zones.services;



import com.sofrecom.zones.entities.Affiliate;

import java.util.List;

public interface IAffiliateService {
    public String addAffiliate(Affiliate af, int id);
    public String updateAffiliate(Affiliate a ,int id);
    public String updateZoneAffiliate(int ida ,int idz);
    public String deleteAffiliate(int id);
    public List<Affiliate> getAllAffiliate();
    public Affiliate getAffiliateById(int id);
    //////////////////////////////////////////
    public List<Affiliate> getAllAffiliateByZone(String zoneName);

}
