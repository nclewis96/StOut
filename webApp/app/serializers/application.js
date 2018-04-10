import DS from 'ember-data';

export default DS.JSONSerializer.extend(DS.EmbeddedRecordsMixin,{
  serializeId(snapshot, json, primaryKey) {
    var id = snapshot.id;
    json[primaryKey] = parseInt(id, 10);
  }
});
