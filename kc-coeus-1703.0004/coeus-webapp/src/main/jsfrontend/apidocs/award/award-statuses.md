## Award Statuses [/award/api/v1/award-statuses/]

### Get Award Statuses by Key [GET /award/api/v1/award-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Award Statuses [GET /award/api/v1/award-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Statuses with Filtering [GET /award/api/v1/award-statuses/]
    
+ Parameters

    + statusCode (optional) - Status Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Statuses [GET /award/api/v1/award-statuses/]
	                                          
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
    
            {"columns":["statusCode","description"],"primaryKey":"statusCode"}
		
### Get Blueprint API specification for Award Statuses [GET /award/api/v1/award-statuses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Statuses.md"
            transfer-encoding:chunked
### Update Award Statuses [PUT /award/api/v1/award-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Statuses [PUT /award/api/v1/award-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Award Statuses [POST /award/api/v1/award-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Statuses [POST /award/api/v1/award-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Award Statuses by Key [DELETE /award/api/v1/award-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Statuses [DELETE /award/api/v1/award-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Statuses with Matching [DELETE /award/api/v1/award-statuses/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + statusCode (optional) - Status Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
