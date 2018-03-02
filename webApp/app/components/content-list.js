import Ember from 'ember';

export default Ember.Component.extend({
	counter: 0,
	actions:  {
		listSelected() {
			this.incrementProperty('counter');
		}
	}
});
