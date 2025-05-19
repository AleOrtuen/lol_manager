package lol_manager.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lol_manager.dto.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lol_manager.dto.WSMessageDTO;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

	private static final long TIMER_DURATION_MS = 30_000;
	private Map<String, Long> timers = new ConcurrentHashMap<>();
	
    public WSMessageDTO handlePick(String idRoom, WSMessageDTO message) {

    	
        return message;
    }

    public WSMessageDTO startTimer(String idRoom) {
        long startTime = System.currentTimeMillis();
        timers.put(idRoom, startTime);
        
        WSMessageDTO timerMsg = new WSMessageDTO();
        timerMsg.setType("TIMER_START");
        timerMsg.setIdRoom(idRoom);
        timerMsg.setStartTime(startTime);
        return timerMsg;
    }

    public WSMessageDTO getCurrentTimer(String idRoom) {
        Long startTime = timers.get(idRoom);
        if (startTime == null) return null;

        WSMessageDTO timerMsg = new WSMessageDTO();
        timerMsg.setType("TIMER_START");
        timerMsg.setIdRoom(idRoom);
        timerMsg.setStartTime(startTime);
        return timerMsg;
    }
    
    @Scheduled(fixedRate = 60_000) 
    public void clearExpiredTimers() {
        long now = System.currentTimeMillis();
        long buffer = 5000; 
        timers.entrySet().removeIf(entry -> now > entry.getValue() + TIMER_DURATION_MS + buffer);
    }

    public WSMessageDTO sideSelection(String idRoom, WSMessageDTO message) {
        WSMessageDTO returnMessage = new WSMessageDTO();
        return returnMessage;
    }

    public void notifyGameUpdate(String idRoom, GameDTO game) {
        WSMessageDTO updateMessage = new WSMessageDTO();
        updateMessage.setIdRoom(idRoom);
        updateMessage.setType("GAME_UPDATE");
        updateMessage.setGame(game);

        messagingTemplate.convertAndSend("/topic/game/" + idRoom, updateMessage);
    }
}
