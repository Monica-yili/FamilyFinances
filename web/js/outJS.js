$(function() {
    var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
    pager.pagination({});
})
$(function() {
    $('#dg').datagrid({
        singleSelect: true,
        toolbar: '#tb',
        url: 'http://localhost:8085/FamilyFinances_war_exploded/json/Expend_data.json',
        method: 'get',
        rownumbers: true,
        pagination: true,
        columns: [
            [{
                field: 'out_id',
                title: '账单编号',
                width: 100,
                align: 'center'
            },
                {
                    field: 'out_type',
                    title: '支出类型',
                    width: 100,
                    align: 'center'
                },
                {
                    field: 'out_amount',
                    title: '金额',
                    width: 100,
                    align: 'right'
                },
                {
                    field: 'out_time',
                    title: '记账时间',
                    width: 80,
                    align: 'center'
                },
                {
                    field: 'out_note',
                    title: '备注',
                    width: 100,
                    align: 'center'
                }
            ]
        ]
    });
});

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


function DoDelete() {
    //选中要删除的行
    var row = $("#dg").datagrid('getSelections');
    if(row.length == 1) {
        var id = row[0].out_id;
        createXHR();
        var url = "http://localhost:8085/FamilyFinances_war_exploded/ExpendServlet";
        var content = "type=del&del_id="+id;
        xhr.open("POST", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resultText = xhr.responseText;
                alert(resultText);
                $("#dg").datagrid("reload");
            }
        };
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
        xhr.send(content);
    }else {
        alert("请选择一行删除！");
    }

}

function DoMod(id){
    var amountU = document.getElementById("amountU");
    var a = amountU.value;
    var Outtype = document.getElementById("SelectU");
    var index = Outtype.selectedIndex; // 选中索引
    var OuttypeValue = Outtype.options[index].value; // 选中值
    var noteU = document.getElementById("noteU");
    var n = noteU.value;
    if(reg("amountU")) {
        createXHR();
        var url = "http://localhost:8085/FamilyFinances_war_exploded/ExpendServlet";
        var content = "type=mod&amountU="+a+"&OuttypeU="+OuttypeValue+"&idU="+in_ID+"&noteU="+n;
        xhr.open("POST", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resultText = xhr.responseText;
                alert(resultText);
                $("#dg").datagrid("reload");
            }
        };
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
        xhr.send(content);
        //保存，重刷
        $("#dg").datagrid("reload");
    } else {
        amount.value = "";
    }
}
var in_ID=0;
function DoUpdate() {
    //选中要编辑的行
    var row = $("#dg").datagrid('getSelections');
    if(row.length == 1) {
        in_ID = row[0].out_id;


        var select = document.getElementById("SelectU");
        for(var i = 0; i < select.options.length; i++) {
            if(select.options[i].innerHTML == row[0].out_type) {
                select.options[i].selected = true;
                break;
            }
        }

        var amountU = document.getElementById("amountU");
        amountU.value = row[0].out_amount;

        var noteU = document.getElementById("noteU");
        noteU.value = row[0].out_note;

        $('#updateWIN').window('open');
    } else {
        alert("请选择要编辑的行！");
    }
}

function DoAdd() {
    var select = document.getElementById("SelectA");
    select.options[0].selected = true;

    var amountA = document.getElementById("amountA");
    amountU.value = "";

    var noteA = document.getElementById("noteA");
    noteU.value = "";

    $('#addWIN').window('open');
}

function DoSave() {
    var amountA = document.getElementById("amountA");
    var a = amountA.value;
    var Outtype = document.getElementById("SelectA");
    var index = Outtype.selectedIndex; // 选中索引
    var OuttypeValue = Outtype.options[index].value; // 选中值
    var timeA = document.getElementById("timeA");
    var t = timeA.value;
    var noteA = document.getElementById("noteA");
    var n = noteA.value;
    if(reg("amountA")) {
        createXHR();
        var url = "http://localhost:8085/FamilyFinances_war_exploded/ExpendServlet";
        var content = "type=add&amountA="+a+"&OuttypeA="+OuttypeValue+"&timeA="+t+"&noteA="+n;
        xhr.open("POST", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resultText = xhr.responseText;
                alert(resultText);
                $("#dg").datagrid("reload");
            }
        };
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
        xhr.send(content);
    } else {
        amount.value = "";
    }
}

function reg(id) {
    var amount = document.getElementById(id);
    var pattern = /^\d+\.+\d+$/;
    var pattern2 = /^\d{1,}$/;
    if(amount.value == "" || ((!pattern.test(amount.value)) && (!pattern2.test(amount.value)))||amount.value<0) {
        alert(amount.value + "金额不可为空或格式错误（必须为整数或小数）！请重新输入");
        return false;
    } else {
        return true;
    }
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