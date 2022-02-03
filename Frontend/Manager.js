let reimbTbl = document.getElementById("reimbTbl");
let toggleBtn = document.getElementById("toggleBtn");
let logoutBtn = document.getElementById("logoutBtn");
let reimbTblLength = document.getElementById("reimbHeader").cells.length;
let spinner = document.getElementById("spinner");
let modalBody = document.getElementById("modal-body");
let statusBtn = document.getElementById("statusBtn");
let greeting = document.getElementById("greeting");
let recptImg = document.getElementById("recptImg");

//get indices of all columns
let idColIndx = document.getElementById("idCol").cellIndex;
let amountColIndx = document.getElementById("amountCol").cellIndex;
let typeColIndx = document.getElementById("typeCol").cellIndex;
let descColIndx = document.getElementById("descCol").cellIndex;
let authorSubmittedColIndx = document.getElementById("authorSubmittedCol").cellIndex;
let authorFirstNameColIndx = document.getElementById("authorFirstNameCol").cellIndex;
let authorLastNameColIndx = document.getElementById("authorLastNameCol").cellIndex;
let authorEmailColIndx = document.getElementById("authorEmailCol").cellIndex;
let resolverResolvedColIndx = document.getElementById("resolverResolvedCol").cellIndex;
let resolverFirstNameColIndx = document.getElementById("resolverFirstNameCol").cellIndex;
let resolverLastNameColIndx = document.getElementById("resolverLastNameCol").cellIndex;
let resolverEmailColColIndx = document.getElementById("resolverEmailCol").cellIndex;
let statusColIndx = document.getElementById("statusCol").cellIndex;
let recptColIndx = document.getElementById("recptCol").cellIndex;
let user = JSON.parse(sessionStorage.userSession);
let reimbBtn = document.getElementById("reimbBtn");

const url = "http://localhost:8080/";

window.addEventListener("load", getAllReimbs); //load data when page opens
toggleBtn.addEventListener("click", toggleResolvedRows);
logoutBtn.addEventListener("click", logoutFunc);


reimbBtn.addEventListener("click", ()=> {
window.location.replace(url + "Employee.html");
})

greeting.innerText = "Hello "+user["userFirstName"]+" "+user["userLastname"]

async function getAllReimbs(){

    // if(sessionStorage.user["userRole"]<2){
    //     let myModal = new bootstrap.Modal(document.getElementById("myModal"));
    //     modalBody.textContent = "You are not authorized"
    //     myModal.show();
    //     //modalBody.innerText = "You are not authorized";
    // }

    //send GET request for all reimbursements
    let response = await fetch(url+"reimb", {
        credentials:"include"
    });


    //if successful, put data into the table
    if(response.status===200){
        //reimbs contains a list of all Reimbursement objects
        let reimbs = await response.json();
        populateReimbs(reimbs);
        spinner.hidden = true;
    }else{
        console.log("there was an error getting your reimbs.");
    }
}

//function for logout button
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
          console.log("Logged out");
          window.location.replace(url + "Login.html");
      }else{
        console.log("Logout unsuccessful");
      }
}

//function to fill reimb data into the table
function populateReimbs(reimbs){
    //clear any current data
    reimbTbl.innerHTML = "";

    //for each reimbursement in the list, extract neccessary data, and add it to the table
    for(let reimb of reimbs){
        let row = document.createElement("tr"); //table row for new data
        row.id = "reimb"+reimb["reimbId"];

        //initialize cells in row based on number of columns
        for(let col =  0; col < reimbTblLength; col++){
            row.insertCell(-1);
        }

        //highlight row, color depending on status
        switch (reimb["reimbStatus"]["reimbStatus"]) {
            case 'Approved':
                row.classList.add("table-success");
                break;
            case 'Denied':
                row.classList.add("table-danger");
                break;
            case 'Pending':
                row.classList.add("table-light");
                break;
        }

        //insert all needed data into approprite column
        row.cells[idColIndx].style = "text-align: right";
        row.cells[idColIndx].innerText = reimb["reimbId"];
        row.cells[amountColIndx].style = "text-align: right";
        row.cells[amountColIndx].innerText = new Intl.NumberFormat('en-US', {
            style: 'currency',
            currency: 'USD'
            }).format(reimb["reimbAmount"]);
        row.cells[typeColIndx].innerText = reimb["reimbType"]["reimbType"];
        row.cells[descColIndx].innerText = reimb["reimbDescription"];

        if(reimb["reimbReceipt"]){

            let recptAnch = document.createElement("i");
            recptAnch.style = "cursor: pointer; color: royalblue;";
            recptAnch.classList.add("bi-card-image")
            recptAnch.setAttribute("data-bs-toggle", "modal");
            recptAnch.setAttribute("data-bs-target", "#recptModal");
            recptAnch.setAttribute("data-bs-reimbId", reimb["reimbId"]);
            row.cells[recptColIndx].style = "text-align: center; vertical-align: middle;";

            row.cells[recptColIndx].appendChild(recptAnch);
        }

        row.cells[authorSubmittedColIndx].innerText = new Intl.DateTimeFormat('default', {
            year: 'numeric',
            month: 'numeric',
            day: 'numeric',
            hour: 'numeric',
            minute: 'numeric',
            hour12: false
            }).format(new Date(reimb["reimbSubmitted"]));
        row.cells[authorFirstNameColIndx].innerText = reimb["reimbAuthor"]["userFirstName"];
        row.cells[authorLastNameColIndx].innerText = reimb["reimbAuthor"]["userLastname"];
        row.cells[authorEmailColIndx].innerText = reimb["reimbAuthor"]["userEmail"];

        //if not pending, insert resolver info and status
        //otherwise, leave blank and insert status selection buttons
        if(reimb["reimbStatus"]["reimbStatusId"] > 1){
            row.cells[resolverResolvedColIndx].innerText = new Intl.DateTimeFormat('default', {
                year: 'numeric',
                month: 'numeric',
                day: 'numeric',
                hour: 'numeric',
                minute: 'numeric',
                hour12: false
                }).format(new Date(reimb["reimbResolved"]));
            row.cells[resolverFirstNameColIndx].innerText = reimb["reimbResolver"]["userFirstName"];
            row.cells[resolverLastNameColIndx].innerText = reimb["reimbResolver"]["userLastname"];
            row.cells[resolverEmailColColIndx].innerText = reimb["reimbResolver"]["userEmail"];
            row.cells[statusColIndx].innerText = reimb["reimbStatus"]["reimbStatus"];
        }else{
            let selectionBtn = statusBtn.cloneNode(true);
            selectionBtn.hidden = false;
            selectionBtn.firstElementChild.addEventListener("click", function() {updateReimb(reimb, 2);});
            selectionBtn.lastElementChild.addEventListener("click", function() {updateReimb(reimb, 3);});
            row.cells[statusColIndx].classList.add("text-center");
            row.cells[statusColIndx].appendChild(selectionBtn);
        }

        //add row to table
        reimbTbl.appendChild(row);
    } //end of for each entry in list
} //end of function to fill the data

//function to toggle the resolved rows visibility
function toggleResolvedRows(){
    for(let row of reimbTbl.rows){
        if(!(row.classList.contains("table-light"))){
            row.toggleAttribute("hidden");
        }
    }
}

async function updateReimb(reimb, status){
    let row = document.getElementById("reimb"+reimb["reimbId"]); //get row associated with id
    let removedBtn = row.cells[statusColIndx].removeChild(row.cells[statusColIndx].firstChild); //remove buttons
    row.cells[statusColIndx].appendChild(spinner); //insert spinner
    let span = document.createElement("span");
    span.innerText = "Updating...";
    row.cells[statusColIndx].appendChild(span); //insert text
    //update spinner options
    spinner.hidden = false;
    spinner.style.removeProperty("width");
    spinner.style.removeProperty("height");
    spinner.classList.add("spinner-border-sm");

    //update reimb with current time for resolved timestamp and chosen status
    reimb["reimbResolved"] = + new Date();
    reimb["reimbStatus"]["reimbStatusId"] = status;

    //send update request
    let response = await fetch(url+"reimb",
        {
            method:"PUT",
            body : JSON.stringify(reimb),
            credentials: "include"
        }
    );


    if(response.status===202){
        //find row of reimb id, change highlight, change status cell and resolver info
        row.classList.remove("table-light");
        row.cells[statusColIndx].classList.remove("text-center");

        if(status>2){
            row.classList.add("table-danger");
            row.cells[statusColIndx].removeChild(spinner);
            row.cells[statusColIndx].innerHTML = "Denied";
        }else{
            row.classList.add("table-success");
            row.cells[statusColIndx].removeChild(spinner);
            row.cells[statusColIndx].innerHTML = "Approved";
        }

        let user = await response.json();
        row.cells[resolverResolvedColIndx].innerText = new Intl.DateTimeFormat('default', {
            year: 'numeric',
            month: 'numeric',
            day: 'numeric',
            hour: 'numeric',
            minute: 'numeric',
            hour12: false
            }).format(reimb["reimbResolved"]);
        row.cells[resolverFirstNameColIndx].innerText = user["userFirstName"];
        row.cells[resolverLastNameColIndx].innerText = user["userLastname"];
        row.cells[resolverEmailColColIndx].innerText = user["userEmail"];

    }else if(response.status==400){
        let myModal = new bootstrap.Modal(document.getElementById("myModal"));
        myModal.show();
        row.cells[statusColIndx].removeChild(spinner);
        row.cells[statusColIndx].removeChild(span);
        row.cells[statusColIndx].appendChild(removedBtn);
    }else{
        row.cells[statusColIndx].removeChild(spinner);
        row.cells[statusColIndx].removeChild(span);
        row.cells[statusColIndx].appendChild(removedBtn);
    }
}

let input = document.getElementById("file");
let image = document.getElementById("image");

input.addEventListener("change", (e)=>getImg(e));


function getImg(e){
    let reader = new FileReader();
    reader.addEventListener("load", ()=>sendImg(reader.result));
    reader.readAsDataURL(e.target.files[0]);
}

async function sendImg(img){
    console.log("sending img");
    let imgdb = img.split(',')[1];
    console.log(imgdb);
    let r = {reimbAmount:55,
        reimbReceipt:imgdb,
        reimbType:{reimbTypeId:4}
    };
    let response2 = await fetch(url+"reimb",
        {
        method:"POST",
        body : JSON.stringify(r),
        credentials: "include"
        }
    );

    if(response2.status===201){
        console.log("Yay");
    }else{
        console.log("Boooo. Returned status code of: "+response2.status);
    }
}

var recptModal = document.getElementById('recptModal')
recptModal.addEventListener('show.bs.modal', function (event) {
    recptImg.src="";
  // Element that triggered the modal
  let anc = event.relatedTarget
  // Extract info from data-bs-* attributes
  let reimbId = anc.getAttribute('data-bs-reimbId')
  // get image from db
  getImgFromDB(reimbId);
})

async function getImgFromDB(id){
    console.log(url+"reimb/receipt/"+id);
    let response = await fetch(url+"reimb/receipt/"+id,
        {credentials: "include" }
    );

    if(response.status===200){
        console.log("status 200?")
        let receipt = await response.json();
        recptImg.src = "data:image/png;base64,"+receipt;
    }else{
        console.log("Oh no! Returned status code of: "+response.status);
    }
}

