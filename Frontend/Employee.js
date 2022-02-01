const url = "http://localhost:8000/";

if (sessionStorage.getItem("userSession") == null ){
    window.location.replace(url + "Login.html");
  }


let addReimbBtn = document.getElementById("addReimbButton");
let reimbTable = document.getElementById("reimbRequestTable");
let logoutBtn = document.getElementById("logoutBtn");

logoutBtn.addEventListener("click", logoutFunc);
addReimbBtn.addEventListener("click", addReimb);

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
        window.location.replace(url + "Login.html");
    }else{
      console.log("Logout unsuccessful");
    }
}

async function addReimb(){
//temp code just to get authorization in testing
let user = {ersUsername:"ChristmasCarol", ersPassword:"PassSw0Rd555"}

let response2 = await fetch(url+"login",
    {
        method:"POST",
        body : JSON.stringify(user),
        credentials: "include"
    }
);

if(response2.status===200){
    console.log("Login successful");
}else{
    console.log("Login unsuccessful. Returned status cose of: "+response2.status);
}
    let reimbTypeId = 0;

    if (document.querySelector("#reimbType").value=="Lodging"){
        reimbTypeId = 1;
    } else if (document.querySelector("#reimbType").value=="Travel"){
        reimbTypeId = 2;
    } else if (document.querySelector("#reimbType").value=="Food"){
        reimbTypeId = 3;
    } else if (document.querySelector("#reimbType").value=="Other"){
        reimbTypeId = 4;
    } 

    let status = {
        amount: document.getElementById("reimbAmount").value,
        description: document.getElementById("reimbDescription").value,
        type: reimbTypeId
    }

    let response = await fetch(url + "reimb",{
        method: "POST",
        body: JSON.stringify(status),
        credentials: "include"
    })

    if(response.status === 201){
        getAllReimbs();
    }else{
        console.log("Unsuccessful" +response.status);
    }
}

// View current employee's own requests

async function getAllReimbs(){
    let response = await fetch(url + "reimb", {
        credentials: "include"
    })

    if(response.status === 200){
        let reimbs = await response.json();
        populateReimbs(reimbs);
    } else{
        console.log("Unsuccessful" + response.status);
    }
}

function populateReimbs(reimbs){
    reimbTable.innerHTML = "";
    for(let reimb of reimbs){
        let row = document.createElement("tr");
        for(let data in reimb){
            let reimbData = reimb[data]; 
            // create new cell with reimbData
            let td = document.createElement("td");
            td.innerHTML = reimbData;
            row.appendChild(td);
        }
        reimbTable.appendChild(row);
    }
}

