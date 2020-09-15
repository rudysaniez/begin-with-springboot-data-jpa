# Character API with Spring-Boot and MongoDB

## Maven

	mvn clean package -Dmaven.test.skip

## Run docker-compose

	docker-compose up --detach --build
	
You can launch :
	
	docker-compose logs -f
	
If you w	ant see the logs of api container :

	docker-compose logs -f character-api
	
## Access to Character API

	curl -X GET "http://localhost:9080/api/v1/characters?pageNumber=0&pageSize=5" -s | jq
	
Response :

	{
		"httpStatus": "NOT_FOUND",
		"message": "The collection of characters is empty.",
		"timestamp": "2020-09-08T14:58:37.886751Z"
	}
	
### Add a new Character
	
This character will be created :

	{
	  "name": "CASSIA",
	  "creationDate": "2020-05-09T10:14:08.71Z",
	  "role": {
	    "name": "REMOTE_ASSASSIN",
	    "creationDate": "2020-05-09T10:14:08.73Z"
	  },
	  "life": {
	    "minimumLife": "1500",
	    "upByLevel": "4"
	  },
	  "spells": [
	    {
	      "name": "LIGHTNING_JAVELIN",
	      "controlType": "NONE",
	      "basicDamage": "165",
	      "upByLevel": "5",
	      "iterationNumber": "1",
	      "effectArea": "LINE",
	      "key":
	        {
	          "name": "A"
	        }
	      ,
	      "range": 
	        {
	          "shootingRange": "9.0",
	          "createDate": "2020-05-09T10:14:08+0000"
	        }
	    },
	    {
	      "name": "BLINDING_LIGHT",
	      "controlType": "BLIND",
	      "basicDamage": "50",
	      "upByLevel": "5",
	      "iterationNumber": "1",
	      "effectArea": "CIRCLE",
	      "key":
	        {
	          "name": "Z"
	        }
	      ,
	      "range":
	        {
	          "shootingRange": "5.0",
	          "createDate": "2020-05-09T10:14:08+0000"
	        }
	    },
	    {
	      "name": "FULGURANCE",
	      "controlType": "BLIND",
	      "basicDamage": "68",
	      "upByLevel": "5",
	      "iterationNumber": "10",
	      "effectArea": "TRIANGLE",
	      "key":
	        {
	          "name": "E"
	        }
	      ,
	      "range":
	        {
	          "shootingRange": "6.0",
	          "createDate": "2020-05-09T10:14:08+0000"
	        }
	    }
	  ]
	}
	
Launch that :

	curl -X POST -H "Content-Type: application/json" -d '{"name":"CASSIA","creationDate":"2020-05-09T10:14:08.71Z","role":{"name":"REMOTE_ASSASSIN","creationDate":"2020-05-09T10:14:08.73Z"},"life":{"minimumLife":"1500","upByLevel":"4"},"spells":[{"name":"LIGHTNING_JAVELIN","controlType":"NONE","basicDamage":"165","upByLevel":"5","iterationNumber":"1","effectArea":"LINE","key":{"name":"A"},"range":{"shootingRange":"9.0","createDate":"2020-05-09T10:14:08+0000"}},{"name":"BLINDING_LIGHT","controlType":"BLIND","basicDamage":"50","upByLevel":"5","iterationNumber":"1","effectArea":"CIRCLE","key":{"name":"Z"},"range":{"shootingRange":"5.0","createDate":"2020-05-09T10:14:08+0000"}},{"name":"FULGURANCE","controlType":"BLIND","basicDamage":"68","upByLevel":"5","iterationNumber":"10","effectArea":"TRIANGLE","key":{"name":"E"},"range":{"shootingRange":"6.0","createDate":"2020-05-09T10:14:08+0000"}}]}' "http://localhost:9080/api/v1/characters" -s | jq
	
## Find a character by name

	curl -X GET "http://localhost:9080/api/v1/characters?name=CASSIA" -s | jq

## Stopping up the api

	docker-compose stop
		
	