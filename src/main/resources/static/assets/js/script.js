
// $('h1').slideUp(1000).slideDown(2000);

// $('#click').click(function () {

//     $('.child-div').css({
//         color: 'red'
//     });

// });


$(window).on("load", function () {
   $('#status').delay(400).fadeOut();
   $('#preloader').delay(400).fadeOut('Slow');

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


$(window).on('load', function () {

   $('#home-heading').addClass("animate__animated animate__fadeInDown animate__delay-500ms animate__slow");

   $('#home-slogan').addClass("animate__animated animate__fadeInLeft animate__delay-600ms animate__slow");

   $('#home-input').addClass("animate__animated animate__zoomIn animate__delay-900ms animate__slow");

   $('#home-btn').addClass("animate__animated animate__zoomIn animate__delay-900ms animate__slow");

   $('.cards').addClass("animate__animated animate__fadeInLeft animate__delay-500ms animate__slow");

   $('#content-box').addClass("animate__animated animate__zoomIn animate__delay-500ms animate__slow");

});