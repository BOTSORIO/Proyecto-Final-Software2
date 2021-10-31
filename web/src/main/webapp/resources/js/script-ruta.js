window.onload = function (){

    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {

            let longitudOrigen = position.coords.longitude;
            let latitudOrigen = position.coords.latitude;
            mapboxgl.accessToken = 'pk.eyJ1IjoiZmptdXJjaWFoIiwiYSI6ImNrcDhvNzVoNjAwY2MydnBjaWZ5am0xeWkifQ.CbBV5gBoxdhFKRt6lU3xCA';

            var map = new mapboxgl.Map({
                container: 'mapa',
                style: 'mapbox://styles/mapbox/streets-v11',
                center: [position.coords.longitude,position.coords.latitude],
                zoom: 12
            });

            //Añade varios controles al mapa
            map.addControl(new mapboxgl.NavigationControl());

            //Obtenemos el url con la longitud y latitud del lugar
            const valores = window.location.search;

            //Creamos la instancia para extraer los valores
            const urlParams = new URLSearchParams(valores);

            //Accedemos a los valores
            let latitud = urlParams.get('latitud');
            let longitud = urlParams.get('longitud');

            //Se añade la ubicación origen (ubicación actual) y destino (el lugar)
            map.on('load', function() {
                var directions = new MapboxDirections({
                    accessToken: mapboxgl.accessToken,language: 'es',unit: 'metric'
                });

                directions.setOrigin([longitudOrigen,latitudOrigen]);
                directions.setDestination([longitud,latitud]);

                map.addControl(directions, 'top-left');

                directions.interactive(false);

                directions.on("route", function (e){


                    console.log(e.route[0].distance);
                })

            });

        })


    }

}