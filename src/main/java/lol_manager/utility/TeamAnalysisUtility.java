package lol_manager.utility;

import lol_manager.dto.*;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class TeamAnalysisUtility {

    // CONTA QUANTE VOLTE E' STATO PICKATO UN CHAMP
    public static List<TeamAnalysisDTO> getPickCount (List<PickDTO> picks) {
        Map<ChampDTO, Integer> champCount = new HashMap<>();

        for (PickDTO pick : picks) {
            DraftDTO draft = pick.getDraft();
            if (draft == null || draft.getWinner() == null) continue;
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
            DraftDTO draft = pick.getDraft();
            if (draft == null || draft.getWinner() == null) continue;
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
            DraftDTO draft = pick.getDraft();
            if (draft == null || draft.getWinner() == null) continue;
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
            if (draft == null || draft.getTeamBlue() == null || draft.getIdDraft() == null || draft.getWinner() == null) continue;

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
        Map<ChampDTO, Integer> totalPickCount = new HashMap<>();
        Map<ChampDTO, Integer> bluePickCount = new HashMap<>();

        for (PickDTO pick : picks) {
            DraftDTO draft = pick.getDraft();
            if (draft == null || draft.getIdDraft() == null || draft.getWinner() == null) continue;

            boolean isTeamBlue = draft.getTeamBlue() != null && idTeam.equals(draft.getTeamBlue().getIdTeam());
            boolean isTeamRed = draft.getTeamRed() != null && idTeam.equals(draft.getTeamRed().getIdTeam());

            if (!isTeamBlue && !isTeamRed) continue;

            ChampDTO champ = pick.getPick();

            if ((isTeamBlue && "blue".equals(pick.getSide())) || (isTeamRed && "red".equals(pick.getSide()))) {
                totalPickCount.put(champ, totalPickCount.getOrDefault(champ, 0) + 1);

                if (isTeamBlue && "blue".equals(pick.getSide())) {
                    bluePickCount.put(champ, bluePickCount.getOrDefault(champ, 0) + 1);
                }
            }
        }

        List<TeamAnalysisDTO> pickRates = new ArrayList<>();
        for (ChampDTO champ : totalPickCount.keySet()) {
            int totalPicks = totalPickCount.getOrDefault(champ, 0);
            int bluePicks = bluePickCount.getOrDefault(champ, 0);

            double pickRate = totalPicks > 0 ? (bluePicks * 100.0) / totalPicks : 0.0;

            TeamAnalysisDTO dto = new TeamAnalysisDTO();
            dto.setChamp(champ);
            dto.setPickRateBlue(pickRate);
            pickRates.add(dto);
        }

        return pickRates;
    }

    // CALCOLA LA PERCENTUALE DI PICK DI UN CHAMP IN RED SIDE
    public static List<TeamAnalysisDTO> getPickRateRed(Long idTeam, List<PickDTO> picks) {
        Map<ChampDTO, Integer> totalPickCount = new HashMap<>();
        Map<ChampDTO, Integer> redPickCount = new HashMap<>();

        for (PickDTO pick : picks) {
            DraftDTO draft = pick.getDraft();
            if (draft == null || draft.getIdDraft() == null || draft.getWinner() == null) continue;

            boolean isTeamBlue = draft.getTeamBlue() != null && idTeam.equals(draft.getTeamBlue().getIdTeam());
            boolean isTeamRed = draft.getTeamRed() != null && idTeam.equals(draft.getTeamRed().getIdTeam());

            if (!isTeamBlue && !isTeamRed) continue;

            ChampDTO champ = pick.getPick();

            if ((isTeamBlue && "blue".equals(pick.getSide())) || (isTeamRed && "red".equals(pick.getSide()))) {
                totalPickCount.put(champ, totalPickCount.getOrDefault(champ, 0) + 1);

                if (isTeamRed && "red".equals(pick.getSide())) {
                    redPickCount.put(champ, redPickCount.getOrDefault(champ, 0) + 1);
                }
            }
        }

        List<TeamAnalysisDTO> pickRates = new ArrayList<>();
        for (ChampDTO champ : totalPickCount.keySet()) {
            int totalPicks = totalPickCount.getOrDefault(champ, 0);
            int redPicks = redPickCount.getOrDefault(champ, 0);

            double pickRate = totalPicks > 0 ? (redPicks * 100.0) / totalPicks : 0.0;

            TeamAnalysisDTO dto = new TeamAnalysisDTO();
            dto.setChamp(champ);
            dto.setPickRateRed(pickRate);
            pickRates.add(dto);
        }

        return pickRates;
    }

    // CONTA QUANTE VOLTE E' STATO PICKATO UN CHAMP CHE HA VINTO
    public static List<TeamAnalysisDTO> getPickWinCount(Long idTeam, List<PickDTO> picks) {
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

    // CONTA QUANTE VOLTE E' STATO PICKATO UN CHAMP CHE HA VINTO IN BLUE SIDE
    public static List<TeamAnalysisDTO> getPickWinCountBlue (Long idTeam, List<PickDTO> picks) {
        Map<ChampDTO, Integer> champCount = new HashMap<>();

        for (PickDTO pick : picks) {
            if (pick == null || pick.getDraft() == null || pick.getDraft().getWinner() == null ) continue;
            if (pick.getDraft().getWinner().getIdTeam().equals(idTeam) && pick.getSide().equals("blue")) {
                ChampDTO champ = pick.getPick();
                champCount.put(champ, champCount.getOrDefault(champ, 0) + 1);
            }
        }

        List<TeamAnalysisDTO> pickCounts = new ArrayList<>();
        for (Map.Entry<ChampDTO, Integer> entry : champCount.entrySet()) {
            TeamAnalysisDTO champPickCount = new TeamAnalysisDTO();
            champPickCount.setChamp(entry.getKey());
            champPickCount.setWinCountPickBlue(entry.getValue());
            pickCounts.add(champPickCount);
        }

        return pickCounts;
    }

    // CONTA QUANTE VOLTE E' STATO PICKATO UN CHAMP CHE HA VINTO IN RED SIDE
    public static List<TeamAnalysisDTO> getPickWinCountRed (Long idTeam, List<PickDTO> picks) {
        Map<ChampDTO, Integer> champCount = new HashMap<>();

        for (PickDTO pick : picks) {
            if (pick == null || pick.getDraft() == null || pick.getDraft().getWinner() == null ) continue;
            if (pick.getDraft().getWinner().getIdTeam().equals(idTeam) && pick.getSide().equals("red")) {
                ChampDTO champ = pick.getPick();
                champCount.put(champ, champCount.getOrDefault(champ, 0) + 1);
            }
        }

        List<TeamAnalysisDTO> pickCounts = new ArrayList<>();
        for (Map.Entry<ChampDTO, Integer> entry : champCount.entrySet()) {
            TeamAnalysisDTO champPickCount = new TeamAnalysisDTO();
            champPickCount.setChamp(entry.getKey());
            champPickCount.setWinCountPickRed(entry.getValue());
            pickCounts.add(champPickCount);
        }

        return pickCounts;
    }

    // CALCOLA LA PERCENTUALE DI WIN RATE DI UN CHAMP
    public static List<TeamAnalysisDTO> getWinRate(Long idTeam, List<PickDTO> picks) {
        Map<ChampDTO, Integer> totalPickCount = new HashMap<>();
        Map<ChampDTO, Integer> winPickCount = new HashMap<>();

        for (PickDTO pick : picks) {
            DraftDTO draft = pick.getDraft();
            if (draft == null || draft.getTeamBlue() == null || draft.getIdDraft() == null || draft.getWinner() == null) continue;

            String side = draft.getTeamBlue().getIdTeam().equals(idTeam) ? "blue" : "red";

            if (side.equals(pick.getSide())) {
                ChampDTO champ = pick.getPick();
                totalPickCount.put(champ, totalPickCount.getOrDefault(champ, 0) + 1);

                if (draft.getWinner().getIdTeam().equals(idTeam)) {
                    winPickCount.put(champ, winPickCount.getOrDefault(champ, 0) + 1);
                }
            }
        }

        List<TeamAnalysisDTO> winRates = new ArrayList<>();
        for (ChampDTO champ : totalPickCount.keySet()) {
            int picksForChamp = totalPickCount.get(champ);
            int winsForChamp = winPickCount.getOrDefault(champ, 0);

            double winRate = (picksForChamp > 0) ? (winsForChamp * 100.0) / picksForChamp : 0.0;

            TeamAnalysisDTO dto = new TeamAnalysisDTO();
            dto.setChamp(champ);
            dto.setWinRatePick(winRate);
            winRates.add(dto);
        }

        return winRates;
    }

    // CALCOLA LA PERCENTUALE DI WIN RATE DI UN CHAMP IN BLUE
    public static List<TeamAnalysisDTO> getPickWinRateBlue(Long idTeam, List<PickDTO> picks) {
        Map<ChampDTO, Integer> totalPickCount = new HashMap<>();
        Map<ChampDTO, Integer> winPickCount = new HashMap<>();

        for (PickDTO pick : picks) {
            DraftDTO draft = pick.getDraft();
            if (draft == null || draft.getTeamBlue() == null || draft.getIdDraft() == null || draft.getWinner() == null) continue;

            String side = draft.getTeamBlue().getIdTeam().equals(idTeam) ? "blue" : "red";

            if (side.equals(pick.getSide()) && pick.getSide().equals("blue")) {
                ChampDTO champ = pick.getPick();
                totalPickCount.put(champ, totalPickCount.getOrDefault(champ, 0) + 1);

                if (draft.getWinner().getIdTeam().equals(idTeam)) {
                    winPickCount.put(champ, winPickCount.getOrDefault(champ, 0) + 1);
                }
            }
        }

        List<TeamAnalysisDTO> winRates = new ArrayList<>();
        for (ChampDTO champ : totalPickCount.keySet()) {
            int picksForChamp = totalPickCount.get(champ);
            int winsForChamp = winPickCount.getOrDefault(champ, 0);

            double winRate = (picksForChamp > 0) ? (winsForChamp * 100.0) / picksForChamp : 0.0;

            TeamAnalysisDTO dto = new TeamAnalysisDTO();
            dto.setChamp(champ);
            dto.setWinRatePickBlue(winRate);
            winRates.add(dto);
        }

        return winRates;
    }

    // CALCOLA LA PERCENTUALE DI WIN RATE DI UN CHAMP IN RED
    public static List<TeamAnalysisDTO> getPickWinRateRed(Long idTeam, List<PickDTO> picks) {
        Map<ChampDTO, Integer> totalPickCount = new HashMap<>();
        Map<ChampDTO, Integer> winPickCount = new HashMap<>();

        for (PickDTO pick : picks) {
            DraftDTO draft = pick.getDraft();
            if (draft == null || draft.getTeamBlue() == null || draft.getIdDraft() == null || draft.getWinner() == null) continue;

            String side = draft.getTeamBlue().getIdTeam().equals(idTeam) ? "blue" : "red";

            if (side.equals(pick.getSide()) && pick.getSide().equals("red")) {
                ChampDTO champ = pick.getPick();
                totalPickCount.put(champ, totalPickCount.getOrDefault(champ, 0) + 1);

                if (draft.getWinner().getIdTeam().equals(idTeam)) {
                    winPickCount.put(champ, winPickCount.getOrDefault(champ, 0) + 1);
                }
            }
        }

        List<TeamAnalysisDTO> winRates = new ArrayList<>();
        for (ChampDTO champ : totalPickCount.keySet()) {
            int picksForChamp = totalPickCount.get(champ);
            int winsForChamp = winPickCount.getOrDefault(champ, 0);

            double winRate = (picksForChamp > 0) ? (winsForChamp * 100.0) / picksForChamp : 0.0;

            TeamAnalysisDTO dto = new TeamAnalysisDTO();
            dto.setChamp(champ);
            dto.setWinRatePickRed(winRate);
            winRates.add(dto);
        }

        return winRates;
    }

    // CONTA QUANTE VOLTE E' STATO BANNATO UN CHAMP
    public static List<TeamAnalysisDTO> getBanCount (List<BanDTO> bans) {
        Map<ChampDTO, Integer> champCount = new HashMap<>();

        for (BanDTO ban : bans) {
            DraftDTO draft = ban.getDraft();
            if (draft == null || draft.getWinner() == null) continue;
            ChampDTO champ = ban.getBan();
            champCount.put(champ, champCount.getOrDefault(champ, 0) + 1);
        }

        List<TeamAnalysisDTO> banCount = new ArrayList<>();
        for (Map.Entry<ChampDTO, Integer> entry : champCount.entrySet()) {
            TeamAnalysisDTO champBanCount = new TeamAnalysisDTO();
            champBanCount.setChamp(entry.getKey());
            champBanCount.setBanCount(entry.getValue());
            banCount.add(champBanCount);
        }

        return banCount;
    }

    // CONTA QUANTE VOLTE E' STATO BANNATO UN CHAMP IN BLUE
    public static List<TeamAnalysisDTO> getBanCountBlue (List<BanDTO> bans) {
        Map<ChampDTO, Integer> champCount = new HashMap<>();

        for (BanDTO ban : bans) {
            DraftDTO draft = ban.getDraft();
            if (draft == null || draft.getWinner() == null) continue;
            ChampDTO champ = ban.getBan();
            if (ban.getSide().equals("blue")) {
                champCount.put(champ, champCount.getOrDefault(champ, 0) + 1);
            }
        }

        List<TeamAnalysisDTO> banCount = new ArrayList<>();
        for (Map.Entry<ChampDTO, Integer> entry : champCount.entrySet()) {
            TeamAnalysisDTO champBanCount = new TeamAnalysisDTO();
            champBanCount.setChamp(entry.getKey());
            champBanCount.setBanCountBlue(entry.getValue());
            banCount.add(champBanCount);
        }

        return banCount;
    }

    // CONTA QUANTE VOLTE E' STATO BANNATO UN CHAMP IN BLUE
    public static List<TeamAnalysisDTO> getBanCountRed (List<BanDTO> bans) {
        Map<ChampDTO, Integer> champCount = new HashMap<>();

        for (BanDTO ban : bans) {
            DraftDTO draft = ban.getDraft();
            if (draft == null || draft.getWinner() == null) continue;
            ChampDTO champ = ban.getBan();
            if (ban.getSide().equals("red")) {
                champCount.put(champ, champCount.getOrDefault(champ, 0) + 1);
            }
        }

        List<TeamAnalysisDTO> banCount = new ArrayList<>();
        for (Map.Entry<ChampDTO, Integer> entry : champCount.entrySet()) {
            TeamAnalysisDTO champBanCount = new TeamAnalysisDTO();
            champBanCount.setChamp(entry.getKey());
            champBanCount.setBanCountRed(entry.getValue());
            banCount.add(champBanCount);
        }

        return banCount;
    }

    // CALCOLA LA PERCENTUALE DI BAN DI UN CHAMP
    public static List<TeamAnalysisDTO> getBanRate(Long idTeam, List<BanDTO> bans) {
        Map<ChampDTO, Integer> champBanCount = new HashMap<>();
        Set<Long> draftIds = new HashSet<>();

        for (BanDTO ban : bans) {
            DraftDTO draft = ban.getDraft();
            if (draft == null || draft.getIdDraft() == null || draft.getWinner() == null) continue;

            boolean isTeamBlue = draft.getTeamBlue() != null && idTeam.equals(draft.getTeamBlue().getIdTeam());
            boolean isTeamRed = draft.getTeamRed() != null && idTeam.equals(draft.getTeamRed().getIdTeam());

            if (!isTeamBlue && !isTeamRed) continue;

            draftIds.add(draft.getIdDraft());

            ChampDTO champ = ban.getBan();
            if (champ == null) continue;

            champBanCount.put(champ, champBanCount.getOrDefault(champ, 0) + 1);
        }

        int totalDrafts = draftIds.size();

        List<TeamAnalysisDTO> banRates = new ArrayList<>();
        for (Map.Entry<ChampDTO, Integer> entry : champBanCount.entrySet()) {
            ChampDTO champ = entry.getKey();
            int champBans = entry.getValue();

            double banRate = totalDrafts > 0 ? (champBans * 100.0) / totalDrafts : 0.0;

            TeamAnalysisDTO dto = new TeamAnalysisDTO();
            dto.setChamp(champ);
            dto.setBanRate(banRate);
            banRates.add(dto);
        }

        return banRates;
    }

    // CALCOLA LA PERCENTUALE DI BAN DI UN CHAMP IN BLUE
    public static List<TeamAnalysisDTO> getBanRateBlue(List<BanDTO> bans) {
        Map<ChampDTO, Integer> totalBanCount = new HashMap<>();
        Map<ChampDTO, Integer> blueBanCount = new HashMap<>();

        for (BanDTO ban : bans) {
            DraftDTO draft = ban.getDraft();
            if (draft == null || draft.getIdDraft() == null || draft.getWinner() == null) continue;

            ChampDTO champ = ban.getBan();
            if (champ == null) continue;

            totalBanCount.put(champ, totalBanCount.getOrDefault(champ, 0) + 1);

            if ("blue".equalsIgnoreCase(ban.getSide())) {
                blueBanCount.put(champ, blueBanCount.getOrDefault(champ, 0) + 1);
            }
        }

        List<TeamAnalysisDTO> result = new ArrayList<>();
        for (ChampDTO champ : totalBanCount.keySet()) {
            int totalBans = totalBanCount.get(champ);
            int blueBans = blueBanCount.getOrDefault(champ, 0);

            double banRateBlue = totalBans > 0 ? (blueBans * 100.0) / totalBans : 0.0;

            TeamAnalysisDTO dto = new TeamAnalysisDTO();
            dto.setChamp(champ);
            dto.setBanRateBlue(banRateBlue);
            result.add(dto);
        }

        return result;
    }

    // CALCOLA LA PERCENTUALE DI BAN DI UN CHAMP IN RED
    public static List<TeamAnalysisDTO> getBanRateRed(List<BanDTO> bans) {
        Map<ChampDTO, Integer> totalBanCount = new HashMap<>();
        Map<ChampDTO, Integer> redBanCount = new HashMap<>();

        for (BanDTO ban : bans) {
            DraftDTO draft = ban.getDraft();
            if (draft == null || draft.getIdDraft() == null || draft.getWinner() == null) continue;

            ChampDTO champ = ban.getBan();
            if (champ == null) continue;

            totalBanCount.put(champ, totalBanCount.getOrDefault(champ, 0) + 1);

            if ("red".equalsIgnoreCase(ban.getSide())) {
                redBanCount.put(champ, redBanCount.getOrDefault(champ, 0) + 1);
            }
        }

        List<TeamAnalysisDTO> result = new ArrayList<>();
        for (ChampDTO champ : totalBanCount.keySet()) {
            int totalBans = totalBanCount.get(champ);
            int redBans = redBanCount.getOrDefault(champ, 0);

            double banRateRed = totalBans > 0 ? (redBans * 100.0) / totalBans : 0.0;

            TeamAnalysisDTO dto = new TeamAnalysisDTO();
            dto.setChamp(champ);
            dto.setBanRateRed(banRateRed);
            result.add(dto);
        }

        return result;
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

    //    public static List<TeamAnalysisDTO> getPickRateBlue(Long idTeam, List<PickDTO> picks) {
//        Map<ChampDTO, Integer> champCount = new HashMap<>();
//        Set<Long> draftIds = new HashSet<>();
//
//        for (PickDTO pick : picks) {
//            DraftDTO draft = pick.getDraft();
//            if (draft == null || draft.getIdDraft() == null) continue;
//
//            boolean isTeamBlue = draft.getTeamBlue() != null && idTeam.equals(draft.getTeamBlue().getIdTeam());
//            boolean isTeamRed = draft.getTeamRed() != null && idTeam.equals(draft.getTeamRed().getIdTeam());
//
//            if (!isTeamBlue && !isTeamRed) continue;
//
//            draftIds.add(draft.getIdDraft());
//
//            if (isTeamBlue && "blue".equals(pick.getSide())) {
//                ChampDTO champ = pick.getPick();
//                champCount.put(champ, champCount.getOrDefault(champ, 0) + 1);
//            }
//        }
//
//        int totalDrafts = draftIds.size();
//
//        List<TeamAnalysisDTO> pickRates = new ArrayList<>();
//        for (Map.Entry<ChampDTO, Integer> entry : champCount.entrySet()) {
//            TeamAnalysisDTO dto = new TeamAnalysisDTO();
//            dto.setChamp(entry.getKey());
//
//            int champPicks = entry.getValue();
//            double pickRate = totalDrafts > 0 ? (champPicks * 100.0) / totalDrafts : 0.0;
//            dto.setPickRateBlue(pickRate);
//            pickRates.add(dto);
//        }
//
//        return pickRates;
//    }


//    public static List<TeamAnalysisDTO> getPickRateRed(Long idTeam, List<PickDTO> picks) {
//        Map<ChampDTO, Integer> champCount = new HashMap<>();
//        Set<Long> draftIds = new HashSet<>();
//
//        for (PickDTO pick : picks) {
//            DraftDTO draft = pick.getDraft();
//            if (draft == null || draft.getIdDraft() == null) continue;
//
//            boolean isTeamBlue = draft.getTeamBlue() != null && idTeam.equals(draft.getTeamBlue().getIdTeam());
//            boolean isTeamRed = draft.getTeamRed() != null && idTeam.equals(draft.getTeamRed().getIdTeam());
//
//            if (!isTeamBlue && !isTeamRed) continue;
//
//            draftIds.add(draft.getIdDraft());
//
//            if (isTeamRed && "red".equals(pick.getSide())) {
//                ChampDTO champ = pick.getPick();
//                champCount.put(champ, champCount.getOrDefault(champ, 0) + 1);
//            }
//        }
//
//        int totalDrafts = draftIds.size();
//
//        List<TeamAnalysisDTO> pickRates = new ArrayList<>();
//        for (Map.Entry<ChampDTO, Integer> entry : champCount.entrySet()) {
//            TeamAnalysisDTO dto = new TeamAnalysisDTO();
//            dto.setChamp(entry.getKey());
//
//            int champPicks = entry.getValue();
//            double pickRate = totalDrafts > 0 ? (champPicks * 100.0) / totalDrafts : 0.0;
//
//            dto.setPickRateRed(pickRate);
//            pickRates.add(dto);
//        }
//
//        return pickRates;
//    }
}
