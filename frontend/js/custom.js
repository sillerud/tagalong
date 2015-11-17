$(document).ready(function () {



    // Kjører hver gang vinduet endrer størrelse
    $(window).resize(function() {

    });


// Hamburgerikon animasjon
var menuOpen = false;
$('.burger-wrap').on("click", function(){
  if(menuOpen == false){
    $('.menu').fadeIn();
    $('.bar1').css({ transform: 'rotate(' + 45 + 'deg)' });
    $('.bar1').css('top', '10px');
    $('.bar2').css({ transform: 'rotate(' + -45 + 'deg)' });
    $('.bar2').css('top', '10px');
    $('.bar3').fadeOut();
    menuOpen = true;
  }else if(menuOpen == true){
    $('.menu').fadeOut();
    $('.bar1').css({ transform: 'rotate(' + 0 + 'deg)' });
    $('.bar1').css('top', '0px');
    $('.bar2').css({ transform: 'rotate(' + 0 + 'deg)' });
    $('.bar2').css('top', '20px');
    $('.bar3').fadeIn();
    menuOpen = false;
  }
});
// END hamburgerikon animasjon

}); // END DOM