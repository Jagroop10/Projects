<?php
  session_start();
  require_once ("../Model/Recipe.php");
  require_once ("../Model/db.php");

  $status = false;

  if(isset($_REQUEST["title"]))
  {
    $title = $_REQUEST["title"];
    $date_added = $_REQUEST["date_added"];
    $ingredients = $_REQUEST["ingredients"];
    $cooking_time = $_REQUEST["cooking_time"];
    $dietary_restrictions = $_REQUEST["dietary_restrictions"];
    $keywords = $_REQUEST["keywords"];
    $recipe_text = $_REQUEST["recipe_text"];
    $price = $_REQUEST["price"];

    $recipe = new recipe();
    $recipe->title = htmlentities($title);
    $recipe->date_added = htmlentities($date_added);
    $recipe->ingredients = htmlentities($ingredients);
    $recipe->cooking_time = htmlentities($cooking_time);
    $recipe->dietary_restrictions = htmlentities($dietary_restrictions);
    $recipe->keywords = htmlentities($keywords);
    $recipe->recipe_text = htmlentities($recipe_text);
    $recipe->price = htmlentities($price);


  
   
    


    addRecipe($recipe);
    $status = "$title has been added.";
  }

  require_once("../View/Admindashboard.php");

?>
