const { Component, String: {w}, computed, computed:{equal}, getProperties, } = Ember;

export default Component.extend({
	saved: false,
	showFaculty: equal('model.userType', 'faculty'),
	showAdmin: equal('model.userType','admin'),
  classNames: ['container'],


statusTypes: [

	{label:'Active', value: 'active'},
	{label:'Unactive', value: 'unactive'},

],




userTypes: [
	{label: 'Faculty', value:'faculty'},
	{label:	'Admin', value:'admin'},
        {label: 'Observer', value:'observer'},

],


rules: computed('showAdmin', 'showFaculty', function() {

const {showFaculty, showAdmin} = getProperties(this, 'showAdmin', 'showFaculty');
const additionalField = showFaculty ? 'companyName' : 'universityName';

return{

sharedValidation: {
	required: w('courseId instructorId semesterId sectionNum locked numStudents'),
},

}

}),

actions: {
	submit() {
		console.log(this.get('data').changedAttributes());
		this.get('data').save();
	},
	deleteRecord() {
		this.get('data').destroyRecord();
	}
}

});


/**
 * A handler function to prevent default submission and run our custom script.
 * @param  {Event} event  the submit event triggered by the user
 * @return {void}
 */
const handleFormSubmit = event => {
  
  // Stop the form from submitting since we’re handling that with AJAX.
  event.preventDefault();

  
  console.log(JSON.stringify(data, null, "  "));
  
  // ...this is where we’d actually do something with the form data...
};

