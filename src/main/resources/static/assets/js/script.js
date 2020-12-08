
// $('h1').slideUp(1000).slideDown(2000);

// $('#click').click(function () {

//     $('.child-div').css({
//         color: 'red'
//     });

// });


$(window).on("load", function () {
   $('#status').delay(400).fadeOut();
   $('#preloader').delay(400).fadeOut('Slow');
	alertFunc();
});

$(function () {

   $('#mobile-nav-open-btn').click(function () {
      $('#mobile-nav').css({
         "height": "100%"
      });
      $('#mobile-nav-open-btn').css({
         "display": "none"
      });
      $('#mobile-nav-close-btn').css({
         "display": "block"
      });
   });

   $('#mobile-nav-close-btn').click(function () {
      $('#mobile-nav').css({
         "height": "0%"
      });
      $('#mobile-nav-close-btn').css({
         "display": "none"
      });
      $('#mobile-nav-open-btn').css({
         "display": "block"
      });
   });

});
