import Ember from 'ember';

export default Ember.Route.extend({
	model() {
		return {
			program: [
			{
				semester: [
				{
					student: [
					{
						name: 'This person is a student',
						id: '32478'
					}],
					name: 'Fall 2017',
					id: '3283'
				}],	
				name: 'ComputerScience',
				id: '3232'
			}]
		};
	}
});
