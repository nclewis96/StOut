const { Component, String: {w}, computed, computed:{equal}, getProperties, } = Ember;

export default Component.extend({
  store: Ember.inject.service(),
  courseList: computed('store', function(){
    return this.get('store').findAll('course');
  }),
  instructorList: computed('store', function(){
    return this.get('store').findAll('user');
  }),
  semesterList: computed('store', function(){
    return this.get('store').findAll('semester');
  }),
  actions: {
    updateUserConnection(data, idStr) {
      let id = parseInt(idStr, 10);
      let child = this.get('store').peekRecord('user', id);
      data.set('userId', child);
    },
    updateCourseConnection(data, idStr) {
      let id = parseInt(idStr, 10);
      let child = this.get('store').peekRecord('course', id);
      data.set('courseId', child);
    },
    updateSemesterConnection(data, idStr) {
      let id = parseInt(idStr, 10);
      let child = this.get('store').peekRecord('semester', id);
      data.set('semesterId', child);
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
