import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';

moduleForComponent('semester-listitem', 'Integration | Component | semester listitem', {
  integration: true
});

test('it renders', function(assert) {

  // Set any properties with this.set('myProperty', 'value');
  // Handle any actions with this.on('myAction', function(val) { ... });

  this.render(hbs`{{semester-listitem}}`);

  assert.equal(this.$().text().trim(), '');

  // Template block usage:
  this.render(hbs`
    {{#semester-listitem}}
      template block text
    {{/semester-listitem}}
  `);

  assert.equal(this.$().text().trim(), 'template block text');
});
