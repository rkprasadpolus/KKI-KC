## Protocol Statuses [/irb/api/v1/protocol-statuses/]

### Get Protocol Statuses by Key [GET /irb/api/v1/protocol-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Protocol Statuses [GET /irb/api/v1/protocol-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Statuses with Filtering [GET /irb/api/v1/protocol-statuses/]
    
+ Parameters

    + protocolStatusCode (optional) - Protocol Status Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 300.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Statuses [GET /irb/api/v1/protocol-statuses/]
	                                          
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
    
            {"columns":["protocolStatusCode","description"],"primaryKey":"protocolStatusCode"}
		
### Get Blueprint API specification for Protocol Statuses [GET /irb/api/v1/protocol-statuses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Statuses.md"
            transfer-encoding:chunked
### Update Protocol Statuses [PUT /irb/api/v1/protocol-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Statuses [PUT /irb/api/v1/protocol-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Protocol Statuses [POST /irb/api/v1/protocol-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Statuses [POST /irb/api/v1/protocol-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Protocol Statuses by Key [DELETE /irb/api/v1/protocol-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Statuses [DELETE /irb/api/v1/protocol-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Statuses with Matching [DELETE /irb/api/v1/protocol-statuses/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolStatusCode (optional) - Protocol Status Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 300.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
