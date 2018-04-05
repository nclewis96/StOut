import Route from '@ember/routing/route';

export default Route.extend({
	model() {
	  if (this.store.peekRecord('offering',1) === null)
		return this.get('store').findRecord('offering', 1);
	},

	actions: {
		addRecord() {
      this.get('store').createRecord('offering', {
        courseId: '',
        instructorId: '',
        semesterId: '',
        sectionNum: '',
        locked: false,
        numStudents: ''
      });
    },
    submitRecord(data) {
		  data.save();
    }
	}
});
