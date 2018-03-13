import Ember from 'ember';

export default Ember.Route.extend({
	classNames: ['users'],
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
