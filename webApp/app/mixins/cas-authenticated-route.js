import Ember from 'ember';
//https://centerforopenscience.github.io/ember-osf/files/addon_mixins_cas-authenticated-route.js.html
/**
 * Replacement for Ember-simple-auth AuthenticatedRouteMixin. Instead of redirecting to an internal route,
 *   this mixin redirects to CAS login URL, and brings the user back to the last requested page afterwards
 *
 * For OAuth this is done via the state parameter, and for cookies this is done via the service parameter. (TODO: Need a mixin that detects this!)
 *
 * @class CasAuthenticatedRouteMixin
 */
export default Ember.Mixin.create({
  /**
      The session service.
      @property session
      @readOnly
      @type SessionService
      @public
      */
  session: Ember.inject.service('session'),
  routing: Ember.inject.service('-routing'),
  /**
      Checks whether the session is authenticated, and if it is not, attempts to authenticate it, and if that fails,
      redirects to the login URL. (Sending back to this page after a successful transition)
      __If `beforeModel` is overridden in a route that uses this mixin, the route's
     implementation must call `this._super(...arguments)`__ so that the mixin's
     `beforeModel` method is actually executed.
      @method beforeModel
      @public
      */
  beforeModel(/*transition*/) {
    let ticket = this.paramsFor('application').ticket;
    if (this.get('session.authenticated.jwt') !== undefined) {return this._super(...arguments);}
    return this.get('session').authenticate('authenticator:oauth2', ticket).then(() => {
      return this._super(...arguments);
    }).catch(() => {
      // Reference: http://stackoverflow.com/a/39054607/414097
      window.location = "https://mtlbsso.mtech.edu/idp/profile/cas/login?service=https://katie.mtech.edu/~tbrooks/AbOut/secret";
    });
  },
});
