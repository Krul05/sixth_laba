package Server.Manager;

import Client.Manager.ExecuteScript;
import lib.Commands;
import Server.Commands.*;
import lib.Models.MovieEntr;
import lib.Models.MovieGenre;
import lib.Response;


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

    public <T> void commandManage(Commands<T> commands) throws IOException, ClassNotFoundException {
        Response response = null;
        if (commands != null) {
            String name = commands.getName();
            T argument = commands.getArgument();
            MovieEntr movieEntr = commands.getMovie();
            if (name.equals("add")) {
                Add add = new Add(server, collectionManager);
                if (scanner == null) {
                    response = new Response(add.action(movieEntr));
                }
            } else if (name.equals("help")) {
                Help help = new Help(server);
                response = new Response(help.action());
            } else if (name.equals("info")) {
                Info info = new Info(server, collectionManager);
                response = new Response(info.action());
            } else if (name.equals("show")) {
                Show show = new Show(server, collectionManager);
                response = new Response(show.action());
            } else if (name.equals("update")) {
                if (argument != null) {
                    Update update = new Update(server, collectionManager);
                    if (scanner == null) {
                        response = new Response(update.action((Integer) argument, movieEntr));
                    }
                } else {
                    response = new Response("Вы забыли ввести id, пожалуйста, повторите ввод");
                }
            } else if (name.equals("remove_by_id")) {
                if (argument != null) {
                    RemoveById removeById = new RemoveById(server, collectionManager);
                    response = new Response(removeById.action((Integer) argument));
                } else {
                    response = new Response("Вы забыли ввести id, пожалуйста, повторите ввод");
                }
            } else if (name.equals("clear")) {
                Clear clear = new Clear(server, collectionManager);
                response = new Response(clear.action());
            } else if (name.equals("exit")) {
                Exit exit = new Exit(server, collectionManager);
                response = new Response(exit.action());
            } else if (name.equals("remove_first")) {
                RemoveFirst removeFirst = new RemoveFirst(server, collectionManager);
                response = new Response(removeFirst.action());
            } else if (name.equals("head")) {
                Head head = new Head(server, collectionManager);
                response = new Response(head.action());
            } else if (name.equals("add_if_max")) {
                AddIfMax addIfMax = new AddIfMax(server, collectionManager);
                if (scanner == null) {
                    response = new Response(addIfMax.action(movieEntr));
                }
            } else if (name.equals("remove_all_by_genre")) {
                if (argument != null) {
                    RemoveAllByGenre removeAllByGenre = new RemoveAllByGenre(server, collectionManager);
                    response = new Response(removeAllByGenre.action((MovieGenre) argument));
                } else {
                    response = new Response("Вы забыли указать жанр");
                }
            } else if (name.equals("filter_greater_than_oscars_count")) {
                if (argument != null) {
                    FilterGreaterThanOscarsCount filterGreaterThanOscarsCount = new FilterGreaterThanOscarsCount(server, collectionManager);
                    response = new Response(filterGreaterThanOscarsCount.action((Integer) argument));
                } else {
                    response = new Response("Вы забыли ввести количество оскаров, пожалуйста, повторите ввод");
                }
            } else if (name.equals("print_field_ascending_operator")) {
                PrintFieldAscendingOperator printFieldAscendingOperator = new PrintFieldAscendingOperator(server, collectionManager);
                response = new Response(printFieldAscendingOperator.action());
            } else {
                response = new Response("Такой команды нет, пожалуйста, повторите ввод");
            }
        }
        server.send(response);
    }
}
