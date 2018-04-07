import DS from 'ember-data';

export default DS.Model.extend({
  programId: belongsTo('program'),
  semesterId: belongsTo('semester'),
  startDate: DS.attr('date'),
  endDate: DS.attr('date')
});
