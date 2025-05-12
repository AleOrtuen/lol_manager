package lol_manager.service;

import org.springframework.stereotype.Service;

import lol_manager.dto.WSMessageDTO;

@Service
public class WebSocketService {

    public WSMessageDTO handlePick(String idRoom, WSMessageDTO message) {
        // validazioni e logica dominio
        return message;
    }

    public WSMessageDTO startTimer(String idRoom) {
        WSMessageDTO timerMsg = new WSMessageDTO();
        timerMsg.setType("TIMER_START");
        timerMsg.setIdRoom(idRoom);
        timerMsg.setStartTime(System.currentTimeMillis());
        return timerMsg;
    }
}
