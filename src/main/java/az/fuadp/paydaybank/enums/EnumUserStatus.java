package az.fuadp.paydaybank.enums;

public enum EnumUserStatus {

    ACTIVE(1), INACTIVE(0);

    private int value;

    EnumUserStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
