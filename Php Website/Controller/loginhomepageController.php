<?php

require_once "../Model/Recipe.php";
require_once "../Model/DataAccess.php";
session_start();

if (!isset($_SESSION["basket"]))
{
    $_SESSION["basket"] = [];
}

$basket = $_SESSION["basket"];


if (isset($_GET["invite"])) {
  $recipeId = $_GET["invite"];
  $recipe = getRecipeById($recipeId);
  $_SESSION["basket"][] = $recipe; 
  header("Location: ../Controller/loginhomepageController.php");
}


if (isset($_REQUEST["search"]) && $_REQUEST["search"] != "") {
  $searchType = $_POST["searchType"];
  if ($searchType == "price") {
    $results = getPrice($_REQUEST["search"]);
  } else if ($searchType == "cookingtime") {
    $results = getCookingTime($_REQUEST["search"]);
  } else {
    $results = getSearchResults($_REQUEST["search"]);
  }
} else {
  $results = getAllRecipes();
}

require_once "../View/userhomepage.php";


?>




