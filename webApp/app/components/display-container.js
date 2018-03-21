import Ember from 'ember';

export default Ember.Component.extend({
	tagName: 'div',
	classNames: ['container'],
	
	renderedComponent: Ember.computed('permission', function() {
        return `display-${this.get('permission')}`;
    }),
});
