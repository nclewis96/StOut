import DS from 'ember-data';

export default DS.Model.extend({
  id: DS.attr('number'),
  offeringId: DS.attr('number'),
  score: DS.attr('number'),
  name: DS.attr('string'),
  desc: DS.attr('string'),
  maxScore: DS.attr('number')
});
