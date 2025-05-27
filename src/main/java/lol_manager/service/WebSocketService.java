package lol_manager.service;

import java.time.Clock;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import lol_manager.dto.DraftDTO;
import lol_manager.dto.DraftEventsDTO;
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

    @Autowired
    private DraftService draftService;

	private static final long TIMER_DURATION_MS = 30_000;
    private static final long READY_DURATION_MS = 180_000;
	private Map<String, Long> timers = new ConcurrentHashMap<>();
    private Map<Long, Set<String>> ready = new ConcurrentHashMap<>();
    private Map<Long, Long> readyTimeOut = new ConcurrentHashMap<>();
    private Map<String, DraftEventsDTO> draftEvents = new ConcurrentHashMap<>();

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

    // NOTIFY TO SUBSCRIBED CLIENTS WHEN A GAME IS UPDATED
    public void notifyGameUpdate(String idRoom, GameDTO game) {
        WSMessageDTO updateMessage = new WSMessageDTO();
        updateMessage.setIdRoom(idRoom);
        updateMessage.setType("GAME_UPDATE");
        updateMessage.setGame(game);

        messagingTemplate.convertAndSend("/topic/game/" + idRoom, updateMessage);
    }

    // NOTIFY TO SUBSCRIBED CLIENTS WHEN A DRAFT IS UPDATED
    public void notifyDraftUpdate(String idRoom, DraftDTO draft) {
        WSMessageDTO updateMessage = new WSMessageDTO();
        updateMessage.setIdRoom(idRoom);
        updateMessage.setType("DRAFT_UPDATE");
        updateMessage.setDraft(draft);

        messagingTemplate.convertAndSend("/topic/game/" + idRoom, updateMessage);
    }

    // CREATE READY CHECKS. IF BOTH PLAYER ARE READY SET TRUE ON DB
    public WSMessageDTO readyCheck(String idRoom, WSMessageDTO message) throws Exception {
        Long draftId = message.getDraft().getIdDraft();
        String sender = message.getSender();

        ready.putIfAbsent(draftId, ConcurrentHashMap.newKeySet());
        Set<String> readyPlayers = ready.get(draftId);
        readyPlayers.add(sender);

        readyTimeOut.put(draftId, System.currentTimeMillis());

        WSMessageDTO returnMessage = new WSMessageDTO();
        returnMessage.setIdRoom(idRoom);
        returnMessage.setSender(sender);

        if (readyPlayers.size() >= 2) {
            ready.remove(draftId);
            returnMessage.setType("READY_BOTH");
            DraftDTO dto = message.getDraft();
            dto.setReady(true);
            returnMessage.setDraft(draftService.update(dto));
        } else {
            returnMessage.setType(sender + " READY");
        }

        return returnMessage;
    }

    // READY CHECKS REMOVER
    @Scheduled(fixedRate = 60_000)
    public void cleanReadyCheck() {
        long now = System.currentTimeMillis();
        Iterator<Map.Entry<Long, Long>> iterator = readyTimeOut.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Long, Long> entry = iterator.next();
            Long draftId = entry.getKey();
            Long timestamp = entry.getValue();

            if (now > timestamp + READY_DURATION_MS) {
                iterator.remove();
                ready.remove(draftId);
            }
        }
    }

    // DRAFT EVENTS SWITCH E NOTIFY CLIENTS
    public WSMessageDTO draftEventsHandler(String idRoom) {
        DraftEventsDTO eventsDTO = draftEvents.computeIfAbsent(idRoom, key -> {
            DraftEventsDTO newEvents = new DraftEventsDTO();
            newEvents.activatePhase("start");
            return newEvents;
        });

        boolean foundCurrent = false;

        for (String phase : eventsDTO.getEvents().keySet()) {
            if (foundCurrent) {
                eventsDTO.activatePhase(phase);
                if (phase.equals("redPick5")) {
                    draftEvents.remove(idRoom);
                }
                break;
            }
            if (phase.equals(eventsDTO.getCurrentPhase())) {
                foundCurrent = true;
            }
        }

        WSMessageDTO returnMessage = new WSMessageDTO();
        returnMessage.setEvents(eventsDTO);
        returnMessage.setType("EVENT_CHANGE");
        return returnMessage;
    }

    // RETRIEVE CURRENT EVENT
    public WSMessageDTO getCurrentDraftEvent(String idRoom) {
        WSMessageDTO returnMessage = new WSMessageDTO();
        returnMessage.setIdRoom(idRoom);
        returnMessage.setType("CURRENT_EVENT");
        returnMessage.setEvents(draftEvents.get(idRoom));
        System.out.println("ciao ricevo");
        return returnMessage;
    }

}
