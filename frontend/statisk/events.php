<?php include 'header.php'; ?>

<div class="container">

    <br>

    <div class="row">
            <div class="events-feed-wrap">
                <div class="col-sm-12">
                    <h3>My events</h3>
                </div>

                <?php for($i = 0; $i < 3; $i++){ ?>

                <div class="col-sm-6">
                    <div class="an-event-wrap">

                        <div class="event-image-wrap" style="background-image: url(images/event_pic.jpg);">
                            <div class="event-image-overlay"></div>
                            <div class="event-attending-btn">
                                <i class="fa fa-check"></i>
                                <div class="attending-text">Attending</div>
                            </div>
                            <h4 class="event-title">X-mas</h4>
                        </div>
                        <div class="events-about">
                            <div class="event-location">Location</div>
                            <div class="an-event-date">24.12.2015 - Kl. 20.00 - 21.00</div>
                            <div class="events-attending">78 attending</div>
                            <div class="card-tag-wrap">
                                <ul class="card-tag-list">
                                    <li>#Utvikling</li>
                                    <li>@Fubar</li>
                                    <li>#Sosialt</li>
                                </ul>
                            </div>
                            <div class="event-info">
                                <i class="fa fa-info"></i>
                            </div>
                        </div><!-- END events about -->

                    </div><!-- END - an-event-wrap -->
                </div><!-- END col 6 -->

                <?php } // END for loop ?>


        </div><!-- END events feed wrap -->
    </div><!-- END row -->


</div><!--end container-->

<?php include 'footer.php'; ?>

