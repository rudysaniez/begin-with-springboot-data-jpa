# Character API with Spring-Boot and MongoDB

## Maven

	mvn clean package

## Run mysql in Docker

	./run-mysql
	
This file launch that :

	docker run --rm -d --name mysql23 -p 3306:3306 -e "MYSQL_ROOT_PASSWORD=welcome" -e "MYSQL_USER=michael" -e "MYSQL_PASSWORD=jordan" -e "MYSQL_DATABASE=characterdb" mysql:latest
	
You can launch :
	
	docker logs -f mysql23
	
## Starting up Characters API

	cd target
	java -jar -Dspring.profiles.active=demo begin-with-springboot-jpa-0.0.1-SNAPSHOT.jar &
	
The "dev" profile is enabled. You can check the configuration in application.yml file.
	
## Access to Character API : JAINA, where are you ?

	curl -X GET "http://localhost:9080/api/v1/characters?name=JAINA" -s | jq
	
Response :

	{
	"content": [
	    {
	      "id": "1",
	      "name": "JAINA",
	      "creationDate": "2020-09-18T07:19:29Z",
	      "role": {
	        "id": "1",
	        "name": "REMOTE_ASSASSIN",
	        "creationDate": "2020-09-18T07:19:29Z"
	      },
	      "life": {
	        "minimumLife": 1400,
	        "upByLevel": 4
	      },
	      "spells": [
	        {
	          "id": "1",
	          "name": "FLASH_OF_FROST",
	          "controlType": "SLOWDOWN",
	          "basicDamage": 80,
	          "upByLevel": 5,
	          "iterationNumber": 1,
	          "effectArea": "LINE",
	          "key": {
	            "name": "A"
	          },
	          "range": {
	            "shootingRange": 7,
	            "createDate": "2020-09-18T07:19:29Z"
	          }
	        },
	        {
	          "id": "2",
	          "name": "BLIZZARD",
	          "controlType": "SLOWDOWN",
	          "basicDamage": 250,
	          "upByLevel": 15,
	          "iterationNumber": 3,
	          "effectArea": "CIRCLE",
	          "key": {
	            "name": "Z"
	          },
	          "range": {
	            "shootingRange": 5,
	            "createDate": "2020-09-18T07:19:29Z"
	          }
	        }
	      ]
	    }
	  ],
	  "page": {
	    "size": 10,
	    "totalElements": 1,
	    "totalPages": 1,
	    "number": 0
	  }
	}
	
### Add a new Character : CASSIA
	
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
	
## Stopping up the API

	ps -ef | grep -i begin-with-springboot-jpa
	
	kill {pid}
	
## Stopping mysql database

	docker stop mysql23

	