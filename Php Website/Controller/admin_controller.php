<?php
  session_start();
  require_once ("../Model/Admin.php");
  require_once ("../Model/db.php");

  if(isset($_POST['email']) && isset($_POST['password'])) {
    $adminemail = $_POST['email'];
    $adminpassword = $_POST['password'];

    $admin = adminlogin($adminemail, $adminpassword);

    if($admin) {
        $_SESSION['logged_in'] = true;

        header("Location: ../View/Admindashboard.php");
        exit();
    } else {
        $error = "Invalid email or password. Please try again.";
    }
}

require_once("../View/adminlogin.php");

?>