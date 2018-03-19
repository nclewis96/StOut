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
	renderedComponent: Ember.computed('renderType', function() {
        return `${this.get('renderType')}-container`;
    }),

	actions:  {
        onItemClicked(data) {
            this.setProperties({
                data: data,
                renderType: 'display'
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
