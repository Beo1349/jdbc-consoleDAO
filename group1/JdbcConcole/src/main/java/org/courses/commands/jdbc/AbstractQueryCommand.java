package org.courses.commands.jdbc;

import org.courses.domain.hbm.Manufacture;
import org.courses.domain.hbm.Material;
import org.courses.domain.hbm.Socks;
import org.courses.domain.jdbc.BaseEntity;
import org.courses.domain.jdbc.Column;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Collection;

public abstract class AbstractQueryCommand {
    protected String dbFile;
    protected Connection con = ConnectionManager.getConnection("socksdb.db");
    protected Connection connect() throws SQLException {
        String url = connectionString();
        return DriverManager.getConnection(url);
    }

    private String connectionString() {
        Path path = Paths.get(dbFile);
        return String.format("jdbc:sqlite:%s", path.toAbsolutePath());
    }
    SessionFactory sessionFactory;

    SessionFactory getSessionFactory() {
        if (null == sessionFactory) {
            sessionFactory = new Configuration()
                    //.configure("/hbm/hibernate.cfg.xml")
                    .setProperty("connection.driver_class", "org.sqlite.JDBC")
                    .setProperty("dialect", "org.hibernate.dialect.SQLiteDialect")
                    .setProperty("connection.pool_size", "1")
                    .setProperty("show_sql", "true")
                    .setProperty("format_sql", "true")
                    .setProperty("hibernate.jdbc.batch_size", "30")
                    .setProperty("hibernate.connection.url", "jdbc:sqlite:test.db")
                    .addAnnotatedClass(Manufacture.class)
                    .addAnnotatedClass(Material.class)
                    .addAnnotatedClass(Socks.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}
