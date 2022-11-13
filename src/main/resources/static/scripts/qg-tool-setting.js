var toolSettingListGrid = new ax5.ui.grid();

ax5.ui.grid.formatter["useCnt"] = function() {
	var value = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["presetCnt"] = function() {
	var value = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

function initGrid() {
	toolSettingListGrid.setConfig({
		target: $('[data-ax5grid="toolSettingList-grid"]'),
		showLineNumber: true,
		showRowSelector: false,
		multipleSelect: false,
		lineNumberColumnWidth: 40,
		rowSelectorColumnWidth: 28,
		sortable: false,
		multiSort: false,
		virtualScrollY: false,
		scroller: { size: 20 },
		header: {
			align: "center",
			columnHeight: 32
		},
		body: {
			mergeCells: ["areaName", "machineName"],
			align: "center",
			columnHeight: 40,
			onDataChanged: function() {
			},
			trStyleClass: function(){
                return "grid-cell-white";
            }
		},
        page: {
            navigationItemCount: 9,
            height: 30,
            display: true,
            firstIcon: '<i class="fa fa-step-backward" aria-hidden="true"></i>',
            prevIcon: '<i class="fa fa-caret-left" aria-hidden="true"></i>',
            nextIcon: '<i class="fa fa-caret-right" aria-hidden="true"></i>',
            lastIcon: '<i class="fa fa-step-forward" aria-hidden="true"></i>',
            onChange: function () {
                pageSetData(this.page.selectPage);
            }
        },
		columns: [
			{ key: "areaName", label: "라인명", width: 180, align: "left" },
			{ key: "machineName", label: "설비명", width: 180, align: "left" },
			{ key: "toolName", label: "공구번호", width: 90, align: "left", sortable: true },
			{ key: "viewName", label: "공구명", width: 130, align: "left", editor: {type: "text"} },	
			{ key: "useCnt", label: "사용횟수", width: 80, align: "right", formatter: "useCnt" },
			{ key: "presetCnt", label: "제한횟수", width: 80, align: "right", formatter: "presetCnt" },
			{ key: "toolSpec", label: "사양", width: 130, align: "left", editor: {type: "text"} },
			{ key: "useYn", label: "사용여부", width: 80, align: "center", editor: {
	                type: "checkbox", 
	                config: {
	                            height: 17, 
	                            trueValue: "y", 
	                            falseValue: "n"
	                        }
	                } 
			},
			{ key: "sortSeq", label: "정렬순서", width: 80, align: "right" },
		]
	});
}

function updateToolSetting(){
	var factoryId = $('#selFactory option:selected').val();
	if(!factoryId) {
		if (alertTrigger) {
			swal("공장을 선택해주세요.", "", "warning");
		}
	}else{
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
                var toolJson = new Object();
                
                toolJson = JSON.stringify(toolSettingListGrid.getList("modified"));

                var param = 
                {
                        "toolJson"     : toolJson,
                };

                $.ajax({
                        url: '/qgToolSettingApi/updateToolSetting',
                        type: 'POST',
                        data: param,
                        success: function (data) {
                            swal({
                                type: 'success',
                                title: '변경되었습니다.',
                                showConfirmButton: true
                            }).then(function () {
								searchToolSettingList();
//                                window.location.reload();
                            })
                        },
						error : function(request, status, error ) {   // 오류가 발생했을 때 호출
							console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
						},
                });
            }
        })
	}
}

var toolSettingList = [];
function searchToolSettingList() {
	
	var factoryId = $('#selFactory option:selected').val();
	var areaId = $('#selArea option:selected').val();
	var machineId = $('#selMachine option:selected').val();
	
	if (!factoryId) {
		if (alertTrigger) {
			swal("공장을 선택해주세요.", "", "warning");
		}
	}else {
		$.ajax({
			url: '/qgToolSettingApi/getToolSettingList',
			type: 'POST',
			data: {
				factoryId: factoryId,
				areaId: areaId,
				machineId: machineId,
			},
			beforeSend: function() {
				swal({
                title: '데이터를 가져오는 중입니다..',
                html: '<div class="progress m-t-xs full progress-striped active" style="width: 90%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="90" role="progressbar" class=" progress-bar progress-bar-success"><div class="progress-bar" style="width: 100%"></div></div>',
                showConfirmButton: false
            });
			},
			success: function(data) {
                toolSettingList = data.toolSettingViewList;
                pageSetData();
//				toolSettingListGrid.setData(data.toolSettingViewList);
				swal.close();
			},
		});
	}
}

