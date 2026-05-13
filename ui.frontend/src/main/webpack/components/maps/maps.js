document.addEventListener("DOMContentLoaded", () => {
   
    const mapContainers = document.querySelectorAll('.itv-maps');

    mapContainers.forEach(container => {
        const lat = parseFloat(container.getAttribute('data-lat'));
        const lng = parseFloat(container.getAttribute('data-lng'));
        const zoom = parseInt(container.getAttribute('data-zoom')) || 15;

        const mapElement = container.querySelector('.itv-googlemap__canvas');


        if (!isNaN(lat) && !isNaN(lng) && typeof google !== 'undefined') {
            const mapPosition = { lat: lat, lng: lng };
            
            const map = new google.maps.Map(mapElement, {
                center: mapPosition,
                zoom: zoom,
                disableDefaultUI: true, 
                zoomControl: true 
            });

            new google.maps.Marker({
                position: mapPosition,
                map: map,
                title: "Localización",
                animation: google.maps.Animation.DROP 
            });
            
        } else {
            console.warn("MapComponent: No se pudo renderizar el mapa. Faltan coordenadas o la API de Google.");
        }
    });
});