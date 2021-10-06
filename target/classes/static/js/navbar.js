let checkURL = 'http://localhost:7000/authorized';
let xhrRequest = new XMLHttpRequest();
let loc = window.location.href;

checkCreds();

function logout(){
    window.location.href = "MarSaraPortal.html";
}

function checkCreds() {
    xhrRequest.open('POST', checkURL);
    xhrRequest.onreadystatechange = function (){

        if(xhrRequest.readyState === XMLHttpRequest.DONE) {
            if(xhrRequest.status === 403) {
                window.location.replace("MarSaraPortal.html");
            }
            if(xhrRequest.status === 200) {
                createNavBar(xhrRequest.response);
            }
        }
    }
    xhrRequest.send(loc);
}


/*
    This is meant to act like a navbar for every page
*/
let index = loc.indexOf("?");
const unap = loc.substr(index);
function createNavBar(priority) {

    let new_div = document.getElementById("navbar");
    new_div.id = "navbar";

    let new_b = document.createElement('b');
    new_div.appendChild(new_b);

    let new_link_a = document.createElement("a")
    new_link_a.href = "newrequest.html" + unap;
    new_link_a.innerHTML = "New Request";

    let new_link_b = document.createElement("a")
    new_link_b.href = "pending.html" + unap;
    new_link_b.innerHTML = "Pending";

    let new_link_c = document.createElement("a")
    new_link_c.href = "NeedsCompletion.html" + unap;
    new_link_c.innerHTML = "Needs Completion";

    let new_link_d = document.createElement("a");
    new_link_d.href = "ViewAll.html" + unap;
    new_link_d.innerHTML = "View All";

    let new_link_e = document.createElement("a");
    new_link_e.href = "Statistics.html" + unap;
    new_link_e.innerHTML = "Statistics";

    let new_link_f = document.createElement("a");
    new_link_f.href = "Completed.html" + unap;
    new_link_f.innerHTML = "Completed";

    let final_link = document.createElement("a")
    final_link.href= "MarSaraPortal.html";
    final_link.innerHTML = "Log Out";
    final_link.addEventListener("click", logout)

    new_b.appendChild(new_link_a);
    new_b.appendChild(new_link_b);
    new_b.appendChild(new_link_f);
    if(priority<40) {
        new_b.appendChild(new_link_c);
    }
    if(priority<26) {
        new_b.appendChild(new_link_d);
        new_b.appendChild(new_link_e);
    }
    new_b.appendChild(final_link);
}