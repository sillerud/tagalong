<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <link rel="shortcut icon" type="image/png" href="images/faneikon.png"/>
    <link rel="stylesheet" type="text/css" href="../bower_components/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/statisk.css">
    <link rel="stylesheet" type="text/css" href="css/menu.css">
    <link rel="stylesheet" type="text/css" href="css/forside.css">
    <link rel="stylesheet" type="text/css" href="css/search.css">
    <link rel="stylesheet" type="text/css" href="css/sidebar.css">
    <link rel="stylesheet" type="text/css" href="css/page_fubar.css">
    <link rel="stylesheet" type="text/css" href="css/pages.css">
    <link rel="stylesheet" type="text/css" href="css/other_profiles.css">
    <link rel="stylesheet" type="text/css" href="css/profil_edit.css">
    <link rel="stylesheet" type="text/css" href="css/events.css">
    <link rel="stylesheet" type="text/css" href="css/new-events.css">
        <link rel="stylesheet" type="text/css" href="css/movie_night_page.css">
    <link rel="stylesheet" href="../bower_components/components-font-awesome/css/font-awesome.css"><!-- Font Awesome ikoner -->

    <title>Tag along</title>
</head>
<body>

<div class="top-header">
    <div class="container">
        <div class="top-left">
            <a href="index.php"><img class="logo" src="images/tagalong_logo.png"></a>
            <input type="button" value="New Post" />
        </div>

        <div class="top-right">

            <div class="varsel">
                <i class="fa fa-bell"></i>
                <i class="fa fa-bell-o"></i>

                <div class="varsel-panel">

                    <div class="header-varsel-panel">

                        <div class="bell-varsel">
                            <i class="fa fa-bell"></i>
                        </div><!--end bell-varsel-->
                        <h3 class="varsel-title">Notifications</h3>

                    </div><!--end header-varsel-panel-->

                <div class="varsel-innhold">

                    <img src="images/fubar_logo.jpg" class="profilbilde-varsel">
                    <p class="tekst-varsel">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.
                    </p>

                </div><!--end varsel-innhold-->

                </div><!--end varsel-panel-->

            </div><!--end varlse-panel-->

            <a href="other_profiles.php">
                <div class="logged-in-person">
                    Ola Nordmann
                    <i class="fa fa-user"></i>
                </div>
            </a>

            <div class="fast-search">
                <input type="search" placeholder="Søk her...">
                <button><i class="fa fa-search"></i></button>
            </div>
        </div>
    </div>

</div>
<div id="one"></div>
    <div id="two"></div>
    <div id="three"></div>
    <div id="four"></div>
    <div id="five"></div>
<div class="menu-wrap">
    <div class="container" id="inner-menu">

        <div class="burger-wrap"><!-- Hamburgermenyen -->
            <div class="bar bar1"></div>
            <div class="bar bar3"></div>
            <div class="bar bar2"></div>
        </div><!-- END hamburgermeny -->

        <div class="menu">
            <ul>
                <li><a href="index.php" class="active">Home</a></li>
                <li><a href="sidebar.php">Feed</a></li>
                <li><a href="events.php">Events</a></li>
                <li><a href="pages.php">Pages</a></li>
            </ul>
        </div><!-- END menu inner-->
    </div><!-- END container -->
</div><!-- END menu-wrap -->

<div class="container">
    <div class="breadcrumbs">
        <a href="index.php">Home</a>
        <?php if(basename($_SERVER['SCRIPT_NAME']) == 'index.php'): ?>

        <?php elseif(basename($_SERVER['SCRIPT_NAME']) == 'sidebar.php'): ?>
            - Feed
        <?php elseif(basename($_SERVER['SCRIPT_NAME']) == 'other_profiles.php'): ?>
            - Profil
        <?php elseif(basename($_SERVER['SCRIPT_NAME']) == 'profil_edit.php'): ?>
            - <a href="other_profiles.php">Profil</a> - Edit profile
        <?php elseif(basename($_SERVER['SCRIPT_NAME']) == 'page_fubar.php'): ?>
            - Fubar
        <?php elseif(basename($_SERVER['SCRIPT_NAME']) == 'sidebar.php'): ?>
            - Feed
        <?php elseif(basename($_SERVER['SCRIPT_NAME']) == 'search.php'): ?>
            - Search
        <?php elseif(basename($_SERVER['SCRIPT_NAME']) == 'new_event.php'): ?>
            - New Event
        <?php elseif(basename($_SERVER['SCRIPT_NAME']) == 'events.php'): ?>
            - Events
        <?php elseif(basename($_SERVER['SCRIPT_NAME']) == 'pages.php'): ?>
            - Pages
        <?php endif; ?>
    </div>
</div>