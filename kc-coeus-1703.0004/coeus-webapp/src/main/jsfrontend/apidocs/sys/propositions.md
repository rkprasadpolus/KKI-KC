## Propositions [/research-sys/api/v1/propositions/]

### Get Propositions by Key [GET /research-sys/api/v1/propositions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","description": "(val)","ruleId": "(val)","typeId": "(val)","propositionTypeCode": "(val)","compoundOpCode": "(val)","compoundSequenceNumber": "(val)","_primaryKey": "(val)"}

### Get All Propositions [GET /research-sys/api/v1/propositions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","description": "(val)","ruleId": "(val)","typeId": "(val)","propositionTypeCode": "(val)","compoundOpCode": "(val)","compoundSequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","description": "(val)","ruleId": "(val)","typeId": "(val)","propositionTypeCode": "(val)","compoundOpCode": "(val)","compoundSequenceNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Propositions with Filtering [GET /research-sys/api/v1/propositions/]
    
+ Parameters

    + id (optional) - Proposition Id.
    + description (optional) - Description.
    + ruleId (optional) - Rule Id.
    + typeId (optional) - Proposition Type Id.
    + propositionTypeCode (optional) - Proposition Type Code.
    + compoundOpCode (optional) - Compound Op Code.
    + compoundSequenceNumber (optional) - Compound Sequence Number.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","description": "(val)","ruleId": "(val)","typeId": "(val)","propositionTypeCode": "(val)","compoundOpCode": "(val)","compoundSequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","description": "(val)","ruleId": "(val)","typeId": "(val)","propositionTypeCode": "(val)","compoundOpCode": "(val)","compoundSequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Propositions [GET /research-sys/api/v1/propositions/]
	                                          
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
    
            {"columns":["id","description","ruleId","typeId","propositionTypeCode","compoundOpCode","compoundSequenceNumber"],"primaryKey":"id"}
		
### Get Blueprint API specification for Propositions [GET /research-sys/api/v1/propositions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Propositions.md"
            transfer-encoding:chunked
### Update Propositions [PUT /research-sys/api/v1/propositions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","description": "(val)","ruleId": "(val)","typeId": "(val)","propositionTypeCode": "(val)","compoundOpCode": "(val)","compoundSequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Propositions [PUT /research-sys/api/v1/propositions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","description": "(val)","ruleId": "(val)","typeId": "(val)","propositionTypeCode": "(val)","compoundOpCode": "(val)","compoundSequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","description": "(val)","ruleId": "(val)","typeId": "(val)","propositionTypeCode": "(val)","compoundOpCode": "(val)","compoundSequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Propositions [POST /research-sys/api/v1/propositions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","description": "(val)","ruleId": "(val)","typeId": "(val)","propositionTypeCode": "(val)","compoundOpCode": "(val)","compoundSequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","description": "(val)","ruleId": "(val)","typeId": "(val)","propositionTypeCode": "(val)","compoundOpCode": "(val)","compoundSequenceNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Propositions [POST /research-sys/api/v1/propositions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","description": "(val)","ruleId": "(val)","typeId": "(val)","propositionTypeCode": "(val)","compoundOpCode": "(val)","compoundSequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","description": "(val)","ruleId": "(val)","typeId": "(val)","propositionTypeCode": "(val)","compoundOpCode": "(val)","compoundSequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","description": "(val)","ruleId": "(val)","typeId": "(val)","propositionTypeCode": "(val)","compoundOpCode": "(val)","compoundSequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","description": "(val)","ruleId": "(val)","typeId": "(val)","propositionTypeCode": "(val)","compoundOpCode": "(val)","compoundSequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
### Delete Propositions by Key [DELETE /research-sys/api/v1/propositions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Propositions [DELETE /research-sys/api/v1/propositions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Propositions with Matching [DELETE /research-sys/api/v1/propositions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Proposition Id.
    + description (optional) - Description.
    + ruleId (optional) - Rule Id.
    + typeId (optional) - Proposition Type Id.
    + propositionTypeCode (optional) - Proposition Type Code.
    + compoundOpCode (optional) - Compound Op Code.
    + compoundSequenceNumber (optional) - Compound Sequence Number.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
