import DS from 'ember-data';

export default DS.Model.extend({
  metricId: DS.attr('number'),
  name: DS.attr('string'),
  description: DS.attr('string')
});
