function addItem(id, model){
	var key = document.getElementById(id).innerHTML;
	document.getElementById("orderResponse").innerHTML=key;
	var url = "/addOrder?item=" + key + "&model=" + model + "&partKey=" + document.getElementById("itemKey").innerHTML;
	var response = sendAjax(url);
	document.getElementById("orderResponse").innerHTML="Item Added, press close to add more parts.";
	//document.getElementById("orderResponse").innerHTML=response;
	document.getElementById("addItemBtn").style.visibility="hidden";
	document.getElementById("dAddItemBtn").style.visibility="hidden";
	//$('addItemBtn').prop('disabled', true);

}

function getItem(id, p, key){
	var part = document.getElementById(id).innerHTML;
	var partNum = document.getElementById(p).innerHTML;
	document.getElementById("addItemBtn").style.visibility="visible";
	document.getElementById("dAddItemBtn").style.visibility="visible";
	var item = partNum + " " + part;
	document.getElementById("orderResponse").innerHTML=item;
	document.getElementById("itemKey").innerHTML=key;
}

function completeOrder(){
	var form = document.getElementById("completeOrder");
	if (form.checkValidity()){
		form.submit();
	}
	else {
		var vMsg="Invalid email please re-enter"
		document.getElementById("validationMsg").innerHTML=vMsg;
	}
		
}

function removeItems(){
	document.getElementById("removeItems").submit();
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
        else if (ajax.readyState == 4 && ajax.status != 200){
        	document.getElementById("orderResponse").innerHTML="An error occurred, try again";
        }
      };
      ajax.send();
}

