/**
 * Created by cellargalaxy on 2017/4/25.
 */




function reload() {
    location.reload(true);
}
function display(name) {
    $('table[name=' + name + ']').toggle();
}
function addHost() {
    address = $('input[name=address]').val();
    building = $('input[name=building]').val();
    model = $('input[name=model]').val();
    floor = $('input[name=floor]').val();
    name = $('input[name=name]').val();
    if (address == null || address == "") {
        alert("请输入ip！");
        return;
    }
    if (building == null || building == "") {
        alert("请输入楼栋！");
        return;
    }
    if (floor == null || floor == "") {
        alert("请输入楼层！");
        return;
    }
    if (model == null || model == "") {
        alert("请输入机型！");
        return;
    }
    $.ajax({
        url: '/nic/netview/json',
        type: 'get',
        cache: false,
        async: false,
        data: {"address": address, "building": building, "floor": floor, "model": model, "name": name},
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",

        error: function () {
            alert("网络错误!");
        },
        success: function (data) {
            if (data.result) {
                alert("成功添加：" + data.address);
                window.location.href = "/nic/netview";
            } else {
                alert("添加失败：" + data.address);
            }
        }
    })
}

function deleteHost(address) {
    if (address == null || address == "") {
        alert("删除ip不得为空!");
        return;
    }
    if (confirm("确认删除?:" + address)) {
        $.ajax({
            url: '/nic/netview/json',
            type: 'post',
            cache: false,
            async: false,
            data: {"address": address},
            contentType: "application/x-www-form-urlencoded",
            dataType: "json",

            error: function () {
                alert("网络错误!");
            },
            success: function (data) {
                if (data.result) {
                    alert("成功删除：" + data.address);
                } else {
                    alert("删除失败：" + data.address);
                }
            }
        })
    }
}



