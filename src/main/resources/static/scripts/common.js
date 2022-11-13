// document.write("<script src='/scripts/jquery.i18n.properties.js'></script>");
var myPageOriginalModal = $('#myPageModalContents').clone();
function setUserInfo(account){
    $('#accountId').val(account.accountId);
    $('#userName').val(account.userName);
//    $('#password').val(account.password);
//    $('#confirmPassword').val("");
    $('#department').val(account.department);
}

function saveUserInfoCheck(){

    var commonJs_enterUserIdMsg =  jQuery.i18n.prop('commonJs_enterUserIdMsg');
    var commonJs_enterUserNameMsg =  jQuery.i18n.prop('commonJs_enterUserNameMsg');
    var commonJs_enterUserNameLengthMsg =  jQuery.i18n.prop('commonJs_enterUserNameLengthMsg');
    var commonJs_enterPasswordMsg =  jQuery.i18n.prop('commonJs_enterPasswordMsg');
    var commonJs_enterPasswordLengthMsg =  jQuery.i18n.prop('commonJs_enterPasswordLengthMsg');
    var commonJs_enterPasswordConfirmMsg =  jQuery.i18n.prop('commonJs_enterPasswordConfirmMsg');
    var commonJs_enterSamePasswordMsg =  jQuery.i18n.prop('commonJs_enterSamePasswordMsg');
    var commonJs_enterDepartmentMsg =  jQuery.i18n.prop('commonJs_enterDepartmentMsg');
    var commonJs_enterDepartmentLengthMsg =  jQuery.i18n.prop('commonJs_enterDepartmentLengthMsg');


    if(isNull($('#accountId').val())){
        swal(commonJs_enterUserIdMsg, "", "warning");
        return false;
    }
    if(isNull($('#userName').val())){
        swal(commonJs_enterUserNameMsg, "", "warning");
        return false;
    }
    if($('#userName').val().length > 50 || $('#userName').val().length < 2){
        swal(commonJs_enterUserNameLengthMsg, "", "warning");
        return false;
    }
    
    if(isNull($('#password').val())){
        swal(commonJs_enterPasswordMsg, "", "warning");
        return false;
    }
    if($('#password').val().length > 50 || $('#password').val().length < 4){
        swal(commonJs_enterPasswordLengthMsg, "", "warning");
        return false;
    }
    
    if(isNull($('#confirmPassword').val())){
        swal(commonJs_enterPasswordConfirmMsg, "", "warning");
        return false;
    }
    
    if($('#password').val() != $('#confirmPassword').val()){
        swal(commonJs_enterSamePasswordMsg, "", "warning");
        return false;
    }
    
    if(isNull($('#department').val())){
        swal(commonJs_enterDepartmentMsg, "", "warning");
        return false;
    }
    if($('#department').val().length > 50){
        swal(commonJs_enterDepartmentLengthMsg, "", "warning");
        return false;
    }
    return true;
}

function saveUserInfo(){
    var commonJs_saveDataComplete =  jQuery.i18n.prop('commonJs_saveDataComplete');
    var commonJs_loginAgain =  jQuery.i18n.prop('commonJs_loginAgain');
    var commonJs_confirm =  jQuery.i18n.prop('commonJs_confirm');
    var commonJs_failedSave =  jQuery.i18n.prop('commonJs_failedSave');
    var commonJs_cancel =  jQuery.i18n.prop('commonJs_cancel');
    var commonJs_saveUserMsg =  jQuery.i18n.prop('commonJs_saveUserMsg');
	
	var param = new Array();
        param = 
        {
                "accountId"       : $('#accountId').val(),
                "userName"        : $('#userName').val(),
                "password"        : $('#password').val(),
                "department"      : $('#department').val()
        };
	
    if(saveUserInfoCheck()){
        var param = new Array();
        param = 
        {
                "accountId"       : $('#accountId').val(),
                "userName"        : $('#userName').val(),
                "password"        : $('#password').val(),
                "department"      : $('#department').val()
        };
        swal({
            title: commonJs_saveUserMsg,
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
            cancelButtonText: commonJs_cancel,
            confirmButtonText: commonJs_confirm
        },
        function(isConfirm){
			console.log("function들어옴");
            if(isConfirm){
                $.ajax({
                    url: '/myPageApi/saveUserInfo',
                    type: 'POST',
                    data: param,
                    success: function (data) {
                        swal({
                                title: commonJs_saveDataComplete,
                                text: commonJs_loginAgain,
                                type: "success",
                                showCancelButton: false,
                                closeOnConfirm: false,
                                showLoaderOnConfirm: true,
                                confirmButtonText: commonJs_confirm
                            },
                            function(){
                                location.href="/logout";
                            })
                    },
                    error : function(request, status, error) {
                        console.log("code:" + request.status + "\n" + "message:"
                                + request.responseText + "\n" + "error:" + error);
                        swal("ERROR", commonJs_failedSave, "error");
                        $btn.button('reset');
                    }
                }
                );
            }else{
                $btn.button('reset');
            }
        });
	}
}

function getUserInfo(){
    $.ajax({
        url: '/myPageApi/getUserInfo',
        type: 'POST',
        dataType: 'json',
        success: function (data) {
            setUserInfo(data);
			console.log(data);
        },
        error : function(request, status, error) {
            console.log("code:" + request.status + "\n" + "message:"
                + request.responseText + "\n" + "error:" + error);
            swal("ERROR", request.responseText, "error");
        }
    });
}

