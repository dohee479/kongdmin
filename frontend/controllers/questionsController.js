angular.module("app")
  .controller("questionsController", function($scope, questionsService, $rootScope) {
    $scope.$on("$routeChangeSuccess", () => {
      $scope.getList(1);
    });

    $scope.view = "list";

    $scope.getView = () => {
      switch($scope.view) {
        case "list": return "views/qna/list.html";
        //case "create": return "views/user/create.html";
        case "read": return "views/qna/read.html";
        //case "update": return "views/user/update.html";
      }
    };

    $scope.getList = (pageNo) => {
      questionsService.list(pageNo)
        .then((response) => {
          $scope.pager = response.data.pager;
          $scope.questions = response.data.questions;
          $scope.pageRange = [];
          for(var i=$scope.pager.startPageNo; i<=$scope.pager.endPageNo; i++) {
            $scope.pageRange.push(i);
          }
          $scope.view = "list";
        });
    };

    $scope.read = (question_id) => {
      questionsService.read(question_id)
        .then((response) => {
          $scope.question = response.data;
          $scope.view = "read";
        });
    };

    $scope.cancel = () => {
      $scope.getList($scope.pager.pageNo);
      $scope.view = "list";
    };

    $scope.updateQuestion=(question)=>{
      questionsService.update(question)
        .then((response)=>{
          $scope.read(question.question_id);
          $scope.view = "read";
        });
    }

    $scope.deleteQuestion=(question_id)=>{
      questionsService.delete(question_id)
        .then((response)=>{
          $scope.getList(1);
          $scope.view = "list";
      });
    };
  });