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
    .when("/user", {templateUrl: "views/user/index.html", controller:"usersController"})
    .when("/question", {templateUrl: "views/qna/index.html", controller:"questionsController"})
    .when("/order", {templateUrl: "views/order/index.html", controller: "orderController"})
    .when("/user/login", {templateUrl: "views/user/login.html"})
    .when("/home", {templateUrl: "views/home/home.html",controller:"homeController"})
    .otherwise({redirectTo: "/"});
  });