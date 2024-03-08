const app =angular.module("account-app",[]);
app.controller("account-ctrl",function($scope,$http,$location){
    $scope.authorities = [];
    $scope.items = [];
    $scope.form = {};
    $scope.roles = [];
    $scope.selection =[];
    // $scope.initialRoles= [{name:"Customer",value:'CUST'},{name:"Staff",value:'STAF'},{name:"Director",value:'DIRE'}]
    $scope.initialize = function(){
		//load all roles
		$http.get("/rest/roles").then(resp=>{
			$scope.roles = resp.data;
		});
		
		//load accounts
		$http.get("/rest/accounts").then(resp=>{
			$scope.items = resp.data;
		})
		
		//load authorities of staffs and directors
		$http.get("/rest/authorities?admin=true").then(resp=>{
			$scope.authorities = resp.data;
		}).catch(err=>{
			$location.path("/unauthorized");
		})
		 
		$scope.reset();
	}
	
	//Chọn roles
	$scope.toggleRole = function(role){
		var compareElement = -1;
		var idx = $scope.selection.indexOf(role);
		console.log(idx);
		//Currently Selected
		if(idx>-1){
			$scope.selection.splice(idx,1);
		}
		//Is newly added
		else{
			$scope.selection.push(role);
		}
	}
	
	//Load Roles on form by clicking Edit
	$scope.getOneByRole = function(tentaikhoan){
		$http.get(`/rest/authoritiesOne?tentaikhoan=${tentaikhoan}`).then(resp=>{
			$scope.selection = [];
			$scope.roles.forEach(e=>{
				resp.data.forEach(e1=>{
					if(e.ten == e1.role.ten){
						$scope.selection.push(e);
					}
				})
			})
		})
	}
	
	 $scope.edit = function(item){
		$scope.form = angular.copy(item);
		$scope.getOneByRole(item.tentaikhoan);
		
    }
    
    
	//Thêm account
    $scope.create = function(){
		var item = angular.copy($scope.form);
		$http.post(`/rest/accountsManage`,item).then(resp=>{
			$scope.items.push(resp.data);
			console.log(resp.data);
			//thêm phân quyền
			$scope.selection.forEach(r=>{
				var quyen = {taikhoan:item,role:r};
				$http.post(`/rest/authorities`,quyen).then(resp=>{
					$scope.items.push(resp.data);
				}).catch(err=>{
					console.log("Error ",err);
				})
			})
			$scope.reset();
			alert("Thêm tài khoản thành công!");
		}).catch(err=>{
			console.log("Error ",err);
			alert("Thêm tài khoản thất bại!");
		})
    }

    //Update account
     $scope.update = function(){
		var item = angular.copy($scope.form);
		$http.put(`/rest/accounts/${item.tentaikhoan}`,item).then(resp=>{
			var index = $scope.items.findIndex(p=>p.tentaikhoan == item.tentaikhoan);
			$scope.items[index] = item;
			
			//xoá toàn bộ roles của user hiện tại
			$http.delete(`/rest/authoritiesOne/${item.tentaikhoan}`).then(resp=>{
			//sau khi xoá thì thêm mới lại role đã chọn
				$scope.selection.forEach(r=>{
					var quyen = {taikhoan:item,role:r};
					$http.post(`/rest/authorities`,quyen).then(resp=>{
						$scope.items.push(resp.data);
					}).catch(err=>{
						console.log("Error ",err);
					})
				})
			})
			
			alert('Cập nhật tài khoản thành công!');
			console.log(resp.data);
		}).catch(err=>{
			alert('Lỗi cập nhật tài khoản!')
			console.log("Error ",err);
		})
		
		
	}

    //Remove account
    $scope.delete = function(item){
		$http.delete(`/rest/accounts/${item.tentaikhoan}`).then(resp=>{
			var index = $scope.items.findIndex(p=>p.tentaikhoan == item.tentaikhoan);
			$scope.items.splice(index,1);
			$scope.reset();
			alert('Xoá tài khoản thành công!');
			console.log(resp.data);
		}).catch(err=>{
			alert('Lỗi xoá tài khoản!')
			console.log("Error ",err);
		})
	}

	//reset form
	$scope.reset = function(){
		$scope.form = {
			hinhanh:'logo_tama.png',
		}
    }
    //Upload Hình
    $scope.imageChanged = function(files){
		var data = new FormData();
		data.append('file',files[0]);
		$http.post('/rest/upload/images',data,{
			transformRequest:angular.identity,
			headers:{'Content-Type':undefined}
		}).then(resp=>{
			$scope.form.hinhanh = resp.data.name;
		}).catch(err=>{
			alert('Lỗi upload Ảnh');
			console.log("Error ",err)
		})
    }
	//khởi đầu
	$scope.initialize();
	
	$scope.pager = {
		page:0,
		size:10,
		get items(){
			var start = this.page * this.size;
			return	$scope.items.slice(start,start+this.size);
		},
		get count(){
			return Math.ceil(1.0*$scope.items.length/this.size);
		},
		first(){
			this.page = 0;
		},
		previous(){
			this.page--;
			if(this.page<0){
				this.last();
			};
		},
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			};
		},
		last(){
			this.page = this.count -1;
		},
	}
})