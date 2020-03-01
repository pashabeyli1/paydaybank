package az.fuadp.paydaybank.enums;

public enum EnumAccountType {

    DEBIT(3), DEPOSIT(4);

    private int value;

    EnumAccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
