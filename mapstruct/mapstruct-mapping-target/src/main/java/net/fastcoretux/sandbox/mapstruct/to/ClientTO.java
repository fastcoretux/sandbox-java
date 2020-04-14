package net.fastcoretux.sandbox.mapstruct.to;

import lombok.Data;

@Data
public class ClientTO {

    private String firstName;
    private String surname;
    private IncomeTO income;

}
