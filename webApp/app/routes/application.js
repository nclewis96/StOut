import Ember from 'ember';
import ApplicationRouteMixin from 'ember-simple-auth/mixins/application-route-mixin';

export default Ember.Route.extend(ApplicationRouteMixin, {
  ticket : null,
  setupController: function(controller, model){
    this._super(controller,model);
    this.ticket = controller.get('con'); 
   },
   
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
