import DS from 'ember-data';
import './singular-plural-rules';

export default DS.Model.extend({
	id: DS.attr('number'),
	courseId: DS.attr('number'),
	instructorId: DS.attr('number'),
	semesterId: DS.attr('number'),
	sectionNum: DS.attr('string'),
	locked: DS.attr('boolean'),
	numStudents: DS.attr('number')
});
