## Version Histories [/research-common/api/v1/version-histories/]

### Get Version Histories by Key [GET /research-common/api/v1/version-histories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"versionHistoryId": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}

### Get All Version Histories [GET /research-common/api/v1/version-histories/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"versionHistoryId": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"},
              {"versionHistoryId": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Version Histories with Filtering [GET /research-common/api/v1/version-histories/]
    
+ Parameters

    + versionHistoryId (optional) - 
    + sequenceOwnerClassName (optional) - 
    + sequenceOwnerVersionNameField (optional) - 
    + sequenceOwnerVersionNameValue (optional) - 
    + sequenceOwnerSequenceNumber (optional) - 
    + statusForOjb (optional) - 
    + userId (optional) - 
    + versionDate (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"versionHistoryId": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"},
              {"versionHistoryId": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Version Histories [GET /research-common/api/v1/version-histories/]
	                                          
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
    
            {"columns":["versionHistoryId","sequenceOwnerClassName","sequenceOwnerVersionNameField","sequenceOwnerVersionNameValue","sequenceOwnerSequenceNumber","statusForOjb","userId","versionDate"],"primaryKey":"versionHistoryId"}
		
### Get Blueprint API specification for Version Histories [GET /research-common/api/v1/version-histories/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Version Histories.md"
            transfer-encoding:chunked
### Update Version Histories [PUT /research-common/api/v1/version-histories/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"versionHistoryId": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Version Histories [PUT /research-common/api/v1/version-histories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"versionHistoryId": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"},
              {"versionHistoryId": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Version Histories [POST /research-common/api/v1/version-histories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"versionHistoryId": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"versionHistoryId": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Version Histories [POST /research-common/api/v1/version-histories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"versionHistoryId": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"},
              {"versionHistoryId": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"versionHistoryId": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"},
              {"versionHistoryId": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}
            ]
### Delete Version Histories by Key [DELETE /research-common/api/v1/version-histories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Version Histories [DELETE /research-common/api/v1/version-histories/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Version Histories with Matching [DELETE /research-common/api/v1/version-histories/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + versionHistoryId (optional) - 
    + sequenceOwnerClassName (optional) - 
    + sequenceOwnerVersionNameField (optional) - 
    + sequenceOwnerVersionNameValue (optional) - 
    + sequenceOwnerSequenceNumber (optional) - 
    + statusForOjb (optional) - 
    + userId (optional) - 
    + versionDate (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
