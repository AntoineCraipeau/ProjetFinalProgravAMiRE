package org.amire.progav_finalproj.model;

import jakarta.persistence.*;

@Entity
@Table(name = "userinfo", schema = "db_prograv_final")
public class UserinfoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_userinfo", nullable = false)
    private long idUserinfo;
    @Basic
    @Column(name = "Login", nullable = false, length = 255)
    private String login;
    @Basic
    @Column(name = "Password", nullable = false, length = 255)
    private String password;
    @Basic
    @Column(name = "id_enseignant", nullable = true, insertable = false, updatable = false)
    private Long idEnseignant;
    @Basic
    @Column(name = "id_ecole", nullable = true, insertable = false, updatable = false)
    private Long idEcole;
    @Basic
    @Column(name = "id_admin", nullable = true, insertable = false, updatable = false)
    private Long idAdmin;
    @ManyToOne
    @JoinColumn(name = "id_enseignant", referencedColumnName = "id_enseignant")
    private EnseignantEntity enseignantByIdEnseignant;
    @ManyToOne
    @JoinColumn(name = "id_ecole", referencedColumnName = "id_ecole")
    private EcoleEntity ecoleByIdEcole;
    @ManyToOne
    @JoinColumn(name = "id_admin", referencedColumnName = "id_admin")
    private AdminEntity adminByIdAdmin;

    public long getIdUserinfo() {
        return idUserinfo;
    }

    public void setIdUserinfo(long idUserinfo) {
        this.idUserinfo = idUserinfo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Long idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public Long getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(Long idEcole) {
        this.idEcole = idEcole;
    }

    public Long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserinfoEntity that = (UserinfoEntity) o;

        if (idUserinfo != that.idUserinfo) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (idEnseignant != null ? !idEnseignant.equals(that.idEnseignant) : that.idEnseignant != null) return false;
        if (idEcole != null ? !idEcole.equals(that.idEcole) : that.idEcole != null) return false;
        if (idAdmin != null ? !idAdmin.equals(that.idAdmin) : that.idAdmin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idUserinfo ^ (idUserinfo >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (idEnseignant != null ? idEnseignant.hashCode() : 0);
        result = 31 * result + (idEcole != null ? idEcole.hashCode() : 0);
        result = 31 * result + (idAdmin != null ? idAdmin.hashCode() : 0);
        return result;
    }

    public EnseignantEntity getEnseignantByIdEnseignant() {
        return enseignantByIdEnseignant;
    }

    public void setEnseignantByIdEnseignant(EnseignantEntity enseignantByIdEnseignant) {
        this.enseignantByIdEnseignant = enseignantByIdEnseignant;
    }

    public EcoleEntity getEcoleByIdEcole() {
        return ecoleByIdEcole;
    }

    public void setEcoleByIdEcole(EcoleEntity ecoleByIdEcole) {
        this.ecoleByIdEcole = ecoleByIdEcole;
    }

    public AdminEntity getAdminByIdAdmin() {
        return adminByIdAdmin;
    }

    public void setAdminByIdAdmin(AdminEntity adminByIdAdmin) {
        this.adminByIdAdmin = adminByIdAdmin;
    }
}
