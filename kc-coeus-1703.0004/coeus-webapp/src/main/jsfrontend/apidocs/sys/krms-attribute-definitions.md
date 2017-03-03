## Krms Attribute Definitions [/research-sys/api/v1/krms-attribute-definitions/]

### Get Krms Attribute Definitions by Key [GET /research-sys/api/v1/krms-attribute-definitions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","description": "(val)","active": "(val)","componentName": "(val)","_primaryKey": "(val)"}

### Get All Krms Attribute Definitions [GET /research-sys/api/v1/krms-attribute-definitions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","description": "(val)","active": "(val)","componentName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","description": "(val)","active": "(val)","componentName": "(val)","_primaryKey": "(val)"}
            ]

### Get All Krms Attribute Definitions with Filtering [GET /research-sys/api/v1/krms-attribute-definitions/]
    
+ Parameters

    + id (optional) - Attribute Id.
    + name (optional) - Attribute Name.
    + namespace (optional) - Attribute Namespace.
    + label (optional) - Attribute Label.
    + description (optional) - Description.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.
    + componentName (optional) - Attribute Component Name.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","description": "(val)","active": "(val)","componentName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","description": "(val)","active": "(val)","componentName": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Krms Attribute Definitions [GET /research-sys/api/v1/krms-attribute-definitions/]
	                                          
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
    
            {"columns":["id","name","namespace","label","description","active","componentName"],"primaryKey":"id"}
		
### Get Blueprint API specification for Krms Attribute Definitions [GET /research-sys/api/v1/krms-attribute-definitions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Krms Attribute Definitions.md"
            transfer-encoding:chunked
### Update Krms Attribute Definitions [PUT /research-sys/api/v1/krms-attribute-definitions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","description": "(val)","active": "(val)","componentName": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Krms Attribute Definitions [PUT /research-sys/api/v1/krms-attribute-definitions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","description": "(val)","active": "(val)","componentName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","description": "(val)","active": "(val)","componentName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Krms Attribute Definitions [POST /research-sys/api/v1/krms-attribute-definitions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","description": "(val)","active": "(val)","componentName": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","description": "(val)","active": "(val)","componentName": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Krms Attribute Definitions [POST /research-sys/api/v1/krms-attribute-definitions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","description": "(val)","active": "(val)","componentName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","description": "(val)","active": "(val)","componentName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","description": "(val)","active": "(val)","componentName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","description": "(val)","active": "(val)","componentName": "(val)","_primaryKey": "(val)"}
            ]
### Delete Krms Attribute Definitions by Key [DELETE /research-sys/api/v1/krms-attribute-definitions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Krms Attribute Definitions [DELETE /research-sys/api/v1/krms-attribute-definitions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Krms Attribute Definitions with Matching [DELETE /research-sys/api/v1/krms-attribute-definitions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Attribute Id.
    + name (optional) - Attribute Name.
    + namespace (optional) - Attribute Namespace.
    + label (optional) - Attribute Label.
    + description (optional) - Description.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.
    + componentName (optional) - Attribute Component Name.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
