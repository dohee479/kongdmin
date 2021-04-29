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
    .when("/user/login", {templateUrl: "views/user/login.html"})
    
    // product
    .when("/product", {templateUrl: "views/product/list.html", controller: "productsController"})
    .when("/product/create", {templateUrl: "views/product/create.html", controller: "productsController"})
    
    // review
    .when("/review", {templateUrl: "views/review/list.html", controller: "reviewsController"})
    .otherwise({redirectTo: "/"});
  });