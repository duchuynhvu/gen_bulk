- Use gen_bulk to generate bulk files.
- After all bulk files were generated successfully, edit file bulk.sql (in gen_bulk source folder) with FORM 'path to each bulk file' 
	Example: 
		\COPY public.event_status_info (event_id, event_additional_param, event_changed_time, event_closed_time, event_info, event_match_info, event_original_info_list, event_raised_time, event_status, event_sub_status, event_sub_type1, event_sub_type2, event_sub_type3, event_type, related_identifier_list) FROM 'D:\hclinh\ConditionalSearch\bulk_file\jsonb1.sql'
	There are 20 line with 500 000 records for each line. Total 10 000 000 records.
	
- Copy file to database
	+ Create database whith name QUOIT
	+ Run project to create table
	+ Create extension
		CREATE EXTENSION pgroonga;
	+ Create index for jsonb column
		CREATE INDEX <index_name> ON <table_name> USING pgroonga (<jsonb_column>);
		
		Example, in this test created index as below:
			CREATE INDEX event_info_index ON event_status_info USING pgroonga (event_info);
			CREATE INDEX event_additional_param_index ON event_status_info USING pgroonga (event_additional_param);
			CREATE INDEX related_identifier_list_index ON event_status_info USING pgroonga (related_identifier_list);
			CREATE INDEX event_original_info_list_index ON event_status_info USING pgroonga (event_original_info_list);
			CREATE INDEX event_match_info_list_index ON event_status_info USING pgroonga (event_match_info);

	+ Use the command below to copy:
		psql -U postgres QUOIT < /path_to_bulk/bulk.sql (this file is in gen_bulk folder)
	

		
- Use Postman insert some data for test
	POST http://localhost:8080/AlarmStatusManagement/v1/eventStatusInfo
	
	Sample data:
		{
			"eventId": "001",
			"eventType": "ABC",
			"eventSubType1": "eventSubType1",
			"eventSubType2": "eventSubType2",
			"eventSubType3": "eventSubType3",
			"eventOriginalInfoList": [
				"eventOriginalInfoList 1",
				"eventOriginalInfoList 2",
				"eventOriginalInfoList 3"
			],
			"eventInfo": [
				{
					"key": "hostname",
					"value": "Sony"
				},
				{
					"key": "status",
					"value": "Inprogress"
				},
				{
					"key": "port",
					"value": "8888"
				}
			],
			"eventRaisedTime": "2019-02-01T15:16:15",
			"eventChangedTime": "2019-02-01T15:16:15",
			"eventClosedTime": "2019-02-01T15:16:15",
			"eventStatus": "ABC",
			"eventSubStatus": "ABC",
			"eventMatchInfo": [
				"eventMatchInfo 1",
				"eventMatchInfo 2",
				"eventMatchInfo 3"
			],
			"eventAdditionalParam": [
				{
					"key": "hostname",
					"value": "Zalo"
				},
				{
					"key": "status",
					"value": "Held"
				},
				{
					"key": "port",
					"value": "8080"
				}
			],
			"relatedIdentifierList": [
				{
					"key": "hostname",
					"value": "facebook"
				},
				{
					"key": "status",
					"value": "Done"
				},
				{
					"key": "port",
					"value": "7777"
				}
			]
		}