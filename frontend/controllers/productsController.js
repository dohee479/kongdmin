angular.module("app")
  .controller("productsController", function($location, $route, $scope, productsService, $timeout) {
    // create select(country, flavor)
    $scope.countries = ["과테말라", "브라질", "에티오피아", "케냐", "콜롬비아"];
    $scope.flavors = ["달콤쌉싸름", "묵직하고 달콤한 여운", "고소함/구수함", "은은하고 편안함", "상큼한 산미와 향"];

    $scope.keywords = ["상품명", "나라", "향/맛"];
    $scope.deleteId = [];

    $scope.createProduct = (product) => {
      if (!product) {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: '모든 항목을 입력해주세요',
        })
        return;
      } else {
        if (product.length !== 6) {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: '모든 항목을 입력해주세요',
          })
          return;
        }
      }
      let formData = new FormData();
      formData.append("product_title", product.title);
      formData.append("product_country", product.country);
      formData.append("product_taste", product.flavor);
      formData.append("product_price", product.price);
      formData.append("product_attach", $("#product_attach")[0].files[0])
      formData.append("product_detail_attach", $("#product_detail_attach")[0].files[0])
      productsService.create(formData)
        .then((response) => {
          $location.url(`/product`);
        })
    }

    $scope.$on("$routeChangeSuccess", () => {
      $scope.getProductList("", "", 1, "");
    });

    $scope.products = [];
    $scope.loading = true;
    $scope.pageNo = 1;

    $scope.getProductList = (keyword, kcontent, pageNo, sort) => {
      $scope.deleteId =[];
      if (!kcontent) {
        kcontent = "";
      }
      
      if (pageNo === 1) {
        $scope.products = [];
      }
      productsService.list(keyword, kcontent, pageNo, sort)
        .then((response) => {
          $scope.pager = response.data.pager;
          if (response.data.productList.length === 0) {
            $scope.loading = false;
            return;
          } else {
            $scope.loading = true;
          }
          $timeout(() => {
            $scope.products = $scope.products.concat(response.data.productList);
            if ($scope.sort === "desc") {
              $scope.products = $scope.products.sort(function(a, b) {
                return a.product_grade - b.product_grade;
              })
            } else if ($scope.sort === "asc") {
              $scope.products = $scope.products.sort(function(a, b) {
                return b.product_grade - a.product_grade; 
              })
            }
  
            if ($scope.sale === "desc") {
              $scope.products = $scope.products.sort(function(a, b) {
                return a.product_sale_count - b.product_sale_count;
              })
            } else if ($scope.sale === "asc") {
              $scope.products = $scope.products.sort(function(a, b) {
                return b.product_sale_count - a.product_sale_count; 
              })
            }
          }, 300)
        })
    }

    $(window).on("mousewheel", function() {
      if ($scope.loading && $(this).scrollTop() + $(this).innerHeight() > $("#infinite").height()) {
        // if ($scope.pageNO === 1) {
        //   $scope.loading = false;
        // }
        $scope.loading = true;
        $scope.pageNo++;
        console.log($scope.pageNo)
        $scope.getProductList($scope.keyword , $scope.kcontent, $scope.pageNo, "");
      }
    })


    $scope.getProductImg = (product_id) => {
      return productsService.getProductImg(product_id);
    }

    $scope.changeSort = () => {
      if ($scope.sort === "asc") {
        $scope.sort = "desc";
      } else {
        $scope.sort = "asc";
      }
    }

    $scope.addDelete = (index, product_id) => {
      if ($scope.products[index].checked === true) {
        $scope.products[index].checked = false;
        $scope.deleteId.splice($scope.deleteId.indexOf(product_id), 1)
      } else {
        $scope.products[index].checked = true;
        $scope.deleteId.push(product_id)
      }
    }

    $scope.delete = (deleteId) => {
      productsService.delete(deleteId)
        .then((response) => {
          $route.reload();
        })
    }

    $scope.changeSort = () => {
      if ($scope.sort === "asc") {
        $scope.sort = "desc";
        $scope.products = $scope.products.sort(function(a, b) {
          return a.product_grade - b.product_grade;
        })
      } else {
        $scope.sort = "asc";
        $scope.products = $scope.products.sort(function(a, b) {
          return b.product_grade - a.product_grade; 
        })
      }
    }

    $scope.sortSale = () => {
      if ($scope.sale === "asc") {
        $scope.sale = "desc";
        $scope.products = $scope.products.sort(function(a, b) {
          return a.product_sale_count - b.product_sale_count;
        })
      } else {
        $scope.sale = "asc";
        $scope.products = $scope.products.sort(function(a, b) {
          return b.product_sale_count - a.product_sale_count; 
        })
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