$(
    function () {
        $("td.title").click(
            function () {
                alert("asf");
            }
        );
        $("td.title").popup({
            content : "int n[] = { 0,3,1 }; 写出符合下列条件的语句: (1)定义整型指针变量p，将n的地址赋给p：________; (2)定义整型指针变量q，将p的地址赋给q:__________; 完成上述两步后计算 *(p++)的值:____;再计算 *(++q)的值________。",
            position: "bottom center"
        });
    }
)