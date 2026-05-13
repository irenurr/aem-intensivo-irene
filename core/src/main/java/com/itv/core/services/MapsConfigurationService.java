package com.itv.core.services;

import com.itv.core.config.MapsConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = MapsConfigurationService.class, immediate = true)
@Designate(ocd = MapsConfig.class)
public class MapsConfigurationService {

    private String geocodingApiKey;
    private String mapsJsApiKey;

    @Activate
    @Modified
    protected void activate(MapsConfig config) {
        this.geocodingApiKey = config.geocodingApiKey();
        this.mapsJsApiKey = config.mapsJsApiKey();
    }


    public String getGeocodingApiKey() {
        return geocodingApiKey;
    }

    public String getMapsJsApiKey() {
        return mapsJsApiKey;
    }
}