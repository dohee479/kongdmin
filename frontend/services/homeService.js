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
      }
    }

  });