import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({
  attrs: {
    outcomes: {
      deserialize: 'records',
      serialize: false
    }
  }
});
