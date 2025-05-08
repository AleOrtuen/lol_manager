package lol_manager.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import lol_manager.dto.WSMessageDTO;

@Controller
public class GameWebSocketController {

	@MessageMapping("/game/{idRoom}/pick")
	@SendTo("/topic/game/{idRoom}")
	public WSMessageDTO handlePick(@DestinationVariable String idRoom, @Payload WSMessageDTO message) {
		return message;
	}
}
