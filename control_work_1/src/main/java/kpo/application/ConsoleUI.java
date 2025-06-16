package kpo.application;

import kpo.commands.Command;
import kpo.commands.TimedCommand;
import kpo.commands.impl.*;
import kpo.domains.BankAccount;
import kpo.domains.Category;
import kpo.domains.Operation;
import kpo.enums.CategoryType;
import kpo.enums.OperationType;
import kpo.facade.FinanceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleUI {
    private final FinanceFacade financeFacade;
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            printMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1 -> manageAccounts();
                case 2 -> manageCategories();
                case 3 -> manageOperations();
                case 4 -> System.exit(0);
                default -> System.out.println("Неверный выбор");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n=== Учет финансов ===");
        System.out.println("1. Управление счетами");
        System.out.println("2. Управление категориями");
        System.out.println("3. Управление операциями");
        System.out.println("4. Выход");
        System.out.print("Выберите действие: ");
    }

    private void manageAccounts() {
        while (true) {
            System.out.println("\n--- Управление счетами ---");
            System.out.println("1. Создать счет");
            System.out.println("2. Изменить название");
            System.out.println("3. Удалить счет");
            System.out.println("4. Просмотреть счет");
            System.out.println("5. Назад");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите название счета: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите начальный баланс: ");
                    int balance = scanner.nextInt();
                    scanner.nextLine();

                    Command cmd = new CreateAccountCommand(financeFacade, name, balance);
                    executeCommand(new TimedCommand(cmd));
                }
                case 2 -> {
                    System.out.print("Введите ID счета: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введите новое название: ");
                    String newName = scanner.nextLine();

                    Command cmd = new UpdateAccountNameCommand(financeFacade, id, newName);
                    executeCommand(new TimedCommand(cmd));
                }
                case 3 -> {
                    System.out.print("Введите ID счета: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Command cmd = new DeleteAccountCommand(financeFacade, id);
                    executeCommand(new TimedCommand(cmd));
                }
                case 4 -> {
                    System.out.print("Введите ID счета: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Command cmd = new ViewAccountCommand(financeFacade, id);
                    executeCommand(new TimedCommand(cmd));
                }
                case 5 -> { return; }
                default -> System.out.println("Неверный выбор");
            }
        }
    }

    private void manageCategories() {
        while (true) {
            System.out.println("\n--- Управление категориями ---");
            System.out.println("1. Создать категорию");
            System.out.println("2. Удалить категорию");
            System.out.println("3. Просмотреть категорию");
            System.out.println("4. Назад");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Выберите тип категории:");
                    for (CategoryType type : CategoryType.values()) {
                        System.out.println((type.ordinal() + 1) + ". " + type);
                    }
                    System.out.print("Введите номер типа: ");
                    int typeIdx = scanner.nextInt() - 1;
                    scanner.nextLine();

                    System.out.print("Введите название категории: ");
                    String name = scanner.nextLine();

                    CategoryType type = CategoryType.values()[typeIdx];

                    Command cmd = new CreateCategoryCommand(financeFacade, type, name);
                    executeCommand(new TimedCommand(cmd));
                }
                case 2 -> {
                    System.out.print("Введите ID категории: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Command cmd = new DeleteCategoryCommand(financeFacade, id);
                    executeCommand(new TimedCommand(cmd));
                }
                case 3 -> {
                    System.out.print("Введите ID категории: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Command cmd = new ViewCategoryCommand(financeFacade, id);
                    executeCommand(new TimedCommand(cmd));
                }
                case 4 -> { return; }
                default -> System.out.println("Неверный выбор");
            }
        }
    }

    private void manageOperations() {
        while (true) {
            System.out.println("\n--- Управление операциями ---");
            System.out.println("1. Создать операцию");
            System.out.println("2. Просмотреть операцию");
            System.out.println("3. Назад");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите ID счета: ");
                    int accountId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Введите ID категории: ");
                    int categoryId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Выберите тип операции:");
                    System.out.println("1. Расход (EXPENSE)");
                    System.out.println("2. Доход (INCOME)");
                    System.out.print("Введите номер: ");
                    int typeChoice = scanner.nextInt();
                    scanner.nextLine();

                    OperationType type = (typeChoice == 1) ? OperationType.EXPENSE : OperationType.INCOME;

                    System.out.print("Введите сумму: ");
                    int amount = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Введите описание: ");
                    String description = scanner.nextLine();

                    Command cmd = new CreateOperationCommand(
                            financeFacade,
                            accountId,
                            categoryId,
                            type,
                            amount,
                            description
                    );
                    executeCommand(new TimedCommand(cmd));
                }
                case 2 -> {
                    System.out.print("Введите ID операции: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Command cmd = new ViewOperationCommand(financeFacade, id);
                    executeCommand(new TimedCommand(cmd));
                }
                case 3 -> { return; }
                default -> System.out.println("Неверный выбор");
            }
        }
    }

    private void executeCommand(Command command) {
        try {
            command.execute();
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}