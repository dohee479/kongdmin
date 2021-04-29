angular.module("app")
  .controller("calendarController", function($scope,homeService) {
    $scope.monthList = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];


    $scope.currentDate={};
    $scope.currentDate.year= new Date().getFullYear();
    $scope.currentDate.month=new Date().getMonth()+1;
    $scope.currentDate.dayone=new Date($scope.currentDate.year+'-'+$scope.currentDate.month+'-1').getDay();
    $scope.currentDate.lastdate=new Date($scope.currentDate.year,$scope.currentDate.month,0).getDate();
    $scope.click_date=new Date().getDate();
    $scope.click_year=$scope.currentDate.year;
    $scope.click_month=$scope.currentDate.month;

    order_list();
    buildCalendar();
    
    

    
    $scope.prevMonth=()=>{
      if($scope.currentDate.month===1){
        $scope.currentDate.year-=1;
        $scope.currentDate.month=12;
      }else{
        $scope.currentDate.month-=1;
      }
      $scope.currentDate.dayone=new Date($scope.currentDate.year+'-'+$scope.currentDate.month+'-1').getDay();
      $scope.currentDate.lastdate=new Date($scope.currentDate.year,$scope.currentDate.month,0).getDate();
      buildCalendar();
    }

    $scope.nextMonth=()=>{
      if($scope.currentDate.month===12){
        $scope.currentDate.year+=1;
        $scope.currentDate.month=1;
      }else{
        $scope.currentDate.month+=1;
      }
      $scope.currentDate.dayone=new Date($scope.currentDate.year+'-'+$scope.currentDate.month+'-1').getDay();
      $scope.currentDate.lastdate=new Date($scope.currentDate.year,$scope.currentDate.month,0).getDate();
      buildCalendar();
    }
    $scope.date_click=(day)=> {
      $scope.click_date=day.date;
      $scope.click_year=$scope.currentDate.year;
      $scope.click_month=$scope.currentDate.month;
      order_list();
    };


    function buildCalendar(){
      $scope.weekly=[];
      var cnt=1;
      var month=$scope.currentDate.month.toString();
      month=month_trasnf(month);

      var order_month=$scope.currentDate.year.toString()+"/"+month;
      homeService.datePrice(order_month)
        .then((response) => {
          $scope.monthTotalPrice=0;
          for(var i=0;i<response.data.length;i++){
            $scope.monthTotalPrice+=response.data[i].order_total_price;
          }
          var idx=0;
          for(var i=0;i<6;i++){
            var first=0;
            var week=new Array();
              for(var k=0;k<7;k++){
                week.push({date :"",order_total_price : 0});
              }
            
              for(var j=0;j<7;j++){
                if(i===0 &&first===0){
                  j=$scope.currentDate.dayone;
                  first=1;
                }
                if(cnt>$scope.currentDate.lastdate){
                  break;
                }
                week[j].date=cnt++;
                week[j].order_total_price=response.data[idx++].order_total_price;
                
              }
            $scope.weekly.push(week);
          }
        })

      
    }

    function month_trasnf(month){
      if(month.length===1){
        month="0"+month;
      }
      return month;
      
    }
    function date_transf(date){
      if(date.length===1){
        date="0"+date;
      }
      return date;
    }
    function order_list(){
      var month=month_trasnf($scope.currentDate.month.toString());
      var click_date=date_transf($scope.click_date.toString());
      var order_date=$scope.currentDate.year+"/"+month+"/"+click_date;
      homeService.orderList(order_date)
        .then((response)=>{
          $scope.dateTotalPrice=0;
          $scope.dateOrderList=response.data;
          for(var i=0;i<response.data.length;i++){
            $scope.dateTotalPrice+=$scope.dateOrderList[i].order_total_price;
            
          }
        })
    }
    
    
  })