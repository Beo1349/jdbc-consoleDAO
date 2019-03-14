package org.courses.DAO;

import org.courses.domain.hbm.Manufacture;
import org.courses.domain.hbm.Material;
import org.courses.domain.hbm.SockType;
import org.courses.domain.jdbc.BaseEntity;
import org.courses.domain.jdbc.Column;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.persistence.*;

@Entity(name = "Type")
public class SockTypeDao extends AbstractDao {

    private String name;
    public SockTypeDao(Connection connection){
        con = connection;
        tableName = "Type";
        Entity = new SockType();
        Entity.setTable(tableName);
    }
    public SockTypeDao(String n, Connection connection){
        name = n;
        con = connection;
        tableName = "Type";
        Entity = new SockType(n);
        Entity.setTable(tableName);
    }
    public void ChangeName(String s){
        ((SockType)Entity)._setName(s);
    }
    @Override
    protected BaseEntity getresult(ResultSet results) throws SQLException {
        results.next();
        ((SockType) Entity).setId(results.getInt("id"));
        ((SockType) Entity).setName(results.getString("name"));
        System.out.println(((SockType)Entity).getId() + " " + ((SockType)Entity)._getName());
        return Entity;
    }
    @Override
    protected LinkedList<BaseEntity> getresult(LinkedList<BaseEntity> l, ResultSet results) throws SQLException {
        while(results.next()){
            ((SockType) Entity).setId(results.getInt("id"));
            ((SockType) Entity).setName(results.getString("name"));
            l.add(Entity);
            System.out.println(((SockType)Entity).getId() + " " + ((SockType) Entity)._getName());
        }
        results.close();
        return l;
    }
}
