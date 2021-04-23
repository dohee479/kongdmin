angular.module("app")
  .factory("orderService", function($http){
    const BASE_URL = "http://localhost:8080/order"; 
    return {
      // 변수에 디폴트 값을 줄 수 있음
      list: function(pageNo=1, state=1, search_user_id="") {
        if(search_user_id != ""){
          const promise = $http.get(BASE_URL + "?pageNo=" + pageNo + "&state=" + state + "&search_user_id=" + search_user_id);
          return promise;
        }
        else{
          const promise = $http.get(BASE_URL + "?pageNo=" + pageNo + "&state=" + state);
          return promise;
        }
      },
      read: function(order_id){
        const promise = $http.get(BASE_URL+"/"+order_id);
        return promise;     
      },

      getMainProduct: function(order_id){
        const promise = $http.get(BASE_URL+"/mainProduct/"+order_id);
        return promise;
      },

      // searchByUserId: function(pageNo=1, state=1, search_user_id=""){
      //   const promise = $http.get(BASE_URL  + "?pageNo=" + pageNo + "&state=" + state + "&search_user_id=" + search_user_id);
      //   return promise;
      // }
    }
  });