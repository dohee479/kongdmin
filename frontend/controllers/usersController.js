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
      usersService.list(pageNo)
        .then((response) => {
          $scope.pager = response.data.pager;
          $scope.users = response.data.users;
          $scope.pageRange = [];
          for(var i=$scope.pager.startPageNo; i<=$scope.pager.endPageNo; i++) {
            $scope.pageRange.push(i);
          }
          $scope.view = "list";
        });
    };

    $scope.read = (user_id) => {
      usersService.read(user_id)
        .then((response) => {
          $scope.user = response.data;
          $scope.view = "read";
        });
    };

    /*$scope.createBoard = (board) => {
      console.log("asd");

      if(user && board.btitle && board.bcontent) {
        var formData = new FormData();
        formData.append("btitle", board.btitle);
        formData.append("bcontent", board.bcontent);
        formData.append("bwriter", $rootScope.uid);
        var battach = $("#battach")[0].files[0];
        if(battach) {
          formData.append("battach", battach);
        }
        userService.create(formData)
          .then((response) => {
            $scope.getList(1);
            $scope.view = "list";
          });
      }
    };
      */
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