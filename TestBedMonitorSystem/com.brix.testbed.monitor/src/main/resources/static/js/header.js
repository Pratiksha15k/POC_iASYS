var specialCharacters = ['~','!','@','#','$','%','^','*','(',')','+','=',':',';',';','{','}','[',']','|','?','\\','/','&'];
/**
 * @purpose This function is used get width of browser and convert it into percentage
 * @param percentage
 * @author caashish
 */
function setWidthInPercentage(percentage){
	var width = window.innerWidth;
	return width = (width / 100)* percentage;
}
/**
 * @purpose This function is used get height of browser and convert it into percentage
 * @param percentage
 * @author caashish
 */
function setHeightInPercentage(percentage){
	var height = window.innerHeight;
	return height = (height / 100)* percentage;
}

function closeWindows(winId, formId, height, width){
	if($$(winId) != undefined){
		$$(winId).define('position',"center");
		$$(winId).define('height', setHeightInPercentage(height));
		$$(winId).define('width', setWidthInPercentage(width));
		$$(formId).clear();
		$$(formId).clearValidation();
		$$(winId).close();
	}
}