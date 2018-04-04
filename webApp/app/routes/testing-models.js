import Route from '@ember/routing/route';

export default Route.extend({
	model() {
		return this.get('store').findAll('offering');
	},

	actions: {
		addOffering() {
			this.get('store').createRecord('offering', {
				courseId: '',
				instructorId: '',
				semesterId: '',
				sectionNum: '',
				locked: false,
				numStudents: ''
			});
		}
	}
});
