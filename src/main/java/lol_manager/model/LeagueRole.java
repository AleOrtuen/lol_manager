package lol_manager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "league_roles")
public class LeagueRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long idRole;

    @ManyToOne
    @JoinColumn(name = "id_champ")
    private Champion champ;

    @Column(name = "role")
    private String role;

    public LeagueRole() {}

    public LeagueRole(Champion champ, String role) {
        this.champ = champ;
        this.role = role;
    }

    public Long getIdRole() {
        return idRole;
    }
    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }
    public Champion getChamp() {
        return champ;
    }
    public void setChamp(Champion champ) {
        this.champ = champ;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

}
