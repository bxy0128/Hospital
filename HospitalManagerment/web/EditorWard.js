/**
 * 初始化表格及表格相关的简单操作 //需要引入 layui.js layui.css文件
 * @param {} t  table参数形如 {id:'test',indexName:'ID',heigt:'full-30',url:'Account?GetTableJson|&',page:true,cols:[ { field: 'ID', title: 'ID', width: 80 },]}
 * @param {} editor  编辑方法
 * @param {} deltes 删除方法
 * @param {} read 查看方法
 * @returns {}
 */
function createTable(t, editor, deltes, read) {

    var bodys = document.getElementsByTagName("body")[0];
    bodys.innerHTML += '<table class="layui-hide" id="test" lay-filter="demo"></' + 'table>' +
        '<script type="text/html" id="barDemo">' +
        '  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</' + 'a>' +
        '  <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit">编辑</' + 'a>' +
        '  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</' + 'a>' +
        '</' + 'script>';
    var path = window.location.pathname;//获取页面的地址也就是：*/*/*/abd.html
    var page = path.split("/").pop();//将最后一个/前后分割成数组 只取abd.html
    var page = page.split(".").at(0);//将.前后分割成数组，只取 abd
    /*以此获得html前缀，以Ward.html为例，只取Ward，用做json的导入和Ajax的发送
    * , url: 'json/'+page +'.json' //json数据的导入
    * url: page+"delete",AJAX数据的发送目标
    * 所以要将所有patient、doctor的展示操作页面全部以Patient.html来命名，以免名称错误
    * json文件和Spring mvc 的命令也同样以Patient.json、Doctor.json||Patientdelete、Patientupdate
    * Doctordelete、Doctorupdate命名 */
    layui.use(['form', 'table', 'layer'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table,
            layer = layui.layer;

        //执行一个 table 实例
        table.render({
            elem: '#' + (t.id || 'test')
            , height: 700
            , url: 'json/Ward.json' //数据接口
            , page: true //开启分页
            ,cols: [ //表头
                t.cols
            ]

        });

        //监听工具条
        table.on('tool(demo)', function(obj) {
            var data = obj.data; //获得当前行数据
            var content = JSON.stringify(data);
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            console.log(data)
            console.log(content)

            console.log(page);
            if (layEvent === 'detail') { //查看
                if (read != null) read(obj);
            } else if (layEvent === 'del') { //删除
                layer.confirm('真的删除行么', function (index) {
                    $.ajax({

                        type: "post",
                        url: page + "delete",
                        contentType: "application/json;charset=UTF-8",
                        data: content,
                        success: function (data) {
                            var abc = 1;
                            $.ajax({
                                url: "renovate",
                                type:"post",
                                data: {temp:abc},
                                success: function (data) {
                                    console.log(data);
                                }

                            })
                            // console.log(data);
                        }

                    })
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                });
            }else if(layEvent === 'edit'){
                layer.open({
                    type: 2
                    ,title: '编辑'
                    ,content: 'Editor'+page+'.html' //展示test1.html中的layui表单
                    ,maxmin: true   //允许最大化
                    ,area: ['500px', '450px']   //弹窗的大小
                    ,btn: ['确定', '取消']
                    //yes函数是点击弹窗后回调的函数
                    ,yes: function(index, layero){
                        //通过以下的方法获取回调的数值
                        var patient_inhospital_id = $(layero).find('iframe')[0].contentWindow.patient_inhospital_id.value;
                        var ward_dept = $(layero).find('iframe')[0].contentWindow.ward_dept.value;
                        var ward_id = $(layero).find('iframe')[0].contentWindow.ward_id.value;
                        var ward_bednum = $(layero).find('iframe')[0].contentWindow.ward_bednum.value;
                        var patient_date_start = $(layero).find('iframe')[0].contentWindow.patient_date_start.value;
                        var ward_doc_id = $(layero).find('iframe')[0].contentWindow.ward_bednum.value;
                        var ward_nurse_id = $(layero).find('iframe')[0].contentWindow.ward_bednum.value;

                        //同步数据表格中的数据
                        obj.update({
                            patient_inhospital_id:patient_inhospital_id,
                            ward_dept:ward_dept,
                            ward_id:ward_id,
                            ward_bednum:ward_bednum,
                            patient_date_start:patient_date_start,
                            ward_nurse_id:ward_nurse_id,
                            ward_doc_id:ward_doc_id,

                        })
                        const json =
                            {
                                patient_inhospital_id: patient_inhospital_id,
                                ward_dept: ward_dept,
                                ward_id: ward_id,
                                ward_bednum: ward_bednum,
                                patient_date_start: patient_date_start,
                                ward_nurse_id:ward_nurse_id,
                                ward_doc_id:ward_doc_id,
                            }
                        var json2 = JSON.stringify(json);
                        //利用ajax同步数据库的数据，具体的后端自己实现吧
                        $.ajax({
                            url: page + "update",
                            type:"post",
                            contentType:"application/json;charset=utf-8",
                            data: json2,
                            success: function (data) {
                                // console.log(data);
                                var abc = 1;
                                $.ajax({
                                    url: "renovate",
                                    type:"post",
                                    data: {temp:abc},
                                    success: function (data) {
                                        console.log(data);
                                    }

                                })
                            }

                        })


                        layer.close(index);
                        console.log(obj.data)

                    }
                    ,success: function(layero, index){


                        var div = layero.find('iframe').contents().find('#useradmin');  // div.html() div里面的内容,不包含当前这个div

                        var body = layer.getChildFrame('body', index);  // body.html() body里面的内容

                        var iframeWindow = window['layui-layer-iframe'+ index]  // 得到iframe页的窗口对象
                        //通过.html中各个输入框的id可以进行赋值
                        body.find("#patient_inhospital_id").val(data.patient_inhospital_id);
                        body.find("#ward_dept").val(data.ward_dept)
                        body.find("#ward_id").val(data.ward_id)
                        body.find("#ward_bednum").val(data.ward_bednum)
                        body.find("#patient_date_start").val(data.patient_date_start)
                        body.find("#ward_nurse_id").val(data.ward_nurse_id)
                        body.find("#ward_doc_id").val(data.ward_doc_id)


                    }
                });
            }



        })

    })
}

function openDetial(title, area, path, sucFunName, callBack) {
    layer.open({
        type: 2,
        title: title, //不显示标题栏
        area: ['450px', '500px'],
        shade: 0.8,
        maxmin:true,
        shadeClose: true,
        id: (new Date()).valueOf(), //设定一个id，防止重复弹出 时间戳1280977330748
        btnAlign: 'r',
        moveType: 1, //拖拽模式，0或者1
        content: path,
        yes: function (index, layero) {

            alert("-----");

        },

    });
}







