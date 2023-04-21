<?php
class OrderItem {
    private $id;
    private $recipe_id;
    private $price;

    function __get($name) {
        return $this->$name;
    }

    function __set($name,$value) {
        $this->$name = $value;
    }
}
?>