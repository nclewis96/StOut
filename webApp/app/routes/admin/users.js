import Ember from 'ember';

import CasAuthenticatedRouteMixin from '../../mixins/cas-authenticated-route';
export default Ember.Route.extend(CasAuthenticatedRouteMixin, {
	model() {
		return 	{
			programs: [
			{
				users: [
				{
					name: 'Jesse Anderson',
					id: '3232'
				}],
			name: 'Computer Science',
			id: '32382'
			}]
		};
	}
});
