<?php
class Recipe{

private $id;
private $title;
private $ingredients = array();
private $cooking_time;
private $dietary_restrictions = array();
private $keywords = array();
private $rating;
private $recipe_text;

    public function __construct($id = null, $title = null, $cooking_time = null, $rating = null, $recipe_text = null)
    {
        $this->id = $id;
        $this->title = $title;
        $this->cooking_time = $cooking_time;
        $this->rating = $rating;
        $this->recipe_text = $recipe_text;
    }
    
    public function addIngredient($ingredient) {
        array_push($this->ingredients, $ingredient);
    }
    
    public function addDietaryRestriction($restriction) {
        array_push($this->dietary_restrictions, $restriction);
    }
    
    public function addKeyword($keyword) {
        array_push($this->keywords, $keyword);
    }
    
    public function getId() {
        return $this->id;
    }
    
    public function getTitle() {
        return $this->title;
    }
    
    public function getCookingTime() {
        return $this->cooking_time;
    }
    
    public function getRating() {
        return $this->rating;
    }
    
    public function getRecipeText() {
        return $this->recipe_text;
    }
    
    public function getIngredients() {
        return $this->ingredients;
    }
    
    public function getDietaryRestrictions() {
        return $this->dietary_restrictions;
    }
    
    public function getKeywords() {
        return $this->keywords;
    }
    
    }
    

?>
