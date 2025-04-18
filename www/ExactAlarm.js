var exec = require('cordova/exec');

exports.checkPermission = function(success, error) {
  exec(success, error, "ExactAlarm", "checkPermission", []);
};
