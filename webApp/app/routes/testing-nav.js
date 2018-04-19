import Route from '@ember/routing/route';

export default Route.extend({
  model: function() {
    return Ember.RSVP.hash({
        programs: this.store.findAll('program')
      })
  }

  });
