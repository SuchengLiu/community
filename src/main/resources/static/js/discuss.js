// html页面加载完成后执行
$(function () {
    $("#topBtn").click(setTop);
    $("#wonderfulBtn").click(setWonderful);
    $("#deleteBtn").click(setDelete);
})

function like(btn, entityType, entityId, entityUserId, postId){
    $.post(
        CONTEXT_PATH + "/like",
        {"entityType":entityType, "entityId":entityId, "entityUserId":entityUserId, "postId":postId},
        function (data) {
            data = $.parseJSON(data);
            if(data.code == 0){
                $(btn).children("i").text(data.likeCount);
                $(btn).children("b").text(data.likeStatus == 1 ? "已赞" : "赞");
            } else {
                alert(data.msg);
            }
        }
    )
}

// 置顶
function setTop() {
    // 发送异步请求
    $.post(
        // 请求路径
        CONTEXT_PATH + "/discuss/top",
        // 请求参数
        {"id": $("#postId").val()},
        // 处理相应结果
        function (data) {
            data = $.parseJSON(data);
            if(data.code == 0) {
                $("#topBtn").attr("disabled", "disabled");
            } else {
                alert(data.msg);
            }
        }

    )
}

// 加精
function setWonderful() {
    // 发送异步请求
    $.post(
        // 请求路径
        CONTEXT_PATH + "/discuss/wonderful",
        // 请求参数
        {"id": $("#postId").val()},
        // 处理相应结果
        function (data) {
            data = $.parseJSON(data);
            if(data.code == 0) {
                $("#wonderfulBtn").attr("disabled", "disabled");
            } else {
                alert(data.msg);
            }
        }

    )
}

// 删帖
function setDelete() {
    // 发送异步请求
    $.post(
        // 请求路径
        CONTEXT_PATH + "/discuss/delete",
        // 请求参数
        {"id": $("#postId").val()},
        // 处理相应结果
        function (data) {
            data = $.parseJSON(data);
            if(data.code == 0) {
                location.href = CONTEXT_PATH + "/index";
            } else {
                alert(data.msg);
            }
        }

    )
}