<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Wiki Edits</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
</head>
<body>
<form name="wikiform" method="post" action="ParseServlet">
	<input type="hidden" id="hiddenField" name="hiddenField">
	<!-- Server responses get written here -->
	<div id="messages"></div>
	<!-- Script to utilise the WebSocket -->
	<script type="text/javascript">
		var webSocket;
		var messages = document.getElementById("messages");
		window.onload = function(){
			openSocket();
			window.setTimeout(document.wikiform.submit.bind(document.wikiform), 5000);
		};
		function openSocket() {
			// Ensures only one connection is open at a time
			 //System.out.println("Inside socket...."); 
			if (webSocket !== undefined
					&& webSocket.readyState !== WebSocket.CLOSED) {
				writeResponse("WebSocket is already opened.");
				return;
			}
			
			webSocket = new WebSocket("ws://wikimon.hatnote.com/en/");

			/**
			 * Binds functions to the listeners for the websocket.
			 */
			webSocket.onopen = function(event) {

				if (event.data === undefined)
					return;
			};

			webSocket.onmessage = function(event) {
				writeResponse(event.data);
			};

			webSocket.onclose = function(event) {
				writeResponse("Connection closed");

		};
		}

		/**
		 * Sends the value of the text input to the server
		 */
		/*  function send(){
		     var text = document.getElementById("messageinput").value;
		     webSocket.send(text);
		 } */

		function closeSocket() {
			webSocket.close();
		}

		function writeResponse(text) {
			messages.innerHTML += "<br/>" + text;
			document.getElementById("hiddenField").value= document.getElementById("hiddenField").value+messages.innerHTML;

		}
	</script>
</form>
</body>
</html>
