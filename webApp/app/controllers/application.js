import Ember from 'ember';
export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
  login: function(){
    console.log('TEST');
    this.get('session').authenticate('authenticator:oauth2', 'tbrooks', '');
  }
});
