angular.module("app")
  .controller("usersController", function($scope, usersService, $rootScope) {
    $scope.$on("$routeChangeSuccess", () => {
      $scope.getList(1);
    });

    $scope.view = "list";

    $scope.getView = () => {
      switch($scope.view) {
        case "list": return "views/user/list.html";
        //case "create": return "views/user/create.html";
        case "read": return "views/user/read.html";
        //case "update": return "views/user/update.html";
      }
    };

    $scope.getList = (pageNo) => {
      console.log("scope.keyword 값 : "+$scope.keyword);
      usersService.list(pageNo,$scope.keyword)
        .then((response) => {
          $scope.pager = response.data.pager;
          $scope.users = response.data.users;
          $scope.pageRange = [];
          for(var i=$scope.pager.startPageNo; i<=$scope.pager.endPageNo; i++) {
            $scope.pageRange.push(i);
          }
          console.log("Response-Data-Pager: "+response.data.pager);
          console.log("response: "+response);
          console.log("pageRange "+$scope.pageRange);
          $scope.view = "list";
        });
    };

    $scope.search=(keyword)=>{
      console.log(keyword);
      $scope.keyword=keyword;
      $scope.getList(1);
    };

    $scope.read = (user_id) => {
      usersService.read(user_id)
        .then((response) => {
          $scope.user = response.data;
          $scope.view = "read";
        });
    };

    $scope.cancel = () => {
      $scope.getList($scope.pager.pageNo);
      $scope.view = "list";
    };

    $scope.deleteUser=(user_id)=>{
      usersService.delete(user_id)
        .then((response)=>{
          $scope.getList(1);
          $scope.view = "list";
      });
    };
  });