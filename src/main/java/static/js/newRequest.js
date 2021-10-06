let url = 'http://localhost:7000/newTicket';
let xhr = new XMLHttpRequest(); // ready state 0
xhr.onreadystatechange = stateChange;
let welcome = document.getElementById('usersName');

function stateChange(){
    if(xhr.readyState === 4) {
        console.log("xhr.status()" + xhr.status)
        console.log()
        if(xhr.status === 201) {
            window.location.href = "index.html" + unap
        } else if(xhr.status === 400){
            window.alert(xhr.response)
        }
    }
}

function loaded(){
    let returned = (xhr.response);
}


document.getElementById("button").onclick = sendInfo

function sendInfo(){
    xhr.open("POST" , url);
    var amount = document.getElementById("amount").value;
    var reason = document.getElementById("reason").value;
    var sendBack = JSON.stringify({"amount":amount, "reason":reason, "requestor":loc})
    xhr.send(sendBack);
}