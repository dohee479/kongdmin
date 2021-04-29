angular.module("app")
  .factory("homeService",function($http) {
    const BASE_URL="http://localhost:8080/home";

    return{
      monthpriceList: function(){
        return $http.get(BASE_URL+"/monthprice");
      },
      monthcountList: function(){
        return $http.get(BASE_URL+"/monthcount");
      },
      countrycountList: function(){
        return $http.get(BASE_URL+"/countrycount");
      },
      tastecountList: function(){
        return $http.get(BASE_URL+"/tastecount");
      },
      orderCount: function(){
        return $http.get(BASE_URL+"/ordercount");
      },
      productBycountryList: function(product_country){
        return $http.get(BASE_URL+"/productcountry",{params:{product_country}})
      },
      productBytasteList: function(product_taste){
        return $http.get(BASE_URL+"/producttaste",{params:{product_taste}})
      },
      productCount: function(){
        return $http.get(BASE_URL+"/productcount");
      },
      datePrice: function(order_month){
        return $http.get(BASE_URL+"/dateprice",{params:{order_month}})
      },
      orderList: function(order_date){
        return $http.get(BASE_URL+"/orderlist",{params:{order_date}})
      }
    }

  });