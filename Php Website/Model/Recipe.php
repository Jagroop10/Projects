<?php
class Recipe {
  private $id;
  private $title;
  private $date_added;
  private $ingredients;
  private $cooking_time;
  private $dietary_restrictions;
  private $keywords;
  private $recipe_text;
  private $price;


  function __get($name) {
    return $this->$name;
  }

  function __set($name,$value) {
    $this->$name = $value;
  }
}
?>