var app = angular.module('configApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/ConfigurationManager',
    CONFIG_SERVICE_API : 'http://localhost:8080/ConfigurationManager/api/configuration/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'ConfigController',
                controllerAs:'ctrl',
                resolve: {
                    params: function ($q, ConfigService) {
                        console.log('Load All Config Parameters');
                        var deferred = $q.defer();
                        ConfigService.loadAllParams().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

