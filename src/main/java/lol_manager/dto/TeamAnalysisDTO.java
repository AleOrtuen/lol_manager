package lol_manager.dto;

import java.util.Objects;

public class TeamAnalysisDTO {

    private ChampDTO champ;

    // ANALISI PICK
    private Integer pickCount;
    private Integer pickCountBlue;
    private Integer pickCountRed;
    private Double pickRate;
    private Double pickRateBlue;
    private Double pickRateRed;
    private Integer winCountPick;
    private Integer winCountPickBlue;
    private Integer winCountPickRed;
    private Double winRatePick;
    private Double winRatePickBlue;
    private Double winRatePickRed;

    // ANALISI BAN
    private Integer banCount;
    private Integer banCountBlue;
    private Integer banCountRed;
    private Double banRate;
    private Double banRateBlue;
    private Double banRateRed;
    private Integer winCountBan;
    private Integer winCountBanBlue;
    private Integer winCountBanRed;
    private Double winRateBan;
    private Double winRateBanBlue;
    private Double winRateBanRed;

    public ChampDTO getChamp() {
        return champ;
    }
    public void setChamp(ChampDTO champ) {
        this.champ = champ;
    }
    public Integer getPickCount() {
        return pickCount;
    }
    public void setPickCount(Integer pickCount) {
        this.pickCount = pickCount;
    }
    public Integer getPickCountBlue() {
        return pickCountBlue;
    }
    public void setPickCountBlue(Integer pickCountBlue) {
        this.pickCountBlue = pickCountBlue;
    }
    public Integer getPickCountRed() {
        return pickCountRed;
    }
    public void setPickCountRed(Integer pickCountRed) {
        this.pickCountRed = pickCountRed;
    }
    public Double getPickRate() {
        return pickRate;
    }
    public void setPickRate(Double pickRate) {
        this.pickRate = pickRate;
    }
    public Double getPickRateBlue() {
        return pickRateBlue;
    }
    public void setPickRateBlue(Double pickRateBlue) {
        this.pickRateBlue = pickRateBlue;
    }
    public Double getPickRateRed() {
        return pickRateRed;
    }
    public void setPickRateRed(Double pickRateRed) {
        this.pickRateRed = pickRateRed;
    }
    public Integer getWinCountPick() {
        return winCountPick;
    }
    public void setWinCountPick(Integer winCountPick) {
        this.winCountPick = winCountPick;
    }
    public Integer getWinCountPickBlue() {
        return winCountPickBlue;
    }
    public void setWinCountPickBlue(Integer winCountPickBlue) {
        this.winCountPickBlue = winCountPickBlue;
    }
    public Integer getWinCountPickRed() {
        return winCountPickRed;
    }
    public void setWinCountPickRed(Integer winCountPickRed) {
        this.winCountPickRed = winCountPickRed;
    }
    public Double getWinRatePick() {
        return winRatePick;
    }
    public void setWinRatePick(Double winRatePick) {
        this.winRatePick = winRatePick;
    }
    public Double getWinRatePickBlue() {
        return winRatePickBlue;
    }
    public void setWinRatePickBlue(Double winRatePickBlue) {
        this.winRatePickBlue = winRatePickBlue;
    }
    public Double getWinRatePickRed() {
        return winRatePickRed;
    }
    public void setWinRatePickRed(Double winRatePickRed) {
        this.winRatePickRed = winRatePickRed;
    }
    public Integer getBanCount() {
        return banCount;
    }
    public void setBanCount(Integer banCount) {
        this.banCount = banCount;
    }
    public Integer getBanCountBlue() {
        return banCountBlue;
    }
    public void setBanCountBlue(Integer banCountBlue) {
        this.banCountBlue = banCountBlue;
    }
    public Integer getBanCountRed() {
        return banCountRed;
    }
    public void setBanCountRed(Integer banCountRed) {
        this.banCountRed = banCountRed;
    }
    public Double getBanRate() {
        return banRate;
    }
    public void setBanRate(Double banRate) {
        this.banRate = banRate;
    }
    public Double getBanRateBlue() {
        return banRateBlue;
    }
    public void setBanRateBlue(Double banRateBlue) {
        this.banRateBlue = banRateBlue;
    }
    public Double getBanRateRed() {
        return banRateRed;
    }
    public void setBanRateRed(Double banRateRed) {
        this.banRateRed = banRateRed;
    }
    public Integer getWinCountBan() {
        return winCountBan;
    }
    public void setWinCountBan(Integer winCountBan) {
        this.winCountBan = winCountBan;
    }
    public Integer getWinCountBanBlue() {
        return winCountBanBlue;
    }
    public void setWinCountBanBlue(Integer winCountBanBlue) {
        this.winCountBanBlue = winCountBanBlue;
    }
    public Integer getWinCountBanRed() {
        return winCountBanRed;
    }
    public void setWinCountBanRed(Integer winCountBanRed) {
        this.winCountBanRed = winCountBanRed;
    }
    public Double getWinRateBan() {
        return winRateBan;
    }
    public void setWinRateBan(Double winRateBan) {
        this.winRateBan = winRateBan;
    }
    public Double getWinRateBanBlue() {
        return winRateBanBlue;
    }
    public void setWinRateBanBlue(Double winRateBanBlue) {
        this.winRateBanBlue = winRateBanBlue;
    }
    public Double getWinRateBanRed() {
        return winRateBanRed;
    }
    public void setWinRateBanRed(Double winRateBanRed) {
        this.winRateBanRed = winRateBanRed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamAnalysisDTO that = (TeamAnalysisDTO) o;
        return Objects.equals(champ, that.champ) && Objects.equals(pickCount, that.pickCount) && Objects.equals(pickCountBlue, that.pickCountBlue) && Objects.equals(pickCountRed, that.pickCountRed) && Objects.equals(pickRate, that.pickRate) && Objects.equals(pickRateBlue, that.pickRateBlue) && Objects.equals(pickRateRed, that.pickRateRed) && Objects.equals(winCountPick, that.winCountPick) && Objects.equals(winCountPickBlue, that.winCountPickBlue) && Objects.equals(winCountPickRed, that.winCountPickRed) && Objects.equals(winRatePick, that.winRatePick) && Objects.equals(winRatePickBlue, that.winRatePickBlue) && Objects.equals(winRatePickRed, that.winRatePickRed) && Objects.equals(banCount, that.banCount) && Objects.equals(banCountBlue, that.banCountBlue) && Objects.equals(banCountRed, that.banCountRed) && Objects.equals(banRate, that.banRate) && Objects.equals(banRateBlue, that.banRateBlue) && Objects.equals(banRateRed, that.banRateRed) && Objects.equals(winCountBan, that.winCountBan) && Objects.equals(winCountBanBlue, that.winCountBanBlue) && Objects.equals(winCountBanRed, that.winCountBanRed) && Objects.equals(winRateBan, that.winRateBan) && Objects.equals(winRateBanBlue, that.winRateBanBlue) && Objects.equals(winRateBanRed, that.winRateBanRed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(champ, pickCount, pickCountBlue, pickCountRed, pickRate, pickRateBlue, pickRateRed, winCountPick, winCountPickBlue, winCountPickRed, winRatePick, winRatePickBlue, winRatePickRed, banCount, banCountBlue, banCountRed, banRate, banRateBlue, banRateRed, winCountBan, winCountBanBlue, winCountBanRed, winRateBan, winRateBanBlue, winRateBanRed);
    }
}
