{
	"actionType": "APPEND",
	"entities": [
	
	<#list rooms as room>
      {
			"id": "${room.entityType}-MX-${room.count}",
			"type": "${room.entityType}",
			"name": {
    			"value": "${room.entityType}-MX-${room.count}",
    			"type": "Text"
  			},
  			"category": {
				"type": "Text",
				"value": "${room.category}",
				"metadata": {}
			},
			"order": {
				"type": "Integer",
				"value": "${room.order}",
				"metadata": {}
			},
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