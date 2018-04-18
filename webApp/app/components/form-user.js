const { Component, String: {w}, computed, computed:{equal}, getProperties, } = Ember;

export default Component.extend({
  store: Ember.inject.service(),

  jobTitleList: computed('store', function(){
    return this.get('store').findAll('jobTitle');
  }),
  roleList: computed('store', function(){
    return this.get('store').findAll('role');
  }),
  actions: {
  },




    // rules hash for validation
    rules: {
        name: {
            required: {
                check: true,
                message: 'You must provide a first name'
		
            },
		//max: 5
        },
      
        username: {
  required: true,
  //max: 5
}
    }

});
