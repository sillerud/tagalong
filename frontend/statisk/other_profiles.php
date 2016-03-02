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

    <!--if(myprofile){-->
        <!--hide-->
    <!--}else{-->
        <!--<a href="profil_edit.php" class="edit-profile-page">Edit</a>-->
    <!--}-->
<div class="row">
    <div class="col-sm-6">
    <div class="tabs">
        <ul class="tab-links">
            <li class=" tabs-item active"><a href="#about-tab">Info</a></li>
            <li class="tabs-item"><a href="#contact-tab">Contact</a></li>
        </ul>

        <div class="tabs-content">
            <div id="about-tab" class="tab active">
                <div class="holder">
                    <p class="info-format" class="age" ><b>Age: </b></p>
                    <p class="age-c"> 23</p>
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
                    <p class="info-format" class="interests"><b>Interests: </b></p>
                    <p class="interests-c"> 3ds Max, java, innebandy og gamings</p>
                </div>
                <a href="profil_edit.php" class="edit-profile-page">Edit</a> <!--edit button-->
            </div><!--about-tab END-->

            <div id="contact-tab" class="tab">
                <div class="holder">
                    <p class="info-format" class="email"><b>Email: </b></p>
                    <p class="">ettellerannet@hotmail.com</p>
                </div>
                <div class="holder">
                    <p class="info-format" class="facebook"><b>Facebook: </b></p>
                    <p class="">olanormann@facebook.com</p>
                </div>
                <div class="holder">
                    <p class="info-format" class="linkedin"><b>LinkedIn: </b></p>
                    <p class="">olanormannprofotballer</p>
                </div>
            </div><!--contact-tab END-->

        </div><!--tabs-content END-->

    </div><!--tabs END-->
        <div>


        </div><!--skills END-->
    </div><!--col-sm-6 END-->
    <div class="col-sm-6">
        <?php include "person_feed.php" ?>


    </div><!--person_feed.php END-->
</div><!--row END-->


</div>

<?php include 'footer.php'; ?>