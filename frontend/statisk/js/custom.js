$(document).ready(function () {

    // Legger inn årstall i edit profile
    var select = $("#years");
    var year = (new Date).getFullYear() - 14;

    for (i = year; i >= 1900; i--){
        select.append($('<option>h</option>').val(i).html(i))
    }
    // END årstall adder

    // Åpne og lukke edit/add new card
    $('.card-wrap-add').on('click', function(){
        $('.edit-card-wrap').fadeIn();
        $('#darkOverlay').fadeIn();
    });
    $('#saveCard').on('click', function(){
        closeCardPopUp();
    });
    $('#darkOverlay').on('click', function(){
        closeCardPopUp();
    });
    $(document).keydown(function(e){
        if(e.keyCode == 27) closeCardPopUp();
    });

    function closeCardPopUp(){
        $('.edit-card-wrap').fadeOut();
        $('#darkOverlay').fadeOut();
    }
    // END Åpne og lukke edit/add new card

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