## People Flows [/research-sys/api/v1/people-flows/]

### Get People Flows by Key [GET /research-sys/api/v1/people-flows/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","namespaceCode": "(val)","typeId": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All People Flows [GET /research-sys/api/v1/people-flows/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespaceCode": "(val)","typeId": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespaceCode": "(val)","typeId": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All People Flows with Filtering [GET /research-sys/api/v1/people-flows/]
    
+ Parameters

    + id (optional) - Generated ID for the people flow definition. Maximum length is 40.
    + name (optional) - Name for the people flow definition. Maximum length is 100.
    + namespaceCode (optional) - Code identifying the namespace. Maximum length is 20.
    + typeId (optional) - Type name for the people flow definition. Maximum length is 40.
    + description (optional) - Description for the people flow definition. Maximum length is 4000.
    + active (optional) - Active. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespaceCode": "(val)","typeId": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespaceCode": "(val)","typeId": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for People Flows [GET /research-sys/api/v1/people-flows/]
	                                          
+ Parameters

      + _schema (required) - will instruct the endpoint to return a schema data structure for the resource
      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"columns":["id","name","namespaceCode","typeId","description","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for People Flows [GET /research-sys/api/v1/people-flows/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="People Flows.md"
            transfer-encoding:chunked
### Update People Flows [PUT /research-sys/api/v1/people-flows/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","namespaceCode": "(val)","typeId": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple People Flows [PUT /research-sys/api/v1/people-flows/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespaceCode": "(val)","typeId": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespaceCode": "(val)","typeId": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert People Flows [POST /research-sys/api/v1/people-flows/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","namespaceCode": "(val)","typeId": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","namespaceCode": "(val)","typeId": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple People Flows [POST /research-sys/api/v1/people-flows/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespaceCode": "(val)","typeId": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespaceCode": "(val)","typeId": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","namespaceCode": "(val)","typeId": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespaceCode": "(val)","typeId": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete People Flows by Key [DELETE /research-sys/api/v1/people-flows/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All People Flows [DELETE /research-sys/api/v1/people-flows/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All People Flows with Matching [DELETE /research-sys/api/v1/people-flows/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Generated ID for the people flow definition. Maximum length is 40.
    + name (optional) - Name for the people flow definition. Maximum length is 100.
    + namespaceCode (optional) - Code identifying the namespace. Maximum length is 20.
    + typeId (optional) - Type name for the people flow definition. Maximum length is 40.
    + description (optional) - Description for the people flow definition. Maximum length is 4000.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
