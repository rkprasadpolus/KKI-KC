## Award Sync Statuses [/award/api/v1/award-sync-statuses/]

### Get Award Sync Statuses by Key [GET /award/api/v1/award-sync-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardSyncStatusId": "(val)","awardId": "(val)","awardNumber": "(val)","parentAwardId": "(val)","success": "(val)","status": "(val)","syncComplete": "(val)","_primaryKey": "(val)"}

### Get All Award Sync Statuses [GET /award/api/v1/award-sync-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardSyncStatusId": "(val)","awardId": "(val)","awardNumber": "(val)","parentAwardId": "(val)","success": "(val)","status": "(val)","syncComplete": "(val)","_primaryKey": "(val)"},
              {"awardSyncStatusId": "(val)","awardId": "(val)","awardNumber": "(val)","parentAwardId": "(val)","success": "(val)","status": "(val)","syncComplete": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Sync Statuses with Filtering [GET /award/api/v1/award-sync-statuses/]
    
+ Parameters

    + awardSyncStatusId (optional) - Award Sync Status Id. Maximum length is 40.
    + awardId (optional) - Award Id. Maximum length is 12.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + parentAwardId (optional) - Award Id. Maximum length is 12.
    + success (optional) - Success. Maximum length is 1.
    + status (optional) - Result Message. Maximum length is 4000.
    + syncComplete (optional) - Sync Complete. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardSyncStatusId": "(val)","awardId": "(val)","awardNumber": "(val)","parentAwardId": "(val)","success": "(val)","status": "(val)","syncComplete": "(val)","_primaryKey": "(val)"},
              {"awardSyncStatusId": "(val)","awardId": "(val)","awardNumber": "(val)","parentAwardId": "(val)","success": "(val)","status": "(val)","syncComplete": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Sync Statuses [GET /award/api/v1/award-sync-statuses/]
	                                          
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
    
            {"columns":["awardSyncStatusId","awardId","awardNumber","parentAwardId","success","status","syncComplete"],"primaryKey":"awardSyncStatusId"}
		
### Get Blueprint API specification for Award Sync Statuses [GET /award/api/v1/award-sync-statuses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Sync Statuses.md"
            transfer-encoding:chunked
### Update Award Sync Statuses [PUT /award/api/v1/award-sync-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSyncStatusId": "(val)","awardId": "(val)","awardNumber": "(val)","parentAwardId": "(val)","success": "(val)","status": "(val)","syncComplete": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Sync Statuses [PUT /award/api/v1/award-sync-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardSyncStatusId": "(val)","awardId": "(val)","awardNumber": "(val)","parentAwardId": "(val)","success": "(val)","status": "(val)","syncComplete": "(val)","_primaryKey": "(val)"},
              {"awardSyncStatusId": "(val)","awardId": "(val)","awardNumber": "(val)","parentAwardId": "(val)","success": "(val)","status": "(val)","syncComplete": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Award Sync Statuses [POST /award/api/v1/award-sync-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSyncStatusId": "(val)","awardId": "(val)","awardNumber": "(val)","parentAwardId": "(val)","success": "(val)","status": "(val)","syncComplete": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardSyncStatusId": "(val)","awardId": "(val)","awardNumber": "(val)","parentAwardId": "(val)","success": "(val)","status": "(val)","syncComplete": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Sync Statuses [POST /award/api/v1/award-sync-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardSyncStatusId": "(val)","awardId": "(val)","awardNumber": "(val)","parentAwardId": "(val)","success": "(val)","status": "(val)","syncComplete": "(val)","_primaryKey": "(val)"},
              {"awardSyncStatusId": "(val)","awardId": "(val)","awardNumber": "(val)","parentAwardId": "(val)","success": "(val)","status": "(val)","syncComplete": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardSyncStatusId": "(val)","awardId": "(val)","awardNumber": "(val)","parentAwardId": "(val)","success": "(val)","status": "(val)","syncComplete": "(val)","_primaryKey": "(val)"},
              {"awardSyncStatusId": "(val)","awardId": "(val)","awardNumber": "(val)","parentAwardId": "(val)","success": "(val)","status": "(val)","syncComplete": "(val)","_primaryKey": "(val)"}
            ]
### Delete Award Sync Statuses by Key [DELETE /award/api/v1/award-sync-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Sync Statuses [DELETE /award/api/v1/award-sync-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Sync Statuses with Matching [DELETE /award/api/v1/award-sync-statuses/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardSyncStatusId (optional) - Award Sync Status Id. Maximum length is 40.
    + awardId (optional) - Award Id. Maximum length is 12.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + parentAwardId (optional) - Award Id. Maximum length is 12.
    + success (optional) - Success. Maximum length is 1.
    + status (optional) - Result Message. Maximum length is 4000.
    + syncComplete (optional) - Sync Complete. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
