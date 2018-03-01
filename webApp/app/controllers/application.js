import Ember from 'ember';
const { inject: { service } } = Ember;
export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
  currentUser: service(),
  queryParams: ['ticket'],
  ticket: null,
  restoredSession: false,
  _loginWithToken: function(jwt) {
    (function(controller) {
      controller.get('session').authenticate('authenticator:cas', {
        jwt: jwt
      }).then(function() {
        controller.send('login');
      }).catch(function(/* reason */) {
        controller.get('session.store').clear();
      });
    }) (this);
  },
  init() {
    "use strict";
    
    this._super(...arguments);
    let session = this.get('session');
    let store = session.get('store');

    if (store) {
      store.restore().then((data) => {
        if (Ember.isPresent(data.authenticated.jwt)) {
          this._loginWithToken(data.authenticated.jwt);
        }
      }).catch(() => {
      }).finally(() => {
            let restored = this.get('restoredSession');
            restored = true;
      });
    }
  },

  actions:{
    login() {
      let session = this.get('session');
      let user = session.get('data.authenticated');

      session.get('store').persist(user);
    }
    }
});
