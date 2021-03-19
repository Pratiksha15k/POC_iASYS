webix.ui({
    type: 'clean',
    rows:[{
        id: 'headingRow',
        cols:[{width:10},{
            view: 'template',
            id: 'tagnos_heading',
            template: "<b>Tag Number</b><br><br>",
            borderless: true,
            height:30
        }]
    },{
        cols:[{width:30},{
            rows:[{height:20},{
                view: 'form',
                width: 1300,
                borderless: true,
                elements:[{
                    view: 'multiselect',
                    id: 'outputTagNo',
                    label: "Output Tag No",
                    css: 'brix_combo',
                    options:{
                        data: outputTagArr,
                        selectAll:true
                    },
                    value: outputTagNoArr,
                    labelWidth: 150,
                    width: 600,
                },{
                    view:"button",
                    label:"Apply",
                    type:'icon',
                    icon : 'filter',
                    autowidth : true,
                    css:'brix_button customiconbutton alignbutton',
                    id:'applybtn',
                    align: "right",
                    click: function(data){
                        webix.ajax().post('getNodeList',{'label':'DataName','attribute':'tagNo'}).then(function(data){
                            result = data.json().data;
                            $$("resultdatatable").show();
                            $$("resultdatatable").clearAll();
                            $$("resultdatatable").parse(result);
                            $$("resultdatatable").refresh();
                            $$("nextbtn").show();
                        });
                    }
                }]
            }]
        },{width:30}]
    },{
        cols:[{ width:30 },{
            view: "datatable",
            id: "resultdatatable",
            // container: "datatable_container",
            borderless:true,
            select:false,
            height: 320,
            resizeColumn: { headerOnly:true },
            fixedRowHeight : false,
            scrollY: true,
            scrollX:false,
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
                id:"inputTagNo",
                header: "Input Tag Number",
                fillspace: true,
                batch: 1
            },{
                id:"nodeId",
                header: "Node ID",
                fillspace: true,
                batch: 2
            }]
        },{width:30}]
    },{height:30},{
        cols:[{width:600},{
            view:"button",
            label:"Next",
            type:'icon',
            icon : 'arrow-circle-right',
            autowidth : true,
            hidden:true,
            css:'brix_button customiconbutton',
            id:'nextbtn',
            click: function(){
                $.LoadingOverlay("show", spinner);
                var gridData =[];
                $$("resultdatatable").data.each(function(obj){
                    if(obj.checkbox)
                        gridData.push(obj);
                });
                if(gridData.length>0){
                    webix.ajax().post('mergeInputTagNumber',JSON.stringify(gridData)).then(function(data){
                        result = data.json();
                        if(result)
                            window.open("/analysis/search/tagNoSearch","_self")
                        $.LoadingOverlay("hide", spinner);
                    });
                }else{
                    notifyBox('warning', 'Check at least one record.', 'top right');
                }
            }
        },{width:50}]
    }]
})