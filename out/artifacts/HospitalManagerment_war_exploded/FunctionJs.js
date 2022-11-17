function createTable(t, editor, deltes, read) {
    var bodys = document.getElementsByTagName("body")[0];
    bodys.innerHTML += '<table class="layui-hide" id="test" lay-filter="demo"></' + 'table>' +
        '<script type="text/html" id="barDemo">' +
        '  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</' + 'a>' +
        '  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</' + 'a>' +
        '  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</' + 'a>' +
        '</' + 'script>';
    var path = window.location.pathname;//获取页面的地址也就是：*/*/*/abd.html
    // http://localhost:8080/HospitalManagerment_war_exploded/Ward.html
    var page = path.split("/").pop();//将最后一个/前后分割成数组 只取abd.html
  //page = Ward.html
    var page = page.split(".").at(0);//将.前后分割成数组，只取 abd
    //page = Ward

    /*以此获得html前缀，以Ward.html为例，只取Ward，用做json的导入和Ajax的发送
    * , url: 'json/'+page +'.json' //json数据的导入
    * url: page+"delete",AJAX数据的发送目标
    * 所以要将所有patient、doctor的展示操作页面全部以Patient.html来命名，以免名称错误
    * json文件和Spring mvc 的命令也同样以Patient.json、Doctor.json||Patientdelete、Patientupdate
    * Doctordelete、Doctorupdate命名 */
    layui.use('table',function(){
        var table = layui.table;
        $ = layui.$
        //执行一个 table 实例
        table.render({
            elem: '#demo'
            , height: 700
            , url: 'json/'+page +'.json' //数据接口 json/Ward.json
            , page: true //开启分页
            ,cols: [ //表头
                t.cols
            ]
        });

        //监听工具条
        table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var content = JSON.stringify(data);
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
console.log(data)
            console.log(content)
            console.log( page );
            if(layEvent === 'detail'){ //查看
                if (read != null) read(obj);
            } else if(layEvent === 'del'){ //删除
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        type: "post",
                        url: page+"delete",
                        contentType:"application/json;charset=UTF-8",
                        data:content,
                        success:function (data){
                            // console.log(data);
                        }

                    })
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if(layEvent === 'edit'){ //编辑
                editor(obj);

                //同步更新缓存对应的值
                obj.update({
                    username: '123'
                    ,title: 'xxx'
                });
            }
        });

    });
}
function openDetial(title, area, path, sucFunName, callBack) {
    layer.open({
        type: 2,
        title: title, //不显示标题栏
        closeBtn: 2,
        area: area,
        shade: 0.8,
        id: (new Date()).valueOf(), //设定一个id，防止重复弹出 时间戳1280977330748
        btn: ['保存', '取消'],
        btnAlign: 'r',
        moveType: 1, //拖拽模式，0或者1
        content: path,
        yes: function (index, layero) {
            var btn = layero.find('.layui-layer-btn').find('.layui-layer-btn0');
            alert("-----");
            try {
                var _ifr = btn[0].parentNode.parentNode.getElementsByClassName("layui-layer-content")[0].children[0].contentWindow ||
                    btn[0].parentNode.parentNode.getElementsByClassName("layui-layer-content")[0].children[0].children[0].contentWindow;
                var func = new Function('_ifr', "return _ifr." + sucFunName + "();");
                var flg = func(_ifr);
                if (flg == false) {
                    return false;
                } else {
                    if (callBack != null) callBack();
                    window.location.reload();
                }
            } catch (ex) {

            }
        },
        btn2: function (index, layero) {
        }
    });
}



