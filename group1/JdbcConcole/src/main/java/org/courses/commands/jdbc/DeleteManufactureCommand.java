package org.courses.commands.jdbc;

import org.courses.DAO.ManufactureDao;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;

import java.sql.SQLException;

public class DeleteManufactureCommand extends AbstractQueryCommand implements Command {
    private String typeName = null;
    ManufactureDao dao = null;
    @Override
    public void parse(String[] args) {
        if (args.length > 0) {
            typeName = args[0];
        }
        else {
            throw new CommandFormatException("ManufactureDao name is not specified");
        }
    }
    @Override
    public void execute() {
        try {
            dao = new ManufactureDao(con);
            dao.Delete(Integer.parseInt(typeName));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
