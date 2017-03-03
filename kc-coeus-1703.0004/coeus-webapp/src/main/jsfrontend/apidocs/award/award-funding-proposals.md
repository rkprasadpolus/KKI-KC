## Award Funding Proposals [/award/api/v1/award-funding-proposals/]

### Get Award Funding Proposals by Key [GET /award/api/v1/award-funding-proposals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Award Funding Proposals [GET /award/api/v1/award-funding-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Funding Proposals with Filtering [GET /award/api/v1/award-funding-proposals/]
    
+ Parameters

    + awardFundingProposalId (optional) - Award Funding Proposal Id. Maximum length is 22.
    + awardId (optional) - Award ID. Maximum length is 22.
    + proposalId (optional) - Proposal ID. Maximum length is 22.
    + active (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Funding Proposals [GET /award/api/v1/award-funding-proposals/]
	                                          
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
    
            {"columns":["awardFundingProposalId","awardId","proposalId","active"],"primaryKey":"awardFundingProposalId"}
		
### Get Blueprint API specification for Award Funding Proposals [GET /award/api/v1/award-funding-proposals/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Funding Proposals.md"
            transfer-encoding:chunked
### Update Award Funding Proposals [PUT /award/api/v1/award-funding-proposals/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Funding Proposals [PUT /award/api/v1/award-funding-proposals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Award Funding Proposals [POST /award/api/v1/award-funding-proposals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Funding Proposals [POST /award/api/v1/award-funding-proposals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Award Funding Proposals by Key [DELETE /award/api/v1/award-funding-proposals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Funding Proposals [DELETE /award/api/v1/award-funding-proposals/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Funding Proposals with Matching [DELETE /award/api/v1/award-funding-proposals/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardFundingProposalId (optional) - Award Funding Proposal Id. Maximum length is 22.
    + awardId (optional) - Award ID. Maximum length is 22.
    + proposalId (optional) - Proposal ID. Maximum length is 22.
    + active (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
