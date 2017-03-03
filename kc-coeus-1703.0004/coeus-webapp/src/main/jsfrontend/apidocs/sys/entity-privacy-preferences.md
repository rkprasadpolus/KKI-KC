## Entity Privacy Preferences [/research-sys/api/v1/entity-privacy-preferences/]

### Get Entity Privacy Preferences by Key [GET /research-sys/api/v1/entity-privacy-preferences/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"entityId": "(val)","suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","_primaryKey": "(val)"}

### Get All Entity Privacy Preferences [GET /research-sys/api/v1/entity-privacy-preferences/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"entityId": "(val)","suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","_primaryKey": "(val)"},
              {"entityId": "(val)","suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Privacy Preferences with Filtering [GET /research-sys/api/v1/entity-privacy-preferences/]
    
+ Parameters

    + entityId (optional) - 
    + suppressName (optional) - 
    + suppressEmail (optional) - 
    + suppressAddress (optional) - 
    + suppressPhone (optional) - 
    + suppressPersonal (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"entityId": "(val)","suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","_primaryKey": "(val)"},
              {"entityId": "(val)","suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Privacy Preferences [GET /research-sys/api/v1/entity-privacy-preferences/]
	                                          
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
    
            {"columns":["entityId","suppressName","suppressEmail","suppressAddress","suppressPhone","suppressPersonal"],"primaryKey":"entityId"}
		
### Get Blueprint API specification for Entity Privacy Preferences [GET /research-sys/api/v1/entity-privacy-preferences/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Privacy Preferences.md"
            transfer-encoding:chunked
### Update Entity Privacy Preferences [PUT /research-sys/api/v1/entity-privacy-preferences/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityId": "(val)","suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Privacy Preferences [PUT /research-sys/api/v1/entity-privacy-preferences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityId": "(val)","suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","_primaryKey": "(val)"},
              {"entityId": "(val)","suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Entity Privacy Preferences [POST /research-sys/api/v1/entity-privacy-preferences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityId": "(val)","suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"entityId": "(val)","suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Privacy Preferences [POST /research-sys/api/v1/entity-privacy-preferences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityId": "(val)","suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","_primaryKey": "(val)"},
              {"entityId": "(val)","suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"entityId": "(val)","suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","_primaryKey": "(val)"},
              {"entityId": "(val)","suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","_primaryKey": "(val)"}
            ]
### Delete Entity Privacy Preferences by Key [DELETE /research-sys/api/v1/entity-privacy-preferences/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Privacy Preferences [DELETE /research-sys/api/v1/entity-privacy-preferences/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Privacy Preferences with Matching [DELETE /research-sys/api/v1/entity-privacy-preferences/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + entityId (optional) - 
    + suppressName (optional) - 
    + suppressEmail (optional) - 
    + suppressAddress (optional) - 
    + suppressPhone (optional) - 
    + suppressPersonal (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
