'use strict';

angular.module('msplearningApp').service('appService', function ($rootScope, $http, userService) {

	this.getFeactures = function(success, error) {
		$http.get($rootScope.getResourceAddress('feature/'))
		.success(function(data) {
			callBackSuccess(data, success);
		})
		.error(function(data) {
			callBackError(data, error);
		});
	};

	this.getApps = function(success, error) {
		$http.get($rootScope.getResourceAddress('app/user/' + userService.getAuthenticatedUser().id))
		.success(function(data) {
			callBackSuccess(data, success);
		})
		.error(function(data) {
			callBackError(data, error);
		});
	};

	this.create = function(app, success, error) {
		var appFeatures = [];

		for (var i = 0; i < app.features.length; i++) {
			var feature = app.features[i];
			appFeatures.push({
				id : {feature : feature}
			});
		};

		app.appFeatures = appFeatures;

		$http.post($rootScope.getResourceAddress('app/user/' + userService.getAuthenticatedUser().id), app)
		.success(function(data) {
			callBackSuccess(data, success);
		})
		.error(function(data) {
			callBackError(data, error);
		});
	};

	function callBackSuccess(data, callback) {
		if(callback !== undefined) {
			callback(data);
		}
	};

	function callBackError(data, callback) {
		if(callback !== undefined) {
			callback(data);
		}
	};
});