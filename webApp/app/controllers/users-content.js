import Ember from 'ember';

export default Ember.Controller.extend({
	counter: 0,
	actions:  {
		navSelect() {
			this.incrementProperty('counter');
		}
	}
});
