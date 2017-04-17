$(document).ready(function() {
	var body = document.body,
    html = document.documentElement;
	var height = $( document ).height();
	
	$("li").click(function() {
		var height = Math.max( $( document ).height(), $('#searchForm').height());
		console.log($('#searchForm').height());
		console.log($( '#nutrition' ).height());	
		$('#leftborder').height(height);
		$('#rightborder').height(height);
	});

	
	console.log($( document ).height());
	console.log($( '#basic' ).height());
	console.log($( '#nutrition' ).height());
	console.log($( '#dietary' ).height());
	$('#leftborder').height(height);
	$('#rightborder').height(height);	
});
 