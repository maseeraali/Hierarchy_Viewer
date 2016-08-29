 <?php
$servername = "localhost";
$username = "root";
$password = "password";    // Write your password here
$dbname = "tcs_project";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT `Position`,`Reports To Position`,`Name of Incumbent` FROM `Hierarchy` ";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
	$output[]=$row;
    }
	print(json_encode($output));

} else {
    echo "0 results";
}
$conn->close();
?> 



