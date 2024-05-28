package Server.Commands;

import Server.Manager.CollectionManager;
import Server.Manager.Server;

import java.io.IOException;

public class RemoveFirst extends Command {

    CollectionManager collectionManager;
    public RemoveFirst(Server server, CollectionManager collectionManager) {
        super(server, "remove_first", "удалить первый элемент из коллекции");
        this.collectionManager = collectionManager;
    }


    public String action() throws IOException {
        try {
            collectionManager.getCollection().remove(0);
            return "Команда выполнена!";
        } catch (Exception ex) {
            return "Коллекция пуста";
        }
    }
}
