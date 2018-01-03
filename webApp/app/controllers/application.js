import Ember from 'ember';
console.log('TEST');
this.get('session').authenticate('authenticator:oauth2', 'tbrooks', '');
export default Ember.Controller.extend({
  session: Ember.inject.service('session')
});
