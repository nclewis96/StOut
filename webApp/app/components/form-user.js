import Component from '@ember/component';

export default Component.extend({
  actions: {
    removeConnection(data) {

    },
    addJobTitle(data) {
      let jobTitle = this.store.findRecord('jobTitle',1);
      data.get('jobTitle').pushObject(jobTitle);
    }

  }
});
