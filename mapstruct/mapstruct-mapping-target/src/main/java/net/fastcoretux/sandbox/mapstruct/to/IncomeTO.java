package net.fastcoretux.sandbox.mapstruct.to;

import lombok.Data;

@Data
public class IncomeTO {

    private Type type;
    private AmountTO amount;

    // CLASS
    public enum Type {
        EMPLOYEE
    }
}
