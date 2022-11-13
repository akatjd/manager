var adminDowntimeScheduleGrid = new ax5.ui.grid();

ax5.ui.grid.formatter["startTime"] = function () {
    return value;
};

ax5.ui.grid.formatter["autoUseYn"] = function () {
    var value = "";
    var dtSeq = this.item.dtSeq;
    var useYn = this.value;
    switch (useYn) {
    case 'y'  : 
        value += '<h5 style="margin-top: .1rem; padding-left: 10px; font-weight: bold "><span class="badge bg-success" style="cursor: pointer;width:60px;height:27px;" onclick="changeAutoUseActive(\'' + dtSeq + '\', \'' + useYn + '\')"><small>사용중</small></span>&nbsp;&nbsp;</h5>';
        break;
    case'n' :
        value += '<h5 style="margin-top: .1rem; padding-left: 10px;"><span class="badge bg-warning" style="cursor: pointer;width:60px;height:27px;" onclick="changeAutoUseActive(\'' + dtSeq + '\', \'' + useYn + '\')"><small>중지</small></span>&nbsp;&nbsp;</h5>';
        break;
    default : 
        value += '<h5 style="margin-top: .1rem; padding-left: 10px;"><span class="badge bg-warning" style="cursor: pointer;width:60px;height:27px;" onclick="changeAutoUseActive(\'' + dtSeq + '\')"><small>중지</small></span>&nbsp;&nbsp;</h5>';
        break;
    }
    
    return value;
};

ax5.ui.grid.formatter["update"] = function () {
    var value = "";
    var dtSeq = this.item.dtSeq;
    value += '<button class="btn btn-primary me-1 mb-1 btn-sm" onclick="updateSchedule(' + dtSeq + ')">변경</button>&nbsp;&nbsp;';
    value += '<button class="btn btn-danger me-1 mb-1 btn-sm" onclick="deleteDowntimeSchedule(' + dtSeq + ')">삭제</button>';
    return value;
    
};

ax5.ui.grid.formatter["scheduleUse"] = function () {
    var value = this.value;
    var scheduleUseYn = this.item.scheduleUseYn;
    var dtSeq = this.item.dtSeq;
    var factoryId = this.item.factoryId;
    switch (scheduleUseYn) {
    case 'y'  : 
        value += '&nbsp;&nbsp;<span class="badge bg-success" style="cursor: pointer;width:60px;height:27px;padding-top: 10px;" onclick="changeActiveSchedule(\'' + factoryId + '\', \'' + dtSeq + '\', \'' + scheduleUseYn + '\')">사용중</span>';
        break;
    case'n' :
        value += '&nbsp;&nbsp;<span class="badge bg-warning" style="cursor: pointer;width:60px;height:27px;" onclick="changeActiveSchedule(\'' + factoryId + '\', \'' + dtSeq + '\', \'' + scheduleUseYn + '\')">중지</span>';
        break;
    default : 
        value += '&nbsp;&nbsp;<span class="badge bg-warning" style="cursor: pointer;width:60px;height:27px;" onclick="changeActiveSchedule(\'' + factoryId + '\', \'' + dtSeq + '\', \'n\')">중지</span>';
        break;
    }
    return value;
    
};

ax5.ui.grid.formatter["autoUseSchedule"] = function () {
    var startTime = this.value;
	var endTime = this.item.endTime;
	var value = startTime + " ~ " + endTime; 
    return value;
};

ax5.ui.grid.formatter["dayOrNight"] = function () {
	var value = this.value;
	if(value === 'day'){
		value = '주간';
	}else{
		value = '야간';
	}
    return value;
};

