angular.module("app")
  .factory("productsService", function($http) {

    const BASE_URL = "http://localhost:8080/product"

    return {
      create: function(formData) {
        return $http.post(BASE_URL, formData, {headers:{"Content-Type":undefined}});
      },

      list: function(keyword, kcontent, pageNo=1, sort) {
        return $http.get(BASE_URL, {params:{keyword, kcontent, pageNo, sort}})
      },

      getProductImg: function(product_id) {
        return `${BASE_URL}/product-attach/${product_id}`
      },
      
      delete: function(deleteId) {
        return $http.delete(`${BASE_URL}/delete`, {params:{deleteId}})
      }

    }
  })