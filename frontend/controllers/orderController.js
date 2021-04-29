angular.module("app")
  .controller("orderController", function($scope, orderService){
    $scope.$on("$routeChangeSuccess", () => {
      $scope.getList(1,7);
    })

    $scope.view = "list";
    $scope.getView = () => {
      switch($scope.view){
        case "list" : return "views/order/list.html"
        case "read" : return "views/order/read.html"
      }
    };

    $scope.search = {};
    $scope.getList = (pageNo, state) => {
      $scope.sel = 
        {s7: false,
         s1: false,
         s5: false,
         s9: false
        }
      
      // const str = "s" + $scope.state;
      // $scope.search.str = true;
      if(state == 1)
        $scope.sel.s1 = true;

      if(state == 7)
        $scope.sel.s7 = true;

      if(state == 5)
        $scope.sel.s5 = true;

      if(state == 9)
        $scope.sel.s9 = true;
      

      $scope.state = state;
      orderService.list(pageNo, state, $scope.search.user_id)
        .then((response) => {
          $scope.pager = response.data.pager;
          $scope.orders = response.data.order;
          $scope.stateCount = response.data.state;
          $scope.pageRange = [];
          for(var i=$scope.pager.startPageNo; i<=$scope.pager.endPageNo; i++){
            $scope.pageRange.push(i);
          }
          $scope.view = "list";
        });
    };

    $scope.read = (order_id) => {
      orderService.read(order_id)
        .then((response) => {
          $scope.order = response.data.order;
          $scope.orderProducts = response.data.orderProducts;
          $scope.view = "read";
        });
    };

    // $scope.searchByUserId = (pagneNo, state) => {
    //   console.log(pagneNo + "" + state + "" + $scope.search_user_id)
    //    orderService.search(pageNo, state, search_user_id)
    //      .then((response) => {

    //      });
    // }
 
    $scope.getMainProduct = (order_id) => {
      orderService.getMainProduct(order_id)
        .then((response) => {
          console.log(response.data.mainProduct);
          //$scope.mainProduct = response.data.mainProduct;
        });
      
      //return orderService.getMainProduct(order_id);
    }


    // function getMainProduct(order_id, $scope){
    //   console.log(order_id);
    //   orderService.getMainProduct(order_id)
    //    .then((response) => {
    //      $scope.mainProduct=response.data.mainProduct;
    //      $scope.view="list";
         
    //   })
    // }
  })