import DS from 'ember-data';

export default DS.Model.extend({
  courseNum: DS.attr('string'),
  prefixId: DS.attr('number'),
  title: DS.attr('string'),
  programId: DS.attr('number')
});
