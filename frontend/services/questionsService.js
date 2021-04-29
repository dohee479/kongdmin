angular.module("app")
  .factory("questionsService", function($http) {
    //변수 선언
    const BASE_URL = "http://localhost:8080/question";
    //서비스 객체 리턴
    return {
      list: function(pageNo=1) {
        const promise = $http.get(BASE_URL, {params:{pageNo}});
        return promise;
      },
      read: function(question_id) {
        const promise = $http.get(BASE_URL + "/" + question_id);
        return promise;
      },
      update: function(question){
        const promise=$http.put(BASE_URL,question);
        return promise;
      },
      delete: function(question_id){
        const promise=$http.delete(BASE_URL + "/" + question_id);
        return promise;
      }
    }
  });