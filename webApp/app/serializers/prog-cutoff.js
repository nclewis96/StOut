import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({
  attrs: {
    semesterId: {
      deserialize: 'records',
      serialize: 'ids'
    },
    programId: {
      deserialize: 'records',
      serialize: 'ids'
    }
  }
});
