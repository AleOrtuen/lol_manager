package lol_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import lol_manager.dto.WSMessageDTO;
import lol_manager.service.WebSocketService;

@Controller
public class GameWebSocketController {

	@Autowired
	private WebSocketService webSocketService;
	
	@MessageMapping("/game/{idRoom}/action")
	@SendTo("/topic/game/{idRoom}")
	public WSMessageDTO handleGameAction(@DestinationVariable String idRoom, @Payload WSMessageDTO message) {
	    switch (message.getType()) {
	        case "PICK":
	            return webSocketService.handlePick(idRoom, message);
//	        case "BAN":
//	            return webSocketService.handleBan(idRoom, message);
	        case "TIMER_REQUEST":
	            return webSocketService.startTimer(idRoom);
	        default:
	            throw new IllegalArgumentException("Unknown action type: " + message.getType());
	    }
	}

}
