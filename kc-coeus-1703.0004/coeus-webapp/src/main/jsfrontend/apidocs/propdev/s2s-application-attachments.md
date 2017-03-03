## S2s Application Attachments [/propdev/api/v1/s2s-application-attachments/]

### Get S2s Application Attachments by Key [GET /propdev/api/v1/s2s-application-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","contentId": "(val)","proposalNumber": "(val)","contentType": "(val)","hashCode": "(val)","_primaryKey": "(val)"}

### Get All S2s Application Attachments [GET /propdev/api/v1/s2s-application-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","contentId": "(val)","proposalNumber": "(val)","contentType": "(val)","hashCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contentId": "(val)","proposalNumber": "(val)","contentType": "(val)","hashCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All S2s Application Attachments with Filtering [GET /propdev/api/v1/s2s-application-attachments/]
    
+ Parameters

    + id (optional) - Id.
    + contentId (optional) - Attachments. Maximum length is 300.
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + contentType (optional) - Content Type. Maximum length is 30.
    + hashCode (optional) - Hash Code. Maximum length is 300.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","contentId": "(val)","proposalNumber": "(val)","contentType": "(val)","hashCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contentId": "(val)","proposalNumber": "(val)","contentType": "(val)","hashCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for S2s Application Attachments [GET /propdev/api/v1/s2s-application-attachments/]
	                                          
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
    
            {"columns":["id","contentId","proposalNumber","contentType","hashCode"],"primaryKey":"contentId:id:proposalNumber"}
		
### Get Blueprint API specification for S2s Application Attachments [GET /propdev/api/v1/s2s-application-attachments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s Application Attachments.md"
            transfer-encoding:chunked
### Update S2s Application Attachments [PUT /propdev/api/v1/s2s-application-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","contentId": "(val)","proposalNumber": "(val)","contentType": "(val)","hashCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s Application Attachments [PUT /propdev/api/v1/s2s-application-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","contentId": "(val)","proposalNumber": "(val)","contentType": "(val)","hashCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contentId": "(val)","proposalNumber": "(val)","contentType": "(val)","hashCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert S2s Application Attachments [POST /propdev/api/v1/s2s-application-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","contentId": "(val)","proposalNumber": "(val)","contentType": "(val)","hashCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","contentId": "(val)","proposalNumber": "(val)","contentType": "(val)","hashCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s Application Attachments [POST /propdev/api/v1/s2s-application-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","contentId": "(val)","proposalNumber": "(val)","contentType": "(val)","hashCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contentId": "(val)","proposalNumber": "(val)","contentType": "(val)","hashCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","contentId": "(val)","proposalNumber": "(val)","contentType": "(val)","hashCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","contentId": "(val)","proposalNumber": "(val)","contentType": "(val)","hashCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete S2s Application Attachments by Key [DELETE /propdev/api/v1/s2s-application-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Application Attachments [DELETE /propdev/api/v1/s2s-application-attachments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Application Attachments with Matching [DELETE /propdev/api/v1/s2s-application-attachments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Id.
    + contentId (optional) - Attachments. Maximum length is 300.
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + contentType (optional) - Content Type. Maximum length is 30.
    + hashCode (optional) - Hash Code. Maximum length is 300.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
