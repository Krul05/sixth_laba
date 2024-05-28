package Server.Commands;

import Server.Manager.CollectionManager;
import Server.Manager.Server;

import java.io.IOException;

public class Exit extends Command{
    CollectionManager collectionManager;

    public Exit(Server server, CollectionManager collectionManager) {
        super(server, "exit", "завершить программу (без сохранения в файл)");
        this.collectionManager = collectionManager;
    }


    public String action() throws IOException {
        Save save = new Save(server, collectionManager);
        save.action(collectionManager.getFile());
        return "exit";
    }
}
