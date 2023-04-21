<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

$db_user = "k2126669";
$db_name = "db_k2126669";
$db_password = "aegother";


$pdo = new PDO("mysql:host=localhost;dbname=$db_name", $db_user, $db_password);



function getUsersById($id)
{
  global $pdo;
  $statement = $pdo->prepare('SELECT * FROM Customers WHERE id = ?');
  $statement->execute([$id]);
  $result = $statement->fetchAll(PDO::FETCH_CLASS, 'Customer');
  return $result[0];
}


function getAllUsers()
{
  global $pdo;
  $statement = $pdo->prepare('SELECT givenname, surname, address, email, password FROM Customers');
  $statement->execute();
  $result = $statement->fetchAll(PDO::FETCH_CLASS, 'Customer');
  return $result;
}

function getUsersBySurname($surname)
{
  if ($surname == "")
  {
    return getAllUsers();
  }
  global $pdo;
  $statement = $pdo->prepare('SELECT givenname, surname, address, email, password FROM Customers
                              WHERE surname = ?');
  $statement->execute([$surname]);
  $users = $statement->fetchAll(PDO::FETCH_CLASS, 'Customer');
  return $users;
}

function addUser($user)
{
  global $pdo;
  $statement = $pdo->prepare('INSERT INTO Customer
    (givenname, surname, address, email, password) VALUES (?,?,?,?,?)');
  $statement->execute([$user->givenname,
                      $user->surname,
                      $user->address,
                      $user->email,
                      $user->password]);
}

function saveOrder($basket, $order)
{
    global $pdo;
    $statement = $pdo->prepare("INSERT INTO Orders (total_price) VALUES (?)");
    $statement->execute([$order->total_price]);

    $order_id = $pdo->lastInsertId();

    foreach ($basket as $recipe) {
        $statement = $pdo->prepare("INSERT INTO Order_items (order_id, recipe_id, price) VALUES (?, ?, ?)");
        $statement->execute([$order_id, $recipe->id, $recipe->price]);
    }
}

 

        
        







function addRecipe($recipe)
{
  global $pdo;
  $statement = $pdo->prepare('INSERT INTO Recipe
    (title, date_added, ingredients, cooking_time, dietary_restrictions, keywords, recipe_text, price) VALUES (?,?,?,?,?,?,?,?)');
  $statement->execute([$recipe->title, $recipe->date_added, $recipe->ingredients, $recipe->cooking_time, $recipe->dietary_restrictions, $recipe->keywords, $recipe->recipe_text, $recipe->price,]);
}


function userlogin($email, $password)
{
  global $pdo;
  $statement = $pdo->prepare("SELECT * FROM Customer WHERE email = ? AND password = ?");
  $statement->execute([$email, $password]);
  $customer = $statement->fetch(PDO::FETCH_OBJ);
  return $customer;
}


function adminlogin($adminemail, $adminpassword)
{
  global $pdo;
  $statement = $pdo->prepare("SELECT * FROM Admin WHERE adminemail = ? AND adminpassword = ?");
  $statement->execute([$adminemail, $adminpassword]);
  $admin = $statement->fetch(PDO::FETCH_OBJ);
  return $admin;
}








?>
