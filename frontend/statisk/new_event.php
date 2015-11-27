<?php include 'header.php'; ?>

<!--Hovedkontainer START-->
<div class="container new-event edit-fields" xmlns="http://www.w3.org/1999/html">
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

        <div class="col-sm-12 ">
            <textarea rows="5" class="edit-field3" placeholder="Describe your event"></textarea>
        </div>

        <div class="col-sm-12 invite-campus"
            <form>
                <p class="invite-text">Invite</p>
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
        <!--Row END-->
    </div>
    <!--Hovedkonteiner END-->
</div>



<?php include 'footer.php'; ?>