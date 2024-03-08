const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function($scope, $http) {

	$scope.getItems = function() {
		$http({
			method: 'GET',
			url: "/rest/cart",
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			}
		}).then(
			function successCallback(response) {// Nếu thành công
				console.log(response.data)
				$scope.cart.items = response.data;
			},
			function errorCallback(response) { // Nếu thất bại

				console.log(response.data)
			}
		)
	}
	$scope.cart = {
		items: [],


		add(id) {
			var authInput = document.getElementById('auth');
			$scope.remoteUser = authInput ? authInput.value : null;
			if ($scope.remoteUser) {
				$http({
					method: 'POST',
					url: "/rest/cart/" + id,
					headers: {
						'Content-Type': 'application/x-www-form-urlencoded'
					}
				}).then(
					function successCallback(response) {// Nếu thành công
						$scope.getItems()
					},
					function errorCallback(response) { // Nếu thất bại

						console.log(response.data)
					}
				)
			} else {
				alert("Vui lòng đăng nhập !")
			}

		},

		// xóa sản phẩm khỏi giỏ hàng
		remove(id) {
			$http({
				method: 'delete',
				url: "/rest/cart/" + id,
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				}
			}).then(
				function successCallback(response) {// Nếu thành công
					$scope.getItems()
				},
				function errorCallback(response) { // Nếu thất bại

					console.log(response.data)
				}
			)
		},

		// xóa sạch giỏ hàng
		clear() {
			$http({
				method: 'POST',
				url: "/rest/cart/clear",
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				}
			}).then(
				function successCallback(response) {// Nếu thành công
					$scope.getItems()
				},
				function errorCallback(response) { // Nếu thất bại
					console.log(response.data)
				}
			)
		},

		// tính thành tiền của 1 sản phẩm
		amt_of(item) { },

		// tính tổng số lượng các mặt hàng trong giỏ
		get count() {
			return this.items
				.map(item => item.soluong)
				.reduce((total, qty) => total += qty, 0);
		},

		// tổng thành tiền các mặt hàng trong giỏ
		get amount() {
			var amount =
				this.items
					.map(item => item.soluong * item.lephuc.gia)
					.reduce((total, qty) => total += qty, 0);
			return amount;
		},

		// lưu giỏ hàng vào local storage
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},
		changeSL(item) {

			if (item.soluong === '' || item.soluong < 1 || item.soluong == null) {
				item.soluong = 1;
			} else {
				$http({
					method: 'POST',
					url: "/rest/cart/update",
					params: {
						idCart: item.id,
						soluong: item.soluong
					},
					headers: {
						'Content-Type': 'application/x-www-form-urlencoded'
					}
				}).then(
					function successCallback(response) {// Nếu thành công
						console
						$scope.getItems()
					},
					function errorCallback(response) { // Nếu thất bại

						console.log(response.data)
					}
				)
			}


		},
		// đọc giỏ hàng từ local storage
		loadFromLocalStorage() {
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
		},

	}
	// $scope.cart.loadFromLocalStorage();
	$scope.getItems();


	// Đặt hàng
	$scope.order = {
		ghi() {
			sessionStorage.setItem("cachnhan", document.getElementById('luucachnhan').value);
			sessionStorage.setItem("diachi", document.getElementById('luudiachi').value);
		},
		ngaytao: new Date(),
		//cachnhan: true,
		//hinhthuc:true,
		taikhoan: { tentaikhoan: $("#tentaikhoan").text() },
		//trangthai: "1",
		tongtien: $scope.cart.amount,
		get chitietdonhangs() {
			console.log($scope.cart.amount)
			return $scope.cart.items.map(item => {
				return {
					lephuc: { id: item.lephuc.id },
					gia: item.lephuc.gia,
					soluong: item.soluong
				}
			});
		},
		purchase() {
			var order = angular.copy(this);
			order.tongtien = $scope.cart.amount + (order.cachnhan == 'false' ? 50000 : 0);
			console.log(order)
			// Thực hiện đặt hàng
			$http.post("/rest/orders", order).then(resp => {
				alert("Đặt hàng thành công!");
				$scope.cart.clear();
				location.href = "/donhang/detail/" + resp.data.id;
			}).catch(error => {
				alert("Đặt hàng lỗi!")
				console.log(error)
			})
		},
		cachnhan: sessionStorage.getItem("cachnhan"),
		hinhthuc: false,
		trangthai: "2",
		diachi: sessionStorage.getItem("diachi"),
		payment() {
			var order = angular.copy(this);
			order.tongtien = $scope.cart.amount + (order.cachnhan == 'false' ? 50000 : 0);
			console.log(order)
			// Thực hiện đặt hàng
			$http.post("/rest/orders", order).then(resp => {
				alert("Đặt hàng thành công!");
				$scope.cart.clear();
				sessionStorage.clear();
				location.href = "/donhang/detail/" + resp.data.id;
			}).catch(error => {
				alert("Đặt hàng lỗi!")
				console.log(error)
			})

		}
	}
}
)