package ru.nuthatch.baseentity;

public enum Role {
    DEVELOPER("Застройщик"),
    DESIGNER_CONTRACTOR("Лицо, осуществляющее подготовку проектной документации"),
    BUILDING_CONTRACTOR("Лицо, осуществляющее строительство, реконструкцию, капитальный ремонт"),
    OPERATING_PERSON("Лицо, ответственное за эксплуатацию здания, сооружения"),
    REGIONAL_OPERATOR("Региональный оператор"),
    TECHNICAL_CUSTOMER("Технический заказчик")
    ;

    private final String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
