<!DOCTYPE html>
<html lang="en">
<?php require_once("../Controller/admin_controller.php"); ?>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Recipes for Disaster</title>
    <link rel="stylesheet" href="../View/addUser-style.css">

</head>
  <body>
  <header>
        <a>Admin Log in</a>
    </header>

    <form action="../Controller/admin_controller.php" method="POST">
      Email: <input name="email"/><br/>
      Password: <input type="password" name="password"/><br/>
      <input type="submit"/>
    </form>
    <?php if (isset($error)): ?>
      <div class="error-message"><?=$error?></div>
    <?php endif; ?>
    <a href="../Controller/login_controller.php"><button class="button">Go back</button></a>
  </body>
</html>
