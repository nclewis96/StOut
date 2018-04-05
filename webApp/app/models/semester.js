import DS from 'ember-data';

export default DS.Model.extend({
  typeId: DS.attr('string'),
  year: DS.attr('number')
});
