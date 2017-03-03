## Ynq Explanations [/research-common/api/v1/ynq-explanations/]

### Get Ynq Explanations by Key [GET /research-common/api/v1/ynq-explanations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"explanationType": "(val)","questionId": "(val)","explanation": "(val)","_primaryKey": "(val)"}

### Get All Ynq Explanations [GET /research-common/api/v1/ynq-explanations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"explanationType": "(val)","questionId": "(val)","explanation": "(val)","_primaryKey": "(val)"},
              {"explanationType": "(val)","questionId": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            ]

### Get All Ynq Explanations with Filtering [GET /research-common/api/v1/ynq-explanations/]
    
+ Parameters

    + explanationType (optional) - Explanation Type. Maximum length is 1.
    + questionId (optional) - Question Id. Maximum length is 4.
    + explanation (optional) - Explanation. Maximum length is 400.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"explanationType": "(val)","questionId": "(val)","explanation": "(val)","_primaryKey": "(val)"},
              {"explanationType": "(val)","questionId": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Ynq Explanations [GET /research-common/api/v1/ynq-explanations/]
	                                          
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
    
            {"columns":["explanationType","questionId","explanation"],"primaryKey":"explanationType:questionId"}
		
### Get Blueprint API specification for Ynq Explanations [GET /research-common/api/v1/ynq-explanations/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Ynq Explanations.md"
            transfer-encoding:chunked
### Update Ynq Explanations [PUT /research-common/api/v1/ynq-explanations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"explanationType": "(val)","questionId": "(val)","explanation": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Ynq Explanations [PUT /research-common/api/v1/ynq-explanations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"explanationType": "(val)","questionId": "(val)","explanation": "(val)","_primaryKey": "(val)"},
              {"explanationType": "(val)","questionId": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Ynq Explanations [POST /research-common/api/v1/ynq-explanations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"explanationType": "(val)","questionId": "(val)","explanation": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"explanationType": "(val)","questionId": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Ynq Explanations [POST /research-common/api/v1/ynq-explanations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"explanationType": "(val)","questionId": "(val)","explanation": "(val)","_primaryKey": "(val)"},
              {"explanationType": "(val)","questionId": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"explanationType": "(val)","questionId": "(val)","explanation": "(val)","_primaryKey": "(val)"},
              {"explanationType": "(val)","questionId": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            ]
### Delete Ynq Explanations by Key [DELETE /research-common/api/v1/ynq-explanations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Ynq Explanations [DELETE /research-common/api/v1/ynq-explanations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Ynq Explanations with Matching [DELETE /research-common/api/v1/ynq-explanations/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + explanationType (optional) - Explanation Type. Maximum length is 1.
    + questionId (optional) - Question Id. Maximum length is 4.
    + explanation (optional) - Explanation. Maximum length is 400.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
