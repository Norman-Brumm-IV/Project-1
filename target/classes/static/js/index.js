let url = 'http://localhost:7000/getNameOfUser';
let xhr = new XMLHttpRequest(); // ready state 0
xhr.onreadystatechange = stateChange;
let welcome = document.getElementById('usersName');

welcome.innerText = "N/A"

function stateChange(){
    if(xhr.readyState === 4 && xhr.status === 200) {
        loaded();
    }
}

function loaded(){
    let returned = (xhr.response);
    welcome.innerText = returned;
}

xhr.open("POST" , url);
xhr.send(loc);