## Iacuc Protocol Submission Statuses [/iacuc/api/v1/iacuc-protocol-submission-statuses/]

### Get Iacuc Protocol Submission Statuses by Key [GET /iacuc/api/v1/iacuc-protocol-submission-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Submission Statuses [GET /iacuc/api/v1/iacuc-protocol-submission-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Submission Statuses with Filtering [GET /iacuc/api/v1/iacuc-protocol-submission-statuses/]
    
+ Parameters

    + protocolSubmissionStatusCode (optional) - IACUC Submission Status Code. Maximum length is 3.
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
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Submission Statuses [GET /iacuc/api/v1/iacuc-protocol-submission-statuses/]
	                                          
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
    
            {"columns":["protocolSubmissionStatusCode","description"],"primaryKey":"protocolSubmissionStatusCode"}
		
### Get Blueprint API specification for Iacuc Protocol Submission Statuses [GET /iacuc/api/v1/iacuc-protocol-submission-statuses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Submission Statuses.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Submission Statuses [PUT /iacuc/api/v1/iacuc-protocol-submission-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Submission Statuses [PUT /iacuc/api/v1/iacuc-protocol-submission-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Protocol Submission Statuses [POST /iacuc/api/v1/iacuc-protocol-submission-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Submission Statuses [POST /iacuc/api/v1/iacuc-protocol-submission-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Protocol Submission Statuses by Key [DELETE /iacuc/api/v1/iacuc-protocol-submission-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Submission Statuses [DELETE /iacuc/api/v1/iacuc-protocol-submission-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Submission Statuses with Matching [DELETE /iacuc/api/v1/iacuc-protocol-submission-statuses/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolSubmissionStatusCode (optional) - IACUC Submission Status Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
