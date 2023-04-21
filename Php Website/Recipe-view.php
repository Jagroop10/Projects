<!doctype html>
<html>
  <head>
    <title>Recipe List</title>
  </head>
  <body>
    <table>
      <thead>
        <tr>
          <th>Recipe ID</th>
          <th>Recipe Title</th>
          <th>Ingredients</th>
          <th>Cooking Time</th>
          <th>Dietary Restrictions</th>
          <th>Keywords</th>
          <th>Rating</th>
          <th>Recipe Text</th>        
        </tr>
      </thead>
      <tbody>
        <?php foreach ($results as $recipe):?>
        <tr>
          <td><?=$recipe->id ?></td>
          <td><?=$recipe->title?></td>
          <td><?= implode(", ", $recipe->ingredients) ?></td>
          <td><?=$recipe->cookingTime ?></td>
          <td><?= implode(", ", $recipe->dietaryRestrictions) ?></td>
          <td><?= implode(", ", $recipe->keywords) ?></td>
          <td><?=$recipe->rating ?></td>
          <td><?=$recipe->recipeText ?></td>
        </tr>
        <?php endforeach ?>
      </tbody>
    </table>
  </body>
</html>







        
        