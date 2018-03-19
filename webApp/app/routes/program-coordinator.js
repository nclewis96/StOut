import Ember from 'ember';

export default Ember.Route.extend({
	classNames: ['program-coord'],
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
		};
	}			
});
