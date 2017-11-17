var connectionURI = "ws://" + document.location.host + document.location.pathname + "../chat";
var webSocket = new WebSocket(connectionURI);
var messages = document.getElementById("mensajes");

var onConnectionFunction = function(e) {
    window.console.log("Conectado exitosamente con el ChatHandler.");
};
webSocket.onopen = onConnectionFunction;


var onMessageFunction = function (e) {
    window.console.log(e);
    serverResponse = JSON.parse(e.data);
    
    switch (serverResponse.responseType) {
        case 0:
            window.console.log("Assigned id: " + serverResponse.id);
            break;
            
        case 1:
            messages.innerHTML += serverResponse.msg + "\n";
            break;
            
        default:
            window.console.log("No responseType provided");
            break;
    }
    
};
webSocket.onmessage = onMessageFunction;

var sendMessage = function () {
    idDestinatario = document.getElementById("id-destinatario").value;
    mensaje = document.getElementById("mensaje").value;
    
    request = {
        action: 1,
        userID: idDestinatario,
        msg: mensaje
    };
    
    webSocket.send(JSON.stringify(request));
};
document.getElementById("enviar-mensaje").onclick = sendMessage;