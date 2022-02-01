let loginBtn = document.getElementById("loginBtn");
let userName = document.getElementById("username");
let passWord = document.getElementById("password");

// const userName = "AliceInWL"
// const passWord = "$2a$10$aQWQsRhNqAlIvG7M4lliouKpzJlZmW3y5Siwx762NCC5DgzfbtSES"
const url = "http:localhost:8080/"


loginBtn.addEventListener("click", loginFunc);


async function loginFunc(){
  console.log("button worked");
    let user = {
      username: userName.value,
      password: passWord.value
    }
  
    let response = await fetch(
      url+"login",
      {
        mode: "no-cors",
        method : "POST",
        body : JSON.stringify(user),
        credentials: "include"
      }
    );
  
    if(response.status===200){
      let userCredentials = await response.json();
        sessionStorage.setItem("userSession", userCredentials);

        if (userCredentials.userRoleId === 1) {
          console.log(userCredentials);

            window.location.replace(url + "Employee.html");
        } else {
            window.location.replace(url + "Manager.html");
        }
    }else{
      console.log("Login unsuccessful. Returned status code of:"+response.status);
    }
}