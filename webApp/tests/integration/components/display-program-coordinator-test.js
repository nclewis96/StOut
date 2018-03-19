import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';

moduleForComponent('display-program-coordinator', 'Integration | Component | display program coordinator', {
  integration: true
});

test('it renders', function(assert) {

  // Set any properties with this.set('myProperty', 'value');
  // Handle any actions with this.on('myAction', function(val) { ... });

  this.render(hbs`{{display-program-coordinator}}`);

  assert.equal(this.$().text().trim(), '');

  // Template block usage:
  this.render(hbs`
    {{#display-program-coordinator}}
      template block text
    {{/display-program-coordinator}}
  `);

  assert.equal(this.$().text().trim(), 'template block text');
});
