## Protocol Attachment Statuses [/irb/api/v1/protocol-attachment-statuses/]

### Get Protocol Attachment Statuses by Key [GET /irb/api/v1/protocol-attachment-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Protocol Attachment Statuses [GET /irb/api/v1/protocol-attachment-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Attachment Statuses with Filtering [GET /irb/api/v1/protocol-attachment-statuses/]
    
+ Parameters

    + code (optional) - Code. Maximum length is 3.
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
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Attachment Statuses [GET /irb/api/v1/protocol-attachment-statuses/]
	                                          
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
    
            {"columns":["code","description"],"primaryKey":"code"}
		
### Get Blueprint API specification for Protocol Attachment Statuses [GET /irb/api/v1/protocol-attachment-statuses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Attachment Statuses.md"
            transfer-encoding:chunked
### Update Protocol Attachment Statuses [PUT /irb/api/v1/protocol-attachment-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Attachment Statuses [PUT /irb/api/v1/protocol-attachment-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Protocol Attachment Statuses [POST /irb/api/v1/protocol-attachment-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Attachment Statuses [POST /irb/api/v1/protocol-attachment-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Protocol Attachment Statuses by Key [DELETE /irb/api/v1/protocol-attachment-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Attachment Statuses [DELETE /irb/api/v1/protocol-attachment-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Attachment Statuses with Matching [DELETE /irb/api/v1/protocol-attachment-statuses/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 300.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
