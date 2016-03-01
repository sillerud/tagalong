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
                        <div id="eventImageOverlay"></div>
                        <div id="eventAttendingBtn"><i class="fa fa-check"></i></div>
                        <h4 id="eventTitle">X-mas</h4>
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
                        <div id="eventInfo">
                            <i class="fa fa-info"></i>
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

