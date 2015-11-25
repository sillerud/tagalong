<?php include 'header.php'; ?>

<div class="container">
    <!-- Kode her -->
    <form id="search-page-form">
        <div class="search-page-wrap">
            <button><i class="fa fa-search"></i></button>
            <input type="search" placeholder="Search for Tags, Pages, People or Events">
        </div>
    </form>

    <div class="search-tabs">

       <div class="tab-tab">
           <input type="radio" id="tags" name="tab-group" checked>
           <label for="tags">Tags</label>

           <div class="tab-content">
               Tags
           </div>
       </div>

       <div class="tab-tab">
           <input type="radio" id="pages" name="tab-group">
           <label for="pages">Pages</label>

           <div class="tab-content">
               <?php include 'search_page.php'; ?>
           </div>
       </div>

        <div class="tab-tab">
           <input type="radio" id="people" name="tab-group">
           <label for="people">People</label>

           <div class="tab-content">
               <?php include 'search_people.php'; ?>
           </div>
       </div>

       <div class="tab-tab">
           <input type="radio" id="events" name="tab-group">
           <label for="events">Events</label>

           <div class="tab-content">
               <?php include 'search_event.php' ?>
           </div>
       </div>

    </div>

</div>

<?php include 'footer.php'; ?>