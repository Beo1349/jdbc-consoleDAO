package org.courses.DAO;

import org.courses.domain.hbm.Manufacture;
import org.courses.domain.hbm.Material;
import org.courses.domain.jdbc.BaseEntity;
import org.courses.domain.jdbc.Column;

import javax.persistence.Entity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

@Entity(name = "Material")
public class MaterialDao extends AbstractDao {

    private String name;

    public MaterialDao(Connection connection){
        con = connection;
        tableName = "Material";
        Entity = new Material();
        Entity.setTable(tableName);
    }
    public MaterialDao(String n, Connection connection){
        name = n;
        con = connection;
        tableName = "Material";
        Entity = new Material(n);
        Entity.setTable(tableName);
    }
    public void ChangeName(String s){
        ((Material)Entity)._setName(s);
    }
    @Override
    protected BaseEntity getresult(ResultSet results) throws SQLException {
        results.next();
        //BaseEntity dao = new Material();
        ((Material) Entity).setId(results.getInt("id"));
        ((Material) Entity).setName(results.getString("name"));
        System.out.println(((Material)Entity).getId() + " " + ((Material) Entity)._getName());
        return Entity;
    }

    @Override
    protected LinkedList<BaseEntity> getresult(LinkedList<BaseEntity> l, ResultSet results) throws SQLException {
        while(results.next()){
            //BaseEntity dao = new Material();
            ((Material) Entity).setId(results.getInt("id"));
            ((Material) Entity).setName(results.getString("name"));
            l.add(Entity);
            System.out.println(((Material)Entity).getId() + " " + ((Material) Entity)._getName());
        }
        results.close();
        return l;
    }
}
