var productSettingListGrid = new ax5.ui.grid();
var gCodeViewGrid = new ax5.ui.grid();
var gCodeListGrid = new ax5.ui.grid();


ax5.ui.grid.formatter["staticCycleTime"] = function() {
	var value = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["gCode"] = function () {
    var value = "";
    value = '<button class="btn btn-primary btn-sm" onclick="gCodeViewer(\'' + this.value + '\', \'' + this.item.machineId + '\')">'+ '보기' +'</button>';
    
    return value;
};

function initGrid() {
	productSettingListGrid.setConfig({
		target: $('[data-ax5grid="productSettingList-grid"]'),
		showLineNumber: true,
		showRowSelector: false,
		multipleSelect: false,
		lineNumberColumnWidth: 40,
		rowSelectorColumnWidth: 28,
		sortable: false,
		multiSort: false,
		scroller: { size: 20 },
		header: {
			selector: true,
			align: "center",
			columnHeight: 32
		},
		body: {
			mergeCells: ["areaName", "machineName"],
			align: "center",
			columnHeight: 40,
			onClick: function() {
			},
			onDBLClick: function() {
			},
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
			{ key: "productId", label: "생산품 No", width: 100, align: "left" },
			{ key: "productOid", label: "OID", width: 250, align: "left" },	
			{ key: "productName", label: "NC 프로그램(코멘트)", width: 370, align: "left", editor: {type: "text"} },
			{ key: "staticCycleTime", label: "통계 사이클", width: 100, align: "right", formatter: "staticCycleTime", editor: {type: "number"} },
			{ key: "activeYn", label: "활성화", width: 70, editor: {
                type: "checkbox", 
                config: {
                            height: 17, 
                            trueValue: "y", 
                            falseValue: "n"
                        }
                } 
			},
			{key: "productId", label: "NC Program", width: 100, formatter: "gCode"}
		],
	});
}

function updateProductSetting(){
	var factoryId = $('#selFactory option:selected').val();
	if(!factoryId) {
		if (alertTrigger) {
			swal("공장을 선택해주세요.", "", "warning");
		}
	}else{
        var chkData = productSettingListGrid.getList("modified");
        if(valChk(chkData)){
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
                    var productJson = new Object();
                    productJson = JSON.stringify(productSettingListGrid.getList("modified"));
                    var param = 
                    {
                            "productJson"     : productJson,
                    };
                    $.ajax({
                            url: '/qgProductSettingApi/updateProductSetting',
                            type: 'POST',
                            data: param,
                            success: function (data) {
                                swal({
                                    type: 'success',
                                    title: '변경되었습니다.',
                                    showConfirmButton: true
                                }).then(function () {
                                    searchProductSettingList();
                                })
                            },
                            error : function(request, status, error ) {   // 오류가 발생했을 때 호출
                                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                            },
                    });
                }
            })
        }else{
            swal({
                title: '변경사항을 확인해주세요.',
                type: 'warning',
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
            })
        }
	}
}

function valChk(chkData){
    if(isNull(chkData)){
        return false;
    }else{
        chkData.forEach(function(item, index, arr){
            if(isNull(item)){
                return false;
            }
            if(isNull(item.productOid)){
                return false;
            }
            if(isNull(item.staticCycleTime)){
                return false;
            }
            if(isNaN(item.staticCycleTime)){
                return false;
            }
        })
    }
    return true;
}

var productSettingList = [];
function searchProductSettingList() {
	
	var factoryId = $('#selFactory option:selected').val();
	var areaId = $('#selArea option:selected').val();
	var machineId = $('#selMachine option:selected').val();
	
	if (!factoryId) {
		if (alertTrigger) {
			swal("공장을 선택해주세요.", "", "warning");
		}
	}else {
		$.ajax({
			url: '/qgProductSettingApi/getProductSettingList',
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
                productSettingList = data.productSettingViewList;
                pageSetData();
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

let selMachineId = 0;
function gCodeViewer(productId, machineId){
	selMachineId = machineId;
    selProductId = productId;
    $('#gCodeViewModal').modal('show');
}

function searchProductPath(productId, machineId){
    $.ajax({
        url: '/qgProductSettingApi/getProductPath',
        type: 'POST',
        data: {
            productId: productId
        },
        success: function (data) {
            if (!Object.keys(data).length) {
                $("#channelSel").attr("disabled","disabled");
                swal("등록된 NC program이 없습니다.", "", "error");
                return;
            }
            var selInner;
            $.each(data, function (index, data) {
                selInner += "<option value='" + data.path + "'>" + data.channelName + "</option>";
            });
            $("#channelSel").html(selInner);
            
            var path = $('#channelSel option:selected').val();
            searchGcodeList(machineId, productId, path);
        }
    });
}

function searchGcodeList(machineId, productId, path){
    $.ajax({
        url: '/qgProductSettingApi/getGcodeList',
        type: 'POST',
        data: {
            machineId: machineId,
            productId: productId,
            path: path
        },
        success: function (data) {
            if (!Object.keys(data).length) {
                swal("데이터가 없습니다.", "", "error");
                return;
            }
            gCodeListGrid.setData(data);
            fileName = gCodeListGrid.select(0).list[0].oid;
            var end = fileName.lastIndexOf('/');
            fileName = fileName.substring((end+1), fileName.length);
            searchProductGcode(gCodeListGrid.select(0).list[0].productGcodeId);
        }
    });
}

function searchProductGcode(productGcodeId){
    $.ajax({
        url: '/qgProductSettingApi/getProductGcode',
        type: 'POST',
        data: {
            productGcodeId: productGcodeId
        },
        beforeSend: function () {
            swal({
                title: "데이터를 가져오고 있습니다..",
                html: '<div class="progress m-t-xs full progress-striped active" style="width: 90%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="90" role="progressbar" class=" progress-bar progress-bar-success"><div class="progress-bar" style="width: 100%"></div></div>',
                showConfirmButton: false,
            });
        },
        success: function (data) {
            var productGcode = data.productGcode.gcodeContent;
            var gCode = JSON.parse(productGcode);
            var gCodeJson = new Array();
            for(var i=0;i<gCode.length;i++){
                var obj = new Object();
                obj.gCodeContents = gCode[i];
                gCodeJson.push(obj);
            }
            gCodeViewGrid.setData(gCodeJson);
            swal.closeModal();
        },
        error: function (request, status, error) {
            swal.closeModal();
            console.log("code:" + request.status + "\n" + "message:"
                + request.responseText + "\n" + "error:" + error);
        }
    });
}

var fileName="";
function initGcodeListGrid(){
    gCodeListGrid.setConfig({
        target: $('[data-ax5grid="gCodeList-grid"]'),
        showLineNumber: true,
        showRowSelector: false,
        multipleSelect: false,
        lineNumberColumnWidth: 40,
        rowSelectorColumnWidth: 28,
        sortable: false,
        multiSort: false,
        scroller: {size: 20},
        header: {
            align: "center",
            display: true,
            columnHeight: 28
        },
        body: {
            align: "center",
            columnHeight: 28,
            onClick: function () {
                this.self.select(this.dindex);
                fileName = gCodeListGrid.select(this.dindex).list[this.dindex].oid;
                var end = fileName.lastIndexOf('/');
                fileName = fileName.substring((end+1), fileName.length);
                searchProductGcode(this.item.productGcodeId);
            }
        },
        columns: [
            {key: "productGcodeId",   label: "ID",   width: 60, align: "left", hidden:true},
            {key: "gcodeType",        label: "Type",   width: 60, align: "left"},
            {key: "oid",              label: "oid",    width: 230, align: "left"}
        ]
    });
}

function initGcodeViewGrid(){
    gCodeViewGrid.setConfig({
        target: $('[data-ax5grid="gCodeView-grid"]'),
        showLineNumber: true,
        showRowSelector: false,
        multipleSelect: true,
        lineNumberColumnWidth: 40,
        rowSelectorColumnWidth: 28,
        sortable: false,
        multiSort: false,
        scroller: {size: 20},
        header: {
            align: "center",
            display: false,
            columnHeight: 28
        },
        body: {
            align: "center",
            columnHeight: 28,
            onClick: function () {
                this.self.select(this.dindex);
            }
        },
        columns: [
            {key: "gCodeContents",   label: "NC Program",   width: 775, align: "left"}
        ]
    });
    
    $('[data-grid-control]').click(function () {
        gCodeViewGrid.exportExcel(fileName+".xls");
    });
}

function isNull(obj){
    return (typeof obj != "undefined" && obj != null && obj != "") ? false :  true;
}

function pageSetData(_pageNo){
    if(isNull(_pageNo)){
        var startNo = 0;
        var endNo = 100;
        
        var list = productSettingList.slice(startNo, endNo);
        
        var totalElementsCnt = productSettingList.length;
        var totalPageCnt = Math.floor(productSettingList.length / 100);
        if((productSettingList.length % 100) > 0){
            totalPageCnt = totalPageCnt + 1;
        }
        productSettingListGrid.setData({
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
        
        var list = productSettingList.slice(startNo, endNo);
        
        var totalElementsCnt = productSettingList.length;
        var totalPageCnt = Math.floor(productSettingList.length / 100);
        if((productSettingList.length % 100) > 0){
            totalPageCnt = totalPageCnt + 1;
        }
        productSettingListGrid.setData({
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
	
	$('#gCodeViewModal').on('shown.bs.modal', function (e) {
        initGcodeListGrid();
        initGcodeViewGrid();    
        searchProductPath(selProductId, selMachineId);
    });
    
    $('#gCodeViewModal').on('hidden.bs.modal', function (e) {
        initGcodeListGrid(); 
        initGcodeViewGrid();
        $("#channelSel").html("");
    });
	
	$('[data-grid-control]').click(function() {
		productSettingListGrid.exportExcel("productSetting.xls");
	});
});