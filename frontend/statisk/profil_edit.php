<?php include 'header.php'; ?>

<div class="container edit-profile-wrap">
    <center><h1>Edit your profile details</h1></center>

    <div class="edit-profile-profile-pic">
        <img class="img-responsive" src="images/profilbilde_jap.jpg">
        <div class="pic-overlay"><b>Click to edit</b></div>
    </div>

    <div class="row">
        <div class="col-sm-6">
            <h4>Edit your personal status</h4>
            <h6>Fields marked with * (asterisk) are required</h6>

            <br><br>

            <label>Year of birth</label>
            <select class="edit-field1" id="years">
                <option selected disabled>Choose when you where born</option>
            </select>

            <label>Your gender</label>
            <select class="edit-field1" id="years">
                <option selected disabled>Choose your gender</option>
            </select>

            <label>City</label>
            <select class="edit-field1" id="years">
                <option selected disabled>Choose where you live</option>
            </select>

            <label>Field of study *</label>
            <select class="edit-field1" id="years">
                <option selected disabled>Choose your field of study</option>
            </select>

            <label>Year of study start *</label>
            <select class="edit-field1" id="years">
                <option selected disabled>Choose the year you started</option>
            </select>

        </div>
        <div class="col-sm-6">
            <h4>Edit your contact information</h4>
            <h6>All fields are optional</h6>

            <br><br>

            <label>Email:</label>
            <input class="edit-field2" type="text" placeholder="ola@nordmann.no">

            <label>Facebook:</label>
            <input class="edit-field2" type="text" placeholder="ola@nordmann.no">

            <label>LinkedIn:</label>
            <input class="edit-field2" type="text" placeholder="ola@nordmann.no">

            <label>Website:</label>
            <input class="edit-field2" type="text" placeholder="ola@nordmann.no">
        </div>
    </div>
</div>

<?php include 'footer.php'; ?>