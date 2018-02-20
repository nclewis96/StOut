import Ember from 'ember';

export default Ember.Route.extend({
	model() {
		return 	{
			roles: [{
				roleName: 'Admin'
			},	{
				roleName: 'Program-Coordinator'
			},	{
				roleName: 'Observer'
			},	{
				roleName: 'Faculty'
			}],
			selectedRole: false
		};
	}
});