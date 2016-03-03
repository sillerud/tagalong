<?php include 'header.php'; ?>

<div class="container"> 

    <!--<h2 class="pages-header-text">Pages</h2>-->

    <div class="pages-main-content">
        <div class="row">
           <div class="col-sm-6">
              <div class="filter-pages box">
                  <div class="box-title">
                      <h3 class="pages-most-popular-header">Filter</h3>
                  </div>

                  <div class=" box">
                      <form class="checkbox-style box">
                            <input type="checkbox" id="checkbox-style" value="None" name="check">Most popular<br>
                            <input type="checkbox" id="checkbox-style" value="None" name="check">Most resent<br>
                            <input type="checkbox" id="checkbox-style" value="None" name="check">Relevant to class
                      </form><!--filter-details END-->

                      <form class="filter-rating box">
                          <input type="checkbox" id="checkbox-style" value="">Film og TV
                      </form><!--filter-rating END-->
                  </div><!--checkbox END-->

                </div><!--most-popular-pages END-->
            </div><!-- col-sm-6 (1) END-->
        
           <div class="col-sm-6">
               <div class="box">
                <div class="box-title">
                    <h3 class="pages-all-pages-header">Pages</h3>
                </div>
               <div class="an-event-wrap">
                   <div class="event-image-wrap" style="background-image: url('../statisk/images/kit.jpg');">

                       <a href="/angular/partials/page.html">
                           <div class="event-image-overlay"></div>
                       </a>
                       <div class="event-attending-btn">
                           <i class="fa fa-check"></i>
                           <div class="attending-text">Attending</div>
                       </div>

                       <h4 class="event-title">Kvinner og IT</h4>


                   </div><!-- END event-image-wrap -->
                   <div class="events-about">
                       <div class="event-location">Forening for jenter som studerer IT ved Westerdals</div>
                       <div class="an-event-date"></div>
                       <div class="events-attending">{{ getRandomNumber() }} following? carded?</div>
                       <div class="card-tag-wrap">
                           <ul class="card-tag-list">
                               <li>#Kvinner&IT</li>
                               <li>#TitsAndAss</li>
                               <li>#Sosialt</li>
                           </ul>
                       </div>
                       <div class="event-info">
                           <i class="fa fa-info"></i>
                       </div>
                   </div><!-- END events about -->

               </div><!-- END - an-event-wrap -->

               <div class="an-event-wrap">
                   <div class="event-image-wrap" style="background-image: url('../statisk/images/event_pic.jpg');">

                       <a href="/angular/partials/page.html">
                           <div class="event-image-overlay"></div>
                       </a>
                       <div class="event-attending-btn">
                           <i class="fa fa-check"></i>
                           <div class="attending-text">Attending</div>
                       </div>

                       <h4 class="event-title">X-mas</h4>


                   </div><!-- END event-image-wrap -->
                   <div class="events-about">
                       <div class="event-location">Location</div>
                       <div class="an-event-date">24.12.2015 - Kl. 20.00 - 21.00</div>
                       <div class="events-attending">{{ getRandomNumber() }} attending</div>
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

            </div><!-- col-sm-6 (2) END-->
           </div><!-- most-popular-pages box END-->
        </div><!--row END-->
    </div><!--end pages-main-content-->

</div><!--end container-->

<?php include 'footer.php'; ?> 