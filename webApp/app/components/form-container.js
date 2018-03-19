import Ember from 'ember';

export default Ember.Component.extend({
	renderedComponent: Ember.computed('controlType', function() {
        return `form-${this.get('controlType')}`;
    })
});
