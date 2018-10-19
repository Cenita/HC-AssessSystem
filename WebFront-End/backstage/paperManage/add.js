$(
    function () {
        $(".dropdown").dropdown();
        onLoadTimeChoiceDemo();
        borainTimeChoice({
            start:"#startTime",
            end:"#endTime",
            level:"HM",
            less:true
        });
        var E=window.wangEditor;
        var editor1=new E("#TestExplanation .top","#TestExplanation .content");
        editor1.customConfig.menus = [
            'head',  // 标题
            'bold',  // 粗体
            'fontSize',  // 字号
            'fontName',  // 字体
            'italic',  // 斜体
            'underline',  // 下划线
            'strikeThrough',  // 删除线
            'foreColor',  // 文字颜色
            'backColor',  // 背景颜色
            'list',  // 列表
            'justify',  // 对齐方式
            'undo',  // 撤销
            'redo'  // 重复
        ]
        editor1.create();
    }
)