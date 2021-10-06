let url = 'http://localhost:7000/login';
let xhr = new XMLHttpRequest(); // ready state 0

xhr.onreadystatechange = stateChange;

function login(){
        xhr.open('POST', url); //readyState is 1

        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;

        var sendBack = JSON.stringify({"username":username, "password":password})

        var testing = xhr.send(sendBack); //readyState is 2
}

document.getElementById("logButton").onclick = login;

function stateChange(){
    if(xhr.readyState === 4 && xhr.status === 200) {
        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;
        var tempVar = xhr.response;
        window.location.href = "index.html?un=" + username + "&pw=" + password;
    }
}

function setCookie(cname, cvalue, exdays) {
    const d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    let expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
  }


  // cheater buttons below here
  console.log("started the cheater stuff")
  let username;
  let password;

  document.getElementById("thirteen").onclick = thirteen
  function thirteen(){
      username = '13'
      password = 'plainText'
      cheaterLogin()
  }

  document.getElementById("twentyfive").onclick = twentyfive
  function twentyfive(){
    username = '666666'
      password = 'iWillBeEmper0r'
      cheaterLogin()
  }

  document.getElementById("twentysix").onclick = twentysix
  function twentysix(){
      username = '855200'
      password = 'SupplyW1nsW@rs'
      cheaterLogin()
  }

  document.getElementById("fifty").onclick = fifty
  function fifty(){
      username = '825275'
      password = 'R@yn0rIsGreat1'
      cheaterLogin()
  }

  function cheaterLogin(){
    window.location.href = "index.html?un=" + username + "&pw=" + password;
}