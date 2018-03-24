
const { Component, String: {w}, computed, computed:{equal}, getProperties, } = Ember;

export default Component.extend({
	saved: false,
	showFaculty: equal('model.userType', 'faculty'),
	showAdmin: equal('model.userType','admin'),
  classNames: ['container'],

model:{

	firstName: null,
	lastName: null,
	mtechUserName: null,	
	userType: null,
	companyName: null, 
	statusType: null,
	universityName: null,
},


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
	required: w('firstName lastName mtechUserName userType ${additionalField}'),
},

}

}),

actions: {
	submit() {
		this.set('saved', true);
		console.log("json:");
		console.log(JSON.stringify(this.model, null, "  "));
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

  
  console.log(JSON.stringify(model, null, "  "));
  
  // ...this is where we’d actually do something with the form data...
};

