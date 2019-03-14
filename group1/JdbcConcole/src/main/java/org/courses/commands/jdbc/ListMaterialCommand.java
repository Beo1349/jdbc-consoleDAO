package org.courses.commands.jdbc;

import org.courses.DAO.ManufactureDao;
import org.courses.DAO.MaterialDao;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListMaterialCommand extends AbstractQueryCommand implements Command {
    private String filter = null;

    @Override
    public void parse(String[] args) {
        if (args != null) {
            filter = args[0];
        }
        else {
            System.out.println("Filter is not specified");
        }
    }
    @Override
    public void execute() {
        try {
            MaterialDao dao = new MaterialDao(con);

            if(filter == null){
                dao.ReadAll();
                //System.out.println("!");
            }
            else dao.Read(Integer.parseInt(filter));
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
