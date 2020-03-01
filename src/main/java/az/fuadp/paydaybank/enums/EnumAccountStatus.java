package az.fuadp.paydaybank.enums;

public enum  EnumAccountStatus {

    ACTIVE(1), INACTIVE(0);

    private int value;

    EnumAccountStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}


