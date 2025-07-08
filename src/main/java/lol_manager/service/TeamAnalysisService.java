package lol_manager.service;

import lol_manager.dto.BanDTO;
import lol_manager.dto.ChampDTO;
import lol_manager.dto.PickDTO;
import lol_manager.dto.TeamAnalysisDTO;
import lol_manager.model.Team;
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
        List<BanDTO> allBans = banService.findByIdTeam(idTeam);

        List<TeamAnalysisDTO> pickCount = TeamAnalysisUtility.getPickCount(chosenPicks);
        List<TeamAnalysisDTO> pickCountBlue = TeamAnalysisUtility.getPickCountBlue(chosenPicks);
        List<TeamAnalysisDTO> pickCountRed = TeamAnalysisUtility.getPickCountRed(chosenPicks);
        List<TeamAnalysisDTO> pickRate = TeamAnalysisUtility.getPickRate(idTeam, allPicks);
        List<TeamAnalysisDTO> pickRateBlue = TeamAnalysisUtility.getPickRateBlue(idTeam, allPicks);
        List<TeamAnalysisDTO> pickRateRed = TeamAnalysisUtility.getPickRateRed(idTeam, allPicks);
        List<TeamAnalysisDTO> winCountPick = TeamAnalysisUtility.getPickWinCount(idTeam, chosenPicks);
        List<TeamAnalysisDTO> winCountPickBlue = TeamAnalysisUtility.getPickWinCountBlue(idTeam, chosenPicks);
        List<TeamAnalysisDTO> winCountPickRed = TeamAnalysisUtility.getPickWinCountRed(idTeam, chosenPicks);
        List<TeamAnalysisDTO> winRatePick = TeamAnalysisUtility.getWinRate(idTeam, allPicks);
        List<TeamAnalysisDTO> winRatePickBlue = TeamAnalysisUtility.getPickWinRateBlue(idTeam, allPicks);
        List<TeamAnalysisDTO> winRatePickRed = TeamAnalysisUtility.getPickWinRateRed(idTeam, allPicks);
        List<TeamAnalysisDTO> banCount = TeamAnalysisUtility.getBanCount(allBans);
        List<TeamAnalysisDTO> banCountBlue = TeamAnalysisUtility.getBanCountBlue(allBans);
        List<TeamAnalysisDTO> banCountRed = TeamAnalysisUtility.getBanCountRed(allBans);
        List<TeamAnalysisDTO> banRate = TeamAnalysisUtility.getBanRate(idTeam, allBans);
        List<TeamAnalysisDTO> banRateBlue = TeamAnalysisUtility.getBanRateBlue(allBans);
        List<TeamAnalysisDTO> banRateRed = TeamAnalysisUtility.getBanRateRed(allBans);

        TeamAnalysisUtility.mergeStat(champAnalysisMap, pickCount, TeamAnalysisDTO::getPickCount, TeamAnalysisDTO::setPickCount);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, pickCountBlue, TeamAnalysisDTO::getPickCountBlue, TeamAnalysisDTO::setPickCountBlue);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, pickCountRed, TeamAnalysisDTO::getPickCountRed, TeamAnalysisDTO::setPickCountRed);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, pickRate, TeamAnalysisDTO::getPickRate, TeamAnalysisDTO::setPickRate);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, pickRateBlue, TeamAnalysisDTO::getPickRateBlue, TeamAnalysisDTO::setPickRateBlue);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, pickRateRed, TeamAnalysisDTO::getPickRateRed, TeamAnalysisDTO::setPickRateRed);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, winCountPick, TeamAnalysisDTO::getWinCountPick, TeamAnalysisDTO::setWinCountPick);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, winCountPickBlue, TeamAnalysisDTO::getWinCountPickBlue, TeamAnalysisDTO::setWinCountPickBlue);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, winCountPickRed, TeamAnalysisDTO::getWinCountPickRed, TeamAnalysisDTO::setWinCountPickRed);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, winRatePick, TeamAnalysisDTO::getWinRatePick, TeamAnalysisDTO::setWinRatePick);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, winRatePickBlue, TeamAnalysisDTO::getWinRatePickBlue, TeamAnalysisDTO::setWinRatePickBlue);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, winRatePickRed, TeamAnalysisDTO::getWinRatePickRed, TeamAnalysisDTO::setWinRatePickRed);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, banCount, TeamAnalysisDTO::getBanCount, TeamAnalysisDTO::setBanCount);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, banCountBlue, TeamAnalysisDTO::getBanCountBlue, TeamAnalysisDTO::setBanCountBlue);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, banCountRed, TeamAnalysisDTO::getBanCountRed, TeamAnalysisDTO::setBanCountRed);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, banRate, TeamAnalysisDTO::getBanRate, TeamAnalysisDTO::setBanRate);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, banRateBlue, TeamAnalysisDTO::getBanRateBlue, TeamAnalysisDTO::setBanRateBlue);
        TeamAnalysisUtility.mergeStat(champAnalysisMap, banRateRed, TeamAnalysisDTO::getBanRateRed, TeamAnalysisDTO::setBanRateRed);

        return new ArrayList<>(champAnalysisMap.values());
    }

}
