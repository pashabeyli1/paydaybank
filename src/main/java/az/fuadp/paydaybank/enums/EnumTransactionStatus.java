package az.fuadp.paydaybank.enums;

public enum EnumTransactionStatus {

    ACTIVE(1), INACTIVE(0);

    private int value;

    EnumTransactionStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
