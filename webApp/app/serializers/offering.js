import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({
  attrs: {
    courseId: {
      deserialize: 'records',
      serialize: 'ids'
    },
    userId: {
      deserialize: 'records',
      serialize: 'ids'
    },
    semesterId: {
      deserialize: 'records',
      serialize: 'ids'
    }
  }
});
