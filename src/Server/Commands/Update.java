package Server.Commands;

import Server.Manager.CollectionManager;
import Server.Manager.Server;
import lib.Models.Movie;
import lib.Models.MovieEntr;

import java.io.IOException;
import java.util.LinkedList;

public class Update extends Command{
    CollectionManager collectionManager;
    public Update(Server server, CollectionManager collectionManager) {
        super(server, "update", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;

    }
    public Movie movieEntr(MovieEntr movieEntr) throws IOException, ClassNotFoundException {
        Movie movie = new Movie(movieEntr.getName(), movieEntr.getCoordinates(), movieEntr.getOscarsCount(), movieEntr.getGenre(), movieEntr.getMpaaRating(), movieEntr.getOperator());
        collectionManager.getCollection().add(movie);
        return movie;
    }

    public String action(int id, MovieEntr movieEntr) throws IOException, ClassNotFoundException {
        LinkedList<Movie> collection = collectionManager.getCollection();
        Movie movie1 = movieEntr(movieEntr);
        collection.stream().filter(movie -> movie.getId() == id).forEach(movie -> movie.setMovie(movie1));
        return "Команда выполнена!";
    }

}
