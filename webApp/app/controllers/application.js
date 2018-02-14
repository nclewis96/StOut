import Ember from 'ember';
export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
  queryParams: ['ticket'],
  ticket: null,
  test: "",
  con: Ember.computed('ticket', function(){
    let ticket = this.get('ticket');
    return ticket;
  }),
  login: function(){
    this.get('session').authenticate('authenticator:oauth2');
  }
});
