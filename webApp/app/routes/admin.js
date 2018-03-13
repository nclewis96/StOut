import Ember from 'ember';

export default Ember.Route.extend({
	
	model() {
		return 	{
			admin: {
				name: 'admin',
				actionList: [{
					actionName: 'Users'
				},	{
					actionName: 'Courses'
				},	{
					actionName: 'Outcomes'
				},	{
					actionName: 'Students'
				},	{
					actionName: 'Reports'
				}]
			}
		};
	}
});
