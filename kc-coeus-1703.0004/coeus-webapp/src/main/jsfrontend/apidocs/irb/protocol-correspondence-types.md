## Protocol Correspondence Types [/irb/api/v1/protocol-correspondence-types/]

### Get Protocol Correspondence Types by Key [GET /irb/api/v1/protocol-correspondence-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}

### Get All Protocol Correspondence Types [GET /irb/api/v1/protocol-correspondence-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"},
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Correspondence Types with Filtering [GET /irb/api/v1/protocol-correspondence-types/]
    
+ Parameters

    + protoCorrespTypeCode (optional) - Proto Corresp Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + moduleId (optional) - Module Id. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"},
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Correspondence Types [GET /irb/api/v1/protocol-correspondence-types/]
	                                          
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
    
            {"columns":["protoCorrespTypeCode","description","moduleId"],"primaryKey":"protoCorrespTypeCode"}
		
### Get Blueprint API specification for Protocol Correspondence Types [GET /irb/api/v1/protocol-correspondence-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Correspondence Types.md"
            transfer-encoding:chunked
### Update Protocol Correspondence Types [PUT /irb/api/v1/protocol-correspondence-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Correspondence Types [PUT /irb/api/v1/protocol-correspondence-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"},
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Protocol Correspondence Types [POST /irb/api/v1/protocol-correspondence-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Correspondence Types [POST /irb/api/v1/protocol-correspondence-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"},
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"},
              {"protoCorrespTypeCode": "(val)","description": "(val)","moduleId": "(val)","_primaryKey": "(val)"}
            ]
### Delete Protocol Correspondence Types by Key [DELETE /irb/api/v1/protocol-correspondence-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Correspondence Types [DELETE /irb/api/v1/protocol-correspondence-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Correspondence Types with Matching [DELETE /irb/api/v1/protocol-correspondence-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protoCorrespTypeCode (optional) - Proto Corresp Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + moduleId (optional) - Module Id. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
