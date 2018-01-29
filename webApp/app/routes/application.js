import Ember from 'ember';

export default Ember.Route.extend({
	model() {
		return 	{
			roles: [{
				roleName: 'Admin'
			},	{
				roleName: 'Program-Coordinator'
			},	{
				roleName: 'Faculty'
			}],
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
			},
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
			},
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
			},
			selectedRole: false
		};
	}
});
