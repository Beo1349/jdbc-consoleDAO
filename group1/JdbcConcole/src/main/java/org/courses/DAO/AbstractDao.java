package org.courses.DAO;

import org.courses.domain.jdbc.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Entity(name = "BaseEntity")
public abstract class AbstractDao extends BaseEntity implements DAOInterface {

    protected BaseEntity Entity;
    @Column(name = "id")
    protected int id = -1;
    protected Connection con;
   // protected String tableName;
   // protected String[] columns;
    public int getId() {
        return id;
    }
    public void Save() throws SQLException, IllegalAccessException {
        if (id == -1) {
            this.id = StaticDAOOperation.insert(Entity, this.con);
        } else {
            StaticDAOOperation.update(Entity,this.con);
        }
    }
    @Override
    public BaseEntity Read(int id) throws SQLException {
        String filter = String.format("id = %s", id);
        ResultSet results = StaticDAOOperation.select(Entity.getName(),"*", filter, this.con);
        BaseEntity obj = getresult(results);
        return obj;
    }

    protected abstract BaseEntity getresult(ResultSet results) throws SQLException;
    protected abstract LinkedList<BaseEntity> getresult(LinkedList<BaseEntity> l, ResultSet results) throws SQLException;
    @Override
    public List<BaseEntity> ReadAll() throws SQLException {
        LinkedList<BaseEntity> list = new LinkedList<>();
        ResultSet results = StaticDAOOperation.select(Entity.getName(),"*", "id", this.con);
        list = getresult(list,results);
        return list;
    }
    @Override
    public void Delete(int id) throws SQLException {
        StaticDAOOperation.Delete(Entity.getName(), id, this.con);
    }
}
