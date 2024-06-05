package Server.Manager;

import Server.Commands.Save;
import lib.Commands;
import lib.Response;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;
import java.util.logging.*;

public class Server {
    DatagramChannel dc;
    InetSocketAddress senderAddress;
    int SERVICE_PORT;

    public Server(int SERVICE_PORT) throws IOException {
        this.SERVICE_PORT = SERVICE_PORT;
    }

    public DatagramChannel getDc() {
        return dc;
    }

    public void send(Response response) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(response);
        byte[] responseBytes = baos.toByteArray();
        buffer.clear();
        buffer.put(responseBytes);
        buffer.flip();
        dc.send(buffer, senderAddress);
    }

    public void init(File file) throws IOException {
        Logger LOGGER = Logger.getLogger("MyLog");
        LOGGER.setUseParentHandlers(false);
        try {
            FileHandler fh = new FileHandler("src/Server/log.config", true);
            LOGGER.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
            LOGGER.info("Log message");
        } catch (SecurityException | IOException ex) {
            LOGGER.log(Level.SEVERE, "Произошла ошибка при работе с FileHandler.", ex);
        }
        LOGGER.log(Level.INFO,"Сервер начал работу");
        dc = DatagramChannel.open();
        dc.bind(new InetSocketAddress(SERVICE_PORT));
        LOGGER.log(Level.INFO,"Канал открыт и подключен");
        dc.configureBlocking(false);
        LOGGER.log(Level.INFO,"Канал начал работу в non-blocking режиме");
        XMLWorker xmlWorker = new XMLWorker(file);
        CollectionManager collection = new CollectionManager(file, xmlWorker.read());
        CommandManager commandManager = new CommandManager(this, collection);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            if (console.ready()) {
                String input = console.readLine();
                if (input.equals("save")) {
                    Save save = new Save(this, collection);
                    save.action(collection.getFile());
                }
            }

                try {
                    ByteBuffer buffer = ByteBuffer.allocate(4096);
                    senderAddress =  (InetSocketAddress) dc.receive(buffer);
                    Commands receivedMessage = null;
                    if(senderAddress != null){
                        LOGGER.log(Level.INFO,"Сервер получил данные от клиента");
                        buffer.flip();
                        ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array(), 0, buffer.limit());
                        ObjectInputStream ois = new ObjectInputStream(bais);
                        receivedMessage = (Commands) ois.readObject();
                        LOGGER.log(Level.INFO,"Данные десериализированы");
                    }
                    if (receivedMessage!=null) {
                        commandManager.commandManage(receivedMessage);
                    }


                } catch (ClassNotFoundException e) {
                    LOGGER.log(Level.WARNING,"Произошла ошибка");
                }
            }

    }
}