function changeActiveSchedule(factoryId, dtSeq, active){
    var msg = '';
    var msg2 = '';
    if(active == 'y'){
        msg = '선택한 스케줄을 중지<br/>하시겠습니까?';
        active = 'n';
    }else{
        msg = '선택한 스케줄을 사용<br/>하시겠습니까?';
//        msg2 = '기존 설정되어 있는 스케쥴은 중지 됩니다.';
        active = 'y';
    }
    swal({
        title: msg,
        type: 'question',
        text: msg2,
        showCancelButton: true,
        confirmButtonText: '확인',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url: '/qgScheduleApi/changeActiveDowntimeSchedule',
                data: {
                    factoryId  : factoryId,
                    dtSeq : dtSeq,
                    active     : active
                },
                success: function (data) {
                    if(data == 'success'){
                        swal({
                            type: 'success',
                            title: '변경 되었습니다.',
                            showConfirmButton: true
                        }).then(function () {
                            searchDowntimeSchedulePolicyList();
                        });
                    }else{
                        swal({
                            type: 'error',
                            title: '변경이 실패 했습니다.',
                            showConfirmButton: true
                        }).then(function () {
                            
                        });
                    }
                },
                error: function (request, status, error) {
                    swal({
                        type: 'error',
                        title: '변경이 실패 했습니다.',
                        showConfirmButton: true
                    }).then(function () {
                        console.log("code:" + request.status + "\n" + "message:"+ request.responseText + "\n" + "error:" + error);
                    });
                }
            });
        }
    });
}

function changeAutoUseActive(dtSeq, active){
    var msg = '';
    if(active == 'y'){
		msg = '무인가공 스케줄을 중지<br/>하시겠습니까?';
        active = 'n';
    }else{
		msg = '무인가공 스케줄을 사용<br/>하시겠습니까?';
        active = 'y';
    }
    swal({
        title: msg,
        type: 'question',
        showCancelButton: true,
        confirmButtonText: '확인',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url: '/qgScheduleApi/changeAutoUseActive',
                data: {
                    dtSeq : dtSeq,
                    active     : active,
                },
                success: function (data) {
                    if(data == 'success'){
                        swal({
                            type: 'success',
                            title: '변경 되었습니다.',
                            showConfirmButton: true
                        }).then(function () {
                            searchDowntimeSchedulePolicyList();
                        });
                    }else{
                        swal({
                            type: 'error',
                            title: '변경이 실패 했습니다.',
                            showConfirmButton: true
                        }).then(function () {
                            
                        });
                    }
                },
                error: function (request, status, error) {
                    swal({
                        type: 'error',
                        title: '변경이 실패 했습니다.',
                        showConfirmButton: true
                    }).then(function () {
                        console.log("code:" + request.status + "\n" + "message:"+ request.responseText + "\n" + "error:" + error);
                    });
                }
            });
        }
    });
}


function initGrid(){
    adminDowntimeScheduleGrid.setConfig({
        target: $('[data-ax5grid="adminDowntimeSchedule-grid"]'),
        showLineNumber: true,
        showRowSelector: false,
        multipleSelect: false,
        lineNumberColumnWidth: 40,
        rowSelectorColumnWidth: 28,
        sortable: true, 
        multiSort: true,
        scroller: {size: 20},
        header: {
            align: "center",
            columnHeight: 36
        },
        body: {
            align: "center",
            columnHeight: 36,
            mergeCells: ["factoryName"],
            onClick: function () {
            },
            trStyleClass: function(){
                return "grid-cell-white";
            }
        },
        columns: [
            {key: "factoryName",    label: "공장명",      width: 160, align: "left"},
			{key: "dayOrNight",    label: "주, 야간",      width: 80, formatter: "dayOrNight"},
            {key: "scheduleName",     label: "비가동 스케줄명",    width: 260, formatter: "scheduleUse"},
			{key: "startTime",   label: "무인가공 스케줄",   width: 220, formatter:"autoUseSchedule"},
//            {key: "startTime",   label: "시작시간",   width: 220},
//            {key: "endTime", label: "종료시간",   width: 220},
//            {key: "allowDayTypeNm", label: "적용요일",    width: 160},
			{key: "autoUseYn", label: "무인가공",   width: 90, formatter: "autoUseYn"},
            {key: "scheduleUseYn",  label: "",         width: 150,      formatter: "update", sortable: false}
        ]
    });
    adminDowntimeScheduleGrid.setColumnSort({factoryName:{seq:0, orderBy:"asc"}});
}

