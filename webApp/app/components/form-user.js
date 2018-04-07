import Component from '@ember/component';

export default Component.extend({
  store: Ember.inject.service(),

  actions: {
    removeConnection(data) {
      var store = this.get('store');
    },
    addJobTitle(data) {
	  var store = this.get('store');
      let jobTitle = store.findRecord('jobTitle',1);
      data.get('jobTitle').pushObject(jobTitle);
    },
	addRole(data) {
	  var store = this.get('store');
      let roleList = store.findRecord('role',1);
      data.get('roleList').pushObject(roleList);
    }

  }
});
