import Route from '@ember/routing/route';

export default Route.extend({
	model() {
		return this.get('store').findAll('offering');
	},
	
	actions: {
		addRecord() {
			this.get('store').createRecord('offering', {
				courseId: 1,
				instructorId: 2,
				semesterId: 3,
				sectionNum: 'test01',
				locked: false,
				numStudents: 7
			});
		}
	}
});
