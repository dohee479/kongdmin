angular.module("app")
  .controller("orderController", function($scope, orderService){
    $scope.$on("$routeChangeSuccess", () => {
      $scope.getList(1,7);
    })

    $scope.view = "list";
    $scope.search = {};
    $scope.getView = () => {
      switch($scope.view){
        case "list" : return "views/order/list.html"
        case "read" : return "views/order/read.html"
      }
    };
    
    $scope.getList = (pageNo, state) => {
 
      $scope.sel = {
         s7: false,
         s1: false,
         s5: false,
         s9: false,
         s2: false,
         s3: false
        }
      
      if(state == 1)
        $scope.sel.s1 = true;

      if(state == 7)
        $scope.sel.s7 = true;

      if(state == 5)
        $scope.sel.s5 = true;

      if(state == 9)
        $scope.sel.s9 = true;

      if(state == 2)
        $scope.sel.s2 = true;

      if(state == 3)
        $scope.sel.s3 = true;    
      
      $scope.state = state;
      
      orderService.list(pageNo, 
                        state, 
                        $scope.search.user_id, 
                        $scope.search.fromPrice, 
                        $scope.search.toPrice,
                        $scope.search.fromDate,
                        $scope.search.toDate,
                        $scope.search.sortDsc)

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

    $scope.sortDsc = (state) =>{
      $scope.search.sortDsc=true;
      $scope.getList(1,state);
    }

    $scope.read = (order_id) => {
      $scope.isModifyed = false;
      orderService.read(order_id)
        .then((response) => {
          $scope.order = response.data.order;
          $scope.orderProducts = response.data.orderProducts;
          $scope.view = "read";
        });
    };

    $scope.changeState = (order_id, changeState) => {
      orderService.changeState(order_id, changeState)
        .then((response)=> {
          $scope.order = response.data.order;
          $scope.orderProducts = response.data.orderProducts;   
          $scope.view = "read";
        });
    };

    $scope.updateOrder = (order) =>{
      if(order){
        orderService.updateOrder(order)
          .then((response) => {
            $scope.read(order.order_id);
            $scope.view = "read";
            $scope.isModifyed = true;
          })

      }
    };
  })