let loginBtn = document.getElementById("loginBtn");
let userName = document.getElementById("username");
let passWord = document.getElementById("password");


if (sessionStorage.getItem("userSession") != null){
  let user = JSON.parse(sessionStorage.getItem("userSession"));
  if(user.ersUserRoleId === 1){
      window.location.replace(url + "Employee.html");
  } else {
    window.location.replace(url + "Manager.html");
  }
}  

const url = "http://localhost:8080/";


loginBtn.addEventListener("click", loginFunc);


async function loginFunc(){
  console.log("button worked");
    let user = {
      ersUsername: userName.value,
      ersPassword: passWord.value
    }
    console.log(user);
    let response = await fetch(
      url+"login",
      {
        // mode: "no-cors",
        method : "POST",
        body : JSON.stringify(user),
        credentials: "include"
      }
    );
    console.log(response);
  
    if(response.status===200){
      let userCredentials = await response.json();
        sessionStorage.setItem("userSession", userCredentials);

        if (userCredentials.ersUserRoleId === 1) {
          console.log(userCredentials);

            window.location.replace(url + "Employee.html");
        } else {
            window.location.replace(url + "Manager.html");
        }
    }else{
      console.log("Login unsuccessful. Returned status code of:"+response.status);
    }
}