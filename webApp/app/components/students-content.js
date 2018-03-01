import Ember from 'ember';

export default Ember.Component.extend({
	counter: 0,
	actions:  {
		navSelect() {
			this.incrementProperty('counter');
		}
	}
});
