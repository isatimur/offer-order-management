(function () {
    'use strict';

    angular
        .module('offerOrderManagApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();
