import Ember from 'ember';

export default Ember.Route.extend({
	model () {
		return { 
			programs: [	
			{
				semesters: [
				{
					courses: [
					{
						name: 'Alex\'s tutoring',
						num: '01',
						id: '323'
					}],
					name:'fall 2017',
					id: '2323'
				}],
				name: 'Computer Science',
				id:'3232'
			}]
		};
	}
});
