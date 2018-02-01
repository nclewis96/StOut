//User Object
var userObj{
	user_type_id: 0,
	fname: "",
	lname: "",
	email: "",
	user_level: ""
	
}

//Program Object
var programObj {
	program_id: 0,
	name: ""
}

//Course Object
var courseObj {
	course_id: 0,
	course_num: "",
	prefix: "",
	course_title: "",
	program : programObj
}

//Semester Object
var semesterObj {
	semester_id: 0,
	type: "",
	year: ""
}

//Offering object
var offeringObj{
	offering_id: 0,
	course: courseObj,
	semester: semesterObj,
}

//Metric Object
var metricObj{
	metric_id: 0,
	program: programObj,
	name: ""
}

//Outcome Object
var outcomeObj{
	outcome_id: 0,
	metric: metricObj,
	name: "",
	description: ""
}

//Program Outcomes
var progOutcomeObj {
	outcome: outcomeObj[],
	program: programObj
}


