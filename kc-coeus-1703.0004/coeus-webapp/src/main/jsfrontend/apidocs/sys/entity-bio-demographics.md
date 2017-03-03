## Entity Bio Demographics [/research-sys/api/v1/entity-bio-demographics/]

### Get Entity Bio Demographics by Key [GET /research-sys/api/v1/entity-bio-demographics/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"entityId": "(val)","birthDateValue": "(val)","genderCode": "(val)","genderChangeCode": "(val)","deceasedDateValue": "(val)","maritalStatusCode": "(val)","primaryLanguageCode": "(val)","secondaryLanguageCode": "(val)","birthCountry": "(val)","birthStateProvinceCode": "(val)","birthCity": "(val)","geographicOrigin": "(val)","noteMessage": "(val)","_primaryKey": "(val)"}

### Get All Entity Bio Demographics [GET /research-sys/api/v1/entity-bio-demographics/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"entityId": "(val)","birthDateValue": "(val)","genderCode": "(val)","genderChangeCode": "(val)","deceasedDateValue": "(val)","maritalStatusCode": "(val)","primaryLanguageCode": "(val)","secondaryLanguageCode": "(val)","birthCountry": "(val)","birthStateProvinceCode": "(val)","birthCity": "(val)","geographicOrigin": "(val)","noteMessage": "(val)","_primaryKey": "(val)"},
              {"entityId": "(val)","birthDateValue": "(val)","genderCode": "(val)","genderChangeCode": "(val)","deceasedDateValue": "(val)","maritalStatusCode": "(val)","primaryLanguageCode": "(val)","secondaryLanguageCode": "(val)","birthCountry": "(val)","birthStateProvinceCode": "(val)","birthCity": "(val)","geographicOrigin": "(val)","noteMessage": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Bio Demographics with Filtering [GET /research-sys/api/v1/entity-bio-demographics/]
    
+ Parameters

    + entityId (optional) - 
    + birthDateValue (optional) - 
    + genderCode (optional) - 
    + genderChangeCode (optional) - 
    + deceasedDateValue (optional) - 
    + maritalStatusCode (optional) - 
    + primaryLanguageCode (optional) - 
    + secondaryLanguageCode (optional) - 
    + birthCountry (optional) - 
    + birthStateProvinceCode (optional) - 
    + birthCity (optional) - 
    + geographicOrigin (optional) - 
    + noteMessage (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"entityId": "(val)","birthDateValue": "(val)","genderCode": "(val)","genderChangeCode": "(val)","deceasedDateValue": "(val)","maritalStatusCode": "(val)","primaryLanguageCode": "(val)","secondaryLanguageCode": "(val)","birthCountry": "(val)","birthStateProvinceCode": "(val)","birthCity": "(val)","geographicOrigin": "(val)","noteMessage": "(val)","_primaryKey": "(val)"},
              {"entityId": "(val)","birthDateValue": "(val)","genderCode": "(val)","genderChangeCode": "(val)","deceasedDateValue": "(val)","maritalStatusCode": "(val)","primaryLanguageCode": "(val)","secondaryLanguageCode": "(val)","birthCountry": "(val)","birthStateProvinceCode": "(val)","birthCity": "(val)","geographicOrigin": "(val)","noteMessage": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Bio Demographics [GET /research-sys/api/v1/entity-bio-demographics/]
	                                          
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
    
            {"columns":["entityId","birthDateValue","genderCode","genderChangeCode","deceasedDateValue","maritalStatusCode","primaryLanguageCode","secondaryLanguageCode","birthCountry","birthStateProvinceCode","birthCity","geographicOrigin","noteMessage"],"primaryKey":"entityId"}
		
### Get Blueprint API specification for Entity Bio Demographics [GET /research-sys/api/v1/entity-bio-demographics/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Bio Demographics.md"
            transfer-encoding:chunked
### Update Entity Bio Demographics [PUT /research-sys/api/v1/entity-bio-demographics/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityId": "(val)","birthDateValue": "(val)","genderCode": "(val)","genderChangeCode": "(val)","deceasedDateValue": "(val)","maritalStatusCode": "(val)","primaryLanguageCode": "(val)","secondaryLanguageCode": "(val)","birthCountry": "(val)","birthStateProvinceCode": "(val)","birthCity": "(val)","geographicOrigin": "(val)","noteMessage": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Bio Demographics [PUT /research-sys/api/v1/entity-bio-demographics/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityId": "(val)","birthDateValue": "(val)","genderCode": "(val)","genderChangeCode": "(val)","deceasedDateValue": "(val)","maritalStatusCode": "(val)","primaryLanguageCode": "(val)","secondaryLanguageCode": "(val)","birthCountry": "(val)","birthStateProvinceCode": "(val)","birthCity": "(val)","geographicOrigin": "(val)","noteMessage": "(val)","_primaryKey": "(val)"},
              {"entityId": "(val)","birthDateValue": "(val)","genderCode": "(val)","genderChangeCode": "(val)","deceasedDateValue": "(val)","maritalStatusCode": "(val)","primaryLanguageCode": "(val)","secondaryLanguageCode": "(val)","birthCountry": "(val)","birthStateProvinceCode": "(val)","birthCity": "(val)","geographicOrigin": "(val)","noteMessage": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Entity Bio Demographics [POST /research-sys/api/v1/entity-bio-demographics/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityId": "(val)","birthDateValue": "(val)","genderCode": "(val)","genderChangeCode": "(val)","deceasedDateValue": "(val)","maritalStatusCode": "(val)","primaryLanguageCode": "(val)","secondaryLanguageCode": "(val)","birthCountry": "(val)","birthStateProvinceCode": "(val)","birthCity": "(val)","geographicOrigin": "(val)","noteMessage": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"entityId": "(val)","birthDateValue": "(val)","genderCode": "(val)","genderChangeCode": "(val)","deceasedDateValue": "(val)","maritalStatusCode": "(val)","primaryLanguageCode": "(val)","secondaryLanguageCode": "(val)","birthCountry": "(val)","birthStateProvinceCode": "(val)","birthCity": "(val)","geographicOrigin": "(val)","noteMessage": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Bio Demographics [POST /research-sys/api/v1/entity-bio-demographics/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityId": "(val)","birthDateValue": "(val)","genderCode": "(val)","genderChangeCode": "(val)","deceasedDateValue": "(val)","maritalStatusCode": "(val)","primaryLanguageCode": "(val)","secondaryLanguageCode": "(val)","birthCountry": "(val)","birthStateProvinceCode": "(val)","birthCity": "(val)","geographicOrigin": "(val)","noteMessage": "(val)","_primaryKey": "(val)"},
              {"entityId": "(val)","birthDateValue": "(val)","genderCode": "(val)","genderChangeCode": "(val)","deceasedDateValue": "(val)","maritalStatusCode": "(val)","primaryLanguageCode": "(val)","secondaryLanguageCode": "(val)","birthCountry": "(val)","birthStateProvinceCode": "(val)","birthCity": "(val)","geographicOrigin": "(val)","noteMessage": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"entityId": "(val)","birthDateValue": "(val)","genderCode": "(val)","genderChangeCode": "(val)","deceasedDateValue": "(val)","maritalStatusCode": "(val)","primaryLanguageCode": "(val)","secondaryLanguageCode": "(val)","birthCountry": "(val)","birthStateProvinceCode": "(val)","birthCity": "(val)","geographicOrigin": "(val)","noteMessage": "(val)","_primaryKey": "(val)"},
              {"entityId": "(val)","birthDateValue": "(val)","genderCode": "(val)","genderChangeCode": "(val)","deceasedDateValue": "(val)","maritalStatusCode": "(val)","primaryLanguageCode": "(val)","secondaryLanguageCode": "(val)","birthCountry": "(val)","birthStateProvinceCode": "(val)","birthCity": "(val)","geographicOrigin": "(val)","noteMessage": "(val)","_primaryKey": "(val)"}
            ]
### Delete Entity Bio Demographics by Key [DELETE /research-sys/api/v1/entity-bio-demographics/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Bio Demographics [DELETE /research-sys/api/v1/entity-bio-demographics/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Bio Demographics with Matching [DELETE /research-sys/api/v1/entity-bio-demographics/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + entityId (optional) - 
    + birthDateValue (optional) - 
    + genderCode (optional) - 
    + genderChangeCode (optional) - 
    + deceasedDateValue (optional) - 
    + maritalStatusCode (optional) - 
    + primaryLanguageCode (optional) - 
    + secondaryLanguageCode (optional) - 
    + birthCountry (optional) - 
    + birthStateProvinceCode (optional) - 
    + birthCity (optional) - 
    + geographicOrigin (optional) - 
    + noteMessage (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
