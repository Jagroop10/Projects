<?php
session_start();
require_once("../Model/customer.php");
require_once("../Model/db.php");

if (isset($_POST['email']) && isset($_POST['password'])) {
    $email = $_POST['email'];
    $password = $_POST['password'];

    $customer = userlogin($email, $password);

    if ($customer) {
        $_SESSION['logged_in'] = true;
        header('Location: ../Controller/checkout_controller.php');
        exit();
    } else {
        $error = "Invalid email or password. Please try again.";
    }
}

require_once("../View/userlogin.php");

?>