package com.itv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.Default;
import javax.annotation.PostConstruct;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itv.core.services.MapsConfigurationService;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = Resource.class)
public class MapsModel {

    @OSGiService
    private MapsConfigurationService mapsConfigService;

    @ValueMapValue
    private String address;

    @ValueMapValue
    @Default(values = "15")
    private String zoom;

    private double lat;
    private double lng;
    private boolean valid;

    @PostConstruct
    protected void init() {
        if (address != null && !address.isEmpty()) {
            String secureKey = mapsConfigService.getGeocodingApiKey();
            geocodeAddress(address, secureKey);
        } else {
            this.valid = false;
        }
    }

    private void geocodeAddress(String addr, String key) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" 
                     + addr.replace(" ", "+") + "&key=" + key;
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
                
                if ("OK".equals(jsonObject.get("status").getAsString())) {
                    JsonObject location = jsonObject.getAsJsonArray("results")
                                            .get(0).getAsJsonObject()
                                            .getAsJsonObject("geometry")
                                            .getAsJsonObject("location");
                    this.lat = location.get("lat").getAsDouble();
                    this.lng = location.get("lng").getAsDouble();
                    this.valid = true;
                }
            }
        } catch (Exception e) {
            this.valid = false;
        }
    }

    public double getLat() { return lat; }
    public double getLng() { return lng; }
    public String getZoom() { return zoom; }
    public boolean isValid() { return valid; }
}