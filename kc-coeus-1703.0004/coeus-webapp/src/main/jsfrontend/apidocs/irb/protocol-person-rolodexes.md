## Protocol Person Rolodexes [/irb/api/v1/protocol-person-rolodexes/]

### Get Protocol Person Rolodexes by Key [GET /irb/api/v1/protocol-person-rolodexes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","createUser": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","_primaryKey": "(val)"}

### Get All Protocol Person Rolodexes [GET /irb/api/v1/protocol-person-rolodexes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","createUser": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","_primaryKey": "(val)"},
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","createUser": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Person Rolodexes with Filtering [GET /irb/api/v1/protocol-person-rolodexes/]
    
+ Parameters

    + rolodexId (optional) - 
    + addressLine1 (optional) - 
    + addressLine2 (optional) - 
    + addressLine3 (optional) - 
    + city (optional) - 
    + comments (optional) - 
    + countryCode (optional) - 
    + county (optional) - 
    + createUser (optional) - 
    + deleteFlag (optional) - 
    + emailAddress (optional) - 
    + faxNumber (optional) - 
    + firstName (optional) - 
    + lastName (optional) - 
    + middleName (optional) - 
    + organization (optional) - 
    + ownedByUnit (optional) - 
    + phoneNumber (optional) - 
    + postalCode (optional) - 
    + prefix (optional) - 
    + sponsorAddressFlag (optional) - 
    + sponsorCode (optional) - 
    + state (optional) - 
    + suffix (optional) - 
    + title (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","createUser": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","_primaryKey": "(val)"},
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","createUser": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Person Rolodexes [GET /irb/api/v1/protocol-person-rolodexes/]
	                                          
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
    
            {"columns":["rolodexId","addressLine1","addressLine2","addressLine3","city","comments","countryCode","county","createUser","deleteFlag","emailAddress","faxNumber","firstName","lastName","middleName","organization","ownedByUnit","phoneNumber","postalCode","prefix","sponsorAddressFlag","sponsorCode","state","suffix","title"],"primaryKey":"rolodexId"}
		
### Get Blueprint API specification for Protocol Person Rolodexes [GET /irb/api/v1/protocol-person-rolodexes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Person Rolodexes.md"
            transfer-encoding:chunked
### Update Protocol Person Rolodexes [PUT /irb/api/v1/protocol-person-rolodexes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","createUser": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Person Rolodexes [PUT /irb/api/v1/protocol-person-rolodexes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","createUser": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","_primaryKey": "(val)"},
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","createUser": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Protocol Person Rolodexes [POST /irb/api/v1/protocol-person-rolodexes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","createUser": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","createUser": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Person Rolodexes [POST /irb/api/v1/protocol-person-rolodexes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","createUser": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","_primaryKey": "(val)"},
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","createUser": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","createUser": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","_primaryKey": "(val)"},
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","createUser": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","_primaryKey": "(val)"}
            ]
### Delete Protocol Person Rolodexes by Key [DELETE /irb/api/v1/protocol-person-rolodexes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Person Rolodexes [DELETE /irb/api/v1/protocol-person-rolodexes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Person Rolodexes with Matching [DELETE /irb/api/v1/protocol-person-rolodexes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + rolodexId (optional) - 
    + addressLine1 (optional) - 
    + addressLine2 (optional) - 
    + addressLine3 (optional) - 
    + city (optional) - 
    + comments (optional) - 
    + countryCode (optional) - 
    + county (optional) - 
    + createUser (optional) - 
    + deleteFlag (optional) - 
    + emailAddress (optional) - 
    + faxNumber (optional) - 
    + firstName (optional) - 
    + lastName (optional) - 
    + middleName (optional) - 
    + organization (optional) - 
    + ownedByUnit (optional) - 
    + phoneNumber (optional) - 
    + postalCode (optional) - 
    + prefix (optional) - 
    + sponsorAddressFlag (optional) - 
    + sponsorCode (optional) - 
    + state (optional) - 
    + suffix (optional) - 
    + title (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
