import Ember from 'ember';

export default Ember.Route.extend({
	model() {
		return 	{
			program: [
			{
				semester: [
				{
					course:	[
					{
						courseName: 'MathyStuffy',
						sectionNum: '01',
						courseID:'32345'
					}],
					semesterName: 'Fall 2017',
					semesterID: '32323'
				}],
				programName: 'Computer Sciences',
				programID: '3232'
			}]
		};
	}

});
