import Ember from 'ember';

export default Ember.Component.extend({
	tagName: 'div',
	idName: 'content-container',
	routing: Ember.inject.service('-routing'),
	subRoute: Ember.computed(function() {
		return this.get('routing.currentRouteName').split(".")[1];
	}),
    renderType: null,
	controlType: null,
    permission: Ember.computed(function() {
		return this.get('routing.currentRouteName').split(".")[0];
	}),
    data: null,
    renderActions: null,
	renderedComponent: Ember.computed('renderType', 'controlType', function() {
        return `${this.get('renderType')}-${this.get('controlType')}`;
    }),

	actions:  {
        onItemClicked(data) {
            this.setProperties({
                data: data,
                renderType: 'display',
				controlType: 'control'
            });
        },
        onEditClicked() {
            this.setProperties({
				renderType: 'form',
				controlType: 'edit'
			});
        },
        onRenderButtonClick(renderType, controlType) {
            this.setProperties({
				renderType: renderType,
				controlType: controlType
			});
        }
	}
});
