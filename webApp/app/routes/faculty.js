import Ember from 'ember';

export default Ember.Route.extend({
	model() {
		return 	{	
			faculty: 	{
				name: 'faculty',
				actionList:	[{
					actionName: 'Courses'
				},	{
					actionName: 'Metrics'
				},	{
					actionName: 'Students'
				},	{
					actionName: 'Reports'
				}]
			}
		}
	}
});
