import DS from 'ember-data';

export default DS.Model.extend({
  id: DS.attr('number'),
  programId: DS.attr('number'),
  name: DS.attr('string')
});
