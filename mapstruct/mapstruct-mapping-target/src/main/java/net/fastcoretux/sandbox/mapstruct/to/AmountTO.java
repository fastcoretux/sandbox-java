package net.fastcoretux.sandbox.mapstruct.to;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

@Data
@RequiredArgsConstructor(staticName = "of")
public class AmountTO {

    private final BigDecimal value;
    private final Currency currency;

}
