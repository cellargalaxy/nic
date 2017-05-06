/**
 * Created by cellargalaxy on 2017/5/3.
 */

function changeNicer() {
	grade = $('input[name=grade]').val();
	id = $('input[name=id]').val();
	name = $('input[name=name]').val();
	sex = $("input[name=sex]:checked").val()
	college = $('input[name=college]').val();
	className = $('input[name=className]').val();
	phone = $('input[name=phone]').val();
	shortPhone = $('input[name=shortPhone]').val();
	qq = $('input[name=qq]').val();
	birthday = $('input[name=birthday]').val();
	password = $('input[name=password]').val();
	password1 = $('input[name=password1]').val();
	status = $('input[name=status]').val();
	admin = $('input[name=admin]').val();
	introduction = $('textarea[name=introduction]').val();
	
	if (shortPhone == null || shortPhone == "" || isNaN(shortPhone)) {
		shortPhone = null;
	}
	if (qq == null || qq == "" || isNaN(qq)) {
		qq = null;
	}
	if (introduction == null || introduction == "") {
		introduction = null;
	}
	
	if (grade == null || grade == "" || isNaN(grade)) {
		alert("请正确输入年级！");
		return;
	}
	if (id == null || id == "" || isNaN(id)) {
		alert("请正确输入学号！");
		return;
	}
	if (name == null || name == "") {
		alert("请正确输入姓名！");
		return;
	}
	if (sex == null || sex == "" || (sex != '男' && sex != '女')) {
		alert("请正确输入性别！");
		return;
	}
	if (college == null || college == "") {
		alert("请正确输入学院！");
		return;
	}
	if (className == null || className == "") {
		alert("请正确输入专业班级！");
		return;
	}
	if (phone == null || phone == "" || isNaN(phone)) {
		alert("请正确输入手机！");
		return;
	}
	if (birthday == null || birthday == "") {
		alert("请正确输入生日！");
		return;
	}
	if (password == null || password == "" || password1 == null || password1 == "") {
		alert("请正确输入密码！");
		return;
	}
	if (password != password1) {
		alert("两次密码不一致！");
		return;
	}
	if (status == null || status == "" || isNaN(status) || (status != 0 && status != 1)) {
		alert("请正确输入状态！");
		return;
	}
	if (admin == null || admin == "" || isNaN(admin) || (admin != 0 && admin != 1)) {
		alert("请正确输入是否管理员！");
		return;
	}
	
	$.ajax({
		url: '/nic/nicerList/changeNicer',
		type: 'post',
		cache: false,
		async: false,
		data: {
			"nicer": "{grade:" + grade + ",id:" + id + ",name:" + name + ",sex:" + sex + "," +
			"college:" + college + ",className:" + className + ",phone:" + phone + "," +
			"shortPhone:" + shortPhone + ",qq:" + qq + ",birthday:" + birthday + "," +
			"password:" + password + ",status:" + status + ",admin:" + admin + ",introduction:" + introduction + "}"
		},
		contentType: "application/x-www-form-urlencoded",
		dataType: "json",
		
		error: function () {
			alert("网络错误!");
		},
		success: function (data) {
			if (data.result) {
				alert("修改成功：" + data.info);
				window.location.href = "/nic/nicerList";
			} else {
				alert("修改失败：" + data.info);
			}
		}
	})
}

function turnRight(id, result) {
	if (id == null || id == "" || isNaN(id)) {
		alert("添加学号为空或者不合法！");
		return;
	}
	if (result == null || result.length > 0 || isNaN(result) || (result != 0 && result != 1)) {
		alert("选择为空或者不合法！");
		return;
	}
	if (result == 0 && !confirm("确认删除?:" + id)) {
		return;
	}
	$.ajax({
		url: '/nic/nicerList',
		type: 'post',
		cache: false,
		async: false,
		data: {"id": id, "result": result},
		contentType: "application/x-www-form-urlencoded",
		dataType: "json",
		
		error: function () {
			alert("网络错误!");
		},
		success: function (data) {
			if (data.result) {
				alert("操作成功：" + data.info);
			} else {
				alert("操作失败：" + data.info);
			}
		}
	})
	window.location.href = "/nic/nicerList";
}

function signUp() {
	grade = $('input[name=grade]').val();
	id = $('input[name=id]').val();
	name = $('input[name=name]').val();
	sex = $("input[name=sex]:checked").val()
	college = $('input[name=college]').val();
	className = $('input[name=className]').val();
	phone = $('input[name=phone]').val();
	shortPhone = $('input[name=shortPhone]').val();
	qq = $('input[name=qq]').val();
	birthday = $('input[name=birthday]').val();
	password = $('input[name=password]').val();
	password1 = $('input[name=password1]').val();
	introduction = $('textarea[name=introduction]').val();
	
	if (shortPhone == null || shortPhone == "" || isNaN(shortPhone)) {
		shortPhone = null;
	}
	if (qq == null || qq == "" || isNaN(qq)) {
		qq = null;
	}
	if (introduction == null || introduction == "") {
		introduction = null;
	}
	
	if (grade == null || grade == "" || isNaN(grade)) {
		alert("请正确输入年级！");
		return;
	}
	if (id == null || id == "" || isNaN(id)) {
		alert("请正确输入学号！");
		return;
	}
	if (name == null || name == "") {
		alert("请正确输入姓名！");
		return;
	}
	if (sex == null || sex == "" || (sex != '男' && sex != '女')) {
		alert("请正确输入性别！");
		return;
	}
	if (college == null || college == "") {
		alert("请正确输入学院！");
		return;
	}
	if (className == null || className == "") {
		alert("请正确输入专业班级！");
		return;
	}
	if (phone == null || phone == "" || isNaN(phone)) {
		alert("请正确输入手机！");
		return;
	}
	if (birthday == null || birthday == "") {
		alert("请正确输入生日！");
		return;
	}
	if (password == null || password == "" || password1 == null || password1 == "") {
		alert("请正确输入密码！");
		return;
	}
	if (password != password1) {
		alert("两次密码不一致！");
		return;
	}
	$.ajax({
		url: '/nic/signUp',
		type: 'post',
		cache: false,
		async: false,
		data: {
			"nicer": "{grade:" + grade + ",id:" + id + ",name:" + name + ",sex:" + sex + "," +
			"college:" + college + ",className:" + className + ",phone:" + phone + "," +
			"shortPhone:" + shortPhone + ",qq:" + qq + ",birthday:" + birthday + "," +
			"password:" + password + ",introduction:" + introduction + "}"
		},
		contentType: "application/x-www-form-urlencoded",
		dataType: "json",
		
		error: function () {
			alert("网络错误!");
		},
		success: function (data) {
			if (data.result) {
				alert("成功添加：" + data.info);
				window.location.href = "/nic/login";
			} else {
				alert("添加失败：" + data.info);
			}
		}
	})
}