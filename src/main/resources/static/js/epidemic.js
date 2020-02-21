var app = new Vue({
    el:".main",
    data:{
        id:$("#qid").val()
    },
    methods:{
        initClick:function(){
            $(".survey .div_question .div_table_radio_question .inputtext").click(function () {
                $(this).closest(".div_question").css("border","2px solid white");
                $(this).closest(".div_question").find(".errorMessage").html("");
            })
            $(".survey .div_question .div_table_radio_question .ulradiocheck li").click(function(){
                // 判断是单选还是多选
                if($(this).find("input[type='radio']").length > 0){
                    // 单选
                    $(this).parent().find("li a").removeClass("jqChecked");
                    $(this).parent().find("li input[type='radio']").removeAttr("checked");

                    $(this).find("a").addClass("jqChecked");
                    $(this).find("input[type='radio']").attr("checked","checked");

                    $(this).closest(".div_question").css("border","2px solid white");
                    $(this).closest(".div_question").find(".errorMessage").html("");

                }else if($(this).find("input[type='checkbox']").length > 0){
                    // 多选
                    if($(this).find("a").hasClass("jqChecked")){
                        $(this).find("a").removeClass("jqChecked")
                        $(this).find("input[type='checkbox']").removeAttr("checked")
                    }else{
                        $(this).find("a").addClass("jqChecked")
                        $(this).find("input[type='checkbox']").attr("checked","checked")
                    }
                    $(this).closest(".div_question").css("border","2px solid white");
                    $(this).closest(".div_question").find(".errorMessage").html("");
                }
            });

        },
        submit:function(){
            var canSubmit = true;
            var isD = true;
            $(".survey .div_question .div_table_radio_question").each(function (d) {
                //这里如果是选择项，判断是否已经选择了
                // var dd = ;
                // console.log(dd);
                var parentDiv;
                if($(this).find("ul").length>0){
                    parentDiv = $(this).closest(".div_question");
                    $(this).find(".ulradiocheck li").each(function (i) {
                        if($(this).find("input").attr("checked")){
                            $(parentDiv).css("border","2px solid white");
                            $(parentDiv).find(".errorMessage").html("");
                            canSubmit = true;
                            return false;
                        }else{
                            $(parentDiv).css("border","2px solid rgb(255, 153, 0)");
                            $(parentDiv).find(".errorMessage").html("请选择选项");
                            canSubmit = false;
                        }
                    })
                }else{
                    var s = $(this).find("textarea").val();
                    parentDiv = $(this).closest(".div_question");
                    if(''==s){
                        $(parentDiv).css("border","2px solid rgb(255, 153, 0)");
                        $(parentDiv).find(".errorMessage").html("请填写此内容");
                        canSubmit = false;
                    }
                }
                if(!canSubmit && isD){
                    alert("请确保所有内容填写正确，页面将自动定位到第一个不符合要求的题目，请检查！");
                    isD = false;
                    $('body,html').animate({scrollTop: $(parentDiv).offset().top}, 500);
                }
            });
            if(canSubmit){
            var param = {
                answers:$("#form").serializeArray()
            }
            $.ajax({
                url:'http://127.0.0.1:8082/question/v1/answer/'+this.id,
                type:"post",
                dataType: 'json',
                contentType: 'application/json',
                data:JSON.stringify(param),
                success:function (resp) {
                    console.log(resp);
                }
            })
            }
        }
    },
    created:function () {

    },
    mounted:function(){
        this.initClick();
        var _this = this;
        $("#submit_button").click(function(){
            _this.submit()
        });
    }
})