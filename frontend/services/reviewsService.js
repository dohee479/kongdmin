angular.module("app")
  .factory("reviewsService", function($http) {

    const BASE_URL = "http://localhost:8080/review";
    
    return {
      list: function(keyword, kcontent, pageNo=1, sort) {
        console.log(keyword, kcontent, pageNo)
        return $http.get(BASE_URL, {params:{keyword, kcontent, pageNo, sort}})
      },

      getReviewImg: function(review_id) {
        return `${BASE_URL}/review-attach/${review_id}`
      },

      delete: function(deleteId) {
        return $http.delete(`${BASE_URL}/delete`, {params:{deleteId}})
      }
    }
  })