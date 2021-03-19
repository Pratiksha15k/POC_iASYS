//var datatable = webix.ui({
//    view: "datatable",
//    id: "resultdatatable",
//   // container: "datatable_container",
//    borderless:true,
//	select:true,
//	height: 400,
//	width: 500,
//	resizeColumn: { headerOnly:true },
//	fixedRowHeight : false,
//	scrollX: true,
//	gravity:0.4,
//	visibleBatch:1,
//	css:'brix_datatable_2 customusergrid',
//	columns:[
//	    {  id:"checkbox", header: "", template:"{common.checkbox()}", batch: 1, width : 75},
//		{ id: "dataname", header: [{text: "Data Name JP"}, {content:"textFilter"}], sort: "int", batch: 1, width : 75},
//		{ id : "tagnumber", editor : "text", header: [{text: "Tag Number"}, {content:"textFilter"}], batch: 1,width : 100,sort:"string"},
//		{ id : "project", editor : "text", header: [{text: "Project"}, {content:"textFilter"}], batch: 1 ,width : 170,sort:"string"}],
//    data:[{
//        "dataname": "Data Name JP", "tagnumber" : "A001-0002-0011-2300", "project": "2YN"
//    },{
//        "dataname": "Data Name JP 1", "tagnumber" : "A001-0002-0011-2400", "project": "2M"
//    },{
//        "dataname": "Data Name JP 2", "tagnumber" : "A001-0002-0011-2500", "project": "2NB"
//    },{
//        "dataname": "Data Name JP 3", "tagnumber" : "A001-0002-0011-2600", "project": "2YN"
//    }]
//});