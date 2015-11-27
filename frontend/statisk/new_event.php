<?php include 'header.php'; ?>


<div class="container new-event edit-fields" xmlns="http://www.w3.org/1999/html"><!--Hovedkontainer START-->
    <center><h1>Create new event</h1></center>

    <div class="edit-profile-profile-pic">
        <img class="img-responsive img-edit"  src="images/event_pic.jpg">
        <input id="chooseProfilePic" type="file" style="visibility: hidden">
        <div class="pic-overlay" onclick="$('#chooseProfilePic').click();"><b>Click to edit</b></div>
    </div>

    <!--Row START-->
    <div class="row">
        <div class="col-sm-12">
            <input class="edit-field2 name-event" placeholder="Name your event">
        </div>
        <div class="col-sm-12">
            <input class="edit-field2 name-event" placeholder="Where will your event take place?">
        </div>
        <div class="col-sm-4">
        <label>When will the event be held?</label>
            <div class="form-group">
                <div class='input-group date' id='datetimepicker2'>
                    <input type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
        </div>
    </div><!--Row END-->

        <div class="describe-event">
            <textarea rows="5" class="edit-field3" placeholder="Describe your event"></textarea>
        </div>

        <div class="invite-container">            <!--invite section-->

            <h3 class="invite-text">Invite</h3>
             <div  class="invite-checkbox">
                <form>
                    <input type="checkbox" name="Invite all campus"> Invite all subjects
                    <br>
                    <input type="checkbox" name="Campus Galleriet"> Teknologi/IT
                    <br>
                    <input type="checkbox" name="Campus Vaterland"> Ledelse
                    <br>
                    <input type="checkbox" name="Campus Galleriet"> Kommunikasjon
                    <br>
                    <input type="checkbox" name="Campus Galleriet"> Kunstfag
                    <br>
                    <input type="checkbox" name="Campus Galleriet"> Film, TV og spill
                 </form>
             </div>

            <div class="invite-people">
                <form>
                    Invite people:
                    <br>
                    <input type="search" name="googlesearch">
                </form>
            </div>
        </div> <!-- Invite section-->
<div>
    <input type="submit" value="Save event" class="btn-fill">
</div>
</div><!--Hovedkonteiner END-->



<?php include 'footer.php'; ?>

