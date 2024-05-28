package Server.Commands;

import Server.Manager.CollectionManager;
import Server.Manager.Server;
import lib.Models.Movie;

import java.io.IOException;
import java.util.LinkedList;

public class Head extends Command{

    CollectionManager collectionManager;
    public Head(Server server, CollectionManager collectionManager) {
        super(server, "head", "вывести первый элемент коллекции");
        this.collectionManager = collectionManager;
    }


    public String action() throws IOException {
        LinkedList<Movie> collection = collectionManager.getCollection();
        try {
            return collection.get(0).getMovie();
        } catch (Exception ex) {
            return "Коллекция пуста";
        }

    }
}
