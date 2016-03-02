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

        <div class="add-page-to-card-btn">
            <div class="add-page-to-card-text">
                Add to card
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
                        <div class="shout-out-title">
                            <h3>Fubar Shout out</h3>
                        </div>
                        <div class="shout-out-text">
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Molestiae incidunt labore sapiente temporibus ab est non quia, cupiditate, ea accusantium perferendis cum quo repellendus, sit quae id voluptates voluptatum voluptas.
                            </p>
                        </div>
                    </div><!-- END shout out box -->


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
