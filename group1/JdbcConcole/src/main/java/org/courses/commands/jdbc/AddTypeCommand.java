package org.courses.commands.jdbc;

import org.courses.DAO.ManufactureDao;
import org.courses.DAO.SockTypeDao;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;
import org.courses.domain.hbm.SockType;

import java.sql.SQLException;

public class AddTypeCommand extends AbstractQueryCommand implements Command {
    private String typeName = null;
    SockTypeDao dao = null;
    @Override
    public void parse(String[] args) {
        if (args.length > 0) {
            typeName = args[0];
        }
        else {
            throw new CommandFormatException("SockTypeDao name is not specified");
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
                    dao = new SockTypeDao(typeName, con);
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