const { Component, String: {w}, computed, computed:{equal}, getProperties, } = Ember;

export default Component.extend({
  store: Ember.inject.service(),
  courseList: computed('store', function(){
    return this.get('store').findAll('course');
  }),
  instructorList: computed('store', function(){
    return this.get('store').findAll('user');
  }),
  action: {
    updateUserConnection(data) {
      console.log(courseId);
      let child = store.peekRecord('user', courseId);
      data.set('user', child);
      console.log(child);
    }
  },
  rules:  {
    courseId: 'required',
    userId: 'required',
    semesterId: 'required',
    sectionNum: 'required',
    locked: 'required',
    numStudents: 'required'

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
