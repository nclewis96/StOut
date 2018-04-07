import DS from 'ember-data';

export default DS.JSONSerializer.extend(DS.EmbeddedRecordsMixin, {
	attrs: {
    jobTitle: {
      deserialize: 'records',
      serialize: 'ids'
    },
    roleList: {
      deserialize: 'records',
      serialize: false
    },

  }
});
