package com.itv.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
    name = "BuyCar - Google Maps Configuration",
    description = "Configuraciones seguras para las APIs de Google Maps"
)
public @interface MapsConfig {

    @AttributeDefinition(
        name = "Geocoding API Key (Backend)",
        description = "Clave privada para traducir direcciones a coordenadas en Java",
        type = AttributeType.STRING
    )
    String geocodingApiKey() default "";

    @AttributeDefinition(
        name = "Maps JavaScript API Key (Frontend)",
        description = "Clave pública para pintar el mapa en el navegador",
        type = AttributeType.STRING
    )
    String mapsJsApiKey() default "";
}