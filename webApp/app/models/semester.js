import DS from 'ember-data';

export default DS.Model.extend({
  id: DS.attr('number'),
  typeId: DS.attr('string'),
  year: DS.attr('number')
});
