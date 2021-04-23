// app이라는 module을 새로 만든다.
// [] 안에는 의존 모듈
angular.module("app", ["ngRoute"])
  .config(function() {
  
 
  })
  .controller("mainController",function($scope,$location,$route) {
    $scope.reloadable = (path) => {
      if($location.url().includes(path)){
          $route.reload();
          
      }
  }
  });