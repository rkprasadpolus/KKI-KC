## Prop Science Keywords [/propdev/api/v1/prop-science-keywords/]

### Get Prop Science Keywords by Key [GET /propdev/api/v1/prop-science-keywords/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}

### Get All Prop Science Keywords [GET /propdev/api/v1/prop-science-keywords/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]

### Get All Prop Science Keywords with Filtering [GET /propdev/api/v1/prop-science-keywords/]
    
+ Parameters

    + hierarchyProposalNumber (optional) - Hierarchy Proposal Number.
    + hiddenInHierarchy (optional) - Hidden In Hierarchy.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Prop Science Keywords [GET /propdev/api/v1/prop-science-keywords/]
	                                          
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
    
            {"columns":["hierarchyProposalNumber","hiddenInHierarchy"],"primaryKey":"developmentProposal:scienceKeyword"}
		
### Get Blueprint API specification for Prop Science Keywords [GET /propdev/api/v1/prop-science-keywords/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Prop Science Keywords.md"
            transfer-encoding:chunked
### Update Prop Science Keywords [PUT /propdev/api/v1/prop-science-keywords/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Prop Science Keywords [PUT /propdev/api/v1/prop-science-keywords/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Prop Science Keywords [POST /propdev/api/v1/prop-science-keywords/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Prop Science Keywords [POST /propdev/api/v1/prop-science-keywords/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
### Delete Prop Science Keywords by Key [DELETE /propdev/api/v1/prop-science-keywords/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Prop Science Keywords [DELETE /propdev/api/v1/prop-science-keywords/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Prop Science Keywords with Matching [DELETE /propdev/api/v1/prop-science-keywords/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + hierarchyProposalNumber (optional) - Hierarchy Proposal Number.
    + hiddenInHierarchy (optional) - Hidden In Hierarchy.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
