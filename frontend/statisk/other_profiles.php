<?php include 'header.php'; ?>

<div class="container">
    <!-- Kode her -->
    <div a href="#" class="logo"></div>



    <!--<a href="profil_edit.php" class="edit-profile-page">Edit your profile</a>
<!-- Profile first frame-->



        <div class="header-image-wrap">
        <img src="images/ola_nordmann_sidebilde.jpg" class="img-responsive" alt="">
        <div class="event-background-overlay"></div>
        <div class="page-profile-image" style="background-image: url('images/ola_nordmann.jpeg');">
        </div>

        <div class="page-name"><h1>Ola Nordmann</h1></div>

        <div class="add-page-to-card-btn">
            <div class="add-page-to-card-text">
                Add to card
                <i class="fa fa-plus"></i>
            </div>
        </div>
    </div><!-- END header-image-wrap -->
<a href="profil_edit.php" class="edit-profile-page">Edit</a> <!--edit button-->
    <!--if(myprofile){-->
        <!--hide-->
    <!--}else{-->
        <!--<a href="profil_edit.php" class="edit-profile-page">Edit</a>-->
    <!--}-->
    <div class="tabs">
        <ul class="tab-links">
            <li class="active"><a href="#about-tab">Info</a></li>
            <li><a href="#contact-tab">Kontakt meg</a></li>
        </ul>

        <div class="tabs-content">
            <div id="about-tab" class="tab active">
                <p>Info om Ola</p>
                <p>Ola er en sabla kar</p>
            </div><!--about-tab END-->

            <div id="contact-tab" class="tab">
                <p>Kontakt info:</p>
                <p>tlf: 1231255213</p>
            </div><!--contact-tab END-->

        </div><!--tabs-content END-->

    </div><!--tabs END-->

        <!-- Profile picture div -->
    <div class="profile-pic">
            <img src="images/olaNordmann.jpeg" class="profile-pic-holder">
        </div>
            <!-- Holds all the personal info -->
            <div class="info-holder">
                <h2 class="name">Lise Nordmann</h2>

                <div class="holder">
                    <p class="info-format" class="age" ><b>Age:</b></p>
                    <p class="age-c">23</p>
                </div>

                <div class="holder">
                    <p class="info-format" class="gender" ><b>Gender:</b></p>
                    <p class="gender-c">Female</p>
                </div>
                <div class="holder">
                    <p class="info-format" class="city"><b>City of residence: </b></p>
                    <p class="city-c">Oslo</p>
                </div>
                <div class="holder">
                    <p class="info-format" class="field-of-study"><b>Field of study: </b></p>
                    <p class="field-of-study-c">3D Design</p>
                </div>
                <div class="holder">
                    <p class="info-format" class="contact-info"><b>Contact info: </b></p>
                    <p class="contact-info-c">norlis14@student.westerdals.no</p>
                </div>
                <div class="holder">
                    <p class="info-format" class="interests"><b>Interests: </b></p>
                    <p class="interests-c"> 3ds Max, java, innebandy og gamings</p>
                </div>
                <div class="holder">
                    <p class="about-me"><b>About me:</b></p>

                </div>
                <div class="about-me-box">
                    <p class="about-me-text">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo
                    ligula eget dolor.Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes,
                    nascetur ridiculus mus.</p>

                </div>
            </div>

    <!-- Profile first frame END -->

    <!-- Profile post START -->
    <div class="profile-posts">Lise's posts</div>

    <div class="profile-post-box"
        <div class="col-feedbar-box" class="inner-feedbar" >
            <div class="boks" >
                <img src="images/olaNordmann.jpeg" class="col-sirkel">
                <h3 class="feed-boks-tittel">Tittel</h3>
                <h6 class="ekstra-info">Dato: 25.11.2015 - Ola Nordmann</h6>
                    <div class="post-profile">
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                    </div>
            </div>
            <div class="boks" >
                <img src="images/olaNordmann.jpeg" class="col-sirkel">
                <h3 class="feed-boks-tittel">Tittel</h3>
                <h6 class="ekstra-info">Dato: 25.11.2015 - Ola Nordmann</h6>
                    <div class="post-profile">
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                    </div>
            </div>
            <div class="boks" >
                <img src="images/olaNordmann.jpeg" class="col-sirkel">
                <h3 class="feed-boks-tittel">Tittel</h3>
                <h6 class="ekstra-info">Dato: 25.11.2015 - Ola Nordmann</h6>
                    <div class="post-profile">
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                    </div>
            </div>


        </div>


    </div>
    <!-- Profile post END -->
</div>

<?php include 'footer.php'; ?>