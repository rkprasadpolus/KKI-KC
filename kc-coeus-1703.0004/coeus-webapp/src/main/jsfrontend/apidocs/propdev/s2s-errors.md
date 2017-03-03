## S2s Errors [/propdev/api/v1/s2s-errors/]

### Get S2s Errors by Key [GET /propdev/api/v1/s2s-errors/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","key": "(val)","message": "(val)","link": "(val)","_primaryKey": "(val)"}

### Get All S2s Errors [GET /propdev/api/v1/s2s-errors/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","key": "(val)","message": "(val)","link": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","key": "(val)","message": "(val)","link": "(val)","_primaryKey": "(val)"}
            ]

### Get All S2s Errors with Filtering [GET /propdev/api/v1/s2s-errors/]
    
+ Parameters

    + id (optional) - S2s Error Id. Maximum length is 12.
    + key (optional) - Key. Maximum length is 200.
    + message (optional) - Message. Maximum length is 200.
    + link (optional) - Link. Maximum length is 200.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","key": "(val)","message": "(val)","link": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","key": "(val)","message": "(val)","link": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for S2s Errors [GET /propdev/api/v1/s2s-errors/]
	                                          
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
    
            {"columns":["id","key","message","link"],"primaryKey":"id"}
		
### Get Blueprint API specification for S2s Errors [GET /propdev/api/v1/s2s-errors/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s Errors.md"
            transfer-encoding:chunked
### Update S2s Errors [PUT /propdev/api/v1/s2s-errors/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","key": "(val)","message": "(val)","link": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s Errors [PUT /propdev/api/v1/s2s-errors/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","key": "(val)","message": "(val)","link": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","key": "(val)","message": "(val)","link": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert S2s Errors [POST /propdev/api/v1/s2s-errors/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","key": "(val)","message": "(val)","link": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","key": "(val)","message": "(val)","link": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s Errors [POST /propdev/api/v1/s2s-errors/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","key": "(val)","message": "(val)","link": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","key": "(val)","message": "(val)","link": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","key": "(val)","message": "(val)","link": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","key": "(val)","message": "(val)","link": "(val)","_primaryKey": "(val)"}
            ]
### Delete S2s Errors by Key [DELETE /propdev/api/v1/s2s-errors/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Errors [DELETE /propdev/api/v1/s2s-errors/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Errors with Matching [DELETE /propdev/api/v1/s2s-errors/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - S2s Error Id. Maximum length is 12.
    + key (optional) - Key. Maximum length is 200.
    + message (optional) - Message. Maximum length is 200.
    + link (optional) - Link. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
