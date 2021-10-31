function crearMapa(lugares) {

    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {

            let enable = true;
            mapboxgl.accessToken = 'pk.eyJ1IjoiZmptdXJjaWFoIiwiYSI6ImNrcDhvNzVoNjAwY2MydnBjaWZ5am0xeWkifQ.CbBV5gBoxdhFKRt6lU3xCA';

            var map = new mapboxgl.Map({
                container: 'map',
                style: 'mapbox://styles/mapbox/streets-v11',
                center: [-75.66, 4.52],
                zoom: 12
            });

            //Añade un control de geolocalizacion al mapa en la ventana de la creacion del lugar
            map.addControl(new mapboxgl.GeolocateControl({
                positionOptions: {
                    enableHighAccuracy: true
                },
                trackUserLocation: true
            }));

            //Añade varios controles al mapa
            map.addControl(new mapboxgl.NavigationControl());

            map.on("load", function (e) {

                ubicarLugares(lugares, map);
            });

        })
    }

    function ubicarLugares(lugares, map) {

        let bounds = new mapboxgl.LngLatBounds();

        for (let l of lugares) {

            new mapboxgl.Marker().setLngLat([l.lng, l.lat]).setPopup(new mapboxgl.Popup().setHTML("<strong>" + l.nombre + "</strong><br>" + l.descripcion)).addTo(map).togglePopup();

            bounds.extend([l.lng, l.lat]);
        }

        map.fitBounds(bounds, {padding: 100});
        document.getElementById("map").style.visibility = "visible";

    }

}