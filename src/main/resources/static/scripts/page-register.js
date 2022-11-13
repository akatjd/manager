function saveUserInfo(){
    $('#signUpForm').attr('action', '/signUp/saveSignUp').submit();
}

function isNull(obj){
    return (typeof obj != "undefined" && obj != null && obj != "") ? false :  true;
}

$(function () {
    $("#signUpForm").validate({
        rules: {
            accountId: {
                required: true,
                minlength: 2,
                maxlength: 50,
                accountIdCheck: true // remote check for duplicate username
            },
            username: {
                required: true,
                minlength: 2,
                maxlength: 12,
            },
            password: {
                required: true,
                minlength: 4,
                maxlength: 20,
            },
            confirm_password: {
                required: true,
                minlength: 4,
                maxlength: 20,
                equalTo: "#password"
            },
        },
        messages: {
            accountId: {
                required: "사용중인 이메일을 입력해주세요.",
                maxlength: "아이디는 50자 이하로 입력해주세요.",
                email : "사용중인 이메일을 입력해주세요.",
                accountIdCheck: "이미 사용중인 아이디 입니다."
            },
            userName: {
                required: "이름을 입력해주세요.",
                minlength: "이름은 2~20자 사이로 입력해주세요.",
                maxlength: "이름은 2~20자 사이로 입력해주세요.",
            },
            password: {
                required: "비밀번호를 입력해주세요.",
                minlength: "비밀번호는 4~20자 사이로 입력해주세요.",
                maxlength: "비밀번호는 4~20자 사이로 입력해주세요."
            },
            confirm_password: {
                required: "패스워드를 입력해주세요.",
                minlength: "비밀번호는 4~20자 사이로 입력해주세요.",
                maxlength: "비밀번호는 4~20자 사이로 입력해주세요.",
                equalTo: "동일한 패스워드를 입력해주세요."
            },
        }
    });
    $.validator.setDefaults({
        ignore: '[readonly=readonly]'
    });
});

jQuery.validator.addMethod('accountIdCheck', function (accountId) {
    var result = false;
    $.ajax({
            cache: false,
            async: false,
            url: '/signUpApi/accountIdChk',
            type: 'POST',
            data: {
                accountId: accountId
            },
            dataType: 'text',
            success: function (data) {
                if(data == 'true'){
                    result = true;
                }else if(data == 'req'){
                	result = false;
                }else{
                	result = false
                }
            }
        }
    );
    return result;
}, '');

$.validator.addMethod('validChars', function (value) {
    var result = true;
    // unwanted characters
    var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
    for (var i = 0; i < value.length; i++) {
        if (iChars.indexOf(value.charAt(i)) != -1) {
            return false;
        }
    }
    if (isKorean(value)) {
        return false;
    }
    return result;
}, '');

isKorean: function isKorean(value) {
    var numUnicode = value.charCodeAt(0);
    if (44031 <= numUnicode && numUnicode <= 55203 || 12593 <= numUnicode && numUnicode <= 12643) return true;
    return false;
}
