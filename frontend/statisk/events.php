<?php include 'header.php'; ?>

<div class="container">
    <div class="event-top">
        <h1></h1>
        <a href="new_event.php">
            <div class="btn btn-no-fill float-left">Create new event</div>
        </a>
    </div>

    <br>

    <div class="row">
        <div class="col-sm-6">
            <div class="events-feed-wrap">

                <h3>My events</h3>

                <div class="an-event-wrap">
                    <div class="event-image-wrap" style="background-image: url(images/event_pic.jpg);">
                        <div class="events-attending">78 attending</div>
                    </div>
                    <div class="events-about">
                        <h4><strong>X-mas</strong></h4>
                        <div class="an-event-date">24.12.2015</div>
                        <div class="attending-or-not-wrap">
                            <div class="attending-btn attending-checked"><i class="fa fa-check"></i></div>
                            <div class="not-attending-btn"><i class="fa fa-times"></i></div>
                            <div class="number-attending">50 Attending</div>
                        </div>
                    </div><!-- END events about -->

                </div><!-- END - an-event-wrap -->

                <div class="an-event-wrap">
                    <h4><strong>New Years Eve</strong></h4>
                    <div class="an-event-date">31.12.2015</div>
                    <div class="attending-or-not-wrap">
                        <div class="attending-btn attending-checked"><i class="fa fa-check"></i></div>
                        <div class="not-attending-btn"><i class="fa fa-times"></i></div>
                        <div class="number-attending">128 Attending</div>
                    </div>
                </div><!-- END - an-event-wrap -->

            </div><!--end my-events-box-->
        </div>

        <div class="col-sm-6">
            <div class="events-feed-wrap">
                <h3>Upcoming events</h3>

               <div class="an-event-wrap">
                    <h4><strong>January code session</strong></h4>
                    <div class="an-event-date">10.01.2016</div>
                    <div class="attending-or-not-wrap">
                        <div class="attending-btn"><i class="fa fa-check"></i></div>
                        <div class="not-attending-btn"><i class="fa fa-times"></i></div>
                        <div class="number-attending">50 Attending</div>
                    </div>
                </div><!-- END - an-event-wrap -->

            </div>
        </div><!--end -->
    </div>


</div><!--end container-->

<?php include 'footer.php'; ?>

