/* HTML5 Placeholder jQuery Plugin - v2.1.0
 * Copyright (c)2015 Mathias Bynens
 * 2015-01-27
 */
!function(a){"function"==typeof define&&define.amd?define(["jquery"],a):a(jQuery)}(function(a){function b(b){var c={},d=/^jQuery\d+$/;return a.each(b.attributes,function(a,b){b.specified&&!d.test(b.name)&&(c[b.name]=b.value)}),c}function c(b,c){var d=this,f=a(d);if(d.value==f.attr("placeholder")&&f.hasClass(m.customClass))if(f.data("placeholder-password")){if(f=f.hide().nextAll('input[type="password"]:first').show().attr("id",f.removeAttr("id").data("placeholder-id")),b===!0)return f[0].value=c;f.focus()}else d.value="",f.removeClass(m.customClass),d==e()&&d.select()}function d(){var d,e=this,f=a(e),g=this.id;if(""===e.value){if("password"===e.type){if(!f.data("placeholder-textinput")){try{d=f.clone().attr({type:"text"})}catch(h){d=a("<input>").attr(a.extend(b(this),{type:"text"}))}d.removeAttr("name").data({"placeholder-password":f,"placeholder-id":g}).bind("focus.placeholder",c),f.data({"placeholder-textinput":d,"placeholder-id":g}).before(d)}f=f.removeAttr("id").hide().prevAll('input[type="text"]:first').attr("id",g).show()}f.addClass(m.customClass),f[0].value=f.attr("placeholder")}else f.removeClass(m.customClass)}function e(){try{return document.activeElement}catch(a){}}var f,g,h="[object OperaMini]"==Object.prototype.toString.call(window.operamini),i="placeholder"in document.createElement("input")&&!h,j="placeholder"in document.createElement("textarea")&&!h,k=a.valHooks,l=a.propHooks;if(i&&j)g=a.fn.placeholder=function(){return this},g.input=g.textarea=!0;else{var m={};g=a.fn.placeholder=function(b){var e={customClass:"placeholder"};m=a.extend({},e,b);var f=this;return f.filter((i?"textarea":":input")+"[placeholder]").not("."+m.customClass).bind({"focus.placeholder":c,"blur.placeholder":d}).data("placeholder-enabled",!0).trigger("blur.placeholder"),f},g.input=i,g.textarea=j,f={get:function(b){var c=a(b),d=c.data("placeholder-password");return d?d[0].value:c.data("placeholder-enabled")&&c.hasClass(m.customClass)?"":b.value},set:function(b,f){var g=a(b),h=g.data("placeholder-password");return h?h[0].value=f:g.data("placeholder-enabled")?(""===f?(b.value=f,b!=e()&&d.call(b)):g.hasClass(m.customClass)?c.call(b,!0,f)||(b.value=f):b.value=f,g):b.value=f}},i||(k.input=f,l.value=f),j||(k.textarea=f,l.value=f),a(function(){a(document).delegate("form","submit.placeholder",function(){var b=a("."+m.customClass,this).each(c);setTimeout(function(){b.each(d)},10)})}),a(window).bind("beforeunload.placeholder",function(){a("."+m.customClass).each(function(){this.value=""})})}});
//# sourceMappingURL=jquery.placeholder.min.js.map




