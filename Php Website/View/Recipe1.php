<?php
require_once "../Model/Recipe.php";
require_once "../Model/DataAccess.php";

$recipe = getRecipeById(1);
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><?= $recipe->title ?></title>
    <link rel="stylesheet" href="../View/Recipe-style.css">
</head>

<body>
    <div class="recipe-container">
        <div class="recipe-image">
        <img src="../View/Images/<?= $recipe->id ?>.jpg" alt="<?= $recipe->title ?>">
        </div>
        <div class="recipe-info">
            <h2><?= $recipe->title ?></h2>
            <p>Price: <?= $recipe->price ?></p>
            <p>Data Added: <?= $recipe->date_added ?></p>
            <p>Ingredients: <?= $recipe->ingredients ?></p>
            <p>Cooking Time: <?= $recipe->cooking_time ?></p>
            <p>Dietary Restrictions: <?= $recipe->dietary_restrictions ?></p>
            <p>Description: <?= $recipe->recipe_text ?></p>
        </div>
    </div>
    <a href="../Controller/HomepageController.php"><button>Go to Homepage</button></a>

</body>
</html>
