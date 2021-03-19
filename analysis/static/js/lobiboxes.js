function notifyBox(option, message, winposition){
	Lobibox.notify(option,{
	    msg: message,
	    position: winposition,
	});
}

function alertBox(option, message){
	Lobibox.alert(option, {
		msg : message,
		buttons: {
			ok: {
					'class': 'lobibox-btn lobibox-btn-default',
					closeOnClick: true
				},
			},
		callback : function(lobibox, type){
			if(type === 'ok'){
				location.reload();
			}
		}
	});
}