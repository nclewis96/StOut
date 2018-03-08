import Ember from 'ember';

import CasAuthenticatedRouteMixin from '../../mixins/cas-authenticated-route';
const { inject: { service } } = Ember;
export default Ember.Route.extend(CasAuthenticatedRouteMixin, {
  currentUser: service(),
  model() {
    return 	{
      programs: [
        {
          users: [
            {
              name: this.get('currentUser.name'),
              id: this.get('currentUser.id') 
            }],
          name: 'Computer Science',
          id: '32382'
        }]
    };
  }
});
