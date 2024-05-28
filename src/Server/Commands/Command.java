package Server.Commands;

import Server.Manager.Server;

/**
 * Базовый класс команд содержит поля <b>console</b>, <b>name</b>, <b>description</b>
 */
public abstract class Command {
    protected final Server server;
    private final String name;
    private final String description;
    /**
     * @param server - выводит в консоль
     * @param name - название команды
     * @param description - описание команды
     */
    protected Command(Server server, String name, String description) {
        this.server = server;
        this.name = name;
        this.description = description;
    }


    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
