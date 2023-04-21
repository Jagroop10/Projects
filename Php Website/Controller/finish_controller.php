<?php
require_once "../Model/Order.php";
require_once "../Model/Recipe.php";
require_once "../Model/DataAccess.php";
require_once "../Model/db.php";
session_start();
$status = false;

$order = new Order();
$order->total_price = $total_price;

foreach ($_SESSION['basket'] as $recipe) {
    $order->total_price += $recipe->price;
}

saveOrder($_SESSION['basket'], $order);

$_SESSION['basket'] = [];


header("Location: ../Controller/HomepageController.php?$status");
exit();


?>
