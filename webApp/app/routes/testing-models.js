import Route from '@ember/routing/route';
import CasAuthenticatedRouteMixin from '../mixins/cas-authenticated-route';
const { inject: { service } } = Ember;

export default Route.extend(CasAuthenticatedRouteMixin,{
	model() {
    return Ember.RSVP.hash({
      offerings: this.store.findAll('offering'),
      offering: this.store.findRecord('offering', 1),
      users: this.store.findAll('user'),
      user: this.store.findRecord('user', 1),
      assigns: this.store.findAll('assign'),
      courses: this.store.findAll('course'),
      outcomes: this.store.findAll('outcome'),
      metrics: this.store.findAll('metric')
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
	addUser() {
	  this.get('store').createRecord('user', {
		    name: '',
			username: ''
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
