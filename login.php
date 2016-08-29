<?php
$servername = "localhost";
$username = "root";
$password = "password";   // Write your password here
$dbname = "tcs_project";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

 
$username = $_POST['username'];
$password = $_POST['password'];
 
$sql = "SELECT * FROM `User` WHERE `Name`='$username' AND `Password`='$password' ";
$res = $conn->query($sql);
if ($res->num_rows > 0) {
	echo 'success';
}else{
	echo 'failure';
}

$conn->close();
?> 



