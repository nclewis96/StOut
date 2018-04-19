import Component from '@ember/component';

export default Component.extend({
  tagName: 'ul',
  classNames: [ 'ul-semester'],
  model: function(params){
    semesters: this.store.query('semester',
      {
        programId: this.get('params')[0]
      })
  }
});
