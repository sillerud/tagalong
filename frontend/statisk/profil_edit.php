<?php include 'header.php'; ?>

<div class="container">
    <h1 class="">Edit your profile details</h1>

    <div class="row edit-profile-wrap">
        <div class="col-sm-6">

            <select class="edit-profile" id="years">
                <option selected disabled>Choose when you where born</option>
            </select>


        </div>
        <div class="col-sm-6">
            <h4>Edit your contact information</h4>
            <p>All fields are optional</p>

            <label>Email:</label>
            <input class="edit-profile" type="text" placeholder="ola@nordmann.no">

            <label>Facebook:</label>
            <input class="edit-profile" type="text" placeholder="ola@nordmann.no">

            <label>LinkedIn:</label>
            <input class="edit-profile" type="text" placeholder="ola@nordmann.no">

            <label>Website:</label>
            <input class="edit-profile" type="text" placeholder="ola@nordmann.no">
        </div>
    </div>
</div>

<?php include 'footer.php'; ?>