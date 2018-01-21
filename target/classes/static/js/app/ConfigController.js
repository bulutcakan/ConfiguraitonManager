'use strict';

angular.module('configApp').controller('ConfigController',
    ['ConfigService', '$scope',  function( ConfigService, $scope) {

        var self = this;
        self.param = {};
        self.params=[];
        $scope.isActive = [
            {text : "Active", value : 1},
            {text : "Deactive", value : 0}
        ];

        self.submit = submit;
        self.getAllParams = getAllParams;
        self.createParam = createParam;
        self.updateParam = updateParam;
        self.removeParam = removeParam;
        self.editParam = editParam;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.param.id === undefined || self.param.id === null) {
                console.log('Saving New param', self.param);
                createParam(self.param);
            } else {
                updateParam(self.param, self.param.id);
                console.log('param updated with id ', self.param.id);
            }
        }

        function createParam(param) {
            console.log('About to create param');
            ConfigService.createParam(param)
                .then(
                    function (response) {
                        console.log('param created successfully');
                        self.successMessage = 'param created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.param={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating param');
                        self.errorMessage = 'Error while creating param: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateParam(param, id){
            console.log('About to update param');
            ConfigService.updateParam(param, id)
                .then(
                    function (response){
                        console.log('param updated successfully');
                        self.successMessage='param updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating param');
                        self.errorMessage='Error while updating param '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeParam(id){
            console.log('About to remove param with id '+id);
            ConfigService.removeParam(id)
                .then(
                    function(){
                        console.log('param '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing param '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllParams(){
            return ConfigService.getAllParams();
        }

        function editParam(id) {
            self.successMessage='';
            self.errorMessage='';
            ConfigService.getParam(id).then(
                function (param) {
                    self.param = param;
                },
                function (errResponse) {
                    console.error('Error while removing param ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.param={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);