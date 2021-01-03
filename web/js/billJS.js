$(function() {
    var pager1 = $('#inTable').datagrid().datagrid('getPager'); // get the pager of datagrid
    var pager2 = $('#outTable').datagrid().datagrid('getPager'); // get the pager of datagrid
    var pager3 = $('#finTable').datagrid().datagrid('getPager'); // get the pager of datagrid
})
$(function() {
    $('#inTable').datagrid({
        singleSelect: true,
        toolbar: '#tb1',
        url: 'http://localhost:8085/FamilyFinances_war_exploded/json/Income_BILL.json',
        method: 'get',
        rownumbers: true,
        pagination: true,
        columns: [
            [{
                field: 'in_id',
                title: '账单编号',
                width: 100,
                align: 'center'
            },
                {
                    field: 'in_type',
                    title: '收入类型',
                    width: 100,
                    align: 'center'
                },
                {
                    field: 'in_amount',
                    title: '金额',
                    width: 100,
                    align: 'right'
                },
                {
                    field: 'in_time',
                    title: '记账时间',
                    width: 80,
                    align: 'center'
                },
                {
                    field: 'in_note',
                    title: '备注',
                    width: 100,
                    align: 'center'
                }
            ]
        ]
    });
});

$(function() {
    $('#outTable').datagrid({
        singleSelect: true,
        toolbar: '#tb2',
        url: 'http://localhost:8085/FamilyFinances_war_exploded/json/Expend_BILL.json',
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

$(function() {
    $('#finTable').datagrid({
        singleSelect: true,
        toolbar: '#tb3',
        url: 'http://localhost:8085/FamilyFinances_war_exploded/json/Finance_BILL.json',
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

function inSearch() {
    if(dateReg('DateStart1', 'DateEnd1')) {
        var dateStart = document.getElementById('DateStart1');
        var dateEnd = document.getElementById('DateEnd1');
        var sobj = document.getElementById('Select1'); //selectid
        var index = sobj.selectedIndex; // 选中索引
        var selectValue = sobj.options[index].value; // 选中值
        createXHR();
        var url = "http://localhost:8085/FamilyFinances_war_exploded/BillServlet";
        var content = "type=income&INdateStart="+dateStart.value+"&INdateEnd="
            +dateEnd.value+"&selectIN="+selectValue;
        xhr.open("POST", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resultText = xhr.responseText;
                $("#inTable").datagrid("reload");
            }
        };
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
        xhr.send(content);
    }
}



function outSearch() {

    if(dateReg('DateStart2', 'DateEnd2')) {
        var dateStart = document.getElementById('DateStart2');
        var dateEnd = document.getElementById('DateEnd2');
        var sobj = document.getElementById('Select2'); //selectid
        var index = sobj.selectedIndex; // 选中索引
        var selectValue = sobj.options[index].value; // 选中值

        createXHR();
        var url = "http://localhost:8085/FamilyFinances_war_exploded/BillServlet";
        var content = "type=expend&OUTdateStart="+dateStart.value+"&OUTdateEnd="
            +dateEnd.value+"&selectOUT="+selectValue;
        xhr.open("POST", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resultText = xhr.responseText;
                $("#outTable").datagrid("reload");
            }
        };
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
        xhr.send(content);
    }

}

function finSearch() {
    if(dateReg('DateStart3', 'DateEnd3')) {
        var dateStart = document.getElementById('DateStart3');
        var dateEnd = document.getElementById('DateEnd3');
        var sobj = document.getElementById('Select3'); //selectid
        var index = sobj.selectedIndex; // 选中索引
        var selectValue = sobj.options[index].value; // 选中值

        createXHR();
        var url = "http://localhost:8085/FamilyFinances_war_exploded/BillServlet";
        var content = "type=finance&FINdateStart="+dateStart.value+"&FINdateEnd="
            +dateEnd.value+"&selectFIN="+selectValue;
        xhr.open("POST", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resultText = xhr.responseText;
                $("#finTable").datagrid("reload");
            }
        };
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
        xhr.send(content);
    }
}

function dateReg(id1, id2) {
    var dateStart = document.getElementById(id1);
    var dateEnd = document.getElementById(id2);
    if(dateStart.value > dateEnd.value) {
        alert("结束日期不可早于开始日期！");
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

//收入导出excel
function INexportExcel(){
    $('#inTable').datagrid('toExcel','收入账单.xls');	// export to excel
}

//支出导出excel
function OUTexportExcel(){
    $('#outTable').datagrid('toExcel','支出账单.xls');	// export to excel
}

//理财导出excel
function FINexportExcel(){
    $('#finTable').datagrid('toExcel','理财账单.xls');	// export to excel
}