import Ember from 'ember';

export default Ember.Route.extend({
	model() {
		return 	{
			programCoordinator:	{
				name: 'program-coordinator',
				actionList:	[{
					actionName: 'Assessments'
				},	{
					actionName: 'Outcomes'
				},	{
					actionName: 'Courses'
				},	{
					actionName: 'Instructors'
				},	{
					actionName: 'Reports'
				}]
			}
		}
	}			
});
