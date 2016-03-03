<?php include 'header.php'; ?>

<div class="container"> 

    <!--<h2 class="pages-header-text">Pages</h2>-->

    <div class="pages-main-content">
        <div class="row">
           <div class="col-sm-5">
              <div class="filter-pages box">
                  <div class="box-title">
                      <h3 class="pages-most-popular-header">Filter</h3>
                  </div>

                  <div class=" box">
                      <div>
                        <h5 class="box-title">Rating</h5>
                      </div>
                      <div class="checkbox-style box">
                            <input type="checkbox" id="checkbox-style" value="None" name="check">Most popular<br>
                            <input type="checkbox" id="checkbox-style" value="None" name="check">Most resent<br>
                            <input type="checkbox" id="checkbox-style" value="None" name="check">Relevant to class
                      </div><!--filter-details END-->

                      <div>
                          <h5 class="box-title">Specifics</h5>
                      </div>
                      <form class="filter-rating box">
                          <input type="checkbox" id="checkbox-style" value="">Film og TV
                      </form><!--filter-rating END-->
                  </div><!--checkbox END-->

                </div><!--most-popular-pages END-->
            </div><!-- col-sm-4 END-->
        
           <div class="col-sm-7">
               <div class="box">
                <div class="box-title">
                    <h3 class="pages-all-pages-header">Pages</h3>
                </div>
               <div class="an-event-wrap">
                   <div class="page-image-wrap" style="background-image: url('../statisk/images/kit.jpg');">

                       <a href="/angular/partials/page.html">
                           <div class="pages-image-overlay"></div>
                       </a>


                       <h4 class="event-title">Kvinner og IT</h4>
                       <div class="page-about">
                           <div class="event-location">Location</div>
                           <div class="card-tag-wrap">
                               <ul class="card-tag-list">
                                   <li>#Utvikling</li>
                                   <li>@Fubar</li>
                                   <li>#Sosialt</li>
                               </ul>
                           </div>

                       </div><!-- END page about -->
                       <div class="event-info">
                           <i class="fa fa-info"></i>
                       </div>
                   </div><!-- END event-image-wrap -->


               </div><!-- END - an-event-wrap -->



            </div><!-- col-sm-8 END-->
           </div><!-- most-popular-pages box END-->
        </div><!--row END-->
    </div><!--end pages-main-content-->

</div><!--end container-->

<?php include 'footer.php'; ?> 