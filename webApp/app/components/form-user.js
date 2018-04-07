import Component from '@ember/component';

export default Component.extend({
  store: Ember.inject.service(),

  actions: {
    removeConnection(data) {
      var store = this.get('store');
      data.eachAttribute(function(name,meta) {
        console.log(name, meta);
      });
      data.eachRelationship(function(name,descriptor) {
        console.log(name, descriptor);
        console.log(store.peekRecord('jobTitle', 1));
      });
    },
    addJobTitle(data) {
      let jobTitle = store.findRecord('jobTitle',1);
      data.get('jobTitle').pushObject(jobTitle);
    }

  }
});
