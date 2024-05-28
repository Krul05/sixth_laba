package Server.Commands;

import Server.Manager.CollectionManager;
import Server.Manager.CommandManager;
import Server.Manager.Server;
import lib.Commands;
import lib.Models.MovieGenre;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Scanner;


public class ExecuteScript extends Command{
    CollectionManager collectionManager;
    public ExecuteScript(Server server, CollectionManager collectionManager) {
        super(server, "execute_script", "считать и исполнить скрипт из файла");
        this.collectionManager = collectionManager;
    }

    public String action(String file) throws IOException {
        try {
            FileReader fileReader = new FileReader(file);
            Scanner scan = new Scanner(fileReader);
            String[] elements;
            while (scan.hasNextLine()) {
                elements = scan.nextLine().trim().split(" ");
                Commands commands = null;
                if (elements[0].equals("remove_by_id") || elements[0].equals("update") || elements[0].equals("execute_script") || elements[0].equals("remove_all_by_genre") || elements[0].equals("filter_greater_than_oscars_count")) {
                    if (elements.length == 2) {
                        try {
                            if (elements[0].equals("remove_by_id") || elements[0].equals("update") || elements[0].equals("filter_greater_than_oscars_count")) {
                                commands = new Commands(elements[0], Integer.parseInt(elements[1]));
                            } else if (elements[0].equals("execute_script")) {
                                commands = new Commands(elements[0], elements[1]);
                            } else {
                                commands = new Commands(elements[0], MovieGenre.valueOf(elements[1]));
                            }
                        } catch (Exception ex) {
                            commands = null;
                        }
                    } else {
                        commands = null;
                    }
                } else {
                    commands = new Commands(elements[0]);
                }
                CommandManager commandManager = new CommandManager(server, collectionManager);
                commandManager.setScanner(scan);
                commandManager.commandManage(commands);
            }
            return "Команда выполнена!";
        } catch (FileNotFoundException exception) {
            return "Такого файла нет";
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
