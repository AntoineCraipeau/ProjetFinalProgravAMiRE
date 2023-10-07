package org.amire.progav_finalproj.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin", schema = "db_prograv_final", catalog = "")
public class AdminEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_admin", nullable = false)
    private long idAdmin;

    public long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(long idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminEntity that = (AdminEntity) o;

        if (idAdmin != that.idAdmin) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (idAdmin ^ (idAdmin >>> 32));
    }
}
