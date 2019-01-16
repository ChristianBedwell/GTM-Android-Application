<?php
    $con=mysqli_connect("localhost","root","","androidloginsystem");
    
    $firstname = $_POST["firstname"] ?? "";
    $lastname = $_POST["lastname"] ?? "";
    $email = $_POST["email"] ?? "";
    $password = $_POST["password"] ?? "";
    
    $statement = mysqli_prepare($con, "INSERT INTO User (firstname, lastname, email, password) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "siss", $firstname, $lastname, $email, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_close($statement);
    
    mysqli_close($con);
?>