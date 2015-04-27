function addItem(id){
	var key = document.getElementById(id).innerHTML;
	var url = "/addOrder?item=" + key;
	var response = sendAjax(url);
	var completed = confirm(response);
	if (completed == true){
		completeOrder();
	}
	else {
		alert("Continue with your Order!");
	}	
}

function completeOrder(){
	var url = "/completeOrder?key=P@$$w0rd";
	var response = sendAjax(url);
	alert(response);
}

function sendAjax(url){
	var ajax;

    if (window.XMLHttpRequest){
      ajax = new XMLHttpRequest();
    }
    else {
      ajax = new ActiveXObject("Microsoft.XMLHTTP");
    }
      ajax.open("GET", url, true);
      ajax.onreadystatechange = function (){
        if (ajax.readyState == 4 && ajax.status == 200){
          var response = ajax.responseText;
          return response;
        }
      };
      ajax.send();
}
