/**
 * Created by cellargalaxy on 2017/4/25.
 */



function test() {
	alert('test');
}
function reload() {
	location.reload(true);
}
function display(name){
	$('table[name='+name+']').toggle();
}
function addHost() {
	building=$('input[name=building]').val();
	floor=$('input[name=floor]').val();
	name=$('input[name=name]').val();
	address=$('input[name=address]').val();
	if (building==null||building==""){
		alert("请输入楼栋！");
		return;
	}
	if(floor==null||floor==""){
		alert("请输入楼层！");
		return;
	}
	if(isNaN(floor)){
		alert("楼层请输入数字！");
		return;
	}
	if(name==null||name==""){
		alert("请输入名字！");
		return;
	}
	if(address==null||address==""){
		alert("请输入ip！");
		return;
	}
	$.ajax({
		url: '/nic/netviewjson',
		type: 'get',
		cache: false,
		async:false,
		data: {"building": building,"floor":floor,"name":name,"address":address},
		contentType: "application/x-www-form-urlencoded",
		dataType: "json",
		
		error: function () {
			alert("网络错误!");
		},
		success: function (data) {
			if(data.result){
				alert("成功添加："+data.ip);
				window.location.href="/nic/netview";
			}else{
				alert("添加失败："+data.ip);
			}
		}
	})
}
function deleteHost(address) {
	if(address==null||address==""){
		alert("删除ip不得为空!");
		return;
	}
	if (confirm("确认删除?:"+address)){
		$.ajax({
			url: '/nic/netviewjson',
			type: 'post',
			cache: false,
			async:false,
			data: {"address": address},
			contentType: "application/x-www-form-urlencoded",
			dataType: "json",
			
			error: function () {
				alert("网络错误!");
			},
			success: function (data) {
				if(data.result){
					alert("成功删除："+data.ip);
				}else{
					alert("删除失败："+data.ip);
				}
			}
		})
	}
}



