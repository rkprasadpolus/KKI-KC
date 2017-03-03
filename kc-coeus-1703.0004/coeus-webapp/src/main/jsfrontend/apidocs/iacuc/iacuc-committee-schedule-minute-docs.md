## Iacuc Committee Schedule Minute Docs [/iacuc/api/v1/iacuc-committee-schedule-minute-docs/]

### Get Iacuc Committee Schedule Minute Docs by Key [GET /iacuc/api/v1/iacuc-committee-schedule-minute-docs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Committee Schedule Minute Docs [GET /iacuc/api/v1/iacuc-committee-schedule-minute-docs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Committee Schedule Minute Docs with Filtering [GET /iacuc/api/v1/iacuc-committee-schedule-minute-docs/]
    
+ Parameters

    + commScheduleMinuteDocId (optional) - 
    + scheduleIdFk (optional) - 
    + minuteNumber (optional) - 
    + minuteName (optional) - 
    + pdfStore (optional) - 
    + createTimestamp (optional) - 
    + createUser (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Committee Schedule Minute Docs [GET /iacuc/api/v1/iacuc-committee-schedule-minute-docs/]
	                                          
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
    
            {"columns":["commScheduleMinuteDocId","scheduleIdFk","minuteNumber","minuteName","pdfStore","createTimestamp","createUser"],"primaryKey":"commScheduleMinuteDocId"}
		
### Get Blueprint API specification for Iacuc Committee Schedule Minute Docs [GET /iacuc/api/v1/iacuc-committee-schedule-minute-docs/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Committee Schedule Minute Docs.md"
            transfer-encoding:chunked
### Update Iacuc Committee Schedule Minute Docs [PUT /iacuc/api/v1/iacuc-committee-schedule-minute-docs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Committee Schedule Minute Docs [PUT /iacuc/api/v1/iacuc-committee-schedule-minute-docs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Committee Schedule Minute Docs [POST /iacuc/api/v1/iacuc-committee-schedule-minute-docs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Committee Schedule Minute Docs [POST /iacuc/api/v1/iacuc-committee-schedule-minute-docs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"commScheduleMinuteDocId": "(val)","scheduleIdFk": "(val)","minuteNumber": "(val)","minuteName": "(val)","pdfStore": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Committee Schedule Minute Docs by Key [DELETE /iacuc/api/v1/iacuc-committee-schedule-minute-docs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committee Schedule Minute Docs [DELETE /iacuc/api/v1/iacuc-committee-schedule-minute-docs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committee Schedule Minute Docs with Matching [DELETE /iacuc/api/v1/iacuc-committee-schedule-minute-docs/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + commScheduleMinuteDocId (optional) - 
    + scheduleIdFk (optional) - 
    + minuteNumber (optional) - 
    + minuteName (optional) - 
    + pdfStore (optional) - 
    + createTimestamp (optional) - 
    + createUser (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
