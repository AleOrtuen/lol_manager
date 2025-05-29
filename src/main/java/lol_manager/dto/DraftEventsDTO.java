package lol_manager.dto;

import java.util.LinkedHashMap;
import java.util.Map;

public class DraftEventsDTO {
    private Map<String, Boolean> events = new LinkedHashMap<>();
    private String currentPhase;

    public DraftEventsDTO() {
        events.put("start", false);
        events.put("blueBan1", false);
        events.put("redBan1", false);
        events.put("blueBan2", false);
        events.put("redBan2", false);
        events.put("blueBan3", false);
        events.put("redBan3", false);
        events.put("bluePick1", false);
        events.put("redPick1", false);
        events.put("redPick2", false);
        events.put("bluePick2", false);
        events.put("bluePick3", false);
        events.put("redPick3", false);
        events.put("redBan4", false);
        events.put("blueBan4", false);
        events.put("redBan5", false);
        events.put("blueBan5", false);
        events.put("redPick4", false);
        events.put("bluePick4", false);
        events.put("bluePick5", false);
        events.put("redPick5", false);
        events.put("end", false);
    }

    public Map<String, Boolean> getEvents() {
        return events;
    }

    public String getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(String currentPhase) {
        this.currentPhase = currentPhase;
    }

    public void activatePhase(String phase) {
        events.replaceAll((k, v) -> false);
        events.put(phase, true);
        currentPhase = phase;
    }
}
