package Server.Commands;

import Server.Manager.CollectionManager;
import Server.Manager.Server;
import Server.Manager.MovieScan;
import lib.Models.Movie;
import lib.Models.MovieEntr;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class AddIfMax extends Command{

    CollectionManager collectionManager;
    public AddIfMax(Server server, CollectionManager collectionManager) {
        super(server, "add_if_max", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.collectionManager = collectionManager;
    }

    public String action(MovieEntr movieEntr) throws IOException, ClassNotFoundException {
        Movie movie = new Movie(movieEntr.getName(), movieEntr.getCoordinates(), movieEntr.getOscarsCount(), movieEntr.getGenre(), movieEntr.getMpaaRating(), movieEntr.getOperator());
        collectionManager.getCollection().add(movie);
        LinkedList<Movie> collection = collectionManager.getCollection();
        int n = collection.size()-1;
        if (movie.compareTo(collection.get(n)) > 0) {
            collectionManager.getCollection().add(movie);
        }
        return "Команда выполнена!";
    }
    public void action(Scanner scanner) {
        MovieScan movieScan = new MovieScan(scanner);
        Movie movie = movieScan.movieScan();
        if (movie!=null) {
            LinkedList<Movie> collection = collectionManager.getCollection();
            int n = collection.size() - 1;
            if (movie.compareTo(collection.get(n)) > 0) {
                collectionManager.getCollection().add(movie);
            }
        }
    }
}
