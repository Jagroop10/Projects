<?php
class Customer {
  private $id;
  private $givenname;
  private $surname;
  private $address;
  private $email;
  private $password;

  

  function __get($name) {
    return $this->$name;
  }

  function __set($name,$value) {
    $this->$name = $value;
  }
}
?>
