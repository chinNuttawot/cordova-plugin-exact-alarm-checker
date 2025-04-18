var exec = require('cordova/exec');

exports.checkPermission = function (success, error) {
  exec(function (result) {
    success(result === 1);
  }, error, "ExactAlarm", "checkPermission", []);
};
