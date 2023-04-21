<!DOCTYPE html>
<html lang="en">
<?php require_once("../Controller/login_controller.php"); ?>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Recipes for Disaster</title>
    <link rel="stylesheet" href="../View/addUser-style.css">

</head>
  <body>
  <header>
        <a >Please Log in or Register to order</a>
    </header>
    <form action="../Controller/login_controller.php" method="POST">
      Email: <input name="email"/><br/>
      Password: <input type="password" name="password"/><br/>
      <input type="submit" value="Log in"/>
    </form>
    <?php if (isset($error)): ?>
      <div class="error-message"><?=$error?></div>
    <?php endif; ?>
    <a href="../Controller/addUser_controller.php"><button class="button">Register</button></a>
    <a href="../Controller/admin_controller.php"><button class="button">Admin</button></a>
    <a href="../Controller/HomepageController.php"><button class="button">Go Back</button></a>
  </body>
</html>
