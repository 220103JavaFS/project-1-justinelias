let reimbTbl = document.getElementById("reimbTbl");
let toggleBtn = document.getElementById("toggleBtn");
let reimbTblLength = document.getElementById("reimbHeader").cells.length;
let spinner = document.getElementById("spinner");

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


//create button to select approve or deny
let statusBtn = document.createElement("div");
statusBtn.classList.add("btn-group");

let btnApprove = document.createElement("button");
btnApprove.classList.add("btn", "btn-sm", "btn-success");
btnApprove.type = "button";
btnApprove.innerText = "Approve";
statusBtn.appendChild(btnApprove);

let btnDeny = document.createElement("button");
btnDeny.classList.add("btn", "btn-sm", "btn-danger");
btnDeny.type = "button";
btnDeny.innerText = "Deny";
statusBtn.appendChild(btnDeny);

const url = "http://localhost:8080/";

window.addEventListener("load", getAllReimbs); //load data when page opens
toggleBtn.addEventListener("click", toggleResolvedRows);

async function getAllReimbs(){

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
            selectionBtn.childNodes[0].addEventListener("click", function() {
                updateReimb(reimb["reimbId"], reimb["reimbAuthor"]["ersUsersId"], reimb["reimbSubmitted"], 2);});
            selectionBtn.childNodes[1].addEventListener("click", function() {
                updateReimb(reimb["reimbId"], reimb["reimbAuthor"]["ersUsersId"], reimb["reimbSubmitted"], 3);});
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

async function updateReimb(reimbId, authorId, submissionTime, status){
    //let spinner = document.createElement("div");
    //spinner.classList.add("spinner-border", "text-primary")
    let row = document.getElementById("reimb"+reimbId);
    let removedBtn = row.cells[statusColIndx].removeChild(row.cells[statusColIndx].firstChild) ;
    // while (row.cells[statusColIndx].firstChild) {
    //     row.cells[statusColIndx].removeChild(row.cells[statusColIndx].firstChild);
    // }
    row.cells[statusColIndx].appendChild(spinner);
    let span = document.createElement("span");
    span.innerText = "Updating...";
    row.cells[statusColIndx].appendChild(span);
    spinner.hidden = false;
    spinner.style.removeProperty("width");
    spinner.style.removeProperty("height");
    spinner.classList.add("spinner-border-sm");

    let resolvedTime = + new Date();


    let reimb = {
        reimbId: reimbId,
        reimbSubmitted: submissionTime,
        reimbResolved: resolvedTime,
        reimbAuthor: {ersUsersId: authorId},
        reimbStatus: {reimbStatusId: status}
    }

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
            }).format(new Date(resolvedTime));
        row.cells[resolverFirstNameColIndx].innerText = user["userFirstName"];
        row.cells[resolverLastNameColIndx].innerText = user["userLastname"];
        row.cells[resolverEmailColColIndx].innerText = user["userEmail"];

    }else{
        let myModal = new bootstrap.Modal(document.getElementById("myModal"));
        myModal.show();
        row.cells[statusColIndx].removeChild(spinner);
        row.cells[statusColIndx].removeChild(span);
        row.cells[statusColIndx].appendChild(removedBtn);
    }
}

// function updateProgress(mutationsList, observer){
//     console.log("hit");
// }