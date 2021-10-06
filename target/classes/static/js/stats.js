let url = 'http://localhost:7000/stats/';
window.onload = orderedChaos;


//the name is just the first thing that came to mind
function orderedChaos(){
    let aver = document.getElementById("average");
    let mx = document.getElementById("max");
    let mn = document.getElementById('min');
    let adp = document.getElementById("adp");
    
    get_set_data(aver, "average");
    get_set_data(mx, "max");
    get_set_data(mn, "min");
    get_set_data(adp, "answered");
}

function get_set_data(obj, urly){
    let xhr = new XMLHttpRequest();
    xhr.open("GET" , url + urly);
    xhr.onreadystatechange = function (){
        // I dont know why, but I prefer splitting up the readyState and status
        if(xhr.readyState === 4) {
            if(xhr.status === 200){
                var thing = xhr.response
                addRows(thing, obj);
            }
        }
    }
        xhr.send();
}

function addRows(returned, table){
    console.log("Response:" + returned);
    let jsonified = JSON.parse(returned); 



    for(var i =0; i<jsonified.length;i++) { // this sets the rows
        var row = table.insertRow();
        var keyArray = Object.keys(jsonified[i]);
        var numOfColumns = keyArray.length;
        
        for(var j=0;j<numOfColumns;j++){ // this will set each column
            let test = keyArray[j];
            row.insertCell().innerHTML = jsonified[i][test];
        }

        // row.insertCell().innerHTML = jsonified[i].tNumber;
        // row.insertCell().innerHTML = jsonified[i].cid;
        // row.insertCell().innerHTML = jsonified[i].reason;
    }
}

function createTable(name, parent){
    let table = document.createElement('table');
    table.id = name;
    parent.appendChild(table);
    return table;
}

function fillTableAverage(returned){    
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

