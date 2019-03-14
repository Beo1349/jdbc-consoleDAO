package org.courses;

import org.courses.DAO.ManufactureDao;
import org.courses.DAO.MaterialDao;
import org.courses.DAO.SockTypeDao;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;
import org.courses.commands.jdbc.*;
import org.apache.tools.ant.types.Commandline;
import org.courses.domain.hbm.Manufacture;
import org.courses.domain.hbm.Material;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    static Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("addtype", new AddTypeCommand());
        commands.put("addmaterial", new AddMaterialCommand());
        commands.put("addmanufacture", new AddManufactureCommand());
        commands.put("listmaterial", new ListMaterialCommand());
        commands.put("listtype", new ListTypeCommand());
        commands.put("listmanufacture", new ListManufactureCommand());
        commands.put("deletematerial", new DeleteMaterialCommand());
        commands.put("deletetype", new DeleteSockTypeCommand());
        commands.put("deletemanufacture", new DeleteManufactureCommand());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        greetUser();
        while (scanner.hasNext()) {
            try {
                String line = scanner.nextLine();
                parseUserInput(line);
                greetUser();
            }
            catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    private static void parseUserInput(String input) {
        String userCommand[] = Commandline.translateCommandline(input);
        String[] params = null;
        String commandName = userCommand[0];
        if(userCommand.length > 1)
        params = new String[userCommand.length - 1];

        if(params != null)
        System.arraycopy(userCommand, 1, params, 0, params.length);

        try {

            executeCommand(commandName, params);
        }
        catch (CommandFormatException e) {
            System.out.println(String.format("%s has some invalid arguments", commandName));
        }
        catch (NotImplementedException e) {
            System.out.println(String.format("Unknown command %s", commandName));
        }
    }

    private static void executeCommand(String commandName, String[] params) {
        Command command = commands.get(commandName);

        if (null == command)
            throw new NotImplementedException();

        command.parse(params);

        command.execute();
    }

    private static void greetUser() {
        System.out.print("courses-jdbc>");
    }
}
