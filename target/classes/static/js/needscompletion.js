let url = 'http://localhost:7000/needscompletion';
let xhr = new XMLHttpRequest(); // ready state 0
xhr.onreadystatechange = stateChange;
let table = document.getElementsByTagName('table');

document.getElementById('needscompletiontable').onclick = function(e) {
    if(e.target.parentNode.id === 'clickable') {
        let row = e.target.parentNode;
        let fillable = document.getElementById('id');
        fillable.value = row.cells[0].innerHTML;
        }
}


function stateChange(){
    if(xhr.readyState === 4 && xhr.status === 200) {
        loaded();
    }

}

function loaded(){
    let returned = (xhr.response);
    let jsonified = JSON.parse(returned);

    for(var i =0; i<jsonified.length;i++) {
        var row = table[0].insertRow();
        row.id = "clickable"
        row.insertCell().innerHTML = jsonified[i].tNumber;
        row.insertCell().innerHTML = jsonified[i].cid;
        row.insertCell().innerHTML = jsonified[i].reason;
        row.insertCell().innerHTML = jsonified[i].amount;
    }
}

function first(){
    xhr.open("GET" , url);
    xhr.send();
}

window.onload = first;

function updateTicket(){
    url = 'http://localhost:7000/updateTicket';
    // Now let's open our HTTP request. We need to specify an HTTP verb and the URL.
    xhr.open('POST', url); //readyState is 1

    var id = document.getElementById("id").value;
    var approved = document.getElementById("approved").value;
    var reason = document.getElementById("reason").value;

    var sendBack = JSON.stringify({"id":id, "approved":approved, "reason":reason, "approver":loc})
    // Now let's send our HTTP request.
    var testing = xhr.send(sendBack); //readyState is 2
}

document.getElementById("logButton").onclick = updateTicket;
