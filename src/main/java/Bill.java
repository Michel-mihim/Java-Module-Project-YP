public class Bill {
    String mealList = "";
    double totalBillPrice = 0;
    int billVolume = 0;
    double billPerMan = 0;
    int peopleCount = 0;
    void billNewMealAdd(String mealName, String mealPrice) {
        this.totalBillPrice += Double.parseDouble(mealPrice);
        this.billVolume++;
        this.billPerMan = this.totalBillPrice / this.peopleCount;
        if (this.mealList != "") {
            this.mealList = this.mealList.concat("\n");
        }
        String pricePerNoteTemplate = "%.2f";
        this.mealList = this.mealList.concat(getBillVolume()+". ").concat(mealName).concat(" - " + String.format(pricePerNoteTemplate, Double.parseDouble(mealPrice)) + " " + chooseCurrencyEnding(Double.parseDouble(mealPrice)));
    }
    void showBillReport() {
        int peopleCount = getPeopleCount();
        String mealList = getMealList();
        double totalBillPrice = getTotalBillPrice();
        String totalCurrencyEnding = chooseCurrencyEnding(totalBillPrice);
        double billPerMan = getBillPerMan();
        String perManCurrencyEnding = chooseCurrencyEnding(billPerMan);
        String messageTemplate = "Добавленные товары в счёте на %d человек:\n%s\nОбщая сумма: %.2f %s.\nС каждого %.2f %s.";
        System.out.println("============================");
        System.out.println(String.format(messageTemplate, peopleCount, mealList, totalBillPrice, totalCurrencyEnding, billPerMan, perManCurrencyEnding));
        System.out.println("============================");
    }
    String getMealList() {
        return this.mealList;
    }
    int getBillVolume() {
        return this.billVolume;
    }
    double getTotalBillPrice() {
        return this.totalBillPrice;
    }
    double getBillPerMan() {
        return this.billPerMan;
    }
    int getPeopleCount() {
        return this.peopleCount;
    }
    void setPeopleCount(String peopleCount) {
        int peopleCountInt = Integer.parseInt(peopleCount);
        this.peopleCount = peopleCountInt;
    }
    public static String chooseCurrencyEnding(double money) {
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
