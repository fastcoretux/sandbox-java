package net.fastcoretux.sandbox.mapstruct.mapper;

import lombok.val;
import net.fastcoretux.sandbox.mapstruct.domain.Client;
import net.fastcoretux.sandbox.mapstruct.domain.ClientFinancial;
import net.fastcoretux.sandbox.mapstruct.to.ClientTO;
import net.fastcoretux.sandbox.mapstruct.to.IncomeTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

public class ClientMapperTest {

    private static final BigDecimal TARGET_INCOME = BigDecimal.valueOf(5000);

    @Test
    @Ignore("https://github.com/mapstruct/mapstruct/issues/2046")
    public void map_null_income_not_ignored() {
        val target = target();
        val source = source();
        ClientIncomeMapper.t().map(source, target);
        val sa = new SoftAssertions();
        sa.assertThat(target.getFirstName()).isEqualTo(source.getFirstName());
        sa.assertThat(target.getSurname()).isEqualTo(source.getSurname());
        sa.assertThat(target.getFinancial().getIncomeType()).isEqualTo(source.getIncome().getType());
        // expected that income value is preserved
        // when nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE was used
        sa.assertThat(target.getFinancial().getIncome()).isEqualTo(TARGET_INCOME);
        sa.assertAll();
    }

    // INTERNAL
    private static ClientTO source() {
        val c = new ClientTO();
        c.setFirstName("Dale");
        c.setSurname("Cooper");
        c.setIncome(income());
        return c;
    }

    private static IncomeTO income() {
        val i = new IncomeTO();
        i.setAmount(null);
        return i;
    }

    private static Client target() {
        val c = new Client();
        c.setFinancial(financial());
        return c;
    }

    private static ClientFinancial financial() {
        val f = new ClientFinancial();
        f.setIncome(TARGET_INCOME);
        return f;
    }

}
