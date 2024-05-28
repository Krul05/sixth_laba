package Server.Commands;

import Server.Manager.CollectionManager;
import Server.Manager.Server;
import lib.Models.Movie;

import java.io.IOException;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class RemoveById extends Command{
    CollectionManager collectionManager;
    public RemoveById(Server server, CollectionManager collectionManager) {
        super(server, "remove_by_id", "удалить элемент коллекции по его id");
        this.collectionManager = collectionManager;
    }


    public String action(int id) throws IOException {
        LinkedList<Movie> collection = collectionManager.getCollection();
        collectionManager.setCollection(collection.stream().filter(movie -> movie.getId()!=id).collect(Collectors.toCollection(LinkedList::new)));
        return "Команда выполнена!";
    }
}
