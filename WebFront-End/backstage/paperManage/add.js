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
    }
)