import Ember from 'ember';

export default Ember.Route.extend({
	model() {
		return 	{
			program: [
			{
				users: [
				{
					userName: 'Jesse Anderson',
					userId: '3232'
				}],
			programName: 'Computer Science',
			programID: '32382'
			}]
		};
	}
});
