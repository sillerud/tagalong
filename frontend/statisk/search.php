<?php include 'header.php'; ?>

<div class="container testboks">
    <!-- Kode her -->
    <!-- Kilde til tab-koden fra: http://codepen.io/wallaceerick/pen/ojtal -->
    <h1> Search </h1>



<ul class="tabs">
    <li>
        <input type="radio" name="tabs" id="tab1" checked />
        <label for="tab1">#tags</label>
        <div id="tab-content1" class="tab-content">
            <?php include "search_people.php"; ?>
        </div>
    </li>

    <li>
        <input type="radio" name="tabs" id="tab2" />
        <label for="tab2">Events</label>
        <div id="tab-content2" class="tab-content">
          <p>"Sed ut" </p>
        </div>
    </li>

     <li>
        <input type="radio" name="tabs" id="tab3" />
        <label for="tab2">Pages</label>
        <div id="tab-content2" class="tab-content">
          <p>"Sed ut" </p>
        </div>
    </li>

     <li>
        <input type="radio" name="tabs" id="tab4" />
        <label for="tab2">Groups</label>
        <div id="tab-content2" class="tab-content">
          <p>"Sed ut" </p>
        </div>
    </li>

     <li>
        <input type="radio" name="tabs" id="tab5" />
        <label for="tab2">People</label>
        <div id="tab-content2" class="tab-content">
          <p>"Sed ut" </p>
        </div>
    </li>
</ul>

<br style="clear: both;" />

</div>

<?php include 'footer.php'; ?>