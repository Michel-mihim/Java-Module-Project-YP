import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Консольное приложение для распределения счёта на оплату.\n\n");
        Bill bill = new Bill();
        // ожидание адекватного количества человек
        while (true) {
            System.out.println("На сколько человек разделить счёт?");
            Scanner userReply = new Scanner(System.in);
            String billShareCountString = userReply.nextLine();
            if (isBillShareCorrect(billShareCountString) == true) {
                bill.setPeopleCount(billShareCountString);
                break;
            }
        }
        // ожидание команды "ЗАВЕРШИТЬ"
        while (true) {
            if (bill.getMealList() == "") {
                System.out.println("Вы хотите добавить товар?\nЕсли нет, то введите слово \"завершить\" в любом регистре,\nесли да, то введите любой символ!");
            } else {
                System.out.println("Вы хотите добавить еще один товар?\nЕсли нет, то введите слово \"завершить\" в любом регистре,\nесли да, то введите любой символ!");
            }
            Scanner userReply = new Scanner(System.in);
            if (userReply.nextLine().equalsIgnoreCase("завершить")) {
                System.out.println("Работа программы завершена!\nИтоговый счёт:\n");
                bill.showBillReport();
                break;
            }
            billInputInAction(bill);
        }
    }

    public static boolean isBillShareCorrect(String billShareCountString) {
        if (isNumericInt(billShareCountString) == false) {
            System.out.println("Введенные данные не являются целым числом! Введите значение еще раз!");
            return false;
        }
        int peopleCount = Integer.parseInt(billShareCountString);
        switch (peopleCount) {
            case 1: {
                System.out.println("Деление счёта на одного человека не имеет смысла! Число должно быть целое, положительное, больше 1.");
                return false;
            }
            case 0: {
                System.out.println("На ноль делить нельзя! Число должно быть целое, положительное, больше 1.");
                return false;
            }
            default: {
                if (peopleCount > 0) {
                    System.out.println("Данные о количестве человек приняты.");
                    return true;
                } else {
                    System.out.println("Наверное, это опечатка. Число должно быть целое, положительное, больше 1.");
                    return false;
                }
            }
        }
    }

    public static void billInputInAction(Bill bill) {
        System.out.println("Введите название товара!");
        Scanner userReplyMealName = new Scanner(System.in);
        String newMealName = userReplyMealName.nextLine();
        while (true) {
            System.out.println("Введите стоимость товара в формате \"рубли.копейки\", например 10.45 или 11.40!");
            Scanner userReplyMealPrice = new Scanner(System.in);
            String newMealPrice = userReplyMealPrice.nextLine();
            // Проверка введенных числовых данных
            if (isNumericDouble(newMealPrice) == true) {
                bill.billNewMealAdd(newMealName, newMealPrice);
                System.out.println("\"" + newMealName + "\" успешно добавлено в счёт!");
                break;
            }
            System.out.println("Введенные данные не корректны! Введите значение еще раз!");
        }
    }

    public static boolean isNumericDouble(String priceString) {
        try {
            Double.parseDouble(priceString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isNumericInt(String peopleCount) {
        try {
            Integer.parseInt(peopleCount);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}