import OAuth2PasswordGrant from 'ember-simple-auth/authenticators/oauth2-password-grant';


export default OAuth2PasswordGrant.extend({
  serverTokenEndpoint: 'https://mtlbsso.mtech.edu/idp/profile/cas'
});