var alertPlaceholder = document.getElementById('liveAlertPlaceholder');
var alertTrigger = document.getElementById('liveAlertBtn');

function alert(message, type) {
	var wrapper = document.createElement('div')
	wrapper.innerHTML = '<div class="alert alert-' + type + ' border-2 d-flex align-items-center" role="alert"><div class="bg-danger me-3 icon-item"><span class="fas fa-times-circle text-white fs-3"></span></div><p class="mb-0 flex-1">' + message + '</p><button class="btn-close" type="button" data-bs-dismiss="alert" aria-label="Close"></button></div>'
	alertPlaceholder.append(wrapper)
}

function searchAreaList() {
	var factoryId = $('#selFactory option:selected').val();
    $.ajax({
        url: '/qgMonitoringApi/getAreaList',
        type: 'POST',
        data: {
            factoryId: factoryId
        },
        dataType:'json',
        success: function (data) {
            var areaOption;
            $.each(data, function (index, areaList) {
                var areaId;
                if(isNull(areaList.areaId)) areaId = '';
                else areaId = areaList.areaId;
                areaOption += '<option value="'+ areaId +'">'+ areaList.areaName  +'</option>';
            });
            $('#selArea').html(areaOption);
            searchMachineList();
        }
    });
}

function searchMachineList() {
	var factoryId = $('#selFactory option:selected').val();
    var areaId = $('#selArea option:selected').val();
    $.ajax({
        url: '/qgMonitoringApi/getMachineList',
        type: 'POST',
        data: {
            factoryId: factoryId,
            areaId: areaId
        },
        success: function (data) {
            var machineOption;
            $.each(data, function (index, machineList) {
                var machineId;
                if(isNull(machineList.machineId)) machineId = '';
                else machineId = machineList.machineId;
                machineOption += '<option value="'+ machineId +'">'+ machineList.machineName  +'</option>';
            });
            $('#selMachine').html(machineOption);
        }
    });
}

function isNull(obj){
    return (typeof obj != "undefined" && obj != null && obj != "") ? false :  true;
}

function pageSetData(_pageNo){
    if(isNull(_pageNo)){
        var startNo = 0;
        var endNo = 100;
        
        var list = toolSettingList.slice(startNo, endNo);
        
        var totalElementsCnt = toolSettingList.length;
        var totalPageCnt = Math.floor(toolSettingList.length / 100);
        if((toolSettingList.length % 100) > 0){
            totalPageCnt = totalPageCnt + 1;
        }
        toolSettingListGrid.setData({
            list: list,
            page: {
                currentPage: 0 || 0,
                pageSize: 50,
                totalElements: totalElementsCnt,
                totalPages: totalPageCnt
            }
        });
    }else{
        var startNo = (_pageNo * 100);
        var endNo = (_pageNo + 1) * 100;
        
        var list = toolSettingList.slice(startNo, endNo);
        
        var totalElementsCnt = toolSettingList.length;
        var totalPageCnt = Math.floor(toolSettingList.length / 100);
        if((toolSettingList.length % 100) > 0){
            totalPageCnt = totalPageCnt + 1;
        }
        toolSettingListGrid.setData({
            list: list,
            page: {
                currentPage: _pageNo || 0,
                pageSize: 50,
                totalElements: totalElementsCnt,
                totalPages: totalPageCnt
            }
        });
    }
}

$(document).ready(function() {
	initGrid();
	searchAreaList();
	searchMachineList();
	
	$('[data-grid-control]').click(function () {
        toolSettingListGrid.exportExcel("toolSetting.xls");
    });
});