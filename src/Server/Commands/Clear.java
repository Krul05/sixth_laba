package Server.Commands;

import Server.Manager.CollectionManager;
import Server.Manager.Server;

import java.io.IOException;

public class Clear extends Command{
    CollectionManager collectionManager;
    public Clear(Server server, CollectionManager collectionManager) {
        super(server, "clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }


    public String action() throws IOException {
        collectionManager.getCollection().clear();
        return "Команда выполнена!";
    }
}
