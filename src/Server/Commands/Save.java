package Server.Commands;

import Server.Manager.CollectionManager;

import Server.Manager.Server;
import Server.Manager.XMLWorker;
import lib.Models.Movie;


import java.io.*;

import java.util.LinkedList;

public class Save extends Command {

    CollectionManager collectionManager;

    public Save(Server server, CollectionManager collectionManager) {
        super(server, "save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        System.out.println("Коллекция сохранена!");
    }


    public void action(File file) throws IOException {
        LinkedList<Movie> collection = collectionManager.getCollection();
        XMLWorker xmlWorker = new XMLWorker(file);
        xmlWorker.write(collection);
    }
}
