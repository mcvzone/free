/**
 * common grid util
 */
function grid(columns, datas, isPaging, event, target){
	var myGrid = new AXGrid();
    myGrid.setConfig({
        targetID : target===undefined?"id-grid":target,
        theme : "AXGrid",
        height: "auto",
        //viewMode: "grid", // ["grid","icon","mobile"]
        // 브라우저 사이즈에 따른 changeGridView 설정
        mediaQuery: {
            mx:{min:0, max:600}, dx:{min:600}
        },
        colGroup : columns,

        body : {
            onclick: event
        },
        page:{
            paging:isPaging
        }
    });
    var gridData = {
	   list: datas,
	   page:{
	       pageNo: 1,
	       pageSize: 20,
	       pageCount: 2,
	       listCount: 29,
	       status:{formatter: null},
	       onchange: function(pageNo){
	           dialog.push(Object.toJSON(this));
	            console.log(this, pageNo);
	        }
	    }
	};
	myGrid.setData(gridData);
	
	myGrid.setList({
        ajaxUrl : "loadGrid.php",
        ajaxPars: {
            "param1": "액시스제이",
            "param2": "AXU4J"
        },
        onLoad  : function(){
            //trace(this);
            //myGrid.setFocus(this.list.length - 1);
        }
    });
}

/**
 * common grid ajax util
 */
function gridAjax(config){
	var myGrid = new AXGrid();
    myGrid.setConfig({
        targetID : config.target===undefined?"id-grid":config.target,
        theme : "AXGrid",
        height: "auto",
        //viewMode: "grid", // ["grid","icon","mobile"]
        // 브라우저 사이즈에 따른 changeGridView 설정
        mediaQuery: {
            mx:{min:0, max:600}, dx:{min:600}
        },
        colGroup : config.cols,

        body : {
            onclick: config.event
        },
        page:{
            paging:config.paging
        }
    });

    myGrid.setList({
	    	ajaxUrl : "/ajax",
	    	dataType: "json",
	        ajaxPars: {
	            "mission":"CM0000007"
	        },
	        onLoad  : function(){
	        	
	        }
	});
}

