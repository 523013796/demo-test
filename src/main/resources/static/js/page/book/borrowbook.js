/**
 * 图书类表格加载,使用layui框架实现
 * @param url
 */
function tabletoborrowbook(url,cz){
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,height: 680
            ,url:url
            ,toolbar: true
            ,title: '借书管理表'
            ,totalRow: true
            ,cols: [[
                {field:'user', title:'借书人',align:'center', totalRowText: '合计'}
                ,{field:'book', title:'此书编号',align:'center'}
                ,{field:'book_name', title:'书名',align:'center'}
                ,{field:'time', title:'借书时间',align:'center', sort: true}
                ,{field:'booknumber', title:'数量',align:'center', sort: true , totalRow: true}
                ,{field:'rTime', title:'归还时间',align:'center', sort: true}
                ,{field:'flag', title:'状态',align:'center',templet:function (d) {
                        return d.flag?'未归还':'已归还';
                    }}
                ,{fixed: 'right', title:'操作',align:'center', toolbar:cz, width:150}
            ]]
            ,page: true
            ,limit:13
            ,limits:[5,10,13,20,30,50,100]
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
                    /*getValue(data);
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
                    updateBook(data);*/
                    layer.msg("暂时还没想好有什么用，敬请期待^_^",{icon:3})
                    break;
                case 'del':
                    layer.confirm('真的要删除它么?', function (index) {
                        deleteBorrowBookRecord(data,obj);
                        layer.close(index);
                    });
                    break;
                case'guihuan':
                    layer.confirm('确定你已归还?', function (index) {
                        guiHuanBook(data);
                        layer.close(index);
                    });
                    break;
            }
        });
    });
}

/**
 * 按姓名，编号，书名搜索
 */
layui.use('form', function(){
    var form = layui.form;
    form.on('submit(demo2)', function(data){
        /*layer.alert(JSON.stringify(data.field.key), {
            title: '最终的提交信息'
        });*/
        //alert(data.field.key)
        var url = 'selectByUserOrBook?key='+data.field.key;
        tabletoborrowbook(url,'#barDemo');
        return false;
    });
});

/**
 * 删除一条借书记录
 * @param data
 */
function deleteBorrowBookRecord(data,obj) {
    $.ajax({
        url:"deleteBorrowBookRecord",
        type:"GET",
        dataType: 'json',
        data:{"id":data.id,"flag":data.flag,"booknumber":data.booknumber,"book":data.book},
        success:function (res) {
            if (res.message=="Y") {
                if(res.data==1){
                    layer.msg("删除成功！", {icon: 6});
                    //刷新表格数据
                    $('.layui-laypage-btn').click();
                }else{
                    layer.msg("网络异常，请稍后重试！", {icon: 5});
                }
            }else {
                layer.msg("网络异常，请稍后重试！", {icon: 5});
            }
        },
        error:function () {
            layer.msg("网络异常，请稍后重试！", {icon: 5});
        }
    });
}

/**
 * 归还图书
 * @param data
 */
function guiHuanBook(data) {
    $.ajax({
       url:'guiHuanBook',
       type:'get',
       dataType:'json',
       data:{'id':data.id,'rTime':new Date(),'book':data.book},
        success:function (res) {
           if (res.status===200) {
               layer.msg("你已经成功归还", {icon: 6});
               $('.layui-laypage-btn').click();
               //tabletoborrowbook('getAllBorrowBook','#barDemo1');
           }else{
               layer.msg("网络异常1，请稍后重试！", {icon: 5});
           }
        },
        error:function () {
            layer.msg("网络异常，请稍后重试！", {icon: 5});
        }
    });
}