function isNull(obj){
    return (typeof obj != "undefined" && obj != null && obj != "") ? false :  true;
}

$(document).ready(function(){
    $("#myPageModal").on("show.bs.modal", function () {
        getUserInfo();
    });
    $('#myPageModal').on('hidden.bs.modal', function (e) {
        $('#myPageModalContents').remove();
        var myPageClone = myPageOriginalModal.clone();
        $('#myPageModalBd').append(myPageClone);
   });
    
    // 한글입력막기 스크립트
    $(".not-kor").keypress(function(e) { 
        if (!(e.keyCode >=37 && e.keyCode<=40)) {
            var v = $(this).val();
            $(this).val(v.replace(/[^A-Za-z0-9_\`\~\!\@\#\$\%\^\&\*\(\)\-\=\+\\\{\}\[\]\'\"\;\:\<\,\>\.\?\/\s]/gm,''));
        }
    });
    
    $(".not-kor").blur(function(e) { 
        if (!(e.keyCode >=37 && e.keyCode<=40)) {
            var v = $(this).val();
            $(this).val(v.replace(/[^A-Za-z0-9_\`\~\!\@\#\$\%\^\&\*\(\)\-\=\+\\\{\}\[\]\'\"\;\:\<\,\>\.\?\/\s]/gm,''));
        }
    });
    
    $(".not-kor").keydown(function(e) { 
        if (!(e.keyCode >=37 && e.keyCode<=40)) {
            var v = $(this).val();
            $(this).val(v.replace(/[^A-Za-z0-9_\`\~\!\@\#\$\%\^\&\*\(\)\-\=\+\\\{\}\[\]\'\"\;\:\<\,\>\.\?\/\s]/gm,''));
        }
    });
    
    $(".not-kor").keyup(function(e) { 
        if (!(e.keyCode >=37 && e.keyCode<=40)) {
            var v = $(this).val();
            $(this).val(v.replace(/[^A-Za-z0-9_\`\~\!\@\#\$\%\^\&\*\(\)\-\=\+\\\{\}\[\]\'\"\;\:\<\,\>\.\?\/\s]/gm,''));
        }
    });
    var selection = getCookie("lang");
    console.log("lang::" + selection);
    if(isNull(selection)){
    	setCookie("lang", selection);
    	selection = "kr";
    	$("#lang").val(selection).trigger("change");
    }
    console.log("lang::" + selection);
    $("#lang").val(selection).attr("selected", "selected");
//    $(".select7").select7();
    loadBundles(selection);
});

// 쿠키 가져오기
function getCookie(cName) {
    cName = cName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cName);
    var cValue = '';
    if (start != -1) {
        start += cName.length;
        var end = cookieData.indexOf(';', start);
        if (end == -1) end = cookieData.length;
        cValue = cookieData.substring(start, end);
    }
    return unescape(cValue);
}

function setCookie(cName, cValue, cDay){
        var expire = new Date();
        expire.setDate(expire.getDate() + cDay);
        cookies = cName + '=' + escape(cValue) + '; path=/ '; // 한글 깨짐을 막기위해 escape(cValue)를 합니다.
        if(typeof cDay != 'undefined') cookies += ';expires=' + expire.toGMTString() + ';';
        document.cookie = cookies;
    }
jQuery('#lang').change(function() {
    var selection = jQuery('#lang option:selected').val();
    setCookie("lang", selection);
    location.href="/?lang="+selection;
});

function loadBundles(lang) {
	if(isNull(lang)) lang = kr;
    jQuery.i18n.properties({
        name:'Messages',
        path:'../../scripts/messages/',
        mode:'both',
        language:lang,
        callback: function() {
//            $("#lang").val(lang);
        }
    });
}

function lpad(s, padLength, padString) {
    while (s.length < padLength)
        s = padString + s;
    return s;
}

function isNull(obj) {
    return (typeof obj != "undefined" && obj != null && obj != "") ? false : true;
}

function funcElasticSave(){
    var apiUrl = $('#apiUrl').val();
	param = $('#elasticData').val();
	param = JSON.parse(param);
	param = JSON.stringify(param);
    $.ajax({
            url: apiUrl,
            type: 'POST',
            data: param ,
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {	
                console.log(data);
                swal(data);
            	$('#elasticData').val('');
            },
            error : function(request, status, error) {
                console.log("code:" + request.status + "\n" + "message:"
                        + request.responseText + "\n" + "error:" + error);
                swal("", request.responseText, "warning");
                swal(request.responseText);
                $('#elasticData').val('');
            }
        });
}

function funcProductSave(){
    param = $('#elasticData').val();
    param = JSON.parse(param);
    param = JSON.stringify(param);
    $.ajax({
            url: 'client/pjs/productReg',
            type: 'POST',
            data: param ,
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                console.log(data);
                swal(data);
                $('#elasticData').val('');
            },
            error : function(request, status, error) {
                console.log("code:" + request.status + "\n" + "message:"
                        + request.responseText + "\n" + "error:" + error);
                swal("", request.responseText, "warning");
                swal(request.responseText);
                $('#elasticData').val('');
            }
        });
}