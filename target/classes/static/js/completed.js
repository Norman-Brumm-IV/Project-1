let url = 'http://localhost:7000/completed';
let xhr = new XMLHttpRequest();
xhr.onreadystatechange = stateChange;
let table = document.getElementById("completedTable");

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
        row.insertCell().innerHTML = jsonified[i].amount;
        row.insertCell().innerHTML = jsonified[i].approved;
        row.insertCell().innerHTML = jsonified[i].reviewer;
        row.insertCell().innerHTML = jsonified[i].adReason;
    }
}

window.onload = first;