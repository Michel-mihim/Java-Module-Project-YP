import java.util.Scanner;

public class Main {
    // Знаю, что изменять значения глобальных переменных в методе не очень хороший приём, лучше выводить результаты через RETURN.
    // Прошу предложить проверенный вариант! Слышал, что существует метод POCO через создание объекта с полями
    public static String mealList = "";
    public static double totalBill = 0;
    public static int drinkAndFoodCounter = 0;
    public static double eachPay = 0;
    public static int peopleCount = 0;
    public static void main(String[] args) {
        System.out.println("Консольное приложение для распределения счёта на оплату.\n\n");
        // ожидание адекватного количества человек
        while (true) {
            System.out.println("На сколько человек разделить счёт?");
            Scanner billShareAnswer = new Scanner(System.in);
            String peopleCountString = billShareAnswer.nextLine();
            if (billShareVerify(peopleCountString) == true) {
                break;
            }
        }

        while (true) {
            if (mealList == "") {
                System.out.println("Вы хотите добавить товар?\nЕсли нет, то введите слово \"завершить\" в любом регистре,\nесли да, то введите любой символ!");
            } else {
                System.out.println("Вы хотите добавить еще один товар?\nЕсли нет, то введите слово \"завершить\" в любом регистре,\nесли да, то введите любой символ!");
            }
            Scanner ready = new Scanner(System.in);
            if (ready.next().equalsIgnoreCase("завершить")) {
                System.out.println("Работа программы завершена!\nИтоговый счёт:\n");
                String messageTemplate = "Добавленные товары в счёте на %d человек:\n%s\nОбщая сумма: %.2f %s.\nС каждого %.2f %s.";
                System.out.println("============================");
                System.out.println(String.format(messageTemplate, peopleCount, mealList, totalBill, rubleyblya(totalBill), eachPay, rubleyblya(eachPay)));
                System.out.println("============================");
                break;
            }
            foodInputInAction(peopleCount);
        }
    }
    public static boolean billShareVerify(String billShareCountString) {
        if (isNumericInt(billShareCountString) == false) {
            System.out.println("Введенные данные не корректны! Попробуйте еще раз!");
            return false;
        }
        peopleCount = Integer.parseInt(billShareCountString);
        switch (peopleCount) {
            case 1: {
                System.out.println("Будете сами за всё платить?! Число должно быть целое, положительное, больше 1.");
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

    public static void foodInputInAction(int peopleCount) {
        System.out.println("Введите название товара!");
        Scanner foodOrDrink = new Scanner(System.in);
        String foodOrDrink_ = foodOrDrink.next();
        // дополняем список блюд внутри глобальной строковой переменной
        drinkAndFoodCounter++;
        if (mealList != "") {
            mealList = mealList.concat("\n");
        }
        mealList = mealList.concat(drinkAndFoodCounter+". ").concat(foodOrDrink_);
        while (true) {
            System.out.println("Введите стоимость товара в формате \"рубли.копейки\", например 10.45 или 11.40!");
            Scanner price = new Scanner(System.in);
            String myDouble = price.nextLine();
            // Проверка введенных числовых данных
            if (isNumeric(myDouble) == true) {
                // увеличиваем общую ссумму блюд внутри глобальной переменной
                totalBill = totalBill + Double.parseDouble(myDouble);
                // выравниваем введенное значение цены для отображения в списке
                String pricePerNoteTemplate = "%.2f";
                mealList = mealList.concat(" - " + String.format(pricePerNoteTemplate, Double.parseDouble(myDouble)) + " " + rubleyblya(Double.parseDouble(myDouble)));
                eachPay = totalBill / peopleCount;
                System.out.println("\""+foodOrDrink_+"\" успешно добавлено в счёт!");
                break;
            }
            System.out.println("Введенные данные не корректны. Попробуйте еще раз!");
        }
    }
    public static boolean isNumeric(String priceString) {
        try {
            Double.parseDouble(priceString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static  boolean isNumericInt(String peopleCount) {
        try {
            Integer.parseInt(peopleCount);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static String rubleyblya(double money) {
        int moneyInt = (int) money;
        String moneyString = "" + moneyInt;
        if (moneyString.length() == 1) {
            switch (moneyString) {
                case "1": {
                    return "рубль";
                }
                case "2":
                case "3":
                case "4": {
                    return "рубля";
                }
                default: {
                    return  "рублей";
                }
            }
        } else {
            moneyString = moneyString.substring(moneyString.length() - 2, moneyString.length());
            switch (moneyString) {
                case "21":
                case "31":
                case "41":
                case "51":
                case "61":
                case "71":
                case "81":
                case "91":
                case "01": {
                    return "рубль";
                }
                case "22":
                case "23":
                case "24":
                case "32":
                case "33":
                case "34":
                case "42":
                case "43":
                case "44":
                case "52":
                case "53":
                case "54":
                case "62":
                case "63":
                case "64":
                case "72":
                case "73":
                case "74":
                case "82":
                case "83":
                case "84":
                case "92":
                case "93":
                case "94":
                case "02":
                case "03":
                case "04": {
                    return "рубля";
                }
                default: {
                    return  "рублей";
                }
            }
        }
    }
}