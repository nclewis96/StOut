import Ember from 'ember';
export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
  queryParams: ['ticket'],
  ticket: null,
  login: function(){
    this.get('session').authenticate('authenticator:oauth2');
  },


});