(function($,window,document,Math,undefined) {

var div = $( "<div>" )[0],
	rsrc = /url\(["']?(.*?)["']?\)/,
	watched = [],
	positions = {
		top: 0,
		left: 0,
		bottom: 1,
		right: 1,
		center: .5
	};

// feature detection
if ( "backgroundSize" in div.style && !$.debugBGS ) { return; }

$.cssHooks.backgroundSize = {
	set: function( elem, value ) {
		var firstTime = !$.data( elem, "bgsImg" ),
			pos,
			$wrapper, $img;

		$.data( elem, "bgsValue", value );

		if ( firstTime ) {
			// add this element to the 'watched' list so that it's updated on resize
			watched.push( elem );

			$.refreshBackgroundDimensions( elem, true );

			// create wrapper and img
			$wrapper = $( "<div>" ).css({
				position: "absolute",
				zIndex: -1,
				top: 0,
				right: 0,
				left: 0,
				bottom: 0,
				overflow: "hidden"
			});

			$img = $( "<img>" ).css({
				position: "absolute"
			}).appendTo( $wrapper ),

			$wrapper.prependTo( elem );

			$.data( elem, "bgsImg", $img[0] );

			pos = ( 
				// Firefox, Chrome (for debug)
				$.css( elem, "backgroundPosition" ) ||
				// IE8
				$.css( elem, "backgroundPositionX" ) + " " + $.css( elem, "backgroundPositionY" )
			).split(" ");

			// Only compatible with 1 or 2 percentage or keyword values,
			// Not yet compatible with length values and 4 values.
			$.data( elem, "bgsPos", [ 
				positions[ pos[0] ] || parseFloat( pos[0] ) / 100, 
				positions[ pos[1] ] || parseFloat( pos[1] ) / 100
			]);

			// This is the part where we mess with the existing DOM
			// to make sure that the background image is correctly zIndexed
			$.css( elem, "zIndex" ) == "auto" && ( elem.style.zIndex = 0 );
			$.css( elem, "position" ) == "static" && ( elem.style.position = "relative" );

			$.refreshBackgroundImage( elem );

		} else {
			$.refreshBackground( elem );
		}
	},

	get: function( elem ) {
		return $.data( elem, "bgsValue" ) || "";
	}
};

// The background should refresh automatically when changing the background-image
$.cssHooks.backgroundImage = {
	set: function( elem, value ) {
		// if the element has a backgroundSize, refresh its background
		return $.data( elem, "bgsImg") ?
			$.refreshBackgroundImage( elem, value ) :
			// otherwise set the background-image normally
			value;
	}
};

$.refreshBackgroundDimensions = function( elem, noBgRefresh ) {
	var $elem = $(elem),
		currDim = {
			width: $elem.innerWidth(),
			height: $elem.innerHeight()
		},
		prevDim = $.data( elem, "bgsDim" ),
		changed = !prevDim ||
			currDim.width != prevDim.width ||
			currDim.height != prevDim.height;

	$.data( elem, "bgsDim", currDim );

	if ( changed && !noBgRefresh ) {
		$.refreshBackground( elem );
	}
};

$.refreshBackgroundImage = function( elem, value ) {
	var img = $.data( elem, "bgsImg" ),
		currSrc = ( rsrc.exec( value || $.css( elem, "backgroundImage" ) ) || [] )[1],
		prevSrc = img && img.src,
		changed = currSrc != prevSrc,
		imgWidth, imgHeight;

	if ( changed ) {
		img.style.height = img.style.width = "auto";

		img.onload = function() {
			var dim = {
				width: img.width,
				height: img.height
			};

			// ignore onload on the proxy image
			if ( dim.width == 1 && dim.height == 1 ) { return; }

			$.data( elem, "bgsImgDim", dim );
			$.data( elem, "bgsConstrain", false );

			$.refreshBackground( elem );

			img.style.visibility = "visible";

			img.onload = null;
		};

		img.style.visibility = "hidden";
		img.src = currSrc;

		if ( img.readyState || img.complete ) {
			img.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
			img.src = currSrc;
		}

		elem.style.backgroundImage = "none";
	}
};

$.refreshBackground = function( elem ) {
	var value = $.data( elem, "bgsValue" ),
		elemDim = $.data( elem, "bgsDim" ),
		imgDim = $.data( elem, "bgsImgDim" ),
		$img = $( $.data( elem, "bgsImg" ) ),
		pos = $.data( elem, "bgsPos" ),
		prevConstrain = $.data( elem, "bgsConstrain" ),
		currConstrain,
		elemRatio = elemDim.width / elemDim.height,
		imgRatio = imgDim.width / imgDim.height,
		delta;

	if ( value == "contain" ) {
		if ( imgRatio > elemRatio ) {
			$.data( elem, "bgsConstrain", ( currConstrain = "width" ) );

			delta = Math.floor( ( elemDim.height - elemDim.width / imgRatio ) * pos[1] );

			$img.css({
				top: delta
			});

			// when switchin from height to with constraint,
			// make sure to release contraint on height and reset left
			if ( currConstrain != prevConstrain ) {
				$img.css({
					width: "100%",
					height: "auto",
					left: 0
				});
			}

		} else {
			$.data( elem, "bgsConstrain", ( currConstrain = "height" ) );

			delta = Math.floor( ( elemDim.width - elemDim.height * imgRatio ) * pos[0] );

			$img.css({
				left: delta
			});

			if ( currConstrain != prevConstrain ) {
				$img.css({
					height: "100%",
					width: "auto",
					top: 0
				});
			}
		}

	} else if ( value == "cover" ) {
		if ( imgRatio > elemRatio ) {
			$.data( elem, "bgsConstrain", ( currConstrain = "height" ) );

			delta = Math.floor( ( elemDim.height * imgRatio - elemDim.width ) * pos[0] );

			$img.css({
				left: -delta
			});

			if ( currConstrain != prevConstrain ) {
				$img.css({
					height:"100%",
					width: "auto",
					top: 0
				});
			}

		} else {
			$.data( elem, "bgsConstrain", ( currConstrain = "width" ) );

			delta = Math.floor( ( elemDim.width / imgRatio - elemDim.height ) * pos[1] );

			$img.css({
				top: -delta
			});

			if ( currConstrain != prevConstrain ) {
				$img.css({
					width: "100%",
					height: "auto",
					left: 0
				});
			}
		}
	}
}

// Built-in throttledresize
var $event = $.event,
	$special,
	dummy = {_:0},
	frame = 0,
	wasResized, animRunning;

$special = $event.special.throttledresize = {
	setup: function() {
		$( this ).on( "resize", $special.handler );
	},
	teardown: function() {
		$( this ).off( "resize", $special.handler );
	},
	handler: function( event, execAsap ) {
		// Save the context
		var context = this,
			args = arguments;

		wasResized = true;

        if ( !animRunning ) {
        	$(dummy).animate(dummy, { duration: Infinity, step: function() {
	        	frame++;

	        	if ( frame > $special.threshold && wasResized || execAsap ) {
	        		// set correct event type
        			event.type = "throttledresize";
	        		$event.dispatch.apply( context, args );
	        		wasResized = false;
	        		frame = 0;
	        	}
	        	if ( frame > 9 ) {
	        		$(dummy).stop();
	        		animRunning = false;
	        		frame = 0;
	        	}
	        }});
	        animRunning = true;
        }
	},
	threshold: 1
};

// All backgrounds should refresh automatically when the window is resized
$(window).on("throttledresize", function() {
	$(watched).each(function() {
		$.refreshBackgroundDimensions( this );
	});
});

})(jQuery,window,document,Math);