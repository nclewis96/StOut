import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';

moduleForComponent('course-listitem', 'Integration | Component | course listitem', {
  integration: true
});

test('it renders', function(assert) {

  // Set any properties with this.set('myProperty', 'value');
  // Handle any actions with this.on('myAction', function(val) { ... });

  this.render(hbs`{{course-listitem}}`);

  assert.equal(this.$().text().trim(), '');

  // Template block usage:
  this.render(hbs`
    {{#course-listitem}}
      template block text
    {{/course-listitem}}
  `);

  assert.equal(this.$().text().trim(), 'template block text');
});
