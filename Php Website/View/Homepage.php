<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Recipes for Disaster</title>
    <link rel="stylesheet" href="../View/Homepage-style.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="../View/javascript.js"></script>
</head>

<body>


    <header>
        <a href='../Controller/HomepageController.php'>Recipes For Disaster</a>
        <div class="toggle" onclick="toggleMenu();">
            <img alt="svgImg"
                src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHg9IjBweCIgeT0iMHB4Igp3aWR0aD0iMzIiIGhlaWdodD0iMzIiCnZpZXdCb3g9IjAgMCAxNiAxNiI+CjxwYXRoIGQ9Ik0gMSAyIEwgMSAzIEwgMTQgMyBMIDE0IDIgWiBNIDEgNyBMIDEgOCBMIDE0IDggTCAxNCA3IFogTSAxIDEyIEwgMSAxMyBMIDE0IDEzIEwgMTQgMTIgWiI+PC9wYXRoPgo8L3N2Zz4=" />
        </div>
        <nav class="dropdown">
            <form id="searchForm" action="../Controller/HomepageController.php" method="POST">
                <label for="searchType">Search:</label>
                <select name="searchType" id="searchType">
                    <option value="name">Recipe name</option>
                    <option value="price">Price</option>
                    <option value="cookingtime">Cooking time</option>
                </select>
                <input name="search" id="searchInput" />
                <input type="submit" value="Search" /><br />
            </form>


            <a href="../Controller/HomepageController.php"><input type="submit" value="Clear Search"></a>

            <div class="nav_buttons">
                <a href="../Controller/CartviewController.php"><input type="submit" value="Show Cart"></a>
                <span>
                    <?= count($basket) ?> Cart
                </span>

            </div>
        </nav>
    </header>





    <div class="card-container">
        <?php foreach ($results as $recipe): ?>
            <div class="card-image">
                <img src="../View/Images/<?= $recipe->id ?>.jpg" alt="<?= $recipe->title ?>">
            </div>
            <div class="card-content">
                <h2 class="card-title">
                    <?= $recipe->title ?>
                </h2>
                <p class="card-price">Price:
                    <?= $recipe->price ?>
                </p>
                <p class="card-time">Cooking Time:
                    <?= $recipe->cooking_time ?>
                </p>
                <p class="card-restrictions">Dietary restrictions:
                    <?= $recipe->dietary_restrictions ?>
                </p>
                <div class="card-buttons">
                    <a href="../View/Recipe<?= $recipe->id ?>.php" class="button view-more">View More</a>
                    <span>&nbsp;</span>
                    <a href="../Controller/HomepageController.php?invite=<?= $recipe->id ?>" class="button">Add to
                        Cart</a>
                </div>
            </div>
        <?php endforeach ?>
    </div>
</body>

</html>

?>