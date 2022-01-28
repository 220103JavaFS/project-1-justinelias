let loginBtn = document.getElementById("loginBtn");


const userName = "AliceInWL"
const passWord = "$2a$10$aQWQsRhNqAlIvG7M4lliouKpzJlZmW3y5Siwx762NCC5DgzfbtSES"
const url = "http:localhost:8080"


loginBtn.addEventListener("click", loginFunc);


async function loginFunc(){
    let user = {
      username: userName,
      password: passWord
    }
  
    let response = await fetch(
      url+"login",
      {
        method : "POST",
        body : JSON.stringify(user),
        credentials: "include"
      }
    );
  
    if(response.status===200){
      loginBtn.innerText = ""; 
    }else{
      console.log("Login unsuccessful. Returned status code of:"+response.status);
    }
}