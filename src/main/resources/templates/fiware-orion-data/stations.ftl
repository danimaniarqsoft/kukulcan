{
	"actionType": "APPEND",
	"entities": [
	
	<#list stations as station>
        {
      "id": "CDMX-AmbientObserved-${station.clave}-${station.count}",
      "type": "AirQualityObserved",
      "address": {
        "type": "StructuredValue",
        "value": {
          "addressCountry": "MX",
          "addressLocality": "Ciudad de México",
          "streetAddress": "${station.address}"
        }
      },
      "location": {
        "value": {
          "type": "Point",
          "coordinates": [
            ${station.latitud},
            ${station.longitud}
          ]
        },
        "type": "geo:json"
      },
      "source": {
        "type": "text",
        "value": "http://www.aire.cdmx.gob.mx/"
      },
      "temperature": {
        "type": "text",
        "value": "${station.temperature}"
      },
      "relativeHumidity": {
        "type": "text",
        "value": "${station.humidity}"
      },
      "CO": {
        "type": "number",
        "value": 500
      },
      "O3": {
        "type": "number",
        "value": ${station.o3}
      },
      "NO2": {
        "type": "number",
        "value": ${station.no2}
      },
      "SO2": {
        "type": "number",
        "value": ${station.so2}
      },
      "PM10": {
        "type": "number",
        "value": ${station.pm10}
      }
    }, 
        </#list>
	]
}
