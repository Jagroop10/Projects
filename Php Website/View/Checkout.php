<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Recipes for Disaster</title>
    <link rel="stylesheet" href="../View/Homepage-style.css">

</head>

<body>

    <header>
        <a>Recipes for Disaster</a>
        <a href="../Controller/finish_controller.php"><input type="submit" value="Pay and Finish"></a>
    </header>

    <body>
        <div class="carttitle">
            <h1>Cart</h1>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Recipe Name</th>
                    <th>Price</th>
                    <th>Edit</th>
                </tr>
            </thead>
            <tbody>
                <?php foreach ($basket as $recipe): ?>
                    <tr>
                        <td>
                            <?= $recipe->title ?>
                        </td>
                        <td>
                            <?= $recipe->price ?>
                        </td>
                        <td>
                            <form action="" method="POST">
                                <input type="hidden" name="recipe_id" value="<?= $recipe->id ?>">
                                <input type="submit" name="RemoveRecipe" value="Remove Recipe">
                            </form>

                        </td>


                    </tr>
                <?php endforeach ?>
                <tr>
                    <th>Total Price</th>
                    <th>
                        <?= $total_price ?>
                    </th>
                </tr>
            </tbody>
        </table>
        <div class="returnhome">
            <a href="../Controller/recipeadd_controller.php"><input type="submit" value="Add more Recipes"></a>
    </body>

</html>