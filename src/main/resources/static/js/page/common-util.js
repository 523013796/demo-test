
//加载日期工具
layui.use(['laydate'], function() {
    var laydate = layui.laydate;

    //日期
    laydate.render({
        elem: '#date'
    });
    laydate.render({
        elem: '#date1'
    });
    //日期时间选择器
    laydate.render({
        elem: '#datetime'
        ,type: 'datetime'
        ,istime:true
        , format: 'yyyy-MM-dd HH:mm:ss'
    });
});

function myAjax(url,data,successdo) {
    $.ajax({
        url:url,
        type:"POST",
        dataType: 'json',
        data:data,   //$("#updateForm").serialize()直接获取表单内容
        success:successdo,
        error:function () {
            alert("网络异常，请稍后再试！");
        }
    });
}

var requestType = 'GET';
var traditional = true;

/**
 * 自定义封装ajax
 * @param requestUrl
 * @param requestData
 * @returns {*|jQuery|{}}
 */
function ajaxRequest(requestUrl,requestData) {
    //使用$.Deferred()和$.when().done(）把异常请求变成同步请求
    //只有这样在同时多个Ajax请求的时候才能保存数据不会窜

    //deferred对象是一个延迟对象，意思是函数延迟到某个点才开始执行，
    //改变执行状态的方法有两个（成功：resolve和失败：reject），
    //分别对应$.when()的两种执行回调（成功回调函数：done和失败回调函数fail）
    var defer = $.Deferred();

    $.ajax({
        traditional: traditional, //防止深度序列化
        type: requestType,
        dataType: "json",
        url: requestUrl,
        data: requestData,
        success: function(res) {
            //延迟延时成功后把数据传递给$.when(Deferred).done(function(resultData){})
            defer.resolve(res);
        },
        error: function() {
            alert("网络出现异常，请稍后再试！");
        }
    });

    return defer; //返回延迟对象

    //谁调用ajaxRequest()函数，就使用下面这个方式来获取请求成功的数据
    /*
    $.when(ajaxRequest()).done(function (resultData) {

        //在这里处理业务

    });
    */

}
