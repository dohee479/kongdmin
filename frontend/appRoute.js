// 이미 만들어진 모듈을 가져와서 추가적으로 구성하겠다.
angular.module("app")
  .config(function($locationProvider, $routeProvider) {
    // HTML5 모드 활성화
    // $locationProvider.html5Mode({
    //   enabled: true,
    //   requireBase: true
    // });

    // 라우트 정의
    $routeProvider
    .when("/", {templateUrl: "views/home/home.html"})
    //.when("/login", {templateUrl: "login.html", controller:"mainController"})
    .when("/user", {templateUrl: "views/user/index.html", controller:"usersController"})
    .when("/question", {templateUrl: "views/qna/index.html", controller:"questionsController"})
    .otherwise({redirectTo: "/"});
  });