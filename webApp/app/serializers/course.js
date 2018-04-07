import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({
  attrs: {
    coursePrefix: {
      deserialize: 'records',
      serialize: 'ids'
    }
  }
});
