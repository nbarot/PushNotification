        $(function () {
            $.fn.resizeWindow = function () {
                var viewPortHeight = $(window).height();

                var headerHeight = $(".patram-header").height();
                var footerHeight = 52;
                var contentfooterHeight=$(".content-footer").height();
//                alert("headerHeight: " + headerHeight + ", footerHeight: " + footerHeight);
                var contentHeight = viewPortHeight - headerHeight - contentfooterHeight - footerHeight-61-70;
                var containerHeight = viewPortHeight ;
                //alert(contentHeight);
                // Set all pages with class="page-content" to be at least contentHeight
                $(".full-window-panel").css({
                    'min-height': contentHeight + 'px',
                });
                $(".patram-content").css({
                    'min-height': containerHeight + 'px',
                });
                return this;
            };
            $(window).resize(function(){
                $(window).resizeWindow();
            });
            $(window).resizeWindow();
            
           $('#updateFlight').on('click',function(){
			   var url='';
			   var data=$('#flightNum').val()+$('#schDep').val();
				console.log('data::'+data);
			   $.ajax({
				  type: "POST",
				  url: url,
				  data: data,
				  success: success,
				  dataType: dataType
				});
			   
		   });
        });