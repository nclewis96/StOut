import Ember from 'ember';

export default Ember.Component.extend({
	classNames: ['primary'],
	counter: 0,
	actions:  {
		listSelected(object) {
			this.incrementProperty('counter');
			console.log("hi");
			console.log(object.name);
			console.log(object.id);
		}
	}
});
