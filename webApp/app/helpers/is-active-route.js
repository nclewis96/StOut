import Ember from 'ember';

export function isActiveRoute([arg0, arg1]) {
  console.log(arg0);
  return arg0 === arg1;
}

export default Ember.Helper.helper(isActiveRoute);
