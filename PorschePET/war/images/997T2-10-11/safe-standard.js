var pskru8sid = "OhU3IvH4LKJN";
// safe-standard@gecko.js

var pskru8iso;
try {
	pskru8iso = (opener != null) && (typeof(opener.name) != "unknown") && (opener.pskru8wid != null);
} catch(e) {
	pskru8iso = false;
}
if (pskru8iso) {
	window.pskru8wid = opener.pskru8wid + 1;
	pskru8sid = pskru8sid + "_" + window.pskru8wid;
} else {
	window.pskru8wid = 1;
}
function pskru8n() {
	return (new Date()).getTime();
}
var pskru8s = pskru8n();
function pskru8st(f, t) {
	if ((pskru8n() - pskru8s) < 7200000) {
		return setTimeout(f, t * 1000);
	} else {
		return null;
	}
}
var pskru8ol = false;
function pskru8ow() {
	if (pskru8ol || (1 == 1)) {
		var pswo = "menubar=0,location=0,scrollbars=auto,resizable=1,status=0,width=650,height=680";
		var pswn = "pscw_" + pskru8n();
		var url = "http://messenger.providesupport.com/messenger/automobileatl.html?ps_l=" + escape(document.location) + "";
		window.open(url, pswn, pswo);
	} else if (1 == 2) {
		document.location = "http://";
	}
}
var pskru8il;
var pskru8it;
function pskru8pi() {
	var il;
	if (3 == 2) {
		il = window.pageXOffset + 50;
	} else if (3 == 3) {
		il = (window.innerWidth * 50 / 100) + window.pageXOffset;
	} else {
		il = 50;
	}
	il -= (271 / 2);
	var it;
	if (3 == 2) {
		it = window.pageYOffset + 50;
	} else if (3 == 3) {
		it = (window.innerHeight * 50 / 100) + window.pageYOffset;
	} else {
		it = 50;
	}
	it -= (191 / 2);
	if ((il != pskru8il) || (it != pskru8it)) {
		pskru8il = il;
		pskru8it = it;
		var d = document.getElementById('cikru8');
		if (d != null) {
			d.style.left  = Math.round(pskru8il) + "px";
			d.style.top  = Math.round(pskru8it) + "px";
		}
	}
	setTimeout("pskru8pi()", 100);
}
var pskru8lc = 0;
function pskru8si(t) {
	window.onscroll = pskru8pi;
	window.onresize = pskru8pi;
	pskru8pi();
	pskru8lc = 0;
	var url = "http://messenger.providesupport.com/" + ((t == 2) ? "auto" : "chat") + "-invitation/automobileatl.html?ps_t=" + pskru8n() + "";
	var d = document.getElementById('cikru8');
	if (d != null) {
		d.innerHTML = '<iframe allowtransparency="true" style="background:transparent;width:271;height:191" src="' + url + 
			'" onload="pskru8ld()" frameborder="no" width="271" height="191" scrolling="no"></iframe>';
	}
}
function pskru8ld() {
	if (pskru8lc == 1) {
		var d = document.getElementById('cikru8');
		if (d != null) {
			d.innerHTML = "";
		}
	}
	pskru8lc++;
}
if (false) {
	pskru8si(1);
}
var pskru8d = document.getElementById('sckru8');
if (pskru8d != null) {
	if (pskru8ol || (1 == 1) || (1 == 2)) {
		var ctt = "";
		if (ctt != "") {
			tt = ' alt="' + ctt + '" title="' + ctt + '"';
		} else {
			tt = '';
		}
		if (false) {
			var p1 = '<table style="display:inline;border:0px;border-collapse:collapse;border-spacing:0;"><tr><td style="padding:0px;text-align:center;border:0px;vertical-align:middle"><a href="#" onclick="pskru8ow(); return false;"><img name="pskru8image" src="http://image.providesupport.com/image/automobileatl/offline-614261178.gif" width="160" height="60" style="border:0;display:block;margin:auto"';
			var p2 = '<td style="padding:0px;text-align:center;border:0px;vertical-align:middle"><a href="http://www.providesupport.com/pb/automobileatl" target="_blank"><img src="http://image.providesupport.com/';
			var p3 = 'style="border:0;display:block;margin:auto"></a></td></tr></table>';
			if ((160 >= 140) || (160 >= 60)) {
				pskru8d.innerHTML = p1+tt+'></a></td></tr><tr>'+p2+'lcbpsh.gif" width="140" height="17"'+p3;
			} else {
				pskru8d.innerHTML = p1+tt+'></a></td>'+p2+'lcbpsv.gif" width="17" height="140"'+p3;
			}
		} else {
			pskru8d.innerHTML = '<a href="#" onclick="pskru8ow(); return false;"><img name="pskru8image" src="http://image.providesupport.com/image/automobileatl/offline-614261178.gif" width="160" height="60" border="0"'+tt+'></a>';
		}
	} else {
		pskru8d.innerHTML = '';
	}
}
var pskru8op = false;
function pskru8co() {
	var w1 = pskru8ci.width - 1;
	pskru8ol = (w1 & 1) != 0;
	pskru8sb(pskru8ol ? "http://image.providesupport.com/image/automobileatl/online-781366296.gif" : "http://image.providesupport.com/image/automobileatl/offline-614261178.gif");
	pskru8scf((w1 & 2) != 0);
	var h = pskru8ci.height;

	if (h == 1) {
		pskru8op = false;

	// manual invitation
	} else if ((h == 2) && (!pskru8op)) {
		pskru8op = true;
		pskru8si(1);
		//alert("Chat invitation in standard code");
		
	// auto-invitation
	} else if ((h == 3) && (!pskru8op)) {
		pskru8op = true;
		pskru8si(2);
		//alert("Auto invitation in standard code");
	}
}
var pskru8ci = new Image();
pskru8ci.onload = pskru8co;
var pskru8pm = false;
var pskru8cp = pskru8pm ? 30 : 60;
var pskru8ct = null;
function pskru8scf(p) {
	if (pskru8pm != p) {
		pskru8pm = p;
		pskru8cp = pskru8pm ? 30 : 60;
		if (pskru8ct != null) {
			clearTimeout(pskru8ct);
			pskru8ct = null;
		}
		pskru8ct = pskru8st("pskru8rc()", pskru8cp);
	}
}
function pskru8rc() {
	pskru8ct = pskru8st("pskru8rc()", pskru8cp);
	try {
		pskru8ci.src = "http://image.providesupport.com/cmd/automobileatl?" + "ps_t=" + pskru8n() + "&ps_l=" + escape(document.location) + "&ps_r=" + escape(document.referrer) + "&ps_s=" + pskru8sid + "" + "";
	} catch(e) {
	}
}
pskru8rc();
var pskru8cb = "http://image.providesupport.com/image/automobileatl/offline-614261178.gif";
function pskru8sb(b) {
	if (pskru8cb != b) {
		var i = document.images['pskru8image'];
		if (i != null) {
			i.src = b;
		}
		pskru8cb = b;
	}
}

