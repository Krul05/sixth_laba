package Server.Commands;

import Server.Manager.CollectionManager;
import Server.Manager.Server;
import Server.Manager.MovieScan;
import lib.Models.Movie;
import lib.Models.MovieEntr;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class Add extends Command{
    CollectionManager collectionManager;
    public Add(Server server, CollectionManager collectionManager) {
        super(server, "add", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }


    public String action(MovieEntr movieEntr) throws IOException, ClassNotFoundException {
        Movie movie = new Movie(movieEntr.getName(), movieEntr.getCoordinates(), movieEntr.getOscarsCount(), movieEntr.getGenre(), movieEntr.getMpaaRating(), movieEntr.getOperator());
        collectionManager.getCollection().add(movie);
        Collections.sort(collectionManager.getCollection());
        return "Команда выполнена!";
    }
    public void action(Scanner scanner) {
        MovieScan movieScan = new MovieScan(scanner);
        Movie movie = movieScan.movieScan();
        if (movie!= null) {
            collectionManager.getCollection().add(movie);
            Collections.sort(collectionManager.getCollection());
        }
    }

}
