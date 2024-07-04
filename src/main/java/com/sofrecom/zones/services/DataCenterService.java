package com.sofrecom.zones.services;


import com.sofrecom.zones.entities.Affiliate;
import com.sofrecom.zones.entities.DataCenter;
import com.sofrecom.zones.repositories.AffiliateRepo;
import com.sofrecom.zones.repositories.DataCenterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class DataCenterService implements IDataCenterService{

    private final DataCenterRepo datacenterrepo;

    private final AffiliateRepo affiliaterepo;

    @Override
    public DataCenter addDataCenter(DataCenter dc, int id) {
        Affiliate af= affiliaterepo.findById(id).get();
        dc.setAffiliate(af);
        return datacenterrepo.save(dc);
    }
    @Override
    public String updateDataCenter(DataCenter dc ,int id) {

        DataCenter dc1 = datacenterrepo.findById(id).get();
        dc1.setName(dc.getName());
        datacenterrepo.saveAndFlush(dc1);
        return "updated";
    }
    @Override
    public String updateAffiliateDataCenter(int iddc ,int ida){
        DataCenter dc = datacenterrepo.findById(iddc).get();
        Affiliate a= affiliaterepo.findById(ida).get();
        dc.setAffiliate(a);
        datacenterrepo.save(dc);
        return "updated";
    }
    @Override
    public String deleteDataCenter(int id) {

        datacenterrepo.deleteById(id);
        return "deleted";
    }
    @Override
    public List<DataCenter> getAllDataCenter() {

        return datacenterrepo.findAll();
    }
    @Override
    public DataCenter getDataCenterById(int id) {

        return datacenterrepo.findById(id).orElse(null);
    }
    @Override
    public List<DataCenter> getAllDataCenterByAffiliate(String dataCenterName){
        Affiliate z = affiliaterepo.findByName(dataCenterName);
        return z.getDataCenters();}

}
