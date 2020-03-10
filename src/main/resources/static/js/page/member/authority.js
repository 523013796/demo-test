

/**
 * 查询点击事件
 */
function selectbook() {
    var  key = $("#key").val();
    if (key==''){
        layer.msg('内容不能为空!', {icon: 5});
    }else {
        var url = 'selectLikeBook?key='+key;
        tableto(url,'#barDemo');
    }

}

/**
 * 图书类表格加载,使用layui框架实现
 * @param url
 */
function tableto(url,cz){
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#table'
            ,height: 680
            ,url:url
            ,toolbar: '#toolbarDemo'
            ,title: '成员管理表'
            ,cols: [[{field:'id', title:'编号',width:105,align:'center'}
                ,{field:'name', title:'姓名',width:100,align:'center'}
                ,{field:'sex', title:'性别',width:60,align:'center',templet: function(d){
                        if(d.sex){
                            return '男';
                        }else {
                            return '女';
                        }
                    }}
                ,{field:'age', title:'年龄',width:60,align:'center'}/*, sort: true*/
                ,{field:'major', title:'专业',align:'center'}
                ,{field:'studentnumber', title:'学号',width:180,align:'center'}
                ,{field:'idnumber', title:'证件号',align:'center'}
                ,{field:'phone', title:'电话', width:140,align:'center'}
                ,{field:'wechat', title:'微信',align:'center'}
                ,{field:'qq', title:'QQ',width:140,align:'center'}
                ,{field:'registertime', title:'注册时间',width:115,align:'center'}
                ,{field:'rank', title:'操作权限',width:114,align:'center'}
                ,{fixed: 'right', title:'操作',align:'center', toolbar: '#barDemo', width:130}
            ]]
            ,page: true
            ,limit:13
            ,limits:[13,20]
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
        table.on('tool(table)',function (obj) {
            var data = obj.data;
            switch(obj.event){
                case 'edit':
                    getValue(data);
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo',
                        closeBtn: 1,
                        anim: 2,
                        shadeClose: true,
                        title: "修改成员信息",
                        area: ['350px', 'auto'],
                        content: $('#form')//引用的弹出层的页面层的方式加载修改界面表单
                    });
                    //动态向表传递赋值可以参看文章进行修改界面的更新前数据的显示，当然也是异步请求的要数据的修改数据的获取
                    updateUser(data);

                    break;
                case 'del':
                    layer.confirm('真的要删除它么?', function (index) {
                        deleteUser(data,obj);
                        layer.close(index);
                    });
                    break;
            }
        });
        /**
         * 工具栏事件
         */
        table.on('toolbar(table)', function(obj){
            switch(obj.event){
                case 'insert':
                    resetForm();
                    //location.reload();
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-demo',
                        closeBtn: 1,
                        anim: 2,
                        shadeClose: true,
                        title: "添加成员信息",
                        area: ['350px', 'auto'],
                        content: $('#form')//引用的弹出层的页面层的方式加载修改界面表单
                    });
                    insertAuthority();
                    break;
            };
        });

    });
}

/**
 * 获取待修改值
 * @param data
 */
function getValue(data) {
    // $("#name").val(data.name);
    // if (!data.sex){
    //     //alert('niha')
    //     layui.use('form', function(){
    //         var form = layui.form;
    //         $("#sex_m").removeAttr("checked");
    //         $("#sex_w").attr("checked",'checked');
    //         form.render()
    //     });
    // }
    // $("#age").val(data.age);
    //$("#major").attr(data.major);
    $("#studentnumber").val(data.studentnumber);
    // $("#idnumber").val(data.idnumber);
    // $("#phone").val(data.phone);
    // $("#wechat").val(data.wechat);
    // $("#qq").val(data.qq);
    /*通过text内容设置select的默认选择的值*/
    layui.use('form', function(){
        var form = layui.form;
        //$("#bookType").val(data.type);
        $("#rank").find("option").each(function(){
            if($(this).text() == data.rank)	{
                $(this).attr("selected",true);
            }
        });
        form.render()
    });
}

/**
 * 重置表单值
 */
function resetForm() {
    // $("#name").val('');
    // layui.use('form', function(){
    //     var form = layui.form;
    //     $("#major").val('');
    //     $("#sex_m").attr("checked",'checked');
    //     $("#sex_w").removeAttr("checked");
    //     form.render()
    // });
    // $("#age").val('');
    // $("#major").attr('');
    $("#studentnumber").val('');
    //$("#idnumber").val('');
    // $("#phone").val('');
    // $("#wechat").val('');
    // $("#qq").val('');
    $("#rank").val('');
}

/**
 * 删除一条数据
 * @param data
 */
function deleteUser(data,obj) {
    $.ajax({
        url:"deleteUser",
        type:"POST",
        dataType: 'json',
        data:{"id":data.id},
        success:function (res) {
            if (res.status===200) {
                layer.msg("删除成功！", {icon: 6});
                //刷新表格数据
                $('.layui-laypage-btn').click();
            }else {
                layer.msg("网络异常，删除失败哦！", {icon: 5});
            }

        }
    });
}

/**
 * 更新一条数据
 * @param data
 */
function updateUser(data) {
    //var form = ;
    //alert(form.toJSON());
    layui.use('form', function(){
        var form = layui.form;
        form.on('submit(demo1)', function(){
            $.ajax({
                url:"updateAuthority",
                type:"GET",
                dataType: 'json',
                data:$("#form1").serialize(),   //$("#updateForm").serialize()直接获取表单内容
                success:function (res) {
                    if (res.status===200) {
                        layer.msg("修改成功^_^", {icon: 6});
                        //刷新表格本行数据
                        $('.layui-laypage-btn').click();
                        //tableto('getAllbook','#barDemo');
                        //延时3.2秒关闭所有弹窗
                        setTimeout(function () { layer.closeAll(); }, 3100);
                        //layer.closeAll();
                    }else {
                        layer.msg("网络异常，请稍后再试！", {icon: 5});
                    }
                },
                error:function () {
                    layer.msg("网络异常，请稍后再试！", {icon: 5});
                }
            })
            return false;
        });
    });
    /*$("#bookUpdateSubmit") .click(function(){

    });*/
}

function insertAuthority() {
    layui.use('form',function () {
        var form = layui.form;
        form.on('submit(demo1)',function () {
            $.ajax({
                url:'insertAuthority'
                ,type:'get'
                ,dataType:'json'
                ,data:$("#form1").serialize()
                ,success:function (res) {
                    if (res.status===200){
                        layer.msg('添加成功',{icon : 6});
                        //刷新表格本页
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
