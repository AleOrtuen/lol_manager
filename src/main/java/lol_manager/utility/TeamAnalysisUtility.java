package lol_manager.utility;

import lol_manager.dto.ChampDTO;
import lol_manager.dto.DraftDTO;
import lol_manager.dto.PickDTO;
import lol_manager.dto.TeamAnalysisDTO;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class TeamAnalysisUtility {

    // CONTA QUANTE VOLTE E' STATO PICKATO UN CHAMP
    public static List<TeamAnalysisDTO> getPickCount (List<PickDTO> picks) {
        Map<ChampDTO, Integer> champCount = new HashMap<>();

        for (PickDTO pick : picks) {
                ChampDTO champ = pick.getPick();
                champCount.put(champ, champCount.getOrDefault(champ, 0) + 1);
        }

        List<TeamAnalysisDTO> pickCounts = new ArrayList<>();
        for (Map.Entry<ChampDTO, Integer> entry : champCount.entrySet()) {
            TeamAnalysisDTO champPickCount = new TeamAnalysisDTO();
            champPickCount.setChamp(entry.getKey());
            champPickCount.setPickCount(entry.getValue());
            pickCounts.add(champPickCount);
        }

        return pickCounts;
    }

    // CONTA QUANTE VOLTE E' STATO PICKATO UN CHAMP IN BLUE SIDE
    public static List<TeamAnalysisDTO> getPickCountBlue (List<PickDTO> picks) {
        Map<ChampDTO, Integer> champCount = new HashMap<>();

        for (PickDTO pick : picks) {
            ChampDTO champ = pick.getPick();
            if (pick.getSide().equals("blue")) {
                champCount.put(champ, champCount.getOrDefault(champ, 0) + 1);
            }
        }

        List<TeamAnalysisDTO> pickCounts = new ArrayList<>();
        for (Map.Entry<ChampDTO, Integer> entry : champCount.entrySet()) {
            TeamAnalysisDTO champPickCount = new TeamAnalysisDTO();
            champPickCount.setChamp(entry.getKey());
            champPickCount.setPickCountBlue(entry.getValue());
            pickCounts.add(champPickCount);
        }

        return pickCounts;
    }

    // CONTA QUANTE VOLTE E' STATO PICKATO UN CHAMP IN RED SIDE
    public static List<TeamAnalysisDTO> getPickCountRed (List<PickDTO> picks) {
        Map<ChampDTO, Integer> champCount = new HashMap<>();

        for (PickDTO pick : picks) {
            ChampDTO champ = pick.getPick();
            if (pick.getSide().equals("red")) {
                champCount.put(champ, champCount.getOrDefault(champ, 0) + 1);
            }
        }

        List<TeamAnalysisDTO> pickCounts = new ArrayList<>();
        for (Map.Entry<ChampDTO, Integer> entry : champCount.entrySet()) {
            TeamAnalysisDTO champPickCount = new TeamAnalysisDTO();
            champPickCount.setChamp(entry.getKey());
            champPickCount.setPickCountRed(entry.getValue());
            pickCounts.add(champPickCount);
        }

        return pickCounts;
    }

    // CALCOLA LA PERCENTUALE DI PICK DI UN CHAMP
    public static List<TeamAnalysisDTO> getPickRate(Long idTeam, List<PickDTO> picks) {
        Map<ChampDTO, Integer> champCount = new HashMap<>();
        Set<Long> draftIds = new HashSet<>();

        for (PickDTO pick : picks) {
            DraftDTO draft = pick.getDraft();
            if (draft == null || draft.getTeamBlue() == null || draft.getIdDraft() == null) continue;

            Long draftId = draft.getIdDraft();
            draftIds.add(draftId);

            String side = draft.getTeamBlue().getIdTeam().equals(idTeam) ? "blue" : "red";

            if (side.equals(pick.getSide())) {
                ChampDTO champ = pick.getPick();
                champCount.put(champ, champCount.getOrDefault(champ, 0) + 1);
            }
        }

        int totalDrafts = draftIds.size();

        List<TeamAnalysisDTO> pickRates = new ArrayList<>();
        for (Map.Entry<ChampDTO, Integer> entry : champCount.entrySet()) {
            TeamAnalysisDTO dto = new TeamAnalysisDTO();
            dto.setChamp(entry.getKey());

            int champPicks = entry.getValue();
            double pickRate = totalDrafts > 0 ? (champPicks * 100.0) / totalDrafts : 0.0;

            dto.setPickRate(pickRate);
            pickRates.add(dto);
        }

        return pickRates;
    }

    // CALCOLA LA PERCENTUALE DI PICK DI UN CHAMP IN BLUE SIDE
    public static List<TeamAnalysisDTO> getPickRateBlue(Long idTeam, List<PickDTO> picks) {
        Map<ChampDTO, Integer> champCount = new HashMap<>();
        Set<Long> draftIds = new HashSet<>();

        for (PickDTO pick : picks) {
            DraftDTO draft = pick.getDraft();
            if (draft == null || draft.getIdDraft() == null) continue;

            boolean isTeamBlue = draft.getTeamBlue() != null && idTeam.equals(draft.getTeamBlue().getIdTeam());
            boolean isTeamRed = draft.getTeamRed() != null && idTeam.equals(draft.getTeamRed().getIdTeam());

            if (!isTeamBlue && !isTeamRed) continue;

            draftIds.add(draft.getIdDraft());

            if (isTeamBlue && "blue".equals(pick.getSide())) {
                ChampDTO champ = pick.getPick();
                champCount.put(champ, champCount.getOrDefault(champ, 0) + 1);
            }
        }

        int totalDrafts = draftIds.size();

        List<TeamAnalysisDTO> pickRates = new ArrayList<>();
        for (Map.Entry<ChampDTO, Integer> entry : champCount.entrySet()) {
            TeamAnalysisDTO dto = new TeamAnalysisDTO();
            dto.setChamp(entry.getKey());

            int champPicks = entry.getValue();
            double pickRate = totalDrafts > 0 ? (champPicks * 100.0) / totalDrafts : 0.0;

            dto.setPickRateBlue(pickRate);
            pickRates.add(dto);
        }

        return pickRates;
    }

    // CALCOLA LA PERCENTUALE DI PICK DI UN CHAMP IN RED SIDE
    public static List<TeamAnalysisDTO> getPickRateRed(Long idTeam, List<PickDTO> picks) {
        Map<ChampDTO, Integer> champCount = new HashMap<>();
        Set<Long> draftIds = new HashSet<>();

        for (PickDTO pick : picks) {
            DraftDTO draft = pick.getDraft();
            if (draft == null || draft.getIdDraft() == null) continue;

            boolean isTeamBlue = draft.getTeamBlue() != null && idTeam.equals(draft.getTeamBlue().getIdTeam());
            boolean isTeamRed = draft.getTeamRed() != null && idTeam.equals(draft.getTeamRed().getIdTeam());

            if (!isTeamBlue && !isTeamRed) continue;

            draftIds.add(draft.getIdDraft());

            if (isTeamRed && "red".equals(pick.getSide())) {
                ChampDTO champ = pick.getPick();
                champCount.put(champ, champCount.getOrDefault(champ, 0) + 1);
            }
        }

        int totalDrafts = draftIds.size();

        List<TeamAnalysisDTO> pickRates = new ArrayList<>();
        for (Map.Entry<ChampDTO, Integer> entry : champCount.entrySet()) {
            TeamAnalysisDTO dto = new TeamAnalysisDTO();
            dto.setChamp(entry.getKey());

            int champPicks = entry.getValue();
            double pickRate = totalDrafts > 0 ? (champPicks * 100.0) / totalDrafts : 0.0;

            dto.setPickRateRed(pickRate);
            pickRates.add(dto);
        }

        return pickRates;
    }

    // CONTA QUANTE VOLTE E' STATO PICKATO UN CHAMP CHE HA VINTO
    public static List<TeamAnalysisDTO> getWinCountPick (Long idTeam, List<PickDTO> picks) {
        Map<ChampDTO, Integer> champCount = new HashMap<>();

        for (PickDTO pick : picks) {
            if (pick == null || pick.getDraft() == null || pick.getDraft().getWinner() == null ) continue;
            if (pick.getDraft().getWinner().getIdTeam().equals(idTeam)) {
                ChampDTO champ = pick.getPick();
                champCount.put(champ, champCount.getOrDefault(champ, 0) + 1);
            }
        }

        List<TeamAnalysisDTO> pickCounts = new ArrayList<>();
        for (Map.Entry<ChampDTO, Integer> entry : champCount.entrySet()) {
            TeamAnalysisDTO champPickCount = new TeamAnalysisDTO();
            champPickCount.setChamp(entry.getKey());
            champPickCount.setWinCountPick(entry.getValue());
            pickCounts.add(champPickCount);
        }

        return pickCounts;
    }

    // METODO DI MERGE STATISTICHE
    public static <T> void mergeStat(
            Map<ChampDTO, TeamAnalysisDTO> map,
            List<TeamAnalysisDTO> source,
            Function<TeamAnalysisDTO, T> getter,
            BiConsumer<TeamAnalysisDTO, T> setter
    ) {
        for (TeamAnalysisDTO dto : source) {
            ChampDTO champ = dto.getChamp();
            T value = getter.apply(dto);
            if (value == null) continue;

            TeamAnalysisDTO existing = map.getOrDefault(champ, new TeamAnalysisDTO());
            existing.setChamp(champ);
            setter.accept(existing, value);
            map.put(champ, existing);
        }
    }

}
