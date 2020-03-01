package az.fuadp.paydaybank.enums;

public enum EnumEmailConfirm {

    ENABLE(1), DISABLE(0);

    private int value;

    EnumEmailConfirm(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
