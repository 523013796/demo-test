/**
 * 经费支出表
 */

/**
 * 查询点击事件
 */
function selectExpend() {
    var  key = $("#key").val();
    var url = 'selectLikeKey_in?key='+key;
    tableto(url,'#barDemo10');
}

/**
 * 图书类表格加载,使用layui框架实现
 * @param url
 */
function tableto(url,cz){
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test10'
            ,height: 636
            ,url:url
            ,toolbar: '#toolbarDemo10'
            ,title: '图书管理表'
            ,totalRow: true
            ,cols: [[
                {field:'id', title:'编号',width: 150,align:'center', sort: true , totalRowText: '合计'}
                ,{field:'user', title:'记录人',width: 200,align:'center'}
                ,{field:'amount', title:'收入金额（￥）',width: 200,align:'center', totalRow: true,sort: true}
                ,{field:'time', title:'时间',width: 200,align:'center', sort: true}
                ,{field:'uses', title:'备注（用途）',align:'center'}
                ,{fixed: 'right', title:'操作',align:'center', toolbar: cz, width:180}
            ]]
            ,page: true
            ,limit:12
            ,limits:[12,20,30,50,100,1000]
            ,response: {
                statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
            }
            ,parseData: function(res){ //将原始数据解析成 table 组件所规定的数据
                return {
                    "code": res.status, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": res.data //解析数据列表
                };
            }
        });
        /**
         * 操作栏按钮的实现
         */
        table.on('tool(test10)',function (obj) {
            var data = obj.data;
            switch(obj.event){
                case 'edit10':
                    getExpendValue(data);
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo',
                        closeBtn: 1,
                        anim: 2,
                        shadeClose: true,
                        title: "修改信息",
                        area: ['auto', 'auto'],
                        content: $("#UpdateExpend")//引用的弹出层的页面层的方式加载修改界面表单
                    });
                    //动态向表传递赋值可以参看文章进行修改界面的更新前数据的显示，当然也是异步请求的要数据的修改数据的获取
                    updateExpend(data);

                    break;
                case 'del10':
                    layer.confirm('真的要删除它么?', function (index) {
                        deleteExpend(data);
                        layer.close(index);
                    });
                    break;
            }
        });

        //工具栏事件
        table.on('toolbar(test10)', function(obj){
            var data = obj.data;
            switch(obj.event){
                case 'insert10':
                    resetForm();
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo',
                        closeBtn: 1,
                        anim: 2,
                        shadeClose: true,
                        title: "添加记录",
                        area: ['auto', 'auto'],
                        content: $("#UpdateExpend")//引用的弹出层的页面层的方式加载修改界面表单
                    });
                    insertExpend(data);
                    break;
            };
        });

    });
}

/**
 * 编辑时编辑框显示原始数据以便修改
 * @param data
 */
function getExpendValue(data) {
    $('#expend_amount').val(data.amount);
    $('#datetime').val(data.time);
    $('#expend_uses').val(data.uses);
}

function resetForm() {
    $('#expend_amount').val('');
    $('#datetime').val('');
    $('#expend_uses').val('');
}

/**
 * 修改消费记录信息
 * @param data
 */
function updateExpend(data) {
    layui.use('form',function (d) {
        var form = layui.form;
        form.on('submit(expend_update_submit)',function () {
            $.ajax({
                url:'updateExpend_in'
                ,type:'POST'
                ,dataType:'json'
                ,data:{
                    'id':data.id
                    /*,'amount':d.amount
                    ,'time':d.time
                    ,'uses':d.uses*/
                    ,'amount':$('#expend_amount').val()
                    ,'time':$('#datetime').val()
                    ,'uses':$('#expend_uses').val()
                }
                ,success:function (res) {
                    if (res.status===200){
                        layer.msg('修改成功',{icon : 6});
                        //刷新表格本行数据
                        $('.layui-laypage-btn').click();
                        //延时3.2秒关闭所有弹窗
                        setTimeout(function () { layer.closeAll(); }, 3100);
                    }else {
                        layer.msg('网络异常，请稍后重试！',{icon : 5});
                    }

                }
                ,error:function () {
                    layer.msg('网络异常，请稍后重试！',{icon : 5});
                }
            });
        })
    });

}

function deleteExpend(data) {
    $.ajax({
        url:'deleteExpend_in'
        ,type:'post'
        ,dataType:'json'
        ,data:{'id':data.id}
        ,success:function (res) {
            if (res.status===200) {
                layer.msg('删除成功',{icon : 6});
                //刷新表格数据
                $('.layui-laypage-btn').click();
            }else {
                layer.msg('网络异常，请稍后重试！',{icon : 5});
            }
        }
        ,error:function () {
            layer.msg('网络异常，请稍后重试！',{icon : 5});
        }
    })
}

