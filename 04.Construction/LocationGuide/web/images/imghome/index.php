//**********************************************************************************************************************
/**
* COMPANY: Zipline Interactive
* EMAIL: info@gozipline.com
* PHONE: 509-321-2849
* DESCRIPTION: This document contains programming required for the website templates.  Requires the jQuery library.
*/
//***********************************************************************************************************************

//CREATE HTML 5 STYLES
document.createElement('header');
document.createElement('nav');
document.createElement('section');
document.createElement('article');
document.createElement('aside');
document.createElement('footer');

//***********************************************************************************************************************
//ON DOCUMENT READY FUNCTIONS USING JQUERY
//***********************************************************************************************************************
$(function() {

	//SHOW WINDOW ABOUT SPOKANE WEB DESIGN ON CLICK
	$('#spokane').click(function() {
		$('#popup').fadeIn("slow");
	});
	
	$('#close_popup').click(function() {
		$('#popup').fadeOut("slow");
	});

});

//**********************************************************************************************************************
/**
* DOCUMENT: /core/plugins/events/front/projects.back.js
* DEVELOPED BY: Ryan Stemkoski
* COMPANY: Zipline Interactive
* EMAIL: ryan@gozipline.com
* PHONE: 509-321-2849
* DATE: 4/28/2010
* DESCRIPTION: This document has all of the javascript functions required for the events  plugin.
*/
//***********************************************************************************************************************

var next 	= 0;
var prev 	= 0;
var max 	= 0;

function show_image(index) {

	// Hide the open image
	$('.portfolio_image').hide();
	
	// Show the new image
	$('.portfolio_image').eq(index).show();
	
	// Set the next and prev

	if(index == max) {
		next = 0;
		prev = parseInt(index) - 1;
	}
	
	else if(index == 0) {
		next = 1;
		prev = max;
	}
	
	else {
		next = parseInt(index) + 1;
		prev = parseInt(index) - 1;
	}
	
	// Update next and prev buttons
	$('#next').attr('rel',next);
	$('#prev').attr('rel',prev);

}

//***********************************************************************************************************************
//ON DOCUMENT READY FUNCTIONS
//***********************************************************************************************************************
$(function() {
	
	// Set variables
	next 	= 1;
	prev 	= $('.portfolio_image').length;
	max 	= $('.portfolio_image').length - 1;
	
	// Show the first image on load
	$('.portfolio_image').eq(0).show();
	
	$('#next,#prev').click(function(){
		show_image($(this).attr('rel'));
	});
	
	$('.portfolio_item').hover(function(){
		var type = $('.portfolio_item').attr('rel');
		var index = $('.portfolio_item').index(this);
		if(type == "regular") {
			$('.portfolio_small_hover').eq(index).fadeIn(300);
		}
	},function(){
		var type = $('.portfolio_item').attr('rel');
		var index = $('.portfolio_item').index(this);
		if(type == "regular") {
			$('.portfolio_small_hover').eq(index).fadeOut(300);
		}
	});
	
});
