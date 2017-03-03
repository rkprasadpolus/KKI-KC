## Award Approved Sub Awards [/award/api/v1/award-approved-sub-awards/]

### Get Award Approved Sub Awards by Key [GET /award/api/v1/award-approved-sub-awards/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardApprovedSubawardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"}

### Get All Award Approved Sub Awards [GET /award/api/v1/award-approved-sub-awards/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardApprovedSubawardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"},
              {"awardApprovedSubawardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Approved Sub Awards with Filtering [GET /award/api/v1/award-approved-sub-awards/]
    
+ Parameters

    + awardApprovedSubawardId (optional) - Award Approved Subaward ID. Maximum length is 8.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + organizationName (optional) - Organization Name. Maximum length is 60.
    + organizationId (optional) - Organization ID. Maximum length is 8.
    + amount (optional) - Amount. Maximum length is 12.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardApprovedSubawardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"},
              {"awardApprovedSubawardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Approved Sub Awards [GET /award/api/v1/award-approved-sub-awards/]
	                                          
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
    
            {"columns":["awardApprovedSubawardId","awardNumber","sequenceNumber","organizationName","organizationId","amount"],"primaryKey":"awardApprovedSubawardId"}
		
### Get Blueprint API specification for Award Approved Sub Awards [GET /award/api/v1/award-approved-sub-awards/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Approved Sub Awards.md"
            transfer-encoding:chunked
