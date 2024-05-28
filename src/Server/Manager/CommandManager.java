package Server.Manager;

import lib.Commands;
import Server.Commands.*;
import lib.Models.MovieEntr;
import lib.Models.MovieGenre;


import java.io.IOException;

import java.util.Scanner;
/**
 * Класс CommandManager - обрабатывает команды
 */
public class CommandManager {
    Server server;
    CollectionManager collectionManager;
    private Scanner scanner = null;
    public CommandManager(Server server, CollectionManager collectionManager) {
        this.server = server;
        this.collectionManager = collectionManager;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public <T> String commandManage(Commands<T> commands) throws IOException, ClassNotFoundException {
        String response = "";
        if (commands!=null) {
            String name = commands.getName();
            T argument = commands.getArgument();
            MovieEntr movieEntr = commands.getMovie();
            if (name.equals("add")) {
                Add add = new Add(server, collectionManager);
                if (scanner == null) {
                    response = add.action(movieEntr);
                } else {
                    add.action(scanner);
                }
            } else if (name.equals("help")) {
                Help help = new Help(server);
                response = help.action();
            } else if (name.equals("info")) {
                Info info = new Info(server, collectionManager);
                response = info.action();
            } else if (name.equals("show")) {
                Show show = new Show(server, collectionManager);
                response = show.action();
            } else if (name.equals("update")) {
                if (argument != null) {
                    Update update = new Update(server, collectionManager);
                    if (scanner == null) {
                        response = update.action((Integer) argument, movieEntr);
                    } else {
                        update.action((Integer) argument, scanner);
                    }
                } else {
                    response = "Вы забыли ввести id, пожалуйста, повторите ввод";
                }
            } else if (name.equals("remove_by_id")) {
                if (argument != null) {
                    RemoveById removeById = new RemoveById(server, collectionManager);
                    response = removeById.action((Integer) argument);
                } else {
                    response = "Вы забыли ввести id, пожалуйста, повторите ввод";
                }
            } else if (name.equals("clear")) {
                Clear clear = new Clear(server, collectionManager);
                response = clear.action();
            } else if (name.equals("exit")) {
                Exit exit = new Exit(server, collectionManager);
                response = exit.action();
            } else if (name.equals("execute_script")) {
                ExecuteScript executeScript = new ExecuteScript(server, collectionManager);
                if (argument != null) {
                    response = executeScript.action((String) argument);
                } else {
                    response = "Вы забыли ввести путь к файлу";
                }
            } else if (name.equals("remove_first")) {
                RemoveFirst removeFirst = new RemoveFirst(server, collectionManager);
                response = removeFirst.action();
            } else if (name.equals("head")) {
                Head head = new Head(server, collectionManager);
                response = head.action();
            } else if (name.equals("add_if_max")) {
                AddIfMax addIfMax = new AddIfMax(server, collectionManager);
                if (scanner == null) {
                    response = addIfMax.action(movieEntr);
                } else {
                    addIfMax.action(scanner);
                }
            } else if (name.equals("remove_all_by_genre")) {
                if (argument != null) {
                    RemoveAllByGenre removeAllByGenre = new RemoveAllByGenre(server, collectionManager);
                    response = removeAllByGenre.action((MovieGenre) argument);
                } else {
                    response = "Вы забыли указать жанр";
                }
            } else if (name.equals("filter_greater_than_oscars_count")) {
                if (argument != null) {
                    FilterGreaterThanOscarsCount filterGreaterThanOscarsCount = new FilterGreaterThanOscarsCount(server, collectionManager);
                    response = filterGreaterThanOscarsCount.action((Integer) argument);
                } else {
                    response = "Вы забыли ввести количество оскаров, пожалуйста, повторите ввод";
                }
            } else if (name.equals("print_field_ascending_operator")) {
                PrintFieldAscendingOperator printFieldAscendingOperator = new PrintFieldAscendingOperator(server, collectionManager);
                response = printFieldAscendingOperator.action();
            } else {
                response = "Такой команды нет, пожалуйста, повторите ввод";
            }
        }
        return response;
    }
}
