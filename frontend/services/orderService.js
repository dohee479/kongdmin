angular.module("app")
  .factory("orderService", function($http){
    const BASE_URL = "http://localhost:8080/order"; 
    return {
      list: function(pageNo=1, 
                     state=7, 
                     search_user_id, 
                     fromPrice, 
                     toPrice, 
                     fromDate, 
                     toDate,
                     sortDsc=false
                     ) {
  
        if(!fromDate){
          var now = new Date();
          fromDate= new Date(now.setFullYear(now.getFullYear() -10));
        }

        if(!toDate){
          toDate= new Date();
        }

        if(!fromPrice){
          fromPrice = 0;
        }
  
        if(!toPrice){
          toPrice = 10000000;
        }

        const promise = $http.get(BASE_URL 
                                  + "?pageNo=" + pageNo 
                                  + "&state=" + state 
                                  + "&search_user_id=" + search_user_id 
                                  + "&fromPrice=" + fromPrice 
                                  + "&toPrice=" + toPrice 
                                  + "&fromDate=" + dateFormatting(fromDate) 
                                  + "&toDate=" + dateFormatting(toDate)
                                  + "&sortDsc=" + sortDsc);
        return promise;
      },

      read: function(order_id){
        const promise = $http.get(BASE_URL+"/"+order_id);
        return promise;     
      },

      getMainProduct: function(order_id){
        const promise = $http.get(BASE_URL+"/mainProduct/"+order_id);
        return promise;
      },

      changeState: function(order_id, changeState){
        const promise = $http.put(BASE_URL+"/changeState/?order_id=" + order_id + "&changeState=" + changeState);
        return promise;
      },

      updateOrder: function(order){
        const promise = $http.put(BASE_URL, order, {headers:{"Content-Type":"application/json"}});
        return promise;
      }
    }
  });

  function dateFormatting(date) {
    var year = date.getFullYear();

    var month = date.getMonth() + 1;
    if (month < 10)  {
        month = '0' + month;
    }

    var date = date.getDate();
    if (date < 10) {
        date = '0' + date;
    }
    
    return year + month + date;
  }
