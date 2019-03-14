package org.courses.commands.jdbc;

import org.courses.DAO.ManufactureDao;
import org.courses.DAO.SockTypeDao;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;
import org.courses.domain.hbm.Manufacture;

import java.sql.SQLException;

public class AddManufactureCommand extends AbstractQueryCommand implements Command {
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
            if(!typeName.equals("Clear")){
                if(dao != null){
                    dao.ChangeName(typeName);
                    dao.Save();
                }
                if(dao == null) {
                    dao = new ManufactureDao(typeName, con);
                    dao.Save();
                }
                typeName = null;
            }else{
                dao = null;
                typeName = null;
            }
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
