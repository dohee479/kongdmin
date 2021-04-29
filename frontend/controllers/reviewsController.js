angular.module("app")
  .controller("reviewsController", function($scope, reviewsService, $route) {

    $scope.keywords = ["작성자", "내용", "상품명"];
    $scope.deleteId = [];

    $scope.$on("$routeChangeSuccess", () => {
      $scope.getReviewList("", "", 1, "");
    });

    $scope.getReviewList = (keyword, kcontent, pageNo, sort) => {
      console.log(keyword, kcontent, pageNo);
      if (!kcontent) {
        kcontent = "";
      }
      reviewsService.list(keyword, kcontent, pageNo, sort)
        .then((response) => {
          $scope.pager = response.data.pager;
          $scope.reviews = response.data.reviewList;
          $scope.pageRange = [];
          $scope.reviews.forEach(element => {
            if ($scope.deleteId.includes(element.review_id)) {
              element.checked = true;
            } else {
              element.checked = false;
            }
          })
          for (i = response.data.pager.startPageNo; i <= response.data.pager.endPageNo; i++) {
            $scope.pageRange.push(i);
          }
          console.log($scope.pageRange);
        })
    };

    $scope.toggle = (review_id) => {
      console.log($("#review-detail" + review_id));
      $("#review-detail" + review_id).slideToggle();
    }

    $scope.getReviewImg = (review_id) => {
      return reviewsService.getReviewImg(review_id);
    }

    $scope.changeSort = () => {
      if ($scope.sort === "asc") {
        $scope.sort = "desc";
      } else {
        console.log("들어옴")
        $scope.sort = "asc";
      }
    }
    
    $scope.delete = () => {
      reviewsService.delete($scope.deleteId)
        .then((response) => {
          $route.reload();
        })
    }

    $scope.addDelete = (index, review_id) => {
      if ($scope.reviews[index].checked === true) {
        $scope.reviews[index].checked = false;
        $scope.deleteId.splice($scope.deleteId.indexOf(review_id), 1)
      } else {
        $scope.reviews[index].checked = true;
        $scope.deleteId.push(review_id)
      }
    }

    $scope.checked = (index, review_id) => {
      if ($scope.deleteId.includes(review_id)) {
        return true
      } else {
        return false;
      }
    }
  })
  .directive('ngEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if (event.which === 13) {
                scope.$apply(function () {
                    scope.$eval(attrs.ngEnter);
                });

                event.preventDefault();
            }
        });
    };
});