import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';

moduleForComponent('outcomes-content', 'Integration | Component | outcomes content', {
  integration: true
});

test('it renders', function(assert) {

  // Set any properties with this.set('myProperty', 'value');
  // Handle any actions with this.on('myAction', function(val) { ... });

  this.render(hbs`{{outcomes-content}}`);

  assert.equal(this.$().text().trim(), '');

  // Template block usage:
  this.render(hbs`
    {{#outcomes-content}}
      template block text
    {{/outcomes-content}}
  `);

  assert.equal(this.$().text().trim(), 'template block text');
});
