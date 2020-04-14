package net.fastcoretux.sandbox.mapstruct.domain;

import lombok.Data;

@Data
public class Client {

    private String firstName;
    private String surname;
    private ClientFinancial financial;

}
