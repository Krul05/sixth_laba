package Server.Commands;

import Server.Manager.CollectionManager;
import Server.Manager.Server;

import java.io.IOException;

public class Info extends Command{

    CollectionManager collectionManager;
    public Info(Server server, CollectionManager collectionManager) {
        super(server, "info", "вывести информацию о коллекции (тип, дата инициализации, количество элементов и т.д.");
        this.collectionManager = collectionManager;
    }


    public String action() throws IOException {
        return collectionManager.getDiscription();
    }
}
