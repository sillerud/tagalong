$(document).ready(function () {

    var bodyWidth = $(document).outerWidth();
    // Kjører hver gang vinduet endrer størrelse
    $(window).resize(function() {
        //createThumbnails();
        $('.sidebar-all-card').removeAttr('style');
        bodyWidth = $('body').outerWidth();

        mobileSettingsDropdown();
    });

    var fixedMenu = function(){
        var topOffset = $(document).scrollTop();
        $(window).scroll(function() {
            topOffset = $(document).scrollTop();
            if( topOffset > 69) makeFixed();
            else {
                $('#colors').removeClass('color-fixed');
                $('.menu-wrap').removeClass('menu-fixed');
                $('.top-header').css('margin-bottom', '0px');
            }
        });

        function makeFixed(){
            $('#colors').addClass('color-fixed');
            $('.menu-wrap').addClass('menu-fixed');
            $('.top-header').css('margin-bottom', '66px');
        }; // END makeFixed
    }();

    var fixedPageCards = function(){
        var leftOffset = $('.container').offset().left;
        var cardShortcuts = $('.card-shortcut').length;
        var csLength = [];

        $(window).resize(function() {
            leftOffset = $('.container').offset().left;
            $('.card-shortcut-wrap').css('left', (leftOffset - 50) + 'px' );
        });

        $('.card-shortcut').each(function(index){
            var thatId = $(this).attr('id');
            var thatIdLength = $('#' + thatId).width() + 10;
            csLength.push(thatIdLength);
        });

        $('.card-shortcut-wrap').css('left', (leftOffset - 50) + 'px' );
        $('.card-shortcut').stop().animate({'width': '40px'}, 200);
        $('.card-name').stop().fadeOut(200);

        var hoverEffect = function(){
            $('.card-shortcut').hover(function(){
                var thatId = $(this).attr('id');
                var idNum = thatId.substr(thatId.length - 1);
                $(this).stop().animate({'width': csLength[idNum] + 'px'}, 200);
                $(this).find('.card-name').stop().fadeIn(200);
            }, function(){
                $(this).stop().animate({'width': '40px'}, 200);
                $(this).find('.card-name').stop().fadeOut(200);
            });
        }();

    }();

    // Legger inn årstall i edit profile
    var select = $("#years");
    var year = (new Date).getFullYear() - 14;

    for (i = year; i >= 1900; i--){
        select.append($('<option>h</option>').val(i).html(i))
    }
    // END årstall adder

    var openCloseOverlays = function(){

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
/*
        var addNewOpen = false;
        $('#addNew').on('click', function(){
            var speed = 300;

            if( !addNewOpen ){
                $('#newPostBtn').stop().css('display', 'block').animate({'bottom': '60px', 'opacity': '1'}, speed);
                $('#newPageBtn').delay(100).css('display', 'block').animate({'bottom': '110px', 'opacity': '1'}, speed);
                $('#newEventBtn').delay(200).css('display', 'block').animate({'bottom': '160px', 'opacity': '1'}, speed);
                $('#newSearchBtn').delay(300).css('display', 'block').animate({'bottom': '210px', 'opacity': '1'}, speed);
                addNewOpen = true;
            }else{
                $('#newSearchBtn').animate({'bottom': '200px', 'opacity': '0'}, speed, function(){ $(this).css('display', 'none'); } );
                $('#newEventBtn').delay(100).animate({'bottom': '150px', 'opacity': '0'}, speed, function(){ $(this).css('display', 'none'); } );
                $('#newPageBtn').delay(200).animate({'bottom': '100px', 'opacity': '0'}, speed, function(){ $(this).css('display', 'none'); });
                $('#newPostBtn').delay(300).animate({'bottom': '50px', 'opacity': '0'}, speed, function(){ $(this).css('display', 'none'); });
                addNewOpen = false;
            }
        });*/

        $(document).keydown(function(e){
            if(e.keyCode == 27){
                closeOverlay('.edit-card-wrap');
                closeOverlay('#darkOverlay');
                closeOverlay('.search-overlay-wrap');
            }
        });

        function closeOverlay(thatClass){
            //$(thatClass).fadeOut();
        };

    }(); // END open close overlays


    $('#datetimepicker2').datetimepicker({
        locale: 'nb'
    });

    var attendingEvents = function(){

        var hoverEffect = function(){
            $('.event-attending-btn').hover(function(){
                $(this).stop().animate({'width': '130px'}, 200);
                $(this).find('.fa').stop().animate({'left': '-80px'}, 200);
                $(this).find('.attending-text').stop().fadeIn(200);
            }, function(){
                $(this).stop().animate({'width': '40px'}, 200);
                $(this).find('.fa').stop().animate({'left': '0px'}, 200);
                $(this).find('.attending-text').stop().fadeOut(200);
            });
        }();

        $('.event-attending-btn').on('click', function(){

            $(this).css('background-color', '#00e525');

        });
        $('.event-info').on('click', function(){ // Åpne
            var openThis = $(this).closest('.an-event').find('.edit-fields').attr('id');
            $('#' + openThis).fadeIn();
            $('#darkOverlay').fadeIn();
        });
    }();



// Hamburgerikon animasjon
/*
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
});*/
// END hamburgerikon animasjon

// Person menu dropdown for mobil
var linkAdded = false;
function mobileSettingsDropdown(){

    if( linkAdded == false && bodyWidth < 654 ){
        $('.menu-person-dropdown').prepend('<a id="mobilePerson" href="#/profile/"><div class="person-list">Your profile</div></a>');
        linkAdded = true;
        $('.menu-person-dropdown')
            .addClass('menu-person-dropdown-mobile')
            .removeClass('menu-person-dropdown');
    }else if( bodyWidth >= 654 ){
        $('.menu-person-dropdown-mobile')
            .addClass('menu-person-dropdown')
            .removeClass('menu-person-dropdown-mobile');
        $('.menu-person-dropdown').find('#mobilePerson').remove();
        linkAdded = false;
    }
};
mobileSettingsDropdown();

}); // END DOM

(function (extension) {
    extension(showdown)
}(function (showdown) {
    showdown.extension('imgur-webm', function() {
        var imgRegex = /(?:<p>)?<img.*?src="(.+?)"(.*?)\/?>(?:<\/p>)?/gi;
        var gyazoRegex = /(?:https?:\/\/)?(?:i\.|embedded\.)(gyazo.com\/.+)\.(?:gif|mp4)/;
        var imgurRegex = /(?:https?:\/\/)?((?:i\.)?imgur.com\/.+)\.(?:gif|gifv|webm)/;
        var gfycatRegex = /(?:https?\/\/)?(?:.+\.)?gfycat.com\/(.+)(?:\.(?:webm|gif|mp4))?/;
        var m;
        return [
            {
                type: 'output',
                filter: function (text) {
                    return text.replace(imgRegex, function(match, url) {
                        if ((m = imgurRegex.exec(url))) {
                            return '<video class="embedded-video" loop="true" autoplay="true" muted="true" src="https://' + m[1] + '.webm"></video>'
                        } else if ((m = gfycatRegex.exec(url))) {
                            var webmUrl; // This is pretty ugly but it has to be async :/
                            console.log('https://gfycat.com/cajax/get/' + m[1]);
                            $.ajax({
                                url: 'https://gfycat.com/cajax/get/' + m[1],
                                async: false,
                                success: function (result) {
                                    webmUrl = result.gfyItem.webmUrl;
                                }
                            });
                            return '<video class="embedded-video" loop="true" autoplay="true" muted="true" src="' + webmUrl + '"></video>'
                            //return '<video loop="true" autoplay="true" muted="true" src="https://zippy.' + m[1] + '.webm"></video>'
                        } else if ((m = gyazoRegex.exec(url))) {
                            return '<video class="embedded-video" loop="true" autoplay="true" muted="true" src="https://embed.' + m[1] + '.mp4"></video>'
                        } else {
                            return match;
                        }
                    });
                }
            }
        ]
    })
}));