var exec = require('cordova/exec');

var compass = {
  /**
    * Asynchronously acquires the current heading.
    * @param {Function} successCallback The function to call when the heading
    * data is available
    * @param {Function} errorCallback The function to call when there is an error
s    */

  checkPermission: function (successCallback, errorCallback) {
    exec(successCallback, errorCallback, "ExactAlarm", "checkPermission", []);
  }
}

module.exports = compass;