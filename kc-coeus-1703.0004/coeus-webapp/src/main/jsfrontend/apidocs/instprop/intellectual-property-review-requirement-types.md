## Intellectual Property Review Requirement Types [/instprop/api/v1/intellectual-property-review-requirement-types/]

### Get Intellectual Property Review Requirement Types by Key [GET /instprop/api/v1/intellectual-property-review-requirement-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"intellectualPropertyReviewRequirementTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Intellectual Property Review Requirement Types [GET /instprop/api/v1/intellectual-property-review-requirement-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"intellectualPropertyReviewRequirementTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"intellectualPropertyReviewRequirementTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Intellectual Property Review Requirement Types with Filtering [GET /instprop/api/v1/intellectual-property-review-requirement-types/]
    
+ Parameters

    + intellectualPropertyReviewRequirementTypeCode (optional) - Intellectual Property Review Requirement Type Code. Maximum length is 3.
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
              {"intellectualPropertyReviewRequirementTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"intellectualPropertyReviewRequirementTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Intellectual Property Review Requirement Types [GET /instprop/api/v1/intellectual-property-review-requirement-types/]
	                                          
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
    
            {"columns":["intellectualPropertyReviewRequirementTypeCode","description"],"primaryKey":"intellectualPropertyReviewRequirementTypeCode"}
		
### Get Blueprint API specification for Intellectual Property Review Requirement Types [GET /instprop/api/v1/intellectual-property-review-requirement-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Intellectual Property Review Requirement Types.md"
            transfer-encoding:chunked
### Update Intellectual Property Review Requirement Types [PUT /instprop/api/v1/intellectual-property-review-requirement-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"intellectualPropertyReviewRequirementTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Intellectual Property Review Requirement Types [PUT /instprop/api/v1/intellectual-property-review-requirement-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"intellectualPropertyReviewRequirementTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"intellectualPropertyReviewRequirementTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Intellectual Property Review Requirement Types [POST /instprop/api/v1/intellectual-property-review-requirement-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"intellectualPropertyReviewRequirementTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"intellectualPropertyReviewRequirementTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Intellectual Property Review Requirement Types [POST /instprop/api/v1/intellectual-property-review-requirement-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"intellectualPropertyReviewRequirementTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"intellectualPropertyReviewRequirementTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"intellectualPropertyReviewRequirementTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"intellectualPropertyReviewRequirementTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Intellectual Property Review Requirement Types by Key [DELETE /instprop/api/v1/intellectual-property-review-requirement-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Intellectual Property Review Requirement Types [DELETE /instprop/api/v1/intellectual-property-review-requirement-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Intellectual Property Review Requirement Types with Matching [DELETE /instprop/api/v1/intellectual-property-review-requirement-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + intellectualPropertyReviewRequirementTypeCode (optional) - Intellectual Property Review Requirement Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
