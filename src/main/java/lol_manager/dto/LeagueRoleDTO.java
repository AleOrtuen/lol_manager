package lol_manager.dto;

public class LeagueRoleDTO {

    private Long idRole;
    private ChampDTO champ;
    private String role;

    public Long getIdRole() {
        return idRole;
    }
    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }
    public ChampDTO getChamp() {
        return champ;
    }
    public void setChamp(ChampDTO champ) {
        this.champ = champ;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
