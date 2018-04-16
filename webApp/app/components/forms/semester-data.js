const { Component, String: {w}, computed, computed:{equal}, getProperties, } = Ember;

export default Component.extend({
  store: Ember.inject.service(),
  semesterTypeList: computed('store', function(){
    return this.get('store').findAll('semester-type');
  }),
  actions: {
  },
  rules:  {
  }

});


/**
 * A handler function to prevent default submission and run our custom script.
 * @param  {Event} event  the submit event triggered by the user
 * @return {void}
 */
const handleFormSubmit = event => {

  // Stop the form from submitting since we’re handling that with AJAX.
  event.preventDefault();


  console.log(JSON.stringify(data, null, "  "));

  // ...this is where we’d actually do something with the form data...
};
