var adminScheduleGrid = new ax5.ui.grid();

ax5.ui.grid.formatter["dayScheduleTime"] = function () {
    var dayType = 'day';
    var value = "";
    var policyId = this.item.policyId;
    var dayBeginTime = this.item.dayBeginTime;
    var dayEndTime = this.item.dayEndTime;
    var dayUseYn = this.item.dayUseYn;
    if(isNull(dayBeginTime)){
        value = '-&nbsp;&nbsp;';
    }else{
        value = dayBeginTime + " ~ " + dayEndTime + "&nbsp;&nbsp;";
    }
    switch (dayUseYn) {
    case 'y'  : 
        value += '<span class="badge bg-success" style="cursor: pointer;width:60px;height:27px;padding-top: 10px;" onclick="changeActiveDayNight(\'' + policyId + '\', \'' + dayUseYn + '\', \'' + dayType + '\')">사용중</span>&nbsp;&nbsp;';
        break;
    case'n' :
        value += '<span class="badge bg-warning" style="cursor: pointer;width:60px;height:27px;padding-top: 10px;" onclick="changeActiveDayNight(\'' + policyId + '\', \'' + dayUseYn + '\', \'' + dayType + '\')">중지</span>&nbsp;&nbsp;';
        break;
    default : 
        value += '<span class="badge bg-warning" style="cursor: pointer;width:60px;height:27px;padding-top: 10px;" onclick="changeActiveDayNight(\'' + policyId + '\', \'n\', \'' + dayType + '\')">중지</span>&nbsp;&nbsp;';
        break;
    }
    
    return value;
};

ax5.ui.grid.formatter["nightScheduleTime"] = function () {
    var dayType = 'night';
    var value = "";
    var policyId = this.item.policyId;
    var nightBeginTime = this.item.nightBeginTime;
    var nightEndTime = this.item.nightEndTime;
    var nightUseYn = this.item.nightUseYn;
    if(isNull(nightBeginTime)){
        value = '-&nbsp;&nbsp;';
    }else{
        value = nightBeginTime + " ~ " + nightEndTime + "&nbsp;&nbsp;";
    }
    switch (nightUseYn) {
    case 'y'  : 
        value += '<span class="badge bg-success" style="cursor: pointer;width:60px;height:27px;padding-top: 10px;" onclick="changeActiveDayNight(\'' + policyId + '\', \'' + nightUseYn + '\', \'' + dayType + '\')">사용중</span>&nbsp;&nbsp;';
        break;
    case'n' :
        value += '<span class="badge bg-warning" style="cursor: pointer;width:60px;height:27px;padding-top: 10px;" onclick="changeActiveDayNight(\'' + policyId + '\', \'' + nightUseYn + '\', \'' + dayType + '\')">중지</span>&nbsp;&nbsp;';
        break;
    default : 
        value += '<span class="badge bg-warning" style="cursor: pointer;width:60px;height:27px;padding-top: 10px;" onclick="changeActiveDayNight(\'' + policyId + '\', \'n\', \'' + dayType + '\')">중지</span>&nbsp;&nbsp;';
        break;
    }
    
    return value;
};

ax5.ui.grid.formatter["update"] = function () {
    var value = "";
    var policyId = this.item.policyId;
    
    value += '<button class="btn btn-primary me-1 mb-1 btn-sm" onclick="updateSchedule(' + policyId + ')">변경</button>&nbsp;&nbsp;';
    value += '<button class="btn btn-danger me-1 mb-1 btn-sm" onclick="deleteSchedule(' + policyId + ')">삭제</button>';
    return value;
    
};

ax5.ui.grid.formatter["scheduleUse"] = function () {
    var value = this.value;
    var scheduleUseYn = this.item.scheduleUseYn;
    var policyId = this.item.policyId;
    var factoryId = this.item.factoryId;
    switch (scheduleUseYn) {
    case 'y'  : 
        value += '&nbsp;&nbsp;<span class="badge bg-success" style="cursor: pointer;width:60px;height:27px;padding-top: 10px;" onclick="changeActiveSchedule(\'' + factoryId + '\', \'' + policyId + '\', \'' + scheduleUseYn + '\')">사용중</span>';
        break;
    case'n' :
        value += '&nbsp;&nbsp;<span class="badge bg-warning" style="cursor: pointer;width:60px;height:27px;padding-top: 10px;" onclick="changeActiveSchedule(\'' + factoryId + '\', \'' + policyId + '\', \'' + scheduleUseYn + '\')">중지</span>';
        break;
    default : 
        value += '&nbsp;&nbsp;<span class="badge bg-warning" style="cursor: pointer;width:60px;height:27px;padding-top: 10px;" onclick="changeActiveSchedule(\'' + factoryId + '\', \'' + policyId + '\', \'n\')">중지</span>';
        break;
    }
    return value;
    
};

function changeActiveSchedule(factoryId, policyId, active){
    var msg = '';
    var msg2 = '';
    if(active == 'y'){
        msg = '선택한 스케쥴을 중지<br/>하시겠습니까?';
        active = 'n';
    }else{
        msg = '선택한 스케쥴을 사용<br/>하시겠습니까?';
        msg2 = '기존 설정되어 있는 스케쥴은 중지 됩니다.';
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
                url: '/qgScheduleApi/changeActiveSchedule',
                data: {
                    factoryId  : factoryId,
                    scheduleId : policyId,
                    active     : active
                },
                success: function (data) {
                    if(data == 'success'){
                        swal({
                            type: 'success',
                            title: '변경 되었습니다.',
                            showConfirmButton: true
                        }).then(function () {
                            searchSchedulePolicyList();
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

function changeActiveDayNight(policyId, active, dayType){
    var msg = '';
    if(active == 'y'){
        if(dayType == 'day'){
            msg = '주간 스케쥴을 중지<br/>하시겠습니까?';
        }else{
            msg = '야간 스케쥴을 중지<br/>하시겠습니까?';
        }
        active = 'n';
    }else{
        if(dayType == 'day'){
            msg = '주간 스케쥴을 사용<br/>하시겠습니까?';
        }else{
            msg = '야간 스케쥴을 사용<br/>하시겠습니까?';
        }
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
                url: '/qgScheduleApi/changeActiveDayNight',
                data: {
                    scheduleId : policyId,
                    active     : active,
                    dayType    : dayType
                },
                success: function (data) {
                    if(data == 'success'){
                        swal({
                            type: 'success',
                            title: '변경 되었습니다.',
                            showConfirmButton: true
                        }).then(function () {
                            searchSchedulePolicyList();
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
    adminScheduleGrid.setConfig({
        target: $('[data-ax5grid="adminSchedule-grid"]'),
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
            {key: "policyName",     label: "스케줄명",    width: 230, formatter: "scheduleUse"},
            {key: "dayBeginTime",   label: "주간스케줄",   width: 220, formatter: "dayScheduleTime"},
            {key: "nightBeginTime", label: "야간스케줄",   width: 220, formatter: "nightScheduleTime"},
            {key: "allowDayTypeNm", label: "적용요일",    width: 180},
            {key: "scheduleUseYn",  label: "",          width: 150,      formatter: "update", sortable: false}
        ]
    });
    adminScheduleGrid.setColumnSort({factoryName:{seq:0, orderBy:"asc"}});
}

function addSchedule() {
	
    $('#policyId').attr("readonly", true);
    $.validator.setDefaults({
        ignore: ':hidden, [readonly=readonly]'
    });
    $("#scheduleModal").modal('show');

    $('#factoryId').val('');
    $('#policyName').val('');
    $('#allowMachine').val('');
    $('#formTitle').text('스케줄 등록');
    $('#submitBtn').text('등록');
}

//조회
function searchSchedulePolicyList(){
    var factoryId = $('#selFactory option:selected').val();
    $.ajax({
        url: '/qgScheduleApi/getScheduleList',
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
            adminScheduleGrid.setData(data);
            swal.closeModal();
        }
    });
}

function updateSchedule(scheduleId){
    var factoryId = $('#selFactory option:selected').val();
    $.ajax({
        url: '/qgScheduleApi/getSchedule',
        type: 'GET',
        data: {
            scheduleId: scheduleId
        },
        success: function (data) {
            $('#formTitle').text('스케줄 수정');
            $('#submitBtn').text('수정');+
            $('input:checkbox[name="allowDayBtn"]').each(function() {
                this.checked = false; //checked 처리
                $('#dayType'+this.value).attr('class','btn btn-default');
            });
            $('#policyId').val(data.policyId);
            $('#factoryId').val(data.factory.factoryId);
            $('#policyName').val(data.policyName);

			if(data.scheduleUseYn === 'y'){
				$('#scheduleUseYn1').attr("checked", true);
			}else{
				$('#scheduleUseYn2').attr("checked", true);
			}
			
			
            var dayStartTimeArr = data.dayBeginTime.split(':');
            var dayEndTimeArr = data.dayEndTime.split(':');
            var nightStartTimeArr = data.nightBeginTime.split(':');
            var nightEndTimeArr = data.nightEndTime.split(':');
            $('#fDay #dayStartHour').val(dayStartTimeArr[0]);
            $('#fDay #dayStartMin').val(dayStartTimeArr[1]);
            $('#fDay #dayEndHour').val(dayEndTimeArr[0]);
            $('#fDay #dayEndMin').val(dayEndTimeArr[1]);
            
            $('#fNight #nightStartHour').val(nightStartTimeArr[0]);
            $('#fNight #nightStartMin').val(nightStartTimeArr[1]);
            $('#fNight #nightEndHour').val(nightEndTimeArr[0]);
            $('#fNight #nightEndMin').val(nightEndTimeArr[1]);
            
            var dayArr = data.allowDayType;
            var result = dayArr.replace('[', '');
            result = result.replace(']', '');
            var foo = result.split(',');
            for(var i=0;i<foo.length;i++){
                $('#dayType'+foo[i]).attr('class','btn btn-default active');
                $('input:checkbox[name="allowDayBtn"]').each(function() {
                    if(foo[i] == this.value)
                        this.checked = true; //checked 처리
                });
            }
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
function saveSchedule(){
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
            var dayBeginHour = $('#fDay #dayStartHour').val();
            var dayBeginMin = $('#fDay #dayStartMin').val();
            var dayEndHour = $('#fDay #dayEndHour').val();
            var dayEndMin = $('#fDay #dayEndMin').val();
            
            var nightBeginHour = $('#fNight #nightStartHour').val();
            var nightBeginMin = $('#fNight #nightStartMin').val();
            var nightEndHour = $('#fNight #nightEndHour').val();
            var nightEndMin = $('#fNight #nightEndMin').val();
            
            var dayBeginTime = dayBeginHour + ":" + dayBeginMin;
            var dayEndTime = dayEndHour + ":" + dayEndMin;
            var nightBeginTime = nightBeginHour + ":" + nightBeginMin;
            var nigtEndTime = nightEndHour + ":" + nightEndMin;
            var allowDayType = arrayDay();
            var scheduleUseYn = 'n';
            var dayUseYn = 'n';
            var nightUseYn = 'n';
//            if($('#scheduleUseYn').prop('checked')){
//                scheduleUseYn = 'y';
//            }
			if($('#scheduleUseYn1').prop('checked')){
                scheduleUseYn = 'y';
            }else{
				scheduleUseYn = 'n';
			}
            if($('#dayUseYn').prop('checked')){
                dayUseYn = 'y';
            }
            if($('#nightUseYn').prop('checked')){
                nightUseYn = 'y';
            }
            
            var param = 
            {
                "policyId"        : $('#policyId').val(),
                "factoryId"       : $('#factoryId').val(),
                "policyName"      : $('#policyName').val(),
                "allowDayType"    : allowDayType,
                "scheduleUseYn"   : scheduleUseYn,
                "dayUseYn"        : dayUseYn,
                "dayBeginTime"    : dayBeginTime,
                "dayEndTime"      : dayEndTime,
                "nightUseYn"      : nightUseYn,
                "nightBeginTime"  : nightBeginTime,
                "nigtEndTime"     : nigtEndTime
            };
            
            $.ajax({
                url: '/qgScheduleApi/saveSchedule',
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
                            searchSchedulePolicyList();
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
    var factoryId = $('#factoryId').val();
    var scheduleName = $('#policyName').val();
	var dayStartHour = $('#dayStartHour').val();
	var dayStartMin = $('#dayStartMin').val();
	var dayEndHour = $('#dayEndHour').val();
	var dayEndMin = $('#dayEndMin').val();
	var nightStartHour = $('#nightStartHour').val();
	var nightStartMin = $('#nightStartMin').val();
	var nightEndHour = $('#nightEndHour').val();
	var nightEndMin = $('#nightEndMin').val();
	if(dayStartHour > dayEndHour){
		swal({
            title: '주간스케줄 시간을 올바르게 선택해주세요.',
            type: 'warning',
            showCancelButton: false,
            confirmButtonColor: '#3085d6',
        }).then(function () {
            $('#fDay #dayStartHour').focus();
        });
        return false;
	}else if(dayStartHour === dayEndHour){
		if(dayStartMin > dayEndMin){
			swal({
	            title: '주간스케줄 시간을 올바르게 선택해주세요.',
	            type: 'warning',
	            showCancelButton: false,
	            confirmButtonColor: '#3085d6',
	        }).then(function () {
	            $('#fDay #dayStartMin').focus();
	        });
	        return false;
		}else if(dayStartMin === dayEndMin) {
			swal({
	            title: '주간스케줄 시간을 올바르게 선택해주세요.',
	            type: 'warning',
	            showCancelButton: false,
	            confirmButtonColor: '#3085d6',
	        }).then(function () {
	            $('#fDay #dayStartMin').focus();
	        });
	        return false;
		}
	}
//	if(nightStartHour > nightEndHour){
//		swal({
//            title: '야간스케줄 시간을 올바르게 선택해주세요.',
//            type: 'warning',
//            showCancelButton: false,
//            confirmButtonColor: '#3085d6',
//        }).then(function () {
//            $('#fDay #nightStartHour').focus();
//        });
//        return false;
//	}else if(nightStartHour === nightEndHour){
//		if(nightStartMin > nightEndMin){
//			swal({
//	            title: '야간스케줄 시간을 올바르게 선택해주세요.',
//	            type: 'warning',
//	            showCancelButton: false,
//	            confirmButtonColor: '#3085d6',
//	        }).then(function () {
//	            $('#fDay #nightStartMin').focus();
//	        });
//	        return false;
//		}
//	}
	if(nightStartHour === nightEndHour){
		if(nightStartMin === nightEndMin){
			swal({
	            title: '야간스케줄 시간을 올바르게 선택해주세요.',
	            type: 'warning',
	            showCancelButton: false,
	            confirmButtonColor: '#3085d6',
	        }).then(function () {
	            $('#fDay #nightStartMin').focus();
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
function deleteSchedule(scheduleId) {
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
                url: '/qgScheduleApi/deleteSchedule',
                data: {
                    scheduleId: scheduleId
                },
                success: function (data) {
                    if(data == 'success'){
                        swal({
                            type: 'success',
                            title: '삭제 되었습니다.',
                            showConfirmButton: true
                        }).then(function () {
                            searchSchedulePolicyList();
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
    searchSchedulePolicyList();
    
    $('#scheduleModal').on('show.bs.modal', function(event) {
//        $('#scheduleUseYn').bootstrapToggle();
    });
    
    $('#scheduleModal').on('hidden.bs.modal', function(event) {
        $('#scheduleModalBody2').remove();
        var myClone = originalModal.clone();
        $('#scheduleModalBody1').append(myClone);
    });
});