function addDowntimeSchedule() {
	
    $('#policyId').attr("readonly", true);
    $.validator.setDefaults({
        ignore: ':hidden, [readonly=readonly]'
    });
    $("#scheduleModal").modal('show');

    $('#factoryId').val('');
    $('#policyName').val('');
    $('#allowMachine').val('');
    $('#formTitle').text('비가동 스케줄 등록');
    $('#submitBtn').text('등록');
}

//조회
function searchDowntimeSchedulePolicyList(){
    var factoryId = $('#selFactory option:selected').val();
    $.ajax({
        url: '/qgScheduleApi/getDowntimeScheduleList',
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
            adminDowntimeScheduleGrid.setData(data);
            swal.closeModal();
        }
    });
}

function updateSchedule(dtSeq){
    $.ajax({
        url: '/qgScheduleApi/getDowntimeSchedule',
        type: 'GET',
        data: {
            dtSeq: dtSeq
        },
        success: function (data) {
            $('#formTitle').text('비가동 스케줄 수정');
            $('#submitBtn').text('수정');+
            $('input:checkbox[name="allowDayBtn"]').each(function() {
                this.checked = false; //checked 처리
                $('#dayType'+this.value).attr('class','btn btn-default');
            });
            $('#policyId').val(data.dtSeq);
            $('#factoryId').val(data.factoryId);
            $('#policyName').val(data.scheduleName);
			
			if(data.scheduleUseYn === 'y'){
				$('#scheduleUseYn1').attr("checked", true);
			}else{
				$('#scheduleUseYn2').attr("checked", true);
			}
			
			if(data.autoUseYn === 'y'){
				$('#downtimeScehduleUserYn1').attr("checked", true);
			}else{
				$('#downtimeScehduleUserYn2').attr("checked", true);
			}
			

            var startTimeArr = data.startTime.split(':');
            var endTimeArr = data.endTime.split(':');
            $('#fDay #startHour').val(startTimeArr[0]);
            $('#fDay #startMin').val(startTimeArr[1]);
            $('#fDay #endHour').val(endTimeArr[0]);
            $('#fDay #endMin').val(endTimeArr[1]);
            
            $("#scheduleModal").modal('show');
        }
    });
}

function arrayDay() {
    var counter=0;
    var checkedItems = "[";
    
    $("input:checkbox[name=allowDayBtn]:checked").each(function(){
        if(counter > 0)
            checkedItems += ",";
        checkedItems += $(this).val();
        counter++;
    });
    checkedItems += "]";
    return checkedItems;
}

//저장
function saveDowntimeSchedule(){
    if(!saveChk()) return;
    
    swal({
        title: '저장 하시겠습니까?',
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '저장',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.value) {
            var beginHour = $('#fDay #startHour').val();
            var beginMin = $('#fDay #startMin').val();
            var endHour = $('#fDay #endHour').val();
            var endMin = $('#fDay #endMin').val();
            
            var beginTime = beginHour + ":" + beginMin;
            var endTime = endHour + ":" + endMin;
            var scheduleUseYn = 'n';
			var autoUseYn = 'n';
			var dayOrNight = 'day';
			if($('#scheduleUseYn1').prop('checked')){
                scheduleUseYn = 'y';
            }else{
				scheduleUseYn = 'n';
			}
			if($('#downtimeScheduleUseYn1').prop('checked')){
				autoUseYn = 'y';
			}else{
				autoUseYn = 'n';
			}
			if($('#dayCheck').prop('checked')){
				dayOrNight = $('#dayCheck').val();
			}else{
				dayOrNight = $('#nightCheck').val();
			}
            
            var param = 
            {
                "policyId"        : $('#policyId').val(),
                "factoryId"       : $('#factoryId').val(),
                "policyName"      : $('#policyName').val(),
                "scheduleUseYn"   : scheduleUseYn,
				"autoUseYn" : autoUseYn,
                "beginTime"    : beginTime,
                "endTime"      : endTime,
				"dayOrNight"   : dayOrNight
            };
            
            $.ajax({
                url: '/qgScheduleApi/saveDowntimeSchedule',
                type: 'POST',
                data: param,
                success: function (data) {
                    if(data == 'success'){
                        swal({
                            type: 'success',
                            title: '저장 되었습니다.',
                            showConfirmButton: true
                        }).then(function () {
                            $("#scheduleModal").modal('hide');
                            searchDowntimeSchedulePolicyList();
                        });
                    }else{
                        swal({
                            type: 'error',
                            title: '저장이 실패 했습니다.',
                            showConfirmButton: true
                        }).then(function () {
                            
                        });
                    }
                }
                ,
                error: function (request, status, error) {
                    swal({
                        type: 'error',
                        title: '저장이 실패 했습니다.',
                        showConfirmButton: true
                    }).then(function () {
                        console.log("code:" + request.status + "\n" + "message:"+ request.responseText + "\n" + "error:" + error);
                    });
                }
            });
        }
    })
}

