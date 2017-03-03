## Kim Document Role Members [/research-sys/api/v1/kim-document-role-members/]

### Get Kim Document Role Members by Key [GET /research-sys/api/v1/kim-document-role-members/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"roleMemberId": "(val)","roleId": "(val)","memberId": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Kim Document Role Members [GET /research-sys/api/v1/kim-document-role-members/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"roleMemberId": "(val)","roleId": "(val)","memberId": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleMemberId": "(val)","roleId": "(val)","memberId": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kim Document Role Members with Filtering [GET /research-sys/api/v1/kim-document-role-members/]
    
+ Parameters

    + roleMemberId (optional) - Role Member Identifier. Maximum length is 40.
    + roleId (optional) - Role. Maximum length is 40.
    + memberId (optional) - Member Identifier. Maximum length is 40.
    + memberTypeCode (optional) - Member Type Code. Maximum length is 40.
    + activeFromDate (optional) - Start Date. Maximum length is 21.
    + activeToDate (optional) - End Date. Maximum length is 21.
    + edit (optional) - Edit.
    + documentNumber (optional) - Document Number.
    + active (optional) - Active.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"roleMemberId": "(val)","roleId": "(val)","memberId": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleMemberId": "(val)","roleId": "(val)","memberId": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kim Document Role Members [GET /research-sys/api/v1/kim-document-role-members/]
	                                          
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
    
            {"columns":["roleMemberId","roleId","memberId","memberTypeCode","activeFromDate","activeToDate","edit","documentNumber","active"],"primaryKey":"roleMemberId"}
		
### Get Blueprint API specification for Kim Document Role Members [GET /research-sys/api/v1/kim-document-role-members/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kim Document Role Members.md"
            transfer-encoding:chunked
### Update Kim Document Role Members [PUT /research-sys/api/v1/kim-document-role-members/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleMemberId": "(val)","roleId": "(val)","memberId": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kim Document Role Members [PUT /research-sys/api/v1/kim-document-role-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"roleMemberId": "(val)","roleId": "(val)","memberId": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleMemberId": "(val)","roleId": "(val)","memberId": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Kim Document Role Members [POST /research-sys/api/v1/kim-document-role-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleMemberId": "(val)","roleId": "(val)","memberId": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"roleMemberId": "(val)","roleId": "(val)","memberId": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kim Document Role Members [POST /research-sys/api/v1/kim-document-role-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"roleMemberId": "(val)","roleId": "(val)","memberId": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleMemberId": "(val)","roleId": "(val)","memberId": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"roleMemberId": "(val)","roleId": "(val)","memberId": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleMemberId": "(val)","roleId": "(val)","memberId": "(val)","memberTypeCode": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Kim Document Role Members by Key [DELETE /research-sys/api/v1/kim-document-role-members/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kim Document Role Members [DELETE /research-sys/api/v1/kim-document-role-members/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kim Document Role Members with Matching [DELETE /research-sys/api/v1/kim-document-role-members/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + roleMemberId (optional) - Role Member Identifier. Maximum length is 40.
    + roleId (optional) - Role. Maximum length is 40.
    + memberId (optional) - Member Identifier. Maximum length is 40.
    + memberTypeCode (optional) - Member Type Code. Maximum length is 40.
    + activeFromDate (optional) - Start Date. Maximum length is 21.
    + activeToDate (optional) - End Date. Maximum length is 21.
    + edit (optional) - Edit.
    + documentNumber (optional) - Document Number.
    + active (optional) - Active.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
