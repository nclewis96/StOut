import DS from 'ember-data';

export default DS.Model.extend({
  semesterTypeId: DS.belongsTo('semesterType'),
  year: DS.attr('number'),
  programCutoffs: DS.hasMany('progCutoff')
});
