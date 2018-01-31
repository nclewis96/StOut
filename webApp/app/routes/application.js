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
			selectedRole: false
		};
	}
});
