<?php
require_once "Recipe.php";

function getAllRecipes() {
    $recipes = array();
    try {
        $conn = new PDO("mysql:host=localhost;dbname=db_k2126669", "k2126669", "aegother");
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $stmt = $conn->prepare("SELECT * FROM recipe");
        $stmt->execute();
        while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
            $recipe = new Recipe(
                $row["id"],
                $row["title"],
                $row["cooking_time"],
                $row["rating"],
                $row["recipe_text"]
            );
            array_push($recipes, $recipe);
        }
    } catch (PDOException $e) {
        echo "Error: " . $e->getMessage();
    }
    return $recipes;
}

?>
