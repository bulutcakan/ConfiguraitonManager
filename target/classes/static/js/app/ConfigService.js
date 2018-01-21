'use strict';

angular.module('configApp').factory('ConfigService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllParams: loadAllParams,
                getAllParams: getAllParams,
                getParam: getParam,
                createParam: createParam,
                updateParam: updateParam,
                removeParam: removeParam
            };

            return factory;

            function loadAllParams() {
                console.log('Fetching all Config');
                var deferred = $q.defer();
                $http.get(urls.CONFIG_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all Config');
                            $localStorage.params = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading Config');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllParams(){
                return $localStorage.params;
            }

            function getParam(id) {
                console.log('Fetching param with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.CONFIG_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Config with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Config with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createParam(param) {
                console.log('Creating Config');
                var deferred = $q.defer();
                $http.post(urls.CONFIG_SERVICE_API, param)
                    .then(
                        function (response) {
                            loadAllParams();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Config : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateParam(param, id) {
                console.log('Updating Config with id '+id);
                var deferred = $q.defer();
                $http.put(urls.CONFIG_SERVICE_API + id, param)
                    .then(
                        function (response) {
                            loadAllParams();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Config with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeParam(id) {
                console.log('Removing Config with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.CONFIG_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllParams();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Config with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);