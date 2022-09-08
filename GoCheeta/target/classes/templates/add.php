<?php
extract($_POST);
if(isset($_POST['usernameSend'])){
    $sql = "INSERT INTO asdTest (username) VALUES ('$usernameSend')";
    $result = mysqli_query($con,$sql);
    if($result){
       $_SESSION['message'] = "Data Inserted Successfully";
    }
    else{
        echo "Error";
    }
}

?>