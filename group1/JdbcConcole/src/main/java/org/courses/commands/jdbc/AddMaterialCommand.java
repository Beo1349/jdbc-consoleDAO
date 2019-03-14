package org.courses.commands.jdbc;

import org.courses.DAO.ManufactureDao;
import org.courses.DAO.MaterialDao;
import org.courses.DAO.SockTypeDao;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;
import org.courses.domain.hbm.Material;
import org.hibernate.Session;

import java.sql.SQLException;

public class AddMaterialCommand extends AbstractQueryCommand implements Command {
    private String typeName = null;
    MaterialDao dao = null;
    @Override
    public void parse(String[] args) {
        if (args.length > 0) {
            typeName = args[0];
        }
        else {
            throw new CommandFormatException("MaterialDao name is not specified");
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
                    dao = new MaterialDao(typeName, con);
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
