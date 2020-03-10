

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
            elem: '#test'
            ,height: 680
            ,url:url
            ,toolbar: '#toolbarDemo'
            ,title: '图书管理表'
            ,totalRow: true
            ,cols: [[{field:'id', title:'编号',width: 120,align:'center', totalRowText: '合计'}
                ,{field:'name', title:'书名',width: 200,align:'center'}
                ,{field:'author', title:'作者/出版社',width: 200,align:'center'}
                ,{field:'price', title:'价格(￥)',width: 100,align:'center', sort: true, totalRow: true}
                ,{field:'type', title:'类别',width: 120,align:'center'}
                ,{field:'buytime', title:'购入时间',width: 120,align:'center', sort: true}
                ,{field:'introduce', title:'简介',align:'center',templet: function(d){
                        return '<div style="text-align:left">'+d.introduce+'</div>';
                    }}
                ,{field:'numberremaining', title:'剩余数量',width: 100,align:'center', totalRow: true}
                ,{field:'number', title:'总数',width: 100,align:'center', totalRow: true}
                ,{fixed: 'right', title:'操作',align:'center', toolbar: cz, width:180}
            ]]
            ,page: true
            ,limit:13
            ,limits:[13,20,30,50,100,1000]
            ,response: {
                statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
            }
            ,parseData: function(res){ //将原始数据解析成 table 组件所规定的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": res.data //解析数据列表
                };
            }
        });
        /**
         * 操作栏按钮的实现
         */
        table.on('tool(test)',function (obj) {
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
                        title: "修改图书信息",
                        area: ['auto', 'auto'],
                        content: $("#UpdateTest")//引用的弹出层的页面层的方式加载修改界面表单
                    });
                    //动态向表传递赋值可以参看文章进行修改界面的更新前数据的显示，当然也是异步请求的要数据的修改数据的获取
                    updateBook(data);

                    break;
                case 'del':
                    layer.confirm('真的要删除它么?', function (index) {
                        deleteBook(data,obj);
                        layer.close(index);
                    });
                    break;
                case'borrow':
                    layer.confirm('你是否想要借阅这本书呢？', function (index) {
                        borrow_book(data);
                        layer.close(index);
                    });
                    break;
            }
        });


        /**
         * 工具栏事件
         */
        table.on('toolbar(test)', function(obj){
            //var checkStatus = table.checkStatus(obj.config.id);
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
                        title: "添加图书信息",
                        area: ['auto', 'auto'],
                        content: $('#UpdateTest')//引用的弹出层的页面层的方式加载修改界面表单
                    });
                    insertBook();
                    break;
                case 'insertType':
                    add_type();
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
    //alert(data.name);
    $("#bookName").val(data.name);
    $("#bookAuthor").val(data.author);
    $("#bookPrice").val(data.price);
    $("#date").val(data.buytime+"");
    $("#bookNumber").val(data.number);
    $("#bookIntroduce").val(data.introduce);
    /*设置select默认选择的值*/
    layui.use('form', function(){
        var form = layui.form;
        //$("#bookType").val(data.type);
        $("#bookType").find("option").each(function(){
            if($(this).text() == data.type)	{
                $(this).attr("selected",true);
            }
        });
        form.render()
    });
}


function resetForm() {
    /*设置select默认选择的值*/
    layui.use('form', function(){
        var form = layui.form;
        $("#bookType").val("");
        /*$("#bookType").find("option").each(function(){
            if($(this).text() == "搜索选择")	{
                $(this).attr("selected",true);
            }
        });*/
        form.render()
    });
    $("#bookName").val('');
    $("#bookAuthor").val('');
    $("#bookPrice").val('');
    $("#date").val('');
    $("#bookNumber").val('');
    $("#bookIntroduce").val('');
}

function insertBook() {
    layui.use('form', function(){
        var form = layui.form;
        //form.render('radio','radio');
        form.on('submit(demo1)',function () {
            $.ajax({
                url:'addBook'
                ,type:'get'
                ,dataType:'json'
                ,data:$("#updateForm").serialize()
                ,success:function (res) {
                    if (res.status===200){
                        layer.msg('添加成功，3秒后自动关闭',{icon : 6});
                        //刷新表格本页
                        $('.layui-laypage-btn').click();
                        //延时3.1秒关闭所有弹窗
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

/**
 * 删除一条数据
 * @param data
 */
function deleteBook(data,obj) {
    $.ajax({
        url:"deleteThisBook",
        type:"POST",
        dataType: 'json',
        data:{"id":data.id},
        success:function (res) {
            if (res.message=="Y") {
                layer.msg("删除成功！", {icon: 6});
                //刷新表格本行数据
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
function updateBook(data) {
    //var form = ;
    //alert(form.toJSON());
    layui.use('form', function(){
        var form = layui.form;
        form.on('submit(demo1)', function(d){
            /*layer.alert(JSON.stringify(d.field), {
                title: '最终的提交信息'
            })*/
            /*var dede = JSON.stringify(d.field)+{"id":data.id,"oldNumber":data.number,"numberremaining":data.numberremaining};
            layer.alert(dede)*/
            $.ajax({
                url:"updateBook",
                type:"GET",
                dataType: 'json',
                data:{"id":data.id,
                    "name":$("#bookName").val(),
                    "author":$("#bookAuthor").val(),
                    "price":$("#bookPrice").val(),
                    "buytime":$("#date").val(),
                    "type":$("#bookType").val(),
                    "number":$("#bookNumber").val(),
                    "oldNumber":data.number,
                    "numberremaining":data.numberremaining,
                    "introduce":$("#bookIntroduce").val()},    //$("#updateForm").serialize()直接获取表单内容
                success:function (res) {
                    if (res.message=="Y") {
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

/**
 * 添加图书类别
 */
function add_type() {
    layer.open({
        type: 1,
        title:'添加图书类型',
        area: ['auto', '160px'],
        shadeClose: true, //点击遮罩关闭
        content: $('#addBookT')
    });
    layui.use('form', function(){
        var form = layui.form;
        form.on('submit(demo2)', function(data){
            /*layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })*/
            $.ajax({
                url:'addBookType',
                type:'GET',
                dataType:'json',
                data:data.field,
                success:function (res) {
                    if (res.status===200){
                        layer.msg('添加成功^_^',{icon: 6});
                        //延时3.2秒关闭所有弹窗
                        setTimeout(function () {
                            layer.closeAll();
                        }, 3200);
                        setTimeout(function () {
                            window.location.reload();//刷新当前页面
                        }, 3500);

                    } else {
                        layer.msg('网络异常1，请稍后再试！',{icon: 5});
                    }
                },
                error:function () {
                    layer.msg('网络异常2，请稍后再试！',{icon: 5});
                }
            });
            return false;
        });
    });
};
