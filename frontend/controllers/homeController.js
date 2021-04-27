angular.module("app")
  .controller("homeController", function($scope,homeService) {
    //월별 수익 현황
    homeService.monthpriceList()
      .then((response) =>{

          $scope.monthlyPriceList= new Array();
          $scope.sum=0;
          for(var i=0;i<response.data.length;i++){
            var data=new Object();
            var year=response.data[i].month.substr(0,4);
            var month=response.data[i].month.substr(4,2);
            month=monthToString(month);

            data.y=response.data[i].month_total_price;
            $scope.sum+=data.y;
            
            data.label=month+" "+year;
            $scope.monthlyPriceList.push(data);
          }
          $scope.sum=$scope.sum.toLocaleString('ko-KR');
          $scope.chart = new CanvasJS.Chart("chartContainer", {
            animationEnabled: true,
            title: {
              text: "1년 총 수익 현황 "+$scope.sum+"원",
              fontSize: 25,
              horizontalAlign: "left",
              padding: 15
            },    
            data: [
            {
              type: "column",
              indexLabelPlacement: "outside",
              dataPoints: $scope.monthlyPriceList
            }
            ]
          });
          $scope.chart.render(); 

      });

      //나라별 주문 수요
      homeService.countrycountList()
        .then((response)=>{
          $scope.CountryCountList= new Array();
          for(var i =0;i<response.data.length;i++){
            var data=new Object();
            var country=response.data[i].product_country;
            var count=response.data[i].order_count;
            data.y=count;
            data.label=country;
            $scope.CountryCountList.push(data);
          }
          $scope.chart2 = new CanvasJS.Chart("chartContainer2", {
            animationEnabled: true,
            title: {
              text: "나라별 주문 수요",
              fontSize: 25,
              padding: 15
            },    
            data: [
            {
              type: "pie",
              indexLabelPlacement: "outside",
              dataPoints: $scope.CountryCountList
            }
            ]
          });
          $scope.chart2.render(); 

        }) 

      //맛별 주문 수요
      homeService.tastecountList()
      .then((response)=>{
        $scope.TasteCountList= new Array();
        for(var i =0;i<response.data.length;i++){
          var data=new Object();
          var taste=response.data[i].product_taste;
          var count=response.data[i].order_count;
          data.y=count;
          data.label=taste;
          $scope.TasteCountList.push(data);
        }
        $scope.chart3 = new CanvasJS.Chart("chartContainer3", {
          animationEnabled: true,
          title: {
            text: "맛별 주문 수요",
            fontSize: 25,
            padding: 15
          },    
          data: [
          {
            type: "pie",
            indexLabelPlacement: "outside",
            dataPoints: $scope.TasteCountList
          }
          ]
        });
        $scope.chart3.render(); 

      }) 
        //월별 주문수량
      homeService.monthcountList()
        .then((response) => {
          $scope.monthlyCountList=new Array;
          for(var i=0;i<response.data.length;i++){
            var data=new Object();
            var year=response.data[i].month.substr(0,4);
            var month=response.data[i].month.substr(4,2);
            month=monthToString(month);
            data.y=response.data[i].month_count;
            data.label=month+" "+year;
            $scope.monthlyCountList.push(data);
          }
          $scope.chart4 = new CanvasJS.Chart("chartContainer4", {
            animationEnabled: true,
            title: {
              text: "주문수",
              fontSize: 25,
              horizontalAlign: "left",
              padding: 15
            },    
            data: [
            {
              type: "spline",
              indexLabelPlacement: "outside",
              dataPoints: $scope.monthlyCountList
            }
            ]
          });
          $scope.chart4.render();

        })

    
    function monthToString(month){
      switch(month){
        case '01' : month="Jan"; break;
        case '02' : month="Feb"; break;
        case '03' : month="Mar"; break;
        case '04' : month="Apr"; break;
        case '05' : month="May"; break;
        case '06' : month="Jun"; break;
        case '07' : month="Jul"; break;
        case '08' : month="Aug"; break;
        case '09' : month="Sept"; break;
        case '10' : month="Oct"; break;
        case '11' : month="Nov"; break;
        case '12' : month="Dec"; break;
      }
      return month;
    }
  });
