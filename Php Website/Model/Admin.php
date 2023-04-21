<?php
class Admin {
  private $id;
  private $name;
  private $adminemail;
  private $adminpassword;

  function __get($name) {
    return $this->$name;
  }

  function __set($name,$value) {
    $this->$name = $value;
  }
}
?>