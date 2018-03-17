
const { Component, String: {w}, computed, computed:{equal}, getProperties, } = Ember;

export default Component.extend({
	showFaculty: equal('model.userType', 'faculty'),
	showAdmin: equal('model.userType','admin'),
  classNames: ['container'],

model:{

	firstName: null,
	lastName: null,
	mtechUserName: null,	
	clientType: null,

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
});
