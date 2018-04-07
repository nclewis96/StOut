import DS from 'ember-data';

export default DS.Model.extend({
  programId: DS.attr('number'),
  name: DS.attr('string')
});
