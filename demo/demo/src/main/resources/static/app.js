var stompClient = null;
var isConnected = false
function setConnected(connected) {
    isConnected = connected
    $("#connect").prop("enabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
     $("#greetings").html("");
}

function connect() {
if (isConnected) {
$("#conversation").show();
}else {
var socket = new SockJS('/gs-guide-websocket');
stompClient = Stomp.over(socket);
stompClient.connect({}, function (frame) {
setConnected(true);
console.log('Connected: ' + frame);
stompClient.subscribe('/topic/greetings', function (greeting) {
showGreeting(JSON.parse(greeting.body));
});
});
}
}


function hideChat(connected) {
$("#conversation").hide();
}

function disconnect() {
if (stompClient !== null) {
stompClient.disconnect();
}
setConnected(false);
console.log("Disconnected");
}

function sendName() {
stompClient.send("/app/hello", {}, JSON.stringify({'message': $("#message").val()}));
}

function showGreeting(greeting) {
$("#greetings").append("<tr><td>" + greeting.content + " : Sent by " + greeting.sender +"</td></tr>");
}

$(function () {
$("#chatForm").on('submit',function (e) {
e.preventDefault(); });

$( "#connect" ).click(function() { connect(); });
$( "#disconnect" ).click(function() { disconnect(); });
$( "#send" ).click(function() { sendName(); });
});
