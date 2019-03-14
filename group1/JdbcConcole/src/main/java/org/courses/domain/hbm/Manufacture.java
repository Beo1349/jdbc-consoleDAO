package org.courses.domain.hbm;

import org.courses.domain.jdbc.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "Manufacture")
public class Manufacture extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public Manufacture(){}
    public Manufacture(String n){name = n;}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String _getName() {
        return name;
    }
    public void  _setName(String n) {
        name = n;
    }

    public void setName(String name) {
        this.name = name;
    }
}
