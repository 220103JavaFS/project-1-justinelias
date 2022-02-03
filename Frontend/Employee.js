const url = "http://localhost:8080/";

// if (sessionStorage.getItem("userSession") == null ){
//     window.location.replace(url + "Login.html");
//   }


let addReimbBtn = document.getElementById("addReimbButton");
let reimbTable = document.getElementById("reimbTbl");
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
console.log("button worked");
// //temp code just to get authorization in testing
// let user = {ersUsername:"ChristmasCarol", ersPassword:"PassSw0Rd555"}

// let response2 = await fetch(url+"login",
//     {
//         method:"POST",
//         body : JSON.stringify(user),
//         credentials: "include"
//     }
// );

// if(response2.status===200){
//     console.log("Login successful");
// }else{
//     console.log("Login unsuccessful. Returned status cose of: "+response2.status);
// }
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
        reimbType: {reimbTypeId:reimbTypeNumber}
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
console.log("got to allReimbs Func")

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
console.log("got to populate func");
let userCredentials = JSON.parse(sessionStorage.getItem('userSession'));
console.log(userCredentials);
    reimbTable.innerHTML = "";
    for(let reimb of reimbs){
//   reimb["reimbAuthor"]["userId"]; check user id in current reimb against the current session's user id, if they match
    console.log(reimb["reimbAuthor"]["ersUsersId"]);
    if(reimb["reimbAuthor"]["ersUsersId"]===userCredentials["ersUsersId"]){
     let row = document.createElement("tr");
    //        let myReimb = []
            console.log(reimb);
//            for(let data in reimb){
//                let reimbData = reimb[data];

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



