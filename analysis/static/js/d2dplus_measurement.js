var outputTagNoArr = [];

webix.ui({
    rows:[{height:30},{
        cols:[{width:30},{
            template:"<b>Measurement</b><br><br>",
            height:30 ,
            id: 'searchHead',
            borderless: true
        },{}]
    },{height:30},{
        cols:[{},{
            view: "datatable",
            id: "measurementGrid",
            // container: "datatable_container",
            borderless:true,
            height: 450,
            width: 1300,
            resizeColumn: { headerOnly:true },
            fixedRowHeight : false,
            scrollY: true,
            scrollX: false,
            visibleBatch:1,
            autoConfig: true,
            css:'brix_datatable_2 datatableCustomCheckBox',
            columns:[
                { id:"remove", header: "Remove", template:"{common.trashIcon()}", batch: 1, width : 75, css:'removeIcon'},
                { id:"tagNo", header: [{text: "Tag No"}, {content:"textFilter"}], sort: "int", batch: 1, fillspace: 1.2},
                { id:"measurementKey", header: "Tag No", sort: "int", batch: 2, fillspace: 2},
                { id:"measurementName", header: [{text: "Measurement"}, {content:"textFilter"}], sort: "int", batch: 1, fillspace: 1.5},],
                //{ id:"measurementDataURL", header: [{text: "Measurement Data URL"}, {content:"textFilter"}],template: "<a href='#measurementDataURL#' target='_blank' class='url'>#measurementDataURL#</a>", sort: "int", batch: 1, fillspace: 3},
            on:{
                'onAfterRender' : webix.once(function(){
                    $.LoadingOverlay("show", spinner);
                    outputTagNoObject = outputTagNoObject.replace(/^\[(.+)\]$/,'$1');
                    outputTagNoObject = outputTagNoObject.replace(/^"|"$/g, '');
                    outputTagNoObject = outputTagNoObject.replace(/'/g, '');
                    console.log(outputTagNoObject);
                    if(outputTagNoObject != "[]"){
                        if(outputTagNoObject.indexOf(",")>1)
                            outputTagNoArr = outputTagNoObject.split(",")
                        else
                            outputTagNoArr.push(outputTagNoObject);
                        console.log(outputTagNoArr);
                        let columns = this.config.columns;
                        for(var i=0;i<outputTagNoArr.length;i++){
                            columns.push({
                                id: outputTagNoArr[i],
                                width: 150,
                                batch: 1,
                                header: outputTagNoArr[i],
                                template:"{common.checkbox()}"
                            })
                        }
                        this.refreshColumns();
                        jsonObject = jsonObject.replace(/'/g, '"');
                        jsonObject = JSON.parse(jsonObject);
                        this.clearAll();
                        this.parse(jsonObject);
                        this.refresh();
                    }
                    $.LoadingOverlay("hide", spinner);
                })
            }
        },{}]
    },{height:10},{
        cols:[{width:550},{
            view:"button",
            label:"Previous",
            type:'icon',
            icon : 'arrow-circle-left',
            autowidth : true,
            css:'brix_button customiconbutton',
            id:'previous',
            click: function(){
                window.open("/analysis/search/tagNoSearch","_self")
            }
        },{
            view:"button",
            label:"Submit",
            type:'icon',
            icon : 'arrow-circle-right',
            autowidth : true,
            css:'brix_button customiconbutton',
            id:'submit',
            click: function(){
                $.LoadingOverlay("show", spinner);
                var outputTagNameArr = [];
                outputTagNameObject = outputTagNameObject.replace(/^\[(.+)\]$/,'$1');
                outputTagNameObject = outputTagNameObject.replace(/'/g, "");
                console.log(outputTagNameObject)
                //outputTagNameObject = outputTagNameObject.replaceAll("'","");
                if(outputTagNameObject != "[]"){
                    if(outputTagNameObject.indexOf(",")>1)
                        outputTagNameArr = outputTagNameObject.split(",")
                    else
                        outputTagNameArr.push(outputTagNameObject);
                    var gridData = $$('measurementGrid').serialize();
                    for(var i=0; i<gridData.length; i++){
                        let outputTagNames = [];
                        let outputTagNos = [];
                        for(var j=0; j<outputTagNoArr.length; j++){
                            if(gridData[i][outputTagNoArr[j]] === 1){
                                outputTagNos.push(outputTagNoArr[j]);
                                outputTagNames.push(outputTagNameArr[j])
                            }
                        }
                        gridData[i]['outputTagNo'] = outputTagNos;
                        gridData[i]['outputTagDataName'] = outputTagNames;
                    }
                    webix.ajax().post('mergeTagNumber',JSON.stringify(gridData)).then(function(data){
                        result = data.json();
                        $.LoadingOverlay("hide", spinner);
                        if(result.errorCode == 1){
                            let fileName = result.data.outPutFileName;
                            notifyBox('success', fileName+'<br>'+result.message, 'top right');
                        }else{
                            notifyBox('warning', result.message, 'top right');
                        }
                    });
                }
            }
        },{width:50}]
    }]
});