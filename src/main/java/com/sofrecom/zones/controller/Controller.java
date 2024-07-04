package com.sofrecom.zones.controller;

import com.sofrecom.zones.entities.Affiliate;
import com.sofrecom.zones.entities.DataCenter;
import com.sofrecom.zones.entities.Zone;
import com.sofrecom.zones.services.IAffiliateService;
import com.sofrecom.zones.services.IDataCenterService;
import com.sofrecom.zones.services.IZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/data")
@CrossOrigin(origins = "http://localhost:4200/")
public class Controller {
    private static final String CONTENT_LENGTH_HEADER = "content-length";


    //////////////////////////////////Zone///////////////////////////////////////////////

    private final IZoneService izoneservice;

    @PostMapping("/addZone")
    public String addZone(@RequestBody Zone z) {

        return izoneservice.addZone(z);
    }

    @PutMapping("/updateZone/{id}")
    public String updateZone(@RequestBody Zone z, @PathVariable int id) {
        return izoneservice.updateZone(z, id);
    }

    @DeleteMapping("/deleteZone/{id}")
    public String deleteZone(@PathVariable int id) {

        return izoneservice.deleteZone(id);
    }

    @GetMapping("/getAllZone")
    @PreAuthorize("hasAnyRole('user','admin')")
    public List<Zone> getAllZone() {

        return izoneservice.getAllZone();
    }

    @GetMapping("/getZoneById/{id}")
    public Zone getZoneById(@PathVariable int id) {

        return izoneservice.getZoneById(id);
    }


    //////////////////////////////////Affiliate///////////////////////////////////////////////

    private final IAffiliateService iaffiliateservice;

    @PostMapping("/addAffiliate/{id}")

    public String addAffiliate(@RequestBody Affiliate a, @PathVariable int id) {
        return iaffiliateservice.addAffiliate(a, id);
    }

    @PutMapping("/updateAffiliate/{id}")
    public String updateAffiliate(@RequestBody Affiliate a, @PathVariable int id) {
        return iaffiliateservice.updateAffiliate(a, id);
    }

    @PutMapping("/updateZoneAffiliate/{ida}/{idz}")
    public String updateAffiliate(@PathVariable int ida, @PathVariable int idz) {
        return iaffiliateservice.updateZoneAffiliate(ida, idz);
    }

    @DeleteMapping("/deleteAffiliate/{id}")
    public String deleteAffiliate(@PathVariable int id) {

        return iaffiliateservice.deleteAffiliate(id);
    }

    @GetMapping("/getAllAffiliate")
    public List<Affiliate> getAllAffiliate() {

        return iaffiliateservice.getAllAffiliate();
    }

    @GetMapping("/getAffiliateById/{id}")
    public Affiliate getAffiliateById(@PathVariable int id) {

        return iaffiliateservice.getAffiliateById(id);
    }

    @GetMapping("/getAllAffiliateByZone/{zoneName}")
    public List<Affiliate> getAllAffiliateByZone(@PathVariable String zoneName) {
        return iaffiliateservice.getAllAffiliateByZone(zoneName);
    }

    //////////////////////////////////DataCenter///////////////////////////////////////////////

    private final IDataCenterService idatacenterservice;

    @PostMapping("/addDataCenter/{id}")
    public String addDataCenter(@RequestBody DataCenter dc, @PathVariable int id, @RequestHeader HttpHeaders headers) {
        DataCenter savedDataCenter = idatacenterservice.addDataCenter(dc,id);

        // Appel au deuxième microservice
        String responseFromSecondMicroservice = callSecondMicroservice(savedDataCenter.getIdDataCenter(), convertHeaders(headers));

        // Appel au troisième microservice
        String responseFromThirdMicroservice = callThirdMicroservice(savedDataCenter.getIdDataCenter(), convertHeaders(headers));

        return "First Microservice: DataCenter added successfully, Second Microservice: " + responseFromSecondMicroservice
                + ", Third Microservice: " + responseFromThirdMicroservice;
    }
    @PutMapping("/updateDataCenter/{id}")
    public String updateDataCenter(@RequestBody DataCenter dc, @PathVariable int id) {
        return idatacenterservice.updateDataCenter(dc, id);
    }

    @PutMapping("/updateAffiliateDataCenter/{iddc}/{ida}")
    public String updateAffiliateDataCenter(@PathVariable int iddc, @PathVariable int ida) {
        return idatacenterservice.updateAffiliateDataCenter(iddc, ida);
    }

    @DeleteMapping("/deleteDataCenter/{id}")
    public String deleteDataCenter(@PathVariable int id) {

        return idatacenterservice.deleteDataCenter(id);
    }

    @GetMapping("/getDataCenterById/{id}")
    public DataCenter getDataCenterById(@PathVariable int id) {

        return idatacenterservice.getDataCenterById(id);
    }

    @GetMapping("/getAllDataCenter")
    public List<DataCenter> getAllDataCenter() {

        return idatacenterservice.getAllDataCenter();
    }

    @GetMapping("/getAllDataCenterByAffiliate/{dataCenterName}")
    public List<DataCenter> getAllDataCenterByAffiliate(@PathVariable String dataCenterName) {
        return idatacenterservice.getAllDataCenterByAffiliate(dataCenterName);
    }




    private MultiValueMap<String, String> convertHeaders(HttpHeaders headers) {
        final MultiValueMap<String, String> convertedHeaders = new LinkedMultiValueMap<>();
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            String key = entry.getKey();
            if (!CONTENT_LENGTH_HEADER.equals(key)) {
                convertedHeaders.put(key, entry.getValue());
            }
        }
        return convertedHeaders;
    }

    private String callSecondMicroservice(int id, MultiValueMap<String, String> headers) {
        try {
            final String addDataCenterUrl = "http://localhost:8091/data/addDataCenter";

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Integer> requestEntity = new HttpEntity<>(id, headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(addDataCenterUrl, requestEntity, String.class);
            return responseEntity.getBody();
        } catch (RestClientException e) {
            return "Error while calling the second microservice: " + e.getMessage();
        }
    }

    private String callThirdMicroservice(int id, MultiValueMap<String, String> headers) {
        try {
            final String addDataCenterUrl = "http://localhost:8092/data/addDataCenter";

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Integer> requestEntity = new HttpEntity<>(id, headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(addDataCenterUrl, requestEntity, String.class);
            return responseEntity.getBody();
        } catch (RestClientException e) {
            return "Error while calling the third microservice: " + e.getMessage();
        }
    }


}


