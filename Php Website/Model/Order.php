<?php
class Order {
    private $id;
    private $total_price;
    private $order_items;
  
    function __construct() {
        $this->order_items = array();
    }

    function __get($name) {
        return $this->$name;
    }

    function __set($name,$value) {
        $this->$name = $value;
    }
}



?>