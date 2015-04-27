/* CONFIGURATION AREA */
/*
	browsers:
		IE - Microsoft Internet Explorer
		FF - FireFox
		Safari - Safari browser
		Opera - Opera browser
		all - includes IE, FF, Opera and Safari

	parentId:
		any existing Id attribute
	tagName:
		any valid tag name
	tagPosition:
		all - all inner elements
		children - first level children only
	className:
		any valid class name
*/
 var $j = jQuery.noConflict();
var browsers = 'all';
var config = {
	hovers: new Array(
		{
			parentId:'drop-down',
			tagName:'li',
			tagPosition:'children',
			className:'hover'
		}
	)
};

/* CODE AREA */

/* hovers */

var initHovers = function()
{
	for (var index in config.hovers){
		var conf = config.hovers[index];
		var topLevelElement = document.getElementById(conf.parentId);
		if (topLevelElement){
			var subElements = topLevelElement.getElementsByTagName(conf.tagName);
			for (var i = 0; i < subElements.length; i++){
				if (conf.tagPosition == 'all' || (conf.tagPosition == 'children' && subElements[i].parentNode.id == conf.parentId))
				{
					subElements[i].configIndex = index;
					subElements[i].onmouseover = function(){
						this.className += ' ' + config.hovers[this.configIndex].className;
					};
					subElements[i].onmouseout = function(){
						this.className = this.className.replace(config.hovers[this.configIndex].className,'');
					};
				}
			}
		}
	}
}

/* common */

var currentState = {};
 var $j = jQuery.noConflict();

$j(document).ready(function (){
		
	$j('ul#drop-down li div.car').hide();
	
	
	$j('ul#drop-down li').mouseover(function(e){
		$j(this).addClass('active2');		
	//	return false;
	});
	$j('ul#drop-down li').mouseout(function(e){
		$j(this).removeClass('active2');		
	//	return false;
	});
	$j('ul#drop-down li h2 a').click(function(e){		
			
			if($j('ul#drop-down li.active2 div.car').css('display')=='none')
			{
				$j('ul#drop-down li div.car').each(function(){
					$j(this).hide(500);			
					$j(this).removeClass("active");		
				});		
				$j('ul#drop-down li').removeClass("active");
				$j('ul#drop-down li.active2 div.car').show(500);
				$j('ul#drop-down li.active2').addClass('active');
			}
			else{
				$j('ul#drop-down li.active2 div.car').hide(500);	
				$j('ul#drop-down li.active2 div.car').removeClass("active");	
			}
			
		return false;
	});		

	
});
