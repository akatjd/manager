var contents;
var defaultGridHeight = 600;
var originalModal = $('#issueModal .modal-dialog').clone();
var selectIdx;
var machineListGrid = new ax5.ui.grid();
var userListGrid = new ax5.ui.grid();

ax5.ui.grid.formatter["date"] = function () {
    var value = "";
    if(isNull(this.value)){
        value = "--:--:--";
    }else{
        value = secDateFormat(new Date(this.value));
    }
    return value;
};

ax5.ui.grid.formatter["inspectionActive"] = function () {
    var value = '';
    switch (this.value) {   
    case 'y':
        value = '사용';
        break;
    case 'ny':
        value = '미사용(설정유)';
        break;
    case 'n':
        value = '미사용';
            break;
    default:
        value = this.value;
        break;
    }

    return value
};
ax5.ui.grid.formatter["machineStatusType"] = function () {
    var value = '';
    switch (this.value) {
    case 'run':
        value = 'RUN';
        break;
    case 'hold':
        value = 'HOLD';
        break;
    case 'stop':
        value = 'STOP';
            break;
    case 'dis':
        value = 'DIS';
            break;
    case 'off':
        value = 'OFF';
            break;
    default:
        value = this.value;
        break;
    }
    return value
};

ax5.ui.grid.formatter["warnStatus"] = function () {
    var warnMsg = this.item.warnMsg;
    if(!isNull(warnMsg)){
        warnMsg = "&nbsp;"+this.item.warnMsg
    }else{
        warnMsg = "";
    }
    
    var value = '';
    
    switch (this.value) {
    case 'info' :
        value = '미검사';
        break;
    case 'first' :
        value = '초도생산';
        break;
    case 'test' :
        value = '테스트';
        break;
    case 'badsync' :
        value = '싱크불량';
        break;
    case 'standard' : 
        value = '표준생성';
        break;
    case 'warmup' :
        value = '웜업';
        break;
    case 'good'  : 
        value = '정상';
        break;
    case 'warn'  : 
        value = 'LV2경고';
        break;
    case 'error' : 
        value = 'LV3경고';
        break;
    case 'stop' : 
        value = '위험정지';
        break;
    default : 
        value = this.value;
        break;
    }
    
    // 민성 추가 2021-08-25 경고현황 Tooltip
    var tooltip = value + warnMsg;
    var htmlValue = "<p title='"+tooltip+"'>";
    htmlValue += tooltip;
    htmlValue += "</p>";
    
    return htmlValue;
};

ax5.ui.grid.formatter["sustainTime"] = function () {
    if(this.item.machineStatusType != 'run')
        return sustainTime(this.value);
};

ax5.ui.grid.formatter["efficiency"] = function () {
    var day = this.item.totalProductionRate;
    var hour = this.item.realTimeEfficiency;
    if(isNull(day)){
        day = '-';
    }else{
        day = day + '%';
    }
    
    if(isNull(hour)){
        hour = '-';
    }else{
        hour = hour + '%';
    }
    value = day + " / " +  hour; 
    return value;
};

ax5.ui.grid.formatter["confirmBtn"] = function () {
    var value = "";
    var text = "시작";
    var issueId = this.item.issueId;
    if(!isNull(issueId)){
        text = "확인";
    }
    value = '<a class="btn btn-info btn-xs" onclick="searchIssueModal('+this.dindex+')">' + text + '</a>';
    return value;
};

ax5.ui.grid.formatter["statusChangeBtn"] = function () {
    var value = "";
    value = '<a class="btn btn-warning btn-xs" onclick="changeType('+this.value+')">상태변경</a>';
    return value;
};

ax5.ui.grid.formatter["timelineBtn"] = function () {
    var value = "";
    var machineId = this.item.machineId;
    var itemType = this.item.itemType;
    var lv2Chk;
    var lv3Chk;
    if(itemType == 'warn'){
        lv2Chk = true;
        lv3Chk = false;
    }else if(itemType == 'error'){
        lv2Chk = false;
        lv3Chk = true;
    }
    value = '<a class="btn btn-dark me-1 mb-1 btn-sm" onclick="openTimeline('+machineId+')" style="height:25px;margin-top:-1px">새창</a>';
    
    return value;
};

// 민성 추가 2021-08-25 기계명 Tooltip formatter
ax5.ui.grid.formatter["machineName"] = function () {
    var tooltip = this.item.machineName;
    var value = "<p title='"+tooltip+"'>";
    value += tooltip;
    value += "</p>";
    
    return value;
};

// 민성 추가 2021-08-24 표준파형 Tooltip formatter
ax5.ui.grid.formatter["standardWave"] = function () {
    var tooltip = this.item.productName + "(" + this.item.standardRegDate + ")";
	var value = "<p title='"+tooltip+"'>";
	value += tooltip;
    value += "</p>";

    return value;
};

// 민성 추가 검사/섹션개수 관련
ax5.ui.grid.formatter["inspectionStr"] = function () {
    var value = this.item.inspectionStr;
    
    return value;
};

// 민성 추가 2021-08-24 정지설정 칼럼 formatter
ax5.ui.grid.formatter["stopUseBtn"] = function () {
    var rowIdx = this.dindex;
    var stopUse = this.value;
    var value = "";
    var machineId = this.item.machineId;
    
    if(stopUse == 'y'){
		  value = '<button class="btn btn-success me-1 mb-1 btn-sm" id="stopUseBtn'+machineId+'" style="height:25px;margin-top:-1px"><p>&nbsp;&nbsp;사용&nbsp;&nbsp;</p></button>';
    }else{
        value = '<button class="btn btn-danger me-1 mb-1 btn-sm" id="stopUseBtn'+machineId+'" style="height:25px;margin-top:-1px"><p>미사용</p></button>';
    }
    
    return value;
};

// 민성 추가 2021-08-14 검사설정 칼럼 formatter
ax5.ui.grid.formatter["inspectionUseBtn"] = function () {
    var inspectionUse = this.value;
    var value = "";
    var machineId = this.item.machineId;
    
    if(inspectionUse == 'y'){
        value = '<button class="btn btn-success me-1 mb-1 btn-sm" id="inspectionUseBtn'+machineId+'" style="height:25px;margin-top:-1px">&nbsp;&nbsp;사용&nbsp;&nbsp;</button>';
    }else{
        value = '<button class="btn btn-danger me-1 mb-1 btn-sm" id="inspectionUseBtn'+machineId+'" style="height:25px;margin-top:-1px">미사용</button>';
    }
    
    return value;
};

ax5.ui.grid.formatter["comma"] = function () {
    var value = this.value;
    value = value.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    return value;
};

// 민성 추가 2021-08-24 정지설정 칼럼 function
function funcStopUse(rowIdx, machineId, stopUse){
	var commonJs_confirm =  jQuery.i18n.prop('commonJs_confirm');
    var commonJs_cancel =  jQuery.i18n.prop('commonJs_cancel');
    var stopUse;
    var titleTxt;
    
    if (stopUse === 'y') {
        stopUse = 'n';
        titleTxt = machineJs_monitoringStopStopMsg;
    } else {
        stopUse = 'y';
        titleTxt = machineJs_monitoringStopUseMsg;
    }
        
    swal({
    title: titleTxt,
    type: 'info',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: commonJs_confirm,
    cancelButtonText: commonJs_cancel
    },
    function (isConfirm) {
            if (isConfirm) {
                machineListGrid.setValue(rowIdx, "stopUse", stopUse);
                $.ajax({
                    url: '/qgMonitoringApi/updateStopUse',
                    type: 'POST',
                    data: {
                        machineId: machineId,
                        stopUse: stopUse
                    },
                    success: function (data) {
                        swal.closeModal();
                        swal(commonJs_success, commonJs_changeSuccess, "success");
                    },
                    error: function (request, status, error) {
                        console.log("code:" + request.status + "\n" + "message:"
                            + request.responseText + "\n" + "error:" + error);
                        swal("ERROR", commonJs_failedChange, "error");
                    }
                });
            } else {
            }
        }
    );
}

// 민성 추가 2021-08-24 검사설정
function funcInspectionUse(rowIdx, machineId, inspectionUse){
    var commonJs_confirm =  jQuery.i18n.prop('commonJs_confirm');
    var commonJs_cancel =  jQuery.i18n.prop('commonJs_cancel');
    var inspectionUse;
    var titleTxt;
    
    if (inspectionUse === 'y') {
        inspectionUse = 'n';
        titleTxt = machineJs_monitoringStopStopMsg;
    } else {
        inspectionUse = 'y';
        titleTxt = machineJs_monitoringStopUseMsg;
    }
    
    swal({
    title: titleTxt,
    type: 'info',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: commonJs_confirm,
    cancelButtonText: commonJs_cancel
    },
    function (isConfirm) {
            if (isConfirm) {
                machineListGrid.setValue(rowIdx, "inspectionActive", inspectionUse);
                $.ajax({
                    url: '/qgMonitoringApi/updateInspectionUse',
                    type: 'POST',
                    data: {
                        machineId: machineId,
                        inspectionUse: inspectionUse
                    },
                    success: function (data) {
                        swal.closeModal();
                        swal(commonJs_success, commonJs_changeSuccess, "success");
                    },
                    error: function (request, status, error) {
                        console.log("code:" + request.status + "\n" + "message:"
                            + request.responseText + "\n" + "error:" + error);
                        swal("ERROR", commonJs_failedChange, "error");
                    }
                });
            } else {
            }
        }
    );
}

function openTimeline(machineId){
    var newWindow = window.open("about:blank");
    newWindow.location.href = "/machine?machineId="+machineId;
}

function searchIssueModal(idx){
    selectIdx = idx;
    $('#issueModal').modal();
}
function secDateFormat(date) {
    var datetime = date.getFullYear() + "-"
        + ((date.getMonth() < 9) ? "0" : "")
        + (date.getMonth() + 1) + "-"
        + ((date.getDate() < 10) ? "0" : "")
        + date.getDate() + " "
        + ((date.getHours() < 10) ? "0" : "")
        + date.getHours() + ":"
        + ((date.getMinutes() < 10) ? "0" : "")
        + date.getMinutes() + ":"
        + ((date.getSeconds() < 10) ? "0" : "")
        + date.getSeconds();
    return datetime;
}

function minDateFormat(date) {
    var datetime = date.getFullYear() + "-"
        + ((date.getMonth() < 9) ? "0" : "")
        + (date.getMonth() + 1) + "-"
        + ((date.getDate() < 10) ? "0" : "")
        + date.getDate() + " "
        + ((date.getHours() < 10) ? "0" : "")
        + date.getHours() + ":"
        + ((date.getMinutes() < 10) ? "0" : "")
        + date.getMinutes();
    return datetime;
}

function initGrid(){
    machineListGrid.setConfig({
        target: $('[data-ax5grid="machineList-grid"]'),
//        frozenColumnIndex: 2,
//        frozenRowIndex: 0,
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
            columnHeight: 32
        },
        body: {
            align: "center",
            columnHeight: 32,
            onClick: function () {
                this.self.select(this.dindex);
            },
			trStyleClass: function(){
                return "grid-cell-white";
            }
        },
        columns: [
            {key: "machineName",            label: "기계명",         width: 150,  formatter: "machineName", styleClass: function () {
                                                                                                            var value;
                                                                                                            switch (this.item.machineStatusType) {
                                                                                                            case 'run':
                                                                                                                value = 'grid-cell-green';
                                                                                                                break;
                                                                                                            case 'hold':
                                                                                                                value = 'grid-cell-blue';
                                                                                                                break;
                                                                                                            case 'stop':
                                                                                                                value = 'grid-cell-red';
                                                                                                                    break;
                                                                                                            case 'dis':
                                                                                                                value = 'grid-cell-yellow';
                                                                                                                    break;
                                                                                                            case 'off':
                                                                                                                value = 'grid-cell-red';
                                                                                                                    break;
                                                                                                            default:
                                                                                                                value = '';
                                                                                                                break;
                                                                                                            }
                                                                                                            return value;
                                                                                                        }},
            {key: "lastProductEventTime",   label: "경과시간",        width: 90,   formatter: "sustainTime"},
            {key: "warnStatus",             label: "경고현황",        width: 90,   formatter: "warnStatus", styleClass: function () {
                                                                                                            var value;
                                                                                                            switch (this.item.warnStatus) {
                                                                                                            case 'info' :
                                                                                                                value = 'grid-cell-blue';
                                                                                                                break;
                                                                                                            case 'first' :
                                                                                                                value = 'grid-cell-blue';
                                                                                                                break;
                                                                                                            case 'test' :
                                                                                                                value = 'grid-cell-blue';
                                                                                                                break;
                                                                                                            case 'badsync' :
                                                                                                                value = 'grid-cell-blue';
                                                                                                                break;
                                                                                                            case 'standard' : 
                                                                                                                value = 'grid-cell-blue';
                                                                                                                break;
                                                                                                            case 'warmup' :
                                                                                                                value = 'grid-cell-blue';
                                                                                                                break;
                                                                                                            case 'good'  : 
                                                                                                                value = 'grid-cell-green';
                                                                                                                break;
                                                                                                            case 'warn'  : 
                                                                                                                value = 'grid-cell-yellow';
                                                                                                                break;
                                                                                                            case 'error' : 
                                                                                                                value = 'grid-cell-red';
                                                                                                                break;
                                                                                                            case 'stop' : 
                                                                                                                value = 'grid-cell-pink';
                                                                                                                break;
                                                                                                            default :
                                                                                                                value = '';
                                                                                                            }
                                                                                                            return value;
                                                                                                        }},
            {key: "machineStatusType",      label: "상태",           width: 60,   formatter: "machineStatusType", hidden: true},
            // 민성 추가 2021-08-24
            {key: "inspectionActive",       label: "검사설정",        width: 90,   formatter: "inspectionUseBtn"},
            {key: "stopUse",       label: "정지설정",        width: 90,  formatter: "stopUseBtn"},
            {key: "totalProductionRate",    label: "효율(목표/시간)",    width: 130,  formatter: "efficiency", sortable: false},
            /* ## 표준파형, 검사/섹션개수, N코드 칼럼 작업 라인 ## */
            {key: "productName",   label: "가공프로그램명", align: "left", width: 225,   formatter: "standardWave"},
            {key: "inspectionStr",   label: "검사/섹션개수",        width: 120,   formatter: "inspectionStr"},
            {
                key: undefined, label: "누적현황", columns: [
                    {key: "goodCnt",            label: "정상",            width: 90, align: "right", formatter: "comma"},
                    {key: "infoCnt",            label: "미검사",           width: 90, align: "right", formatter: "comma"},
                    {key: "badsyncCnt",         label: "싱크불량",          width: 90, align: "right", formatter: "comma"},
                    {key: "errorCnt",           label: "경고",             width: 90, align: "right", formatter: "comma"},
                    {key: "stopCnt",            label: "위험정지",          width: 90, align: "right", formatter: "comma"},
            ]
            },
        ]
    });
}

function initUserListGrid(){
    userListGrid.setConfig({
        target: $('[data-ax5grid="userList-grid"]'),
//        frozenColumnIndex: 2,
//        frozenRowIndex: 0,
        showLineNumber: true,
        showRowSelector: true,
        lineNumberColumnWidth: 40,
        rowSelectorColumnWidth: 28,
        scroller: {size: 20},
        header: {
            align: "center",
            columnHeight: 32
        },
        columns: [
            {key: "department",   label: "부서",    width: 120, align: "center"},
            {key: "userName",     label: "이름",    width: 150, align: "center"}
        ],
        body: {
            columnHeight: 26,
            onClick: function () {
                this.self.select(this.dindex);
            }
        }
    });
}
var $req;
function searchMachineList(type){
    var factoryId = $('#selFactory option:selected').val();
    var areaId = $('#selArea option:selected').val();
    var machineStatusType = $('#selMachineStatusType option:selected').val();
    var warnStatus = $('#selWarnStatus option:selected').val();

    /* 민성 추가 */
    var accumulSearchTime = $('#selAccumulSearchTime').val();
    
    var machineName = $('#machineName').val();
    if(!isNull($req)){
        $req.abort();
        $req = null;
    }
    $req = $.ajax({
        url: '/qgMonitoringApi/getMonitoringMachineList',
        type: 'POST',
        beforeSend: function () {
            if(type != 'auto'){
                swal({
                    title: '데이터를 가져오는 중입니다....',
                    html: '<div class="progress m-t-xs full progress-striped active" style="width: 90%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="90" role="progressbar" class=" progress-bar progress-bar-success"><div class="progress-bar" style="width: 100%"></div></div>',
                    showConfirmButton: false,
                });
            }
        },
        data: {
            factoryId:         factoryId,
            areaId:            areaId,
            machineStatusType: machineStatusType,
            warnStatus:        warnStatus,
            machineName:       machineName,
            /* 민성 추가 */
            accumulSearchTime: accumulSearchTime
        },
        dataType: 'json',
        success: function (data) {
            machineListGrid.setData(data.machines);
            if(type != 'auto'){
                swal.closeModal();
            }
        }
    });
    
}

function searchAreaList(){
    var factoryId = $('#selFactory option:selected').val();
    $.ajax({
        url: '/qgMonitoringApi/getAreaList',
        type: 'POST',
        data: {
            factoryId: factoryId
        },
        success: function (data) {
            var areaOption;
            $.each(data, function (index, areaList) {
                var areaId;
                if(isNull(areaList.areaId)) areaId = '';
                else areaId = areaList.areaId;
                areaOption += '<option value="'+ areaId +'">'+ areaList.areaName  +'</option>';
            });
            $('#selArea').html(areaOption);
        }
    });
}

function lpad(s, padLength, padString) { 
    while (s.length < padLength)
        s = padString + s;
    return s;
}

function isNull(obj){
    return (typeof obj != "undefined" && obj != null && obj != "") ? false :  true;
}

function sustainTime(lastProductEventTime) {
	let tDate = null;
	if(lastProductEventTime !== null){
		tDate = new Date(lastProductEventTime);
	}
	if(isNull(tDate)){
        realtime = '- : -';
        return realtime;
    }
	var resultTime = Date.now() - tDate;
    //시간
    var hour = Math.floor(resultTime / (1000 * 60 * 60));
    //분
    var minute = Math.floor(resultTime / (1000 * 60)) % 60;
    var second = Math.floor(resultTime / 1000 % 60 % 60);

    if (minute <= 9)
        minute = '0' + minute
    if (second <= 9)
        second = '0' + second
    if (hour == 0) {
        realtime = minute + ':' + second
    } else {
        realtime = hour + ':' + minute + ':' + second
    }
    return realtime;
}

// 민성 추가 표준파형 2021-08-13
function standardWave(productName) {
    var productName = productName;
    
    return productName;
}

// 민성 추가 검사/섹션개수 2021-08-17
function inspectionStr(inspectionStr) {
    var inspectionStr = inspectionStr;
    
    return inspectionStr;
}

var refInterval;
function refIntervalChange(){
    if ($("input:checkbox[id='reflechChk']").is(":checked")) {
        var refMin = $('#refMin').val();
        clearInterval(refInterval);
        if(refMin > 0){
            var refType = $('#refType').val();
            switch (refType) {
            case 'sec':
                refInterval = window.setInterval('searchMachineList("auto")', refMin * 1000); // 10 seconds
                break;
            case 'min':
                refInterval = window.setInterval('searchMachineList("auto")', refMin * 1000 * 60); // 10 seconds
                break;
            case 'hour':
                refInterval = window.setInterval('searchMachineList("auto")', refMin * 1000 * 60 * 60); // 10 seconds
                break;
            default:
                break;
            }
        }
    }else{
        clearInterval(refInterval);
    }
}

function changeRefType(){
    var refType = $('#refType').val();
    switch (refType) {
    case 'sec':
        $('#refType').val('min');
        $('#refType').text('분');
        break;
    case 'min':
        $('#refType').val('hour');
        $('#refType').text('시간');
        break;
    case 'hour':
        $('#refType').val('sec');
        $('#refType').text('초');
        break;
    default:
        $('#refType').val('min');
        $('#refType').text('분');
        break;
    }
    refIntervalChange();
}

function maxLengthCheck(object){
    if (object.value.length > object.maxLength){
        object.value = object.value.slice(0, object.maxLength);
    }
}

function gridHighLow(btnType){
    if(btnType == 'plus'){
        defaultGridHeight = defaultGridHeight + 200;
    }else{
        if(defaultGridHeight == 400){
            swal("더이상 줄일수 없습니다.", "", "warning");
            return;
        } 
        defaultGridHeight = defaultGridHeight - 200;
    }
    machineListGrid.setHeight(defaultGridHeight);
}
$(document).ready(function () {
    $("#machineName").keyup(function(e){if(e.keyCode == 13)  searchMachineList(); });

    initGrid();
    searchAreaList();
    searchMachineList("auto");
    refIntervalChange();
    
    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth();
    var date = today.getDate();
    var strDate = year + '-' + month + '-' + date;
        
	$('[data-grid-control]').click(function () {
        machineListGrid.exportExcel("monitoring.xls");
    });
});