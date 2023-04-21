<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

$db_user = "k2126669";
$db_name = "db_k2126669";
$db_password = "aegother";

$pdo = new
 PDO("mysql:host=localhost;dbname=$db_name",
$db_user,$db_password);

function getAllRecipes()
{
  global $pdo;
  $statement = $pdo->prepare('SELECT * FROM Recipe');
  $statement->execute();
  $result = $statement->fetchAll(PDO::FETCH_CLASS, 'Recipe');
  return $result;
}

function getRecipename($title)
{
  global $pdo;
  $statement = $pdo->prepare('SELECT * FROM Recipe WHERE title = ?');
  $statement->execute([$title]);
  $result = $statement->fetchAll(PDO::FETCH_CLASS, 'Recipe');
  return $result;
}

function getCookingTime($cooking_time)
{
  global $pdo;
  $statement = $pdo->prepare('SELECT * FROM Recipe WHERE cooking_time = ?');
  $statement->execute([$cooking_time]);
  $result = $statement->fetchAll(PDO::FETCH_CLASS, 'Recipe');
  return $result;
}

function getPrice($price)
{
  global $pdo;
  $statement = $pdo->prepare('SELECT * FROM Recipe WHERE price = ?');
  $statement->execute([$price]);
  $result = $statement->fetchAll(PDO::FETCH_CLASS, 'Recipe');
  return $result;
}

function getDietaryRestrictions($dietary_restrictions)
{
  global $pdo;
  $statement = $pdo->prepare('SELECT * FROM Recipe WHERE dietary_restrictions = ?');
  $statement->execute([$dietary_restrictions]);
  $result = $statement->fetchAll(PDO::FETCH_CLASS, 'Recipe');
  return $result[0];
}


function getRecipeById($id)
{
  global $pdo;
  $statement = $pdo->prepare('SELECT * FROM Recipe WHERE id = ?');
  $statement->execute([$id]);
  $result = $statement->fetchAll(PDO::FETCH_CLASS, 'Recipe');
  return $result[0];
}

function getSearchResults($searchTerm)
{
  global $pdo;
  $statement = $pdo->prepare('SELECT * FROM Recipe WHERE title LIKE ? OR ingredients LIKE ?');
  $statement->execute(["%$searchTerm%", "%$searchTerm%"]);
  $result = $statement->fetchAll(PDO::FETCH_CLASS, 'Recipe');
  return $result;
}







?>






































