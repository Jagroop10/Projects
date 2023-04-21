<?php
session_start();
require_once ("../Model/customer.php");
require_once ("../Model/db.php");

$status = false;

if(isset($_REQUEST["surname"]) && isset($_REQUEST["givenname"]) && isset($_REQUEST["address"]) && isset($_REQUEST["email"]) && isset($_REQUEST["password"]))
{
  $surname = $_REQUEST["surname"];
  $givenname = $_REQUEST["givenname"];
  $address = $_REQUEST["address"];
  $email = $_REQUEST["email"];
  $password = $_REQUEST["password"];

  if(!empty($surname) && !empty($givenname) && !empty($address) && !empty($email) && !empty($password))
  {
    $customer = new Customer();
    $customer->surname = htmlentities($surname);
    $customer->givenname = htmlentities($givenname);
    $customer->address = htmlentities($address);
    $customer->email = htmlentities($email);
    $customer->password = htmlentities($password);

    addUser($customer);
    $status = "$givenname has been added.";
  }
  else
  {
    $status = "All fields are required.";
  }
}

require_once("../View/addUser.php");

?>
