var checked = [];
var searchFilter = {};
var search_buttons = [{
    view:"button",
    label:"Apply",
	type:'icon',
	icon : 'filter',
	autowidth : true,
	css:'brix_button customiconbutton alignbutton',
	id:'applybtn',
	align: "right",
	click: function(data){
        getSearchResult(searchFilter);
	}
},{
    view:"button",
	label:"Clear Filter",
	type:'icon',
	icon : 'remove',
	autowidth : true,
	css:'brix_button customiconbutton alignbutton',
	id:'clearfilterbtn',
	align: "right",
	click: function(){
        var children = $$("formElements").getChildViews();
        if(Object.keys(searchFilter).length > 0){
            var keys = Object.keys(searchFilter);
            for(var j=0;j<children.length;j++){
                for(var i=0;i<keys.length;i++){
                    if(children[j].config.name === keys[i]){
                        $$(children[i]).setValue("");
                        $$(children[i]).getPopup().getList().unselect();
                        $$(children[i]).refresh();
                    }
                }
            }
            //$$("formElements").refresh();
        }
        searchFilter = {};
	}
},{width: 10}];

function getSearchResult(searchFilter){
$.LoadingOverlay("show", spinner);
   var result;
   webix.ajax().post('getMeasurementBySearch',JSON.stringify(searchFilter)).then(function(data){
        result = [];
        result = data.json().data;
            $$("resultdatatable").show();
            $$("searchHead").show();
            $$("resultdatatable").clearAll();
            $$("resultdatatable").parse(result);
            $$("resultdatatable").refresh();
            $('#count').html('<b>Total Result Count: '+data.json().total+'</b>');
        $.LoadingOverlay("hide", spinner);
    });
}

function getNodeList(nodeLabel, attribute, id){
   var result;
   webix.ajax().post('getNodeList',{'label':nodeLabel,'attribute':attribute}).then(function(data){
        result = data.json().data;
        for(var i=0; i<result.length; i++){
            result[i]['value'] = result[i][attribute];
            result[i]['id'] = result[i][attribute];
        }
        $$(id).define('options',result);
        $$(id).refresh();
   });
}

webix.ui({
	type: "clean",
	//container: "d2dplus",
	rows:[{
		view: 'accordion',
        //css: 'brix_accordion_1',
        /*css: 'brixAccordionStyle brixAccordionBackgroundStyle brix_accordion_Header_Style brix_accordion_Header_Style1',*/
        css: 'search_Accordion',
        multi: true,
        autoheight: true,
        collapsed: false,
        id: "searchCriteria_accordianId",
        rows: [{
			id:'id_initial_accordion',
			//header:"<div class='brix_accordion_label_line'><span class='brix_accordion_label_style'>Filter</span></div>",
			//header : "<div class=''><span style='float:left;' class='fa fa-search'><span >Search</span></div>",
			header: false,
			//headerHeight : 30,
			onClick : {
				"openFilters" : function() {
					return false;
				}
			},
			body : {
			    rows:[{
                    view: 'template',
                    id: 'search_heading',
                    template: "<b>Search Parameters</b><br><br>",
                    borderless: true,
                    height:30
			    },{
                    view : 'form',
                    autoheight : true,
                    padding : 0,
                    id : 'id_brix_filter_form',
                    elements : [{
                        id:"formElements",
                        view:"gridlayout",
                        gridColumns:3,
                        cellHeight:40,
                        height: 160,
                        rows:[]
                    },{
                        cols:[{width:20},{
                            template:"<b>Search Result</b><span style='float:right' id='count'></span><br><br>",
                            height:30 ,
                            id: 'searchHead',
                            hidden: true,
                            borderless: true
                        },{width:20}]
                    },{
                        cols:[{width:40},{
                            view: "datatable",
                            id: "resultdatatable",
                            // container: "datatable_container",
                            borderless:true,
                            select:false,
                            height: 320,
                            resizeColumn: { headerOnly:true },
                            fixedRowHeight : false,
                            scroll: true,
                            visibleBatch:1,
                            hidden: true,
                            tooltip: true,
                            rowHeight:60,
                            css:'brix_datatable_2 datatableCustomCheckBox ',
                            columns:[{
                                id:"checkbox",
                                header: "Select",
                                template:"{common.checkbox()}",
                                batch: 1,
                                width : 75
                            },{
                                id: "measurementKey",
                                header: "measurementKey",
                                sort: "int",
                                batch: 2,
                                width:100,
                          },{
                                id: "measurementName",
                                header: [{text: "Measurement Name"},{content:"textFilter"}],
                                sort: "int",
                                batch: 1,
                                width:300,
                            },{
                                id: "dataName",
                                editor : "text",
                                header: [{text: "Data Name"}, {content:"textFilter"}],
                                batch: 1,
                                width:200,
                                sort:"string"
                            },{
                                id: "test",
                                editor : "text",
                                header: [{text: "Test Name"}, {content:"textFilter"}],
                                batch: 1,
                                width:250,
                                sort:"string"
                            },{
                                id: "taskName",
                                editor : "text",
                                header: [{text: "Test Plan"}, {content:"textFilter"}],
                                batch: 1,
                                width:200,
                                sort:"string"
                            },{
                                id: "tagNo",
                                header: [{text: "Tag Number"}, {content:"textFilter"}],
                                sort: "int",
                                batch: 1,
                                width:200,
                            },{
                                id: "projectCode",
                                header: [{text: "Project"}, {content:"textFilter"}],
                                sort: "int",
                                batch: 1,
                                width:200,
                            },{
                                id: "measurementDataURL",
                                editor : "text",
                                header: [{text: "Measurement Data URL"}, {content:"textFilter"}],
    //                            template:"{common.url()}",
                                template: "<a href='#measurementDataURL#' target='_blank' class='url'>#measurementDataURL#</a>",
                                batch: 1 ,
                                width:300,
                                sort:"string",
                            }],
                            on:{
                                'onCheck' : function(row, column, state){
                                    console.log(checked);
                                }
                            }
                        },{width:40}]
                    },{
                        cols:[{},{
                            view:"button",
                            label:"Next",
                            type:'icon',
                            icon : 'arrow-right',
                            autowidth : true,
                            css:'brix_button customiconbutton',
                            id:'nextbtn',
                            click: function(){
                                $$("resultdatatable").data.each(function(obj){
                                        if(obj.checkbox)
                                            checked.push(obj);
                                });
                                if(checked.length>0){
                                    webix.ajax().post('getMeasurementData',JSON.stringify(checked)).then(function(data){
                                        result = data.json().result;
                                        if(result === 'true' || result === true)
                                            window.open("/analysis/search/measurement","_self")
                                    });
                                }else{
                                    notifyBox('warning', 'Check at least one record.', 'top right');
                                }
                            }
                        },{}]
                    }]
			    }]
			}
		}]
	},]
});

webix.ready(function(){
    var data;
    webix.ajax().get('getSearchParameters').then(function(data){
        data = data.json();
        $$("formElements").define('gridColumns',data.noofcolumns);
    $$("formElements").define('gridRows',data.noofrows);
    for (var i = 0; i < data.parameter.length; i++) {
        $$("formElements").addView({
            view:data.parameter[i]['displayType'],
            label:data.parameter[i]['displayName'] + "&nbsp" +":",
            name:data.parameter[i]['searchKey'],
            id:data.parameter[i]['name'],
            labelWidth:150,
            dx:data.parameter[i].dx,
            css:'brix_text brix_combo',
            on:{
                'onAfterRender': webix.once(function(){
//                    if(data.parameter[i]['nodeType']==='Input Tag Number'){
//                        var id = data.parameter[i]['name'];
//                        webix.ajax().get('getInputTagNosList').then(function(data){
//                            result = data.json().data;
//                            var options=[];
//                            for(var i=0; i<result.length; i++){
//                                options.push({'id':result[i].inputTagNo, 'value':result[i].inputTagNo});
//                            }
//                            $$(id).define('options',options);
//                            $$(id).refresh();
//                       });
//                    }else{
                        getNodeList(data.parameter[i]['nodeLabel'], data.parameter[i]['name'],this);
//                    }
                }),
                'onChange': function(newV, oldV){
                    if(newV != "" && newV != undefined){
                        if(this.config.view === 'multiselect'){
                            let arr = [];
                            if(this.config.value.indexOf(",")>0){
                                arr=this.config.value.split(",");
                            }else{
                                arr.push(this.config.value);
                            }
                            searchFilter[this.config.name] = arr;
                        }else{
                            searchFilter[this.config.name] = this.config.value;
                        }
                    }
                }
            }
        })
    }
    $$("formElements").addView({
        cols:search_buttons,
        dx: data.noofcolumns
    })
    });
});