package org.courses.DAO;
import org.courses.domain.hbm.Manufacture;
import org.courses.domain.hbm.Material;
import org.courses.domain.jdbc.BaseEntity;
import org.courses.domain.jdbc.Column;

import javax.naming.Name;
import javax.persistence.Entity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

@Entity(name = "Manufacture")
public class ManufactureDao extends AbstractDao {

    private String name;

    public ManufactureDao(Connection connection){
        con = connection;
        tableName = "Manufacture";
        Entity = new Manufacture();
        Entity.setTable(tableName);
    }
    public ManufactureDao(String n, Connection connection){
        name = n;
        con = connection;
        tableName = "Manufacture";
        Entity = new Manufacture(n);
        Entity.setTable(tableName);
    }
    public void ChangeName(String s){
        ((Manufacture)Entity)._setName(s);
    }
    @Override
    protected BaseEntity getresult(ResultSet results) throws SQLException {
        results.next();
        //BaseEntity dao = new Manufacture();
        ((Manufacture) Entity).setId(results.getInt("id"));
        ((Manufacture) Entity).setName(results.getString("name"));
        System.out.println(((Manufacture)Entity).getId() + " " + ((Manufacture) Entity)._getName());
        return Entity;
    }
    @Override
    protected LinkedList<BaseEntity> getresult(LinkedList<BaseEntity> l, ResultSet results) throws SQLException {
        while(results.next()){
            //BaseEntity dao = new Manufacture();
            ((Manufacture) Entity).setId(results.getInt("id"));
            ((Manufacture) Entity).setName(results.getString("name"));
            l.add(Entity);
            System.out.println(((Manufacture)Entity).getId() + " " + ((Manufacture) Entity)._getName());
        }
        results.close();
        return l;
    }
}
