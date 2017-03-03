## Document Accesses [/research-common/api/v1/document-accesses/]

### Get Document Accesses by Key [GET /research-common/api/v1/document-accesses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}

### Get All Document Accesses [GET /research-common/api/v1/document-accesses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Document Accesses with Filtering [GET /research-common/api/v1/document-accesses/]
    
+ Parameters

    + id (optional) - Document Access Id. Maximum length is 12.
    + documentNumber (optional) - The document id is generated by the workflow environment and is unique to each installation of Kuali... Maximum length is 14.
    + principalId (optional) - Principal ID. Maximum length is 40.
    + roleName (optional) - Role Name. Maximum length is 80.
    + namespaceCode (optional) - Code identifying the role namespace. Maximum length is 20.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Document Accesses [GET /research-common/api/v1/document-accesses/]
	                                          
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
    
            {"columns":["id","documentNumber","principalId","roleName","namespaceCode"],"primaryKey":"id"}
		
### Get Blueprint API specification for Document Accesses [GET /research-common/api/v1/document-accesses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Document Accesses.md"
            transfer-encoding:chunked
### Update Document Accesses [PUT /research-common/api/v1/document-accesses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Document Accesses [PUT /research-common/api/v1/document-accesses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Document Accesses [POST /research-common/api/v1/document-accesses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Document Accesses [POST /research-common/api/v1/document-accesses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","principalId": "(val)","roleName": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Document Accesses by Key [DELETE /research-common/api/v1/document-accesses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Accesses [DELETE /research-common/api/v1/document-accesses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Accesses with Matching [DELETE /research-common/api/v1/document-accesses/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Document Access Id. Maximum length is 12.
    + documentNumber (optional) - The document id is generated by the workflow environment and is unique to each installation of Kuali... Maximum length is 14.
    + principalId (optional) - Principal ID. Maximum length is 40.
    + roleName (optional) - Role Name. Maximum length is 80.
    + namespaceCode (optional) - Code identifying the role namespace. Maximum length is 20.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204