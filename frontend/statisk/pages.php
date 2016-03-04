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


                </div><!--box END-->
               <div class="page-preview">

                   <div class="page-preview-image" style="background-image: url('../statisk/images/event_pic.jpg');">
                       <div  class="event-image-overlay"></div>
                       <div class="add-page-to-card-pages" ng-click="addToCard()">

                           <div class="add-page-to-card-text">Add to card</div>
                           <div class="add-page-to-card-btn">
                               <i class="fa fa-plus"></i>
                           </div>
                       </div>
                       <h3 class="page-preview-header"><strong>Kvinner & IT</strong></h3>
                       <div class="page-preview-info">

                           <ul class="card-tag-list">
                               <li>#Kvinner&IT</li>
                               <li>#Datafordamer</li>
                               <li>#BedreEnnGutta</li>
                           </ul><!--card-tag-list END-->
                       </div><!--page-preview-info END-->
                   </div><!--page-preview-image END-->


               </div><!--page-preview END-->

               <div class="page-preview">

                   <div class="page-preview-image" style="background-image: url('../statisk/images/event_pic.jpg');">
                       <div  class="event-image-overlay"></div>
                       <div class="add-page-to-card-pages" ng-click="addToCard()">

                           <div class="add-page-to-card-text">Add to card</div>
                           <div class="add-page-to-card-btn">
                               <i class="fa fa-plus"></i>
                           </div>
                       </div>
                       <h3 class="page-preview-header"><strong>Kvinner & IT</strong></h3>
                       <div class="page-preview-info">

                           <ul class="card-tag-list">
                               <li>#Kvinner&IT</li>
                               <li>#Datafordamer</li>
                               <li>#BedreEnnGutta</li>
                           </ul><!--card-tag-list END-->
                       </div><!--page-preview-info END-->
                   </div><!--page-preview-image END-->


               </div><!--page-preview END-->

               <div class="page-preview">

                   <div class="page-preview-image" style="background-image: url('../statisk/images/event_pic.jpg');">
                       <div  class="event-image-overlay"></div>
                       <div class="add-page-to-card-pages" ng-click="addToCard()">

                           <div class="add-page-to-card-text">Add to card</div>
                           <div class="add-page-to-card-btn">
                               <i class="fa fa-plus"></i>
                           </div>
                       </div>
                       <h3 class="page-preview-header"><strong>Kvinner & IT</strong></h3>
                       <div class="page-preview-info">

                           <ul class="card-tag-list">
                               <li>#Kvinner&IT</li>
                               <li>#Datafordamer</li>
                               <li>#BedreEnnGutta</li>
                           </ul><!--card-tag-list END-->
                       </div><!--page-preview-info END-->
                   </div><!--page-preview-image END-->


               </div><!--page-preview END-->
            </div><!-- col-sm-8 END-->
           </div><!-- most-popular-pages box END-->
        </div><!--row END-->
    </div><!--end pages-main-content-->

</div><!--end container-->

<?php include 'footer.php'; ?> 