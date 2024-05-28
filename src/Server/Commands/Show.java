package Server.Commands;

import Server.Manager.CollectionManager;
import Server.Manager.Server;

import java.io.IOException;

public class Show extends Command{

    CollectionManager collectionManager;
    public Show(Server server, CollectionManager collectionManager) {
        super(server, "show", "вывести все элементы коллекции");
        this.collectionManager = collectionManager;
    }


    public String action() throws IOException {
        return collectionManager.show();
    }
}
