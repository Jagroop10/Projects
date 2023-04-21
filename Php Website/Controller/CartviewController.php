<?php
require_once "../Model/Recipe.php";
require_once "../Model/DataAccess.php";
session_start();

if (!isset($_SESSION["basket"])) {
    $_SESSION["basket"] = [];
}

// Check if the "RemoveRecipe" button was pressed
if (isset($_POST["RemoveRecipe"])) {
    // Get the ID of the recipe that was selected
    $recipe_id = $_POST["recipe_id"];
    // Loop through the list of recipes in the basket
    foreach ($_SESSION["basket"] as $key => $recipe) {
        // Check if the current recipe has the same ID as the selected recipe
        if ($recipe->id == $recipe_id) {
            // Remove the recipe from the basket
            unset($_SESSION["basket"][$key]);
            // Stop searching for the recipe
            break;
        }
    }
}


$basket = $_SESSION["basket"];

$total_price = 0;

foreach ($basket as $recipe) {
    $total_price += $recipe->price;
}

require_once "../View/Cartview.php";
?>
