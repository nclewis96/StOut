import Component from '@ember/component';

export default Component.extend({
  tagName: 'ul',
  classNames: [ 'ul-semester'],
  model: function(semester){
    courses: this.store.query('course',
      {
        semesterId: semester.id
      })
  }
});