function saveChk(){
	var startHour = $('#fDay #startHour').val();
    var startMin = $('#fDay #startMin').val();
    var endHour = $('#fDay #endHour').val();
    var endMin = $('#fDay #endMin').val();
    var factoryId = $('#factoryId').val();
    var scheduleName = $('#policyName').val();
	if(startHour > endHour){
		swal({
            title: '시간을 올바르게 선택해주세요.',
            type: 'warning',
            showCancelButton: false,
            confirmButtonColor: '#3085d6',
        }).then(function () {
            $('#fDay #startHour').focus();
        });
        return false;
	}else if(startHour === endHour){
		if(startMin > endMin){
			swal({
	            title: '시간을 올바르게 선택해주세요.',
	            type: 'warning',
	            showCancelButton: false,
	            confirmButtonColor: '#3085d6',
	        }).then(function () {
	            $('#fDay #startMin').focus();
	        });
	        return false;
		}
	}
    if(isNull(factoryId)){
        swal({
            title: '공장을 선택하세요.',
            type: 'warning',
            showCancelButton: false,
            confirmButtonColor: '#3085d6',
        }).then(function () {
            $('#factoryId').focus();
        });
        return false;
    }
    if(isNull(scheduleName)){
        swal({
            title: '스케쥴명을 입력하세요.',
            type: 'warning',
            showCancelButton: false,
            confirmButtonColor: '#3085d6',
        }).then(function () {
            $('#policyName').focus();
        });
        return false;
    }
    return true;
}

//삭제
function deleteDowntimeSchedule(dtSeq) {
    swal({
        title: "정말 삭제하시겠습니까?",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: '삭제',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url: '/qgScheduleApi/deleteDowntimeSchedule',
                data: {
                    dtSeq: dtSeq
                },
                success: function (data) {
                    if(data == 'success'){
                        swal({
                            type: 'success',
                            title: '삭제 되었습니다.',
                            showConfirmButton: true
                        }).then(function () {
                            searchDowntimeSchedulePolicyList();
                        });
                    }else{
                        swal({
                            type: 'error',
                            title: '삭제가 실패 했습니다.',
                            showConfirmButton: true
                        }).then(function () {
                        });
                    }
                },
                error: function (request, status, error) {
                    swal({
                        type: 'error',
                        title: '삭제가 실패 했습니다.',
                        showConfirmButton: true
                    }).then(function () {
                        console.log("code:" + request.status + "\n" + "message:"+ request.responseText + "\n" + "error:" + error);
                    });
                }
            });
        }
    });
}

function isNull(obj){
    return (typeof obj != "undefined" && obj != null && obj != "") ? false :  true;
}

$(document).ready(function() {
	
	initGrid();
    searchDowntimeSchedulePolicyList();
    
    $('#scheduleModal').on('show.bs.modal', function(event) {
//        $('#scheduleUseYn').bootstrapToggle();
    });
    
    $('#scheduleModal').on('hidden.bs.modal', function(event) {
        $('#scheduleModalBody2').remove();
        var myClone = originalModal.clone();
        $('#scheduleModalBody1').append(myClone);
    });
});