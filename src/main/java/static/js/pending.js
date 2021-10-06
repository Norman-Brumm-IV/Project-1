let url = 'http://localhost:7000/pending';
let xhr = new XMLHttpRequest();
xhr.onreadystatechange = stateChange;
let table = document.getElementById("pendingTable");

function stateChange(){
    if(xhr.readyState === 4 && xhr.status === 200) {
        loaded();
    }

}

function first(){
    xhr.open("POST" , url);
    xhr.send(loc);
}
function loaded(){
    let returned = (xhr.response);
    let jsonified = JSON.parse(returned);
    

    for(var i =0; i<jsonified.length;i++) {
        var row = table.insertRow();
        row.insertCell().innerHTML = jsonified[i].tNumber;
        row.insertCell().innerHTML = jsonified[i].cid;
        row.insertCell().innerHTML = jsonified[i].reason;
        row.insertCell().innerHTML = "Pending";
        row.insertCell().innerHTML = jsonified[i].amount;
    }
}

window.onload = first;