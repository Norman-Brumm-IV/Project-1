let url = 'http://localhost:7000/all';
let xhr = new XMLHttpRequest(); // ready state 0
xhr.onreadystatechange = stateChange;
let table = document.getElementById("table");

function stateChange(){
    if(xhr.readyState === 4 && xhr.status === 200) {
        loaded();
    }

}

function loaded(){
    let returned = (xhr.response);
    let jsonified = JSON.parse(returned);
    console.log(jsonified)

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

function first(){
    xhr.open("GET" , url);
    xhr.send();
}

window.onload = first;