package lol_manager.service;

import lol_manager.dto.ChampDTO;
import lol_manager.dto.PickDTO;
import lol_manager.dto.TeamAnalysisDTO;
import lol_manager.utility.TeamAnalysisUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeamAnalysisService {

    @Autowired
    private PickService pickService;

    @Autowired
    private BanService banService;

    public List<TeamAnalysisDTO> getTeamAnalysis(Long idTeam) throws Exception {
        Map<ChampDTO, TeamAnalysisDTO> champAnalysisMap = new HashMap<>();

        List<PickDTO> chosenPicks = pickService.findChosenByIdTeam(idTeam);
        List<PickDTO> allPicks = pickService.findByIdTeam(idTeam);

        List<TeamAnalysisDTO> pickCount = TeamAnalysisUtility.getPickCount(chosenPicks);
        List<TeamAnalysisDTO> pickCountBlue = TeamAnalysisUtility.getPickCountBlue(chosenPicks);
        List<TeamAnalysisDTO> pickCountRed = TeamAnalysisUtility.getPickCountRed(chosenPicks);
        List<TeamAnalysisDTO> pickRate = TeamAnalysisUtility.getPickRate(idTeam, allPicks);
        List<TeamAnalysisDTO> pickRateBlue = TeamAnalysisUtility.getPickRateBlue(idTeam, allPicks);
        List<TeamAnalysisDTO> pickRateRed = TeamAnalysisUtility.getPickRateRed(idTeam, allPicks);
        List<TeamAnalysisDTO> winCountPick = TeamAnalysisUtility.getWinCountPick(idTeam, chosenPicks);

        TeamAnalysisUtility.mergeStat(champAnalysisMap, pickCount, TeamAnalysisDTO::getPickCount, TeamAnalysisDTO::setPickCount);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, pickCountBlue, TeamAnalysisDTO::getPickCountBlue, TeamAnalysisDTO::setPickCountBlue);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, pickCountRed, TeamAnalysisDTO::getPickCountRed, TeamAnalysisDTO::setPickCountRed);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, pickRate, TeamAnalysisDTO::getPickRate, TeamAnalysisDTO::setPickRate);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, pickRateBlue, TeamAnalysisDTO::getPickRateBlue, TeamAnalysisDTO::setPickRateBlue);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, pickRateRed, TeamAnalysisDTO::getPickRateRed, TeamAnalysisDTO::setPickRateRed);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, winCountPick, TeamAnalysisDTO::getWinCountPick, TeamAnalysisDTO::setWinCountPick);

        return new ArrayList<>(champAnalysisMap.values());
    }

}
