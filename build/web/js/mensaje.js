// Destinatario de pruebas al que le vamos a enviar un mensaje
document.destinatarioActual = 7;

//Los elementos con los que nos interesa que interactue nuestro usuario
var textAreaMensaje = document.getElementById("message");
var botonEnviar = document.getElementById("send-button");

var funcionEnviarMensaje = function(e) {
    $.post(
            "mensaje",
            {
                mensaje: textAreaMensaje.value,
                destinatario: document.destinatarioActual
            },
            function(serverResponse){
                window.console.log(serverResponse);
            });
            
    $(textAreaMensaje).val("");
};


// Cuando se presiona Enter en nuestro <textarea> ejecutamos nuestra funcion enviar mensajes
var funcionTeclaPresionada = function(e) {
    e.preventDefault();
    //window.console.log(e);
    switch (e.keyCode) {
        case 13:
            funcionEnviarMensaje();
        break;
        // En caso de que no haya keyCode, no hacemos nada
        default:
    }
};

// Agregamos nuestras funciones a los eventos
textAreaMensaje.onkeyup = funcionTeclaPresionada;
botonEnviar.onclick = funcionEnviarMensaje;

// Parte de los WebSockets
var connectionURI = "ws://" + document.location.host + "/chat/chat";
var webSocket = new WebSocket(connectionURI);

webSocket.onopen = function () {
    window.console.log("Conectado a traves del protocolo WebSocket");
};

webSocket.onmessage = function(serverMessage) {
    serverCall = JSON.parse(serverMessage.data);
    window.console.log(serverCall);
};