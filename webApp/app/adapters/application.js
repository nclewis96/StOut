import Ember from 'ember';
const { inject: { service } } = Ember;
import DS from 'ember-data';
import CasAuthenticatedRouteMixin from '../mixins/cas-authenticated-route';
export default DS.JSONAPIAdapter.extend(CasAuthenticatedRouteMixin, {
  currentUser: service(),
  jwt: Ember.computed('currentUser', function(){
    return `Bearer ${this.get('currentUser.token')}`;
  }),
  host: 'https://csdept29.mtech.edu:443',
  headers: Ember.computed('jwt', function(){
    return{
    'Accept': 'application/json',
    'Authorization': this.get('jwt')
    };
  })
});
