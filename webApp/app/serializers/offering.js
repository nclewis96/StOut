import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({
  attrs: {
    courseId: {
      deserialize: 'records',
      serialize: 'ids'
    },
    instructorId: {
      deserialize: 'records',
      serialize: 'ids'
    },
    semesterId: {
      deserialize: 'records',
      serialize: 'ids'
    },
    scaleId: {
      deserialize: 'records',
      serialize: 'ids'
    }
  }
});
