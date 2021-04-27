// app이라는 module을 새로 만든다.
// [] 안에는 의존 모듈
angular.module("app", ["ngRoute"])
  
  .run(function($rootScope,$http){
    console.log("app - run callback");

    //세션 저장소에 있는 uid, authToken을 읽기
    $rootScope.uid=sessionStorage.getItem("uid");
    $rootScope.authToken=sessionStorage.getItem("authToken");
    //$rootScope.authToken의 값의 변화를 감시
    $rootScope.$watch("authToken",(newValue)=>{
        if(newValue){
            $http.defaults.headers.common.authToken = newValue;
        }else{
            delete $http.defaults.headers.common.authToken;
        }
    });
  })

  //중첩된 컨트롤러 범위에서 사용할 수 있는 상태 데이터 및 함수
  .controller("mainController",function($rootScope,$scope,$location,$route,authService, $window){
    $scope.mainGetGreet=()=>{
      return "Hello! MainController";
    };

    $scope.logout=()=>{
      $rootScope.uid="";
      $rootScope.authToken="";
      sessionStorage.removeItem("uid");
      sessionStorage.removeItem("authToken");
    };

    $scope.$on("loginSuccess",(event,message)=>{
      console.log("mainController가 loginSuccess 방송 수신함");
      console.log(message);
      $rootScope.uid=message.uid;
    });

    $scope.$on("logout",(event,message)=>{
      console.log("mainController가 loginSuccess 방송 수신함");
      $rootScope.uid="";
    });

    //이전 URL과 동일한 URL일 경우 리프레쉬함
    $scope.reloadable=(path)=>{
      if($location.url().includes(path)){
        $route.reload();
      }
    };

    $scope.login=(user)=>{
      authService.login(user)
        .then((response)=>{
          
          console.log(user);

          $rootScope.uid=response.data.uid;
          $rootScope.authToken=response.data.authToken;


          sessionStorage.setItem("uid",response.data.uid);
          console.log(response.data);
          sessionStorage.setItem("authToken",response.data.authToken);

          $location.url("/");
        })
        .catch((response)=>{
          console.log(response.data.message);
          if(response.data.message=="Access Denied"){ // 둘중 하나라도 입력했을때
            $window.alert("아이디 또는 비밀번호를 확인해주세요.");
          }
          else{ //공백일때
            $window.alert("데이터를 입력해주세요.");
          }
        });
    };
  })
  .config(function() {
  
 
  })
  .controller("mainController",function($scope,$location,$route) {
    $scope.reloadable = (path) => {
      if($location.url().includes(path)){
          $route.reload();
          
      }
  }
  });