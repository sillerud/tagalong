<?php include 'header.php'; ?>

<div class="container">

    <div class="card-shortcut-wrap">

        <div class="card-shortcut" id="cs0">
            <div class="cs-icon">M</div>
            <div class="card-name">My social card is very long</div>
        </div><br>
        <div class="card-shortcut" id="cs1">
            <div class="cs-icon">S</div>
            <div class="card-name">Skolerelatert</div>
        </div><br>
        <div class="card-shortcut" id="cs2">
            <div class="cs-icon">K</div>
            <div class="card-name">Katta med slips</div>
        </div><br>

    </div><!-- END card-shortcut-wrap -->

    <div class="header-image-wrap">
        <img src="images/event_pic.jpg" class="img-responsive" alt="">
        <div class="event-background-overlay"></div>
        <div class="page-profile-image" style="background-image: url('images/fubar_logo.jpg');">
        </div>

        <div class="page-name"><h1>@Fubar</h1></div>

        <div class="add-page-to-card">
            <div class="add-page-to-card-text">Add to card</div>
            <div class="add-page-to-card-btn">
                <i class="fa fa-plus"></i>
            </div>
        </div>
    </div><!-- END header-image-wrap -->


    <!-- Page content -->
    <div class="page-content">
        <div class="row">
            <div class="col-sm-6">
                <div class="page-left-column">

                    <div class="shout-out-box box">
                        <div class="box-title">
                            <h3>Fubar Shout out</h3>
                        </div>
                        <div class="shout-out-text">
                            <p>
                                Lorem ipsum <strong>dolor sit amet</strong>, consectetur adipisicing elit. Molestiae incidunt labore sapiente.
                            </p>
                            <p>
                                <strong>temporibus ab est non quia, cupiditate</strong>
                            </p>
                        </div>
                    </div><!-- END shout out box -->

                    <div class="about-n-gallery box">
                        <div class="box-title">
                            <h3><a href="">About</a></h3>
                        </div>
                        <span></span>
                        <div class="box-title">
                            <h3><a href="">Gallery</a></h3>
                        </div>
                    </div>

                    <div class="about-box box">
                        <div class="box-title">
                            <h3>About</h3>
                        </div>
                        <div class="about-box-text">
                            <p class="text-shown">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Natus aut voluptatum iste? Aliquam facilis reiciendis sequi sapiente quos, ipsum voluptatum voluptate veniam consectetur assumenda doloremque quod animi fugiat, cum in...
                            </p>
                            <p class="text-hidden">
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nobis quod eveniet totam ipsum provident vitae porro pariatur distinctio modi debitis, quas fuga culpa, odit ipsa sed eum placeat non dolor!
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sint velit iure cum itaque commodi assumenda deleniti laboriosam neque consequuntur aspernatur iste nihil, molestiae perspiciatis inventore dolorum est, reprehenderit magni omnis.
                            </p>

                        </div><!-- END about tekst -->
                        <div class="about-read-more">
                            <span class="read-more">Read more</span>
                        </div>

                    </div><!-- END about-box -->

                    <div class="gallery-box box">
                        <div class="box-title">
                            <h3>Gallery</h3>
                        </div>

                        <div class="gallery-thumbs-wrap">
                            <div class="gallery-thumb">
                                <img class="thumb" src="images/ola_nordmann.jpeg" alt="">
                            </div>
                            <div class="gallery-thumb">
                                <img class="thumb" src="images/ola_nordmann_sidebilde.jpg" alt="">
                            </div>
                            <div class="gallery-thumb">
                                <img class="thumb" src="images/event_pic.jpg" alt="">
                            </div>
                            <div class="gallery-thumb">
                                <img class="thumb" src="images/ola_nordmann_sidebilde.jpg" alt="">
                            </div>
                            <div class="gallery-thumb">
                                <img class="thumb" src="images/profilbilde_jap.jpg" alt="">
                            </div>
                            <div class="gallery-thumb">
                                <img class="thumb" src="images/ola_nordmann_sidebilde.jpg" alt="">
                            </div>
                        </div>

                        <div class="view-gallery">
                            <span>View gallery</span>
                        </div>

                    </div><!-- END about-box -->


                </div><!-- END page-left-column -->
            </div>
            <div class="col-sm-6">
                <div class="page-right-column">
                    <?php include 'person_feed.php' ?>
                </div>
            </div>
        </div>
    </div><!-- END page-content -->

</div><!--end container-->

<?php include 'footer.php'; ?>
