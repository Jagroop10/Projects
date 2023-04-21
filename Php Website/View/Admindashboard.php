<!DOCTYPE html>
<html lang="en">
<?php require_once("../Controller/addrecipe_controller.php"); ?>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Recipes for Disaster</title>
    <link rel="stylesheet" href="../View/addUser-style.css">

</head>
  <body>
    <?php if ($status): ?>
      <p style="color: green"><?=$status?></p>
    <?php endif ?>
    <form action="../Controller/addrecipe_controller.php" method="POST">
      Recipe Name: <input name="title"/><br/>
      Date Added: <input name="date_added"/><br/>
      Ingredients: <input name="ingredients"/><br/>
      Cooking Time: <input name="cooking_time"/><br/>
      Dietary Restrictions: <input name="dietary_restrictions"/><br/>
      Keywords: <input name="keywords"/><br/>
      Recipe Text: <input name="recipe_text"/><br/>
      Price: <input name="price"/><br/>
      <input type="submit" />
      
    </form>
    <a href="../Controller/admin_controller.php"><button class="button">Go back</button></a>

  </body>
</html>

