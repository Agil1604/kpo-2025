package hse.zoo.zoo.commands;

import hse.zoo.zoo.interfaces.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class CommandHandler {
    private final Map<String, ICommand> commands = new HashMap<>();
    private final Scanner scanner;

    @Autowired
    public CommandHandler(Scanner scanner, List<ICommand> commandList) {
        this.scanner = scanner;
        commandList.forEach(cmd -> commands.put(cmd.getName(), cmd));
    }

    /**
     * Регистрация доступных команд
     * @param commandKey Название команды (как будет использоваться при вводе)
     * @param command реализация команды
     */
    public void registerCommand(String commandKey, ICommand command) {
        commands.put(commandKey, command);
    }

    /**
     * Исполнение команды при ее вызове
     */
    public boolean handleCommand() {
        System.out.print("Введите команду: ");
        String input = scanner.nextLine().trim();
        if (input.equals("exit")){
            return false;
        }

        ICommand command = commands.get(input);
        if (command != null) {
            command.execute();
        } else if (input.equals("help")) {
            this.printHelp();
        } else {
            System.out.println("Wrong input. Type 'help' for getting list of commands");
        }
        return true;
    }

    /**
     * Вывод списка команд
      */
    public void printHelp() {
        System.out.println("Доступные команды:");
        commands.values().forEach(cmd ->
                System.out.printf("- %s: %s%n", cmd.getName(), cmd.getDescription())
        );
        System.out.println("- exit: closes the app");
    }
}
