$(document).ready(function () {

    // Legger inn årstall i edit profile
    var select = $("#years");
    var year = (new Date).getFullYear() - 14;

    for (i = year; i >= 1900; i--){
        select.append($('<option>h</option>').val(i).html(i))
    }
    // END årstall adder

    var openCloseOverlays = function(){

        // Åpne og lukke edit/add new card
        $('.card-wrap-add').on('click', function(){ // Åpne
            $('.edit-card-wrap').fadeIn();
            $('#darkOverlay').fadeIn();
        });
        $('#saveCard').on('click', function(){ // Lukke
            closeOverlay('.edit-card-wrap');
            closeOverlay('#darkOverlay');
        });
        $('#darkOverlay').on('click', function(){ // Lukke
            closeOverlay('.edit-card-wrap');
            closeOverlay('#darkOverlay');
        });
        $('#searchBtn').on('click', function(){ // Åpne
            $('.search-overlay-wrap').fadeIn();
        });
        $('#newSearchBtn').on('click', function(){ // Åpne
            $('.search-overlay-wrap').fadeIn();
        });
        $('.exit-search').on('click', function(){ // Lukke
            closeOverlay('.search-overlay-wrap');
        });
        var addNewOpen = false;
        $('#addNew').on('click', function(){

            if( !addNewOpen ){
                $('#newPostBtn').animate({'bottom': '60px', 'opacity': '1'}, 300);
                $('#newPageBtn').delay(100).animate({'bottom': '110px', 'opacity': '1'}, 300);
                $('#newEventBtn').delay(200).animate({'bottom': '160px', 'opacity': '1'}, 300);
                $('#newSearchBtn').delay(300).animate({'bottom': '210px', 'opacity': '1'}, 300);
                addNewOpen = true;
            }else{
                $('#newSearchBtn').animate({'bottom': '200px', 'opacity': '0'}, 300);
                $('#newEventBtn').delay(100).animate({'bottom': '150px', 'opacity': '0'}, 300);
                $('#newPageBtn').delay(200).animate({'bottom': '100px', 'opacity': '0'}, 300);
                $('#newPostBtn').delay(300).animate({'bottom': '50px', 'opacity': '0'}, 300);
                addNewOpen = false;
            }
        });

        $(document).keydown(function(e){
            if(e.keyCode == 27){
                closeOverlay('.edit-card-wrap');
                closeOverlay('#darkOverlay');
                closeOverlay('.search-overlay-wrap');
            }
        });

        function closeOverlay(thatClass){
            $(thatClass).fadeOut();
        };

    }(); // END open close overlays


    $('#datetimepicker2').datetimepicker({
        locale: 'nb'
    });

    // Kjører hver gang vinduet endrer størrelse
    $(window).resize(function() {

    });

    var attendingEvents = function(){

        var hoverEffect = function(){
            $('.event-attending-btn').hover(function(){
                $(this).animate({'width': '130px'}, 200);
                $(this).find('.fa').animate({'left': '-80px'}, 200);
                $(this).find('.attending-text').fadeIn(200);
            }, function(){
                $(this).animate({'width': '40px'}, 200);
                $(this).find('.fa').animate({'left': '0px'}, 200);
                $(this).find('.attending-text').fadeOut(200);
            });
        }();

        $('.event-attending-btn').on('click', function(){

            $(this).css('background-color', '#00e525');

        });
        $('.event-info').on('click', function(){ // Åpne
            var openThis = $(this).closest('.an-event').find('.edit-fields').attr('id');
            console.log(openThis);
            $('#' + openThis).fadeIn();
            $('#darkOverlay').fadeIn();
        });
    }();



// Hamburgerikon animasjon
var menuOpen = false;
$('.burger-wrap').on("click", function(){
  if(menuOpen == false){
    $('body').css('overflow', 'hidden');
    $('.menu').fadeIn();
    $('.bar1').css({ transform: 'rotate(' + 45 + 'deg)' });
    $('.bar1').css('top', '10px');
    $('.bar2').css({ transform: 'rotate(' + -45 + 'deg)' });
    $('.bar2').css('top', '10px');
    $('.bar3').fadeOut();
    menuOpen = true;
  }else if(menuOpen == true){
    $('body').css('overflow', 'auto');
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

if(localStorage.currentColor == '') localStorage.currentColor = 'blue'; // blue
var colorArray = ['blue', 'red', 'yellow', 'purple', 'green'];
var idMenuArray = ['one', 'two', 'three', 'four', 'five'];

changeColor();


$('#blue').on('click', function(){
    localStorage.currentColor = 'blue'; //green
    changeColor();
});
$('#red').on('click', function(){
    localStorage.currentColor = 'red'; //green
    changeColor();
});
$('#yellow').on('click', function(){
    localStorage.currentColor = 'yellow'; //green
    changeColor();
});
$('#purple').on('click', function(){
    localStorage.currentColor = 'purple'; // purple
    changeColor();
});
$('#green').on('click', function(){
    localStorage.currentColor = 'green'; //green
    changeColor();

});

function changeColor(){
    $('.menu-wrap').addClass('ta-bg-color-' + localStorage.currentColor);
    menuStripes('ta-menu-', localStorage.currentColor);

} // END changeColor

function menuStripes(className, currentColor){ // Stripene i menyen
    removeOtherClass('ta-menu-', localStorage.currentColor);

    // for-loop
    // Sett farge til alle
        // Finn currentColor
        // Sett currentColor til #three
        // Finn classen currentColor og bytt ut med grønn

    if(currentColor == 'blue'){

        $('#one').removeClass().addClass('ta-menu-red');
        $('#two').removeClass().addClass('ta-menu-yellow');
        $('#three').removeClass().addClass('ta-menu-blue');
        $('#four').removeClass().addClass('ta-menu-purple');
        $('#five').removeClass().addClass('ta-menu-green');
    }else if(currentColor == 'red'){
        $('#one').removeClass().addClass('ta-menu-blue');
        $('#two').removeClass().addClass('ta-menu-yellow');
        $('#three').removeClass().addClass('ta-menu-red');
        $('#four').removeClass().addClass('ta-menu-purple');
        $('#five').removeClass().addClass('ta-menu-green');
    }else if(currentColor == 'green'){
        $('#one').removeClass().addClass('ta-menu-blue');
        $('#two').removeClass().addClass('ta-menu-yellow');
        $('#three').removeClass().addClass('ta-menu-green');
        $('#four').removeClass().addClass('ta-menu-red');
        $('#five').removeClass().addClass('ta-menu-purple');
    }else if(currentColor == 'yellow'){
        $('#one').removeClass().addClass('ta-menu-blue');
        $('#two').removeClass().addClass('ta-menu-red');
        $('#three').removeClass().addClass('ta-menu-yellow');
        $('#four').removeClass().addClass('ta-menu-purple');
        $('#five').removeClass().addClass('ta-menu-green');
    }else if(currentColor == 'purple'){
        $('#one').removeClass().addClass('ta-menu-blue');
        $('#two').removeClass().addClass('ta-menu-yellow');
        $('#three').removeClass().addClass('ta-menu-purple');
        $('#four').removeClass().addClass('ta-menu-red');
        $('#five').removeClass().addClass('ta-menu-green');
    }


} // END menuStripes

function removeOtherClass(className, currentColor){

    for(var k = 0; k < 5; k++){

        if( colorArray[k] != currentColor){
            $('.' + className + localStorage.currentColor).removeClass(className + colorArray[k]);
        }else{
        }
    }

} // END removeOtherClass



}); // END DOM

