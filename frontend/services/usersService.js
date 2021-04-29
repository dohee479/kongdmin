angular.module("app")
  .factory("usersService", function($http) {
    //변수 선언
    const BASE_URL = "http://localhost:8080/user";
    //서비스 객체 리턴
    return {
      list: function(pageNo=1,keyword=null) {
        const promise = $http.get(BASE_URL, {params:{pageNo,keyword}});
        console.log("pageNo:"+pageNo);
        console.log("promise:"+promise);
        return promise;
      },
      read: function(user_id) {
        const promise = $http.get(BASE_URL + "/" + user_id);
        return promise;
      },
      delete: function(user_id){
        const promise=$http.delete(BASE_URL + "/" + user_id);
        return promise;
      }
    }
  });