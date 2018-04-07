import DS from 'ember-data';

export default DS.JSONSerializer.extend(DS.EmbeddedRecordsMixin, {
	attrs: {
    jobTitle: { embedded: 'always'},
	roleList: { embedded: 'always'}
  }
});
