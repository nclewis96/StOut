import Ember from 'ember';

export default Ember.Route.extend({
	classNames: ['observer'],
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
