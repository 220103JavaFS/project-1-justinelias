let loginBtn = document.getElementById("loginBtn");
let userName = document.getElementById("username");
let passWord = document.getElementById("password");


//if (sessionStorage.getItem("userSession") != null){
//  let user = JSON.parse(sessionStorage.getItem("userSession"));
//  if(user.ersUserRoleId === 1){
//      window.location.replace(url + "Employee.html");
//  } else {
//    window.location.replace(url + "Manager.html");
//  }
//}

const url = "http://localhost:8080/";


loginBtn.addEventListener("click", loginFunc);


async function loginFunc(){
  console.log("button worked");
    let user = {
      ersUsername: userName.value,
      ersPassword: passWord.value
    }
    let response = await fetch(
      url+"login",
      {
        //Origin : "http://localhost:8080",
        method : "POST",
        body : JSON.stringify(user),
        credentials: "include"

      }
    );

  
    if(response.status===200){
      let userCredentials = await response.json();
        sessionStorage.setItem("userSession", JSON.stringify(userCredentials));

        if (userCredentials.userRole.ersUserRoleId === 1) {
          console.log(userCredentials);

            window.location.replace(url + "Employee.html");
        } else {
            window.location.replace(url + "Manager.html");
        }
    }else{
      alert("Not so fast, my friend! Check those credentials. Returned status code of: "+ response.status);
    }
}