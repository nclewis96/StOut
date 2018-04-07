import Route from '@ember/routing/route';

export default Route.extend({
	model() {
    return Ember.RSVP.hash({
      offerings: this.store.findAll('offering'),
      offering: this.store.findRecord('offering', 1),
      users: this.store.findAll('user'),
      user: this.store.findRecord('user', 1)
    });
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
    },
    submitRecord(data) {
		  data.save();
    },
    deleteRecord(data) {
		  data.destroyRecord();
    }
	}
});
