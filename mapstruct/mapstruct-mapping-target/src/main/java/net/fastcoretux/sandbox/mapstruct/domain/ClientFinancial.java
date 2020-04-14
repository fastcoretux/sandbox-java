package net.fastcoretux.sandbox.mapstruct.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientFinancial {

    private Type incomeType;
    private BigDecimal income;

    // CLASS
    public enum Type {
        EMPLOYEE
    }
}
