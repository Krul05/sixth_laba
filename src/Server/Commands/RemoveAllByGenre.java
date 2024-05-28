package Server.Commands;

import Server.Manager.CollectionManager;
import Server.Manager.Server;
import lib.Models.Movie;
import lib.Models.MovieGenre;

import java.io.IOException;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class RemoveAllByGenre extends Command{

    CollectionManager collectionManager;
    public RemoveAllByGenre(Server server, CollectionManager collectionManager) {
        super(server, "remove_all_by_genre", "удалить из коллекции все элементы, значение поля genre которых эквивалентно заданному");
        this.collectionManager = collectionManager;
    }


    public String action(MovieGenre genre) throws IOException {
        LinkedList<Movie> collection = collectionManager.getCollection();
        collectionManager.setCollection(collection.stream().filter(movie -> movie.getGenre()!=genre).collect(Collectors.toCollection(LinkedList::new)));
        return "Команда выполнена!";
    }
}
