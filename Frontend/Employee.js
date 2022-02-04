const url = "http://localhost:8080/";


let addReimbBtn = document.getElementById("addReimbButton");
let reimbTable = document.getElementById("reimbTbl");
let logoutBtn = document.getElementById("logoutBtn");
let greeting = document.getElementById("greeting")
let userCredentials = JSON.parse(sessionStorage.getItem('userSession'));
let spinner = document.getElementById("spinner");
let returnToManagerPageBtn = document.getElementById("returnToManagerPage")
let input = document.getElementById("file");
let receipt;


input.addEventListener("change", (e)=>getImg(e));
logoutBtn.addEventListener("click", logoutFunc);
addReimbBtn.addEventListener("click", addReimb);
returnToManagerPageBtn.addEventListener("click", ()=> {
window.location.replace(url + "Manager.html");
})


if (sessionStorage.getItem("userSession") == null ){
     window.location.replace(url + "Login.html");
   }else{
   greeting.innerText = "Hello "+userCredentials["userFirstName"]+" "+userCredentials["userLastname"];

   getAllReimbs();
   if (userCredentials.userRole.ersUserRoleId === 1) {
   returnToManagerPageBtn.hidden=true;
   }
 }

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
console.log("button worked");

    let reimbTypeNumber = 0;

    if (document.querySelector("#reimbType").value=="Lodging"){
        reimbTypeNumber = 1;
    } else if (document.querySelector("#reimbType").value=="Travel"){
        reimbTypeNumber = 2;
    } else if (document.querySelector("#reimbType").value=="Food"){
        reimbTypeNumber = 3;
    } else if (document.querySelector("#reimbType").value=="Other"){
        reimbTypeNumber = 4;
    } 
        console.log(reimbTypeNumber);
    let status = {
        reimbAmount: document.getElementById("reimbAmount").value,
        reimbDescription: document.getElementById("reimbDescription").value,
        reimbType: {reimbTypeId:reimbTypeNumber},
        reimbReceipt: receipt
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


async function getAllReimbs(){
console.log("got to allReimbs Func")
   spinner.hidden = false;
    let response = await fetch(url + "reimb", {
        credentials: "include"
    })

    if(response.status === 200){
        let reimbs = await response.json();
        populateReimbs(reimbs);
    } else{
        console.log("Unsuccessful" + response.status);
    }
       spinner.hidden = true;
}

function populateReimbs(reimbs){
console.log("got to populate func");
console.log(userCredentials);
    reimbTable.innerHTML = "";
    // View current employee's own requests
    for(let reimb of reimbs){
//   reimb["reimbAuthor"]["userId"]; check user id in current reimb against the current session's user id, if they match
    console.log(reimb["reimbAuthor"]["ersUsersId"]);
    if(reimb["reimbAuthor"]["ersUsersId"]===userCredentials["ersUsersId"]){
     let row = document.createElement("tr");
            console.log(reimb);
                // create new cell with reimbData
                let td = document.createElement("td");
                td.innerText = reimb["reimbId"];
                  row.appendChild(td);


                let td2 = document.createElement("td");
                td2.innerText = reimb["reimbAmount"];
                row.appendChild(td2);

                let td3 = document.createElement("td");
                console.log(reimb["reimbType"]["reimbType"])
                td3.innerText = reimb["reimbType"]["reimbType"];
                                row.appendChild(td3);

                  let td4 = document.createElement("td");
                  td4.innerText = reimb["reimbDescription"];
                  row.appendChild(td4);

                  let td5 = document.createElement("td");
                td5.innerText = reimb["reimbStatus"]["reimbStatus"];
                                  row.appendChild(td5);

//            }
            reimbTable.appendChild(row);
        }else{
        console.log("Users dont match")
        }
      }
    }

    function getImg(e){
        let reader = new FileReader();
        reader.addEventListener("load", ()=> {
        receipt=(reader.result.split(',')[1])});
        console.log(receipt);
        reader.readAsDataURL(e.target.files[0]);

}

