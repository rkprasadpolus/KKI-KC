## States [/research-sys/api/v1/states/]

### Get States by Key [GET /research-sys/api/v1/states/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","countryCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All States [GET /research-sys/api/v1/states/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","countryCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","countryCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All States with Filtering [GET /research-sys/api/v1/states/]
    
+ Parameters

    + code (optional) - The two digit code for a state. Maximum length is 2.
    + countryCode (optional) - The code uniquely identify a country. Maximum length is 2.
    + name (optional) - The name assigned to this state. Maximum length is 40.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","countryCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","countryCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for States [GET /research-sys/api/v1/states/]
	                                          
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
    
            {"columns":["code","countryCode","name","active"],"primaryKey":"code:countryCode"}
		
### Get Blueprint API specification for States [GET /research-sys/api/v1/states/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="States.md"
            transfer-encoding:chunked
### Update States [PUT /research-sys/api/v1/states/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","countryCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple States [PUT /research-sys/api/v1/states/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","countryCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","countryCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert States [POST /research-sys/api/v1/states/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","countryCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","countryCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple States [POST /research-sys/api/v1/states/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","countryCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","countryCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","countryCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","countryCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete States by Key [DELETE /research-sys/api/v1/states/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All States [DELETE /research-sys/api/v1/states/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All States with Matching [DELETE /research-sys/api/v1/states/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - The two digit code for a state. Maximum length is 2.
    + countryCode (optional) - The code uniquely identify a country. Maximum length is 2.
    + name (optional) - The name assigned to this state. Maximum length is 40.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
