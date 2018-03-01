import Ember from 'ember';

export default Ember.Route.extend({
	model() {
		return 	{
			observer:	{
				name: 'observer',
				actionList:	[{
					actionName: 'Outcomes'
				},	{
					actionName: 'Courses'
				},	{
					actionName: 'Reports'
				}]
			}
		};
	}			
});
