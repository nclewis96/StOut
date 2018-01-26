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
			admin: [
			{
				actionName: 'Admin action'
			},	
			{
				actionName: 'Admin action2'
			},	
			{
				actionName: 'Admin action3'
			}
			],
			programCoordinator: [{
				actionName: 'Program-coordinator action'
			},	{
				actionName: 'Program-coordinator action2'
			},	{
				actionName: 'Program-coordinator action3'
			}],
			faculty: [{
				actionName: 'Faculty action'
			},	{
				actionName: 'Faculty action2'
			},	{
				actionName: 'Faculty action3'
			}],
			selectedRole: false
		};
	}
});
