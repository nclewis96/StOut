import DS from 'ember-data';

export default DS.Model.extend({
  name: DS.attr('string'),
  username: DS.attr('string'),
  jobTitle: DS.belongsTo('jobTitle', {async: true}),
  roleList: DS.hasMany('roleList', {async: true})

});
