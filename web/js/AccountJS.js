var flag = 0;

function showSetPW() {
    if(flag = 1) {
        hideSetAccount();
    }
    flag = 1;
    var show_setPW = document.querySelector('.setPW');
    show_setPW.style.display = 'block';
}

function showSetAccount() {
    if(flag = 1) {
        hideSetPW();
    }
    flag = 1;
    var show_setPW = document.querySelector('.setAccount');
    show_setPW.style.display = 'block';
}

function hideSetPW() {
    flag = 0;
    var show_part = document.querySelector('.setPW');
    show_part.style.display = 'none';
}

function hideSetAccount() {
    flag = 0;
    var show_part = document.querySelector('.setAccount');
    show_part.style.display = 'none';
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

function modPW() {
    var reg = /^[a-zA-Z0-9]{6,12}$/;
    var flag = 1;
    var newPW = document.getElementById("newPW");
    var surPW = document.getElementById("surPW");

    var PW = document.getElementById("PW");
    var oldPW = document.getElementById("oldPW");
    if(PW.value != oldPW.value){
        alert(PW.value+"旧密码错误！！！"+oldPW.value);
        flag = 0;
    }
    else if(newPW.value == "" || (!reg.test(newPW.value))) {
        alert("新密码为空或格式（6-12位，使用字母或數字）不正确！请重新输入");
        flag = 0;
    }
    else if(surPW.value == "" || (!reg.test(surPW.value))) {
        alert("确认密码为空或格式（6-12位，使用字母或數字）不正确！请重新输入");
        flag = 0;
    }
    else if(newPW.value != surPW.value) {
        alert("新密码与确认密码不一致");
        flag = 0;
    }


    if(flag) {
        //触发Servlet修改密码
        createXHR();
        var url = "http://localhost:8085/FamilyFinances_war_exploded/AccountServlet";
        var content = "type=modPW&newPW="+newPW.valuea;
        xhr.open("POST", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resultText = xhr.responseText;
                alert(resultText);
            }
        };
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
        xhr.send(content);
    }
}

function modInfo(){
    var men = document.getElementById("familymen");
    var city = document.getElementById("city");
    var sign = document.getElementById("sign");

    var reg = /^\d$/;
    if(men.value==""||(!reg.test(men.value))||men.value<=0){
        alert("家庭人口必须为正整数大于0！");
    }else{
        alert(men.value+"---"+sign.value+"---"+city.value);
        createXHR();
        var url = "http://localhost:8085/FamilyFinances_war_exploded/AccountServlet";
        var content = "type=modInfo&familysize="+men.value+"&city="+city.value+"&sign="+sign.value;
        xhr.open("POST", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resultText = xhr.responseText;
                alert(resultText);
            }
        };
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
        xhr.send(content);
    }
}
