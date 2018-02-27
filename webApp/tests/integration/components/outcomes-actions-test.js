import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';

moduleForComponent('outcomes-actions', 'Integration | Component | outcomes actions', {
  integration: true
});

test('it renders', function(assert) {

  // Set any properties with this.set('myProperty', 'value');
  // Handle any actions with this.on('myAction', function(val) { ... });

  this.render(hbs`{{outcomes-actions}}`);

  assert.equal(this.$().text().trim(), '');

  // Template block usage:
  this.render(hbs`
    {{#outcomes-actions}}
      template block text
    {{/outcomes-actions}}
  `);

  assert.equal(this.$().text().trim(), 'template block text');
});
