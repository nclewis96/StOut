import Ember from 'ember';

export default Ember.Component.extend({
	tagName: 'div',
	classNames: ['container'],
	renderedComponent: Ember.computed('controlType', function() {
        return `form-${this.get('controlType')}`;
    })
});
