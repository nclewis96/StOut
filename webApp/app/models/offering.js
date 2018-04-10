import DS from 'ember-data';
import './singular-plural-rules';

export default DS.Model.extend({
	courseId: DS.belongsTo('course'),
	userId: DS.belongsTo('user'),
	semesterId: DS.belongsTo('semester'),
	sectionNum: DS.attr('string'),
	locked: DS.attr('boolean'),
	numStudents: DS.attr('number'),
  instructorName: DS.attr('string'),
  scaleId: DS.hasMany('scale')
});
