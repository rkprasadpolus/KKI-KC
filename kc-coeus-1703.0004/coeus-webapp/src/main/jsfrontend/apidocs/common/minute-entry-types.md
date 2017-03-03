## Minute Entry Types [/research-common/api/v1/minute-entry-types/]

### Get Minute Entry Types by Key [GET /research-common/api/v1/minute-entry-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Minute Entry Types [GET /research-common/api/v1/minute-entry-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Minute Entry Types with Filtering [GET /research-common/api/v1/minute-entry-types/]
    
+ Parameters

    + minuteEntryTypeCode (optional) - Minute Entry Type Code. Maximum length is 3.
    + sortId (optional) - Sort Id. Maximum length is 3.
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
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Minute Entry Types [GET /research-common/api/v1/minute-entry-types/]
	                                          
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
    
            {"columns":["minuteEntryTypeCode","sortId","description"],"primaryKey":"minuteEntryTypeCode"}
		
### Get Blueprint API specification for Minute Entry Types [GET /research-common/api/v1/minute-entry-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Minute Entry Types.md"
            transfer-encoding:chunked
### Update Minute Entry Types [PUT /research-common/api/v1/minute-entry-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Minute Entry Types [PUT /research-common/api/v1/minute-entry-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Minute Entry Types [POST /research-common/api/v1/minute-entry-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Minute Entry Types [POST /research-common/api/v1/minute-entry-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Minute Entry Types by Key [DELETE /research-common/api/v1/minute-entry-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Minute Entry Types [DELETE /research-common/api/v1/minute-entry-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Minute Entry Types with Matching [DELETE /research-common/api/v1/minute-entry-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + minuteEntryTypeCode (optional) - Minute Entry Type Code. Maximum length is 3.
    + sortId (optional) - Sort Id. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
