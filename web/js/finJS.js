$(function() {
    var pager1 = $('#living').datagrid().datagrid('getPager'); // get the pager of datagrid
    var pager2 = $('#end').datagrid().datagrid('getPager'); // get the pager of datagrid
})
$(function() {
    $('#living').datagrid({
        singleSelect: true,
        toolbar: '#tb',
        url: 'http://localhost:8085/FamilyFinances_war_exploded/json/fin_living.json',
        method: 'get',
        rownumbers: true,
        pagination: true,
        columns: [
            [{
                field: 'fin_id',
                title: '理财编号',
                width: 100,
                align: 'center'
            },
                {
                    field: 'fin_type',
                    title: '理财类型',
                    width: 100,
                    align: 'center'
                },
                {
                    field: 'fin_rate',
                    title: '年利率',
                    width: 100,
                    align: 'center'
                },
                {
                    field: 'fin_amount',
                    title: '投入金额',
                    width: 100,
                    align: 'right'
                },
                {
                    field: 'start_time',
                    title: '起始时间',
                    width: 80,
                    align: 'center'
                },
                {
                    field: 'end_time',
                    title: '结束时间',
                    width: 80,
                    align: 'center'
                },
                {
                    field: 'fin_note',
                    title: '备注',
                    width: 100,
                    align: 'center'
                },
                {
                    field: 'fin_earn',
                    title: '预计获利',
                    width: 100,
                    align: 'center'
                }
            ]
        ]
    });
});

$(function() {
    $('#end').datagrid({
        singleSelect: true,
        toolbar: '#tb',
        url: 'http://localhost:8085/FamilyFinances_war_exploded/json/fin_finish.json',
        method: 'get',
        rownumbers: true,
        pagination: true,
        columns: [
            [{
                field: 'fin_id',
                title: '理财编号',
                width: 100,
                align: 'center'
            },
                {
                    field: 'fin_type',
                    title: '理财类型',
                    width: 100,
                    align: 'center'
                },
                {
                    field: 'fin_rate',
                    title: '年利率',
                    width: 100,
                    align: 'center'
                },
                {
                    field: 'fin_amount',
                    title: '投入金额',
                    width: 100,
                    align: 'right'
                },
                {
                    field: 'start_time',
                    title: '起始时间',
                    width: 80,
                    align: 'center'
                },
                {
                    field: 'end_time',
                    title: '结束时间',
                    width: 80,
                    align: 'center'
                },
                {
                    field: 'fin_note',
                    title: '备注',
                    width: 100,
                    align: 'center'
                },
                {
                    field: 'fin_earn',
                    title: '获利',
                    width: 100,
                    align: 'center'
                }
            ]
        ]
    });
});

function addFinance(){
    var selectType = document.getElementById("SelectType");
    selectType.options[0].selected = true;
    var selectTime = document.getElementById("SelectTime");
    selectTime.options[0].selected = true;
    var timeA = document.getElementById("timeA");
    timeA.value = "";

    var amount = document.getElementById("amountA");
    amount.value = "";

    var rate = document.getElementById("rateA");
    rate.value = "";

    var note = document.getElementById("noteA");
    note.value = "";
    $("#addWIN").window('open');
}

var xhr = false;

function createXHR() {
    try {
        //适用于IE7、Firefox、Chrome、Opera、和Safari
        xhr = new XMLHttpRequest();
    } catch (e) {
        try {
            //适用于IE6、IE5
            xhr = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (e1) {
            xhr = false;
        }
    }
    if (!xhr)
        alert("初始化XMLHttpRequest对象失败！");
}

function save(){
    var selectType = document.getElementById("SelectType");
    var index = selectType.selectedIndex; // 选中索引
    var selectTypeValue = selectType.options[index].value; // 选中值

    var timeA = document.getElementById("timeA");
    var t = timeA.value;

    var selectTime = document.getElementById("SelectTime");
    var index2 = selectTime.selectedIndex; // 选中索引
    var selectTimeValue = selectTime.options[index2].value; // 选中值

    var amount = document.getElementById("amountA");
    var a=amount.value;

    var rate = document.getElementById("rateA");
    var r=rate.value;

    var note = document.getElementById("noteA");
    var n=note.value;

    if(reg("amountA","rateA")) {
        createXHR();
        var url = "http://localhost:8085/FamilyFinances_war_exploded/FinanceServlet";
        var content = "type=add&selectType="+selectTypeValue+"&timeA="+t+"&selectTime="+selectTimeValue
            +"&amountA="+a+"&rateA="+r+"&noteA="+n;
        xhr.open("POST", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resultText = xhr.responseText;
                alert(resultText);
                $("#living").datagrid("reload");
                $("#end").datagrid("reload");
            }
        };
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
        xhr.send(content);
    } else {
        amount.value = "";
    }
}

function reg(money,rate) {
    var amount = document.getElementById(money);
    var rate = document.getElementById(rate);
    var pattern = /^\d+\.+\d+$/;
    var pattern2 = /^\d{1,}$/;
    if(amount.value == "" || ((!pattern.test(amount.value)) && (!pattern2.test(amount.value)))
        ||amount.value<0) {
        alert(amount.value + "金额不可为空或格式错误（必须为整数或小数）！请重新输入");
        return false;
    }
    if(rate.value == "" || ((!pattern.test(rate.value)) && (!pattern2.test(rate.value)))
        || rate.value>100 ||rate.value<0){
        alert(rate.value + "利率不可为空或格式错误（必须为正整数或小数），取值0-100！请重新输入");
        return false;
    }
    return true;

}

function myformatter(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}

function myparser(s) {
    if(!s) return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0], 10);
    var m = parseInt(ss[1], 10);
    var d = parseInt(ss[2], 10);
    if(!isNaN(y) && !isNaN(m) && !isNaN(d)) {
        return new Date(y, m - 1, d);
    } else {
        return new Date();
    }
}
