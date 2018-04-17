import DS from 'ember-data';

export default DS.Model.extend({
  name: DS.attr('string'),
  metrics: DS.hasMany('metric'),
  programCutoffs: DS.hasMany('programCutoff')
});
