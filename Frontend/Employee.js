const url = "http://localhost:8000";
let addReimbBtn = document.getElementById("addReimButton");
let reimbTable = document.getElementById("reimbRequestTable");
let logoutBtn = document.getElementById("logoutBtn");

logoutBtn.addEventListener("click", logoutFunc);

async function logoutFunc(){
    
  
    let response = await fetch(
      url+"logout",
      {
        method : "POST",   
        credentials: "include"
      }
    );
  
    if(response.status===200){
        sessionStorage.clear();
        window.location.replace(url + "login.html");
    }else{
      console.log("Logout unsuccessful");
    }
}