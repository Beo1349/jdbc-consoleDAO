package org.courses.DAO;

import org.courses.domain.jdbc.BaseEntity;
import org.courses.domain.jdbc.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public class StaticDAOOperation {

    static int insert(String table, String columns, String values, Connection con) throws SQLException {
        Statement statement = con.createStatement();
        System.out.println(String.format("INSERT INTO %s" +
                "(%s) " +
                "VALUES" +
                "(%s)", table, columns, values));
        statement.execute(String.format("INSERT INTO %s" +
                "(%s) " +
                "VALUES" +
                "(%s)", table, columns, values));
        ResultSet rs = statement.executeQuery("SELECT id FROM " + table + " WHERE rowid=last_insert_rowid();");
        int id = rs.getInt("id");
        statement.close();
        return id;
        //return id of inserted record
    }

   public static void update(BaseEntity entity, Connection con) throws SQLException, IllegalAccessException {
       String table = entity.getName();
       StringBuilder columns = new StringBuilder();
       StringBuilder values = new StringBuilder();

       Collection<Column> definitions = entity.getColumns();

       fieldParse(table, columns, values, definitions, entity);

       update(table, columns.toString(), values.toString(),con);
   }
    public static int insert(BaseEntity entity, Connection con) throws IllegalAccessException, SQLException {
        String table = entity.getName();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        Collection<Column> definitions = entity.getColumns();

        fieldParse(null, columns, values, definitions, entity);

        return insert(table, columns.toString(), values.toString(),con);
    }
    static void update(String table, String columns, String values, Connection con) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT id FROM " + table + " WHERE rowid=last_insert_rowid();");
        int id = rs.getInt("id");
        System.out.println(String.format("UPDATE %s" +
                " SET %s " +
                "= %s where id = %s", table, columns, values, id));
        statement.execute(String.format("UPDATE %s" +
                " SET %s " +
                "= %s where id = %s", table, columns, values, id));
        statement.close();
    }

   public static ResultSet select(String table, String columns, String filter, Connection con) throws SQLException {
      Statement statement = con.createStatement();
        ResultSet results = statement.executeQuery(String.format("SELECT %s " +
                "FROM %s WHERE %s", columns, table, filter));
        return results;
    }
    public static void Delete(String table, int id, Connection con) throws SQLException {
      Statement statement = con.createStatement();
      System.out.println(String.format("delete from %s where id = %d", table, id));
      statement.execute(String.format("delete from %s where id = %d", table, id));
    }
    static void fieldParse(String table, StringBuilder columns, StringBuilder values,  Collection<Column> definitions, BaseEntity entity) throws IllegalAccessException {

        for (Column definition : definitions) {
            if (columns.length() > 0)
                columns.append(", ");
            columns.append(definition.getName());

            if (values.length() > 0)
                values.append(", ");
            Object value = entity.getColumn(definition);
            if (null == value)
                values.append("NULL");
            else if (value instanceof String)
                values.append(String.format("'%s'", (String)value));
            else
                values.append(value.toString());
        }
    }
}
