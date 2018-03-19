import Ember from 'ember';

export default Ember.Component.extend({
	renderedComponent: Ember.computed('permission', function() {
        return `display-${this.get('permission')}`;
    }),
});
