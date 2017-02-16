{
	"actionType": "APPEND",
	"entities": [
	
	<#list rooms as room>
      {
			"id": "${room.entityType}-MX-${room.count}",
			"type": "${room.entityType}",
			"temperature": {
    			"value": ${room.temperatureValue},
    			"type": "Float"
  			},
  			"pressure": {
    			"value": ${room.pressureValue},
    			"type": "Integer"
  			}
		},
    </#list>
	]
}