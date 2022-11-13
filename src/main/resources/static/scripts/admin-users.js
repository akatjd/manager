var membersGrid = new ax5.ui.grid();

ax5.ui.grid.formatter["updateBtn"] = function () {
    var value = "";
    var accountId = this.value;
    value = '<button class="btn btn-primary me-1 mb-1 btn-sm" onclick="editMember(\'' + accountId + '\')" style="height:25px;margin-top:-1px" data-bs-toggle="modal" data-bs-target="#editModal">수정</button>';
    return value;
};
ax5.ui.grid.formatter["deleteBtn"] = function () {
    var value = "";
    var accountId = this.value;
    if(sessionAccountId != accountId)
        value = '<button class="btn btn-danger me-1 mb-1 btn-sm" onclick="deleteMember(\'' + accountId + '\')" style="height:25px;margin-top:-1px">삭제</button>';
    return value;
};
ax5.ui.grid.formatter["role"] = function () {
    var value = "";
    switch (this.value) {
    case 'ROLE_ADMIN'  : 
        value = 'Admin';
        break;
    case'ROLE_MANAGER' :
        value = 'Manager';
        break;
    case'ROLE_USER' :
        value = 'USER';
        break;
    case'ROLE_MEMBER' :
        value = 'USER';
        break;
    default : 
        value = this.value;
        break;
    }
    return value;
};
ax5.ui.grid.formatter["approval"] = function () {
    var value = "";
    switch (this.value) {
    case 'approval'  : 
        value = 'Approval';
        break;
    case'req' :
        value = 'Requset';
        break;
    default : 
        value = this.value;
        break;
    }
    return value;
};
ax5.ui.grid.formatter["dashType"] = function () {
    var value = "";
    switch (this.value) {
    case 'area_dashboard'  : 
        value = 'Area';
        break;
    case 'area_dashboard2'  : 
        value = 'Area';
        break;
    case 'tool_dashboard'  : 
        value = 'Machine';
        break;
    default : 
        value = 'Basic';
        break;
    }
    return value;
};

function initGrid(){
    membersGrid.setConfig({
        target: $('[data-ax5grid="members-grid"]'),
//        frozenColumnIndex: 2,
//        frozenRowIndex: 0,
        showLineNumber: true,
        showRowSelector: false,
        multipleSelect: false,
        lineNumberColumnWidth: 40,
        rowSelectorColumnWidth: 28,
        sortable: true, 
        multiSort: false,
        scroller: {size: 20},
        header: {
            align: "center",
            columnHeight: 32
        },
        body: {
            align: "center",
            columnHeight: 32,
            onClick: function () {
            }
        },
        columns: [
            {key: "accountId",       label: "사용자ID",          width: 160, align: "left"},
            {key: "userName",        label: "사용자명",           width: 160, align: "left"},
            {key: "department",      label: "부서명",            width: 140, align: "left"},
            {key: "role",            label: "권한",              width: 80, align: "left", formatter: "role"},
            {key: "factoryName",     label: "연결공장",           width: 180, align: "left"},
            {key: "mainPageUrl",     label: "메인화면URL",        width: 180, align: "left", formatter:"dashType"},
            {key: "approvalStatus",  label: "승인",              width: 80, align: "left", formatter:"approval"},
            {key: "accountId",       label: "수정",              width: 80, formatter:"updateBtn"},
            {key: "accountId",       label: "삭제",              width: 80, formatter:"deleteBtn"}
        ]
    });
}

$(function () {
    $("#simpleForm").validate({
        rules: {
            accountId: {
                required: true,
                minlength: 2,
                maxlength: 50,
                validChars: false,
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
            allowFactory: {
                factoryIdCheck: true
            }
        },
        messages: {
            accountId: {
                required: "사용자 ID 선택해 주세요.",
                minlength: "사용자 ID는 2자 이상으로 선택해 주세요.",
                maxlength: "사용자 ID는 50자 이하로 선택해 주세요.",
                validChars: "특수문자 및 한글은 사용할 수 없습니다.",
                accountIdCheck: "이미 사용중인 ID 입니다."
            },
            userName: {
                required: "사용자 이름을 선택해 주세요.",
                minlength: "이름은 2자 이상으로 선택해 주세요.",
                maxlength: "이름은 20자 이하로 선택해 주세요.",
            },
            password: {
                required: "패스워드를 선택해 주세요.",
                minlength: "패스워드는 4자 이상으로 선택해 주세요.",
                maxlength: "패스워드는 20자 이하로 선택해 주세요.",
            },
            role: {
                roleChk: "승인 시 권한이 없으면 로그인을 할 수 없습니다.",
            },
            allowFactory: {
            	factoryIdCheck: "공장을 선택해 주세요."
            }
        }
    });
    $.validator.setDefaults({
        ignore: ':hidden, [readonly=readonly]'
    });
    initGrid();
});

var result = false;
var statisticTable;
// extend the validation plugin to do remote username and email dupe checking
jQuery.validator.addMethod('accountIdCheck', function (accountId) {
    /*accountId = $('#accountId');*/
    var postURL = "/adminApi/accountIdCheck";


    $.ajax({
        cache: false,
        async: false,
        url: postURL,
        data: {
            accountId: accountId
        },
        success: function (msg) {
            result = (msg == 'TRUE') ? true : false;
        }
    });
    return result;
}, '');

jQuery.validator.addMethod('factoryIdCheck', function (role) {
if($('#approvalStatus').val() == 'approval' && isNull($('#allowFactory').val())){
  result = false;
}else{
  result = true;
}
return result;
}, '');

//jQuery.validator.addMethod('roleChk', function (role) {
//    if($('#approvalStatus').val() == 'approval' && role == 'ROLE_GUEST'){
//        result = false;
//    }else{
//        result = true;
//    }
//    return result;
//}, '');

jQuery.validator.setDefaults({
    ignore: ':hidden, [readonly=readonly]'
});

// check for unwanted characters
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

function isNull(obj){
    return (typeof obj != "undefined" && obj != null && obj != "") ? false :  true;
}

//문자열 한글 체크 - 한글 true
isKorean: function isKorean(value) {
    var numUnicode = value.charCodeAt(0);
    if (44031 <= numUnicode && numUnicode <= 55203 || 12593 <= numUnicode && numUnicode <= 12643) return true;
    return false;
}
function ChangeUrl(page, url) {
    if (typeof (history.pushState) != "undefined") {
        var obj = {Page: page, Url: url};
        history.pushState(obj, obj.Page, obj.Url);
    } else {
        alert("Browser does not support HTML5.");
    }
}

$(document).ready(function () {
    var paramValue = getUrlParameter('type');
    if (paramValue) {
        if (paramValue == 'add') {
            swal('사용자 등록 완료');
        } else if (paramValue == 'edit') {
            swal('사용자 수정 완료');
        }
        else if (paramValue == 'delete') {
            swal('사용자 삭제 완료');
        }
    }
    ChangeUrl('./', './getUsersView');
});

function addMember() {
    editType = 0;
    $('#accountId').removeAttr("readonly");
    $("#editModal").modal();
    $('#accountId').val('');
    $('#userName').val('');
    $('#password').val('');
    $('#confirm_password').val('');
    $('#department').val('');
    $('#allowFactory').val('');
    $("#role").val("ROLE_USER");
    $("#approvalStatus").val("approval");
    $('#formTitle').text('사용자 등록');
    $('#submitBtn').text('등록');
    $("#mainPageUrl").val('');
}

function editMember(accountId) {
    $('#accountId').attr("readonly", true);
    $.validator.setDefaults({
        ignore: ':hidden, [readonly=readonly]'
    });
    $('#formTitle').text('사용자 정보 수정');
    $('#submitBtn').text('수정');
    $.ajax({
        url: '/adminApi/getMember',
        dataType: 'json',
        data: {
            accountId: accountId
        },
        success: function (data) {
            $('#accountId').val(data.accountId);
            $('#userName').val(data.userName);
            $('#department').val(data.department);
            $('#allowFactory').val(data.allowFactory);
            $("#role").val(data.role);
            $("#approvalStatus").val(data.approvalStatus);
//            $("#password").val(data.password);
            $("#mainPageUrl").val(data.mainPageUrl);
        }
    });

    editType = 1;
    $("#editModal").modal();
}

function searchMemberList(){
    var factoryId = $('#selFactory option:selected').val();
    $.ajax({
        url: '/adminApi/getMemberList',
        type: 'POST',
        data: {
            factoryId: factoryId
        },
        beforeSend: function () {
            swal({
                title: '데이터를 가져오는 중입니다..',
                html: '<div class="progress m-t-xs full progress-striped active" style="width: 90%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="90" role="progressbar" class=" progress-bar progress-bar-success"><div class="progress-bar" style="width: 100%"></div></div>',
                showConfirmButton: false,
            });
        },
        success: function (data) {
            membersGrid.setData(data);
            swal.closeModal();
        }
    });
}

function deleteMember(accountId) {
    swal({
        title: "정말 삭제하시겠습니까?",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: '삭제',
        cancelButtonText: '취소'
    },
	function (isConfirm) {
		if(isConfirm){
			$.ajax({
	            url: '/adminApi/deleteMember',
	            data: {
	                accountId: accountId
	            },
	            success: function (data) {
					console.log("data :: " + data);
	                if(data == 'success'){
	                    swal({
	                        type: 'success',
	                        title: '삭제 되었습니다.',
	                        showConfirmButton: true
	                    },
						function(){
							searchMemberList();
						});
	                }else{
	                    swal({
	                        type: 'error',
	                        title: '삭제가 실패 했습니다.',
	                        showConfirmButton: true
	                    });
	                }
	            },
	            error: function (request, status, error) {
	                swal({
	                    type: 'error',
	                    title: '삭제가 실패 했습니다.',
	                    showConfirmButton: true
	                },
					function(){
						console.log("code:" + request.status + "\n" + "message:"+ request.responseText + "\n" + "error:" + error);
					});
	            }
	        });
		}
	});
}

function submitForm() {
    if (editType === 0) {
        $('#simpleForm').attr('action', './addMember').submit();
    } else {
        $('#simpleForm').attr('action', './editMember').submit();
    }
}

var selectedUserId = '';
var editType = 0; //0 > add, 1 > modify

$('#editModal').on('show.bs.modal', function (e) {
    //if (!data) return e.preventDefault() // stops modal from being shown
});

$('#editModal').on('hide.bs.modal', function (e) {
});

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;
    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

$(document).ready(function () {
    initGrid();
    searchMemberList();
    
    $('#editModal').on('hidden.bs.modal', function (e) {
        $('#editModal #bsModalBody').remove();
        var myClone = originalBasicModal.clone();
        $('#editModal #bsModalBd').append(myClone);
    });
});