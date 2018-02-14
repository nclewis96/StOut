import Ember from 'ember';
import OAuth2PasswordGrant from 'ember-simple-auth/authenticators/oauth2-password-grant';

export default OAuth2PasswordGrant.extend({
  serverTokenEndpoint: 'https://mtlbsso.mtech.edu/idp/profile/cas',
  backendEndpoint: 'https://katie.mtech.edu/login',
  session: Ember.inject.service(),
  queryParams: ['ticket'],
  ticket: null,
  authenticate : function(){
    let backendEndpoint = this.get('backendEndpoint');
    let ticket = this.get('ticket');
    return new Ember.RSVP.Promise((resolve, reject) => {
      Ember.$.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: backendEndpoint,
        data: JSON.stringify({
          //token: options.jwt,
          data: {
            ticket: ticket,
          }
        })
      }).done((response) => {
        Ember.run(() => {
          resolve({ user: response.user });
        });
      }).fail((xhr/* , status, error */) => {
        var response = xhr.responseText;
        Ember.run(() => {
          reject(response);
        });
      });
    });
  }
});

