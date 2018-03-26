import DS from 'ember-data';

export default DS.Model.extend({
  id: DS.attr('number'),
  name: DS.attr('string'),
  username: DS.attr('string'),
  jobTitle: DS.attr(),
  roleList: DS.attr()

});
