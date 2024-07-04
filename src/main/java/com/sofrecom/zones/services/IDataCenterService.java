package com.sofrecom.zones.services;


import com.sofrecom.zones.entities.DataCenter;

import java.util.List;

public interface IDataCenterService {

    public DataCenter addDataCenter(DataCenter af, int id);
    public String updateDataCenter(DataCenter a ,int id);
    public String updateAffiliateDataCenter(int iddc ,int ida);
    public String deleteDataCenter(int id);
    public List<DataCenter> getAllDataCenter();
    public DataCenter getDataCenterById(int id);
    public List<DataCenter> getAllDataCenterByAffiliate(String affiliateName);

}
