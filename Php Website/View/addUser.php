<!DOCTYPE html>
<html lang="en">
<?php require_once("../Controller/addUser_controller.php"); ?>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Recipes for Disaster</title>
  <link rel="stylesheet" href="../View/addUser-style.css">

</head>

<body>
  <header>
    <a href='../Controller/HomepageController.php'>Recipes For Disaster</a>
  </header>
  <?php if ($status): ?>
    <p style="color: green">
      <?= $status ?>
    </p>
  <?php endif ?>
  <form action="../Controller/addUser_controller.php" method="POST">
    Surname: <input name="surname" /><br />
    Givenname: <input name="givenname" /><br />
    Order Address: <input name="address" /><br />
    Email: <input name="email" /><br />
    Password: <input name="password" /><br />
    <input type="submit" />
  </form>
  <a href="../Controller/login_controller.php"><button class="button">Log-in</button></a>

</body>

</html>