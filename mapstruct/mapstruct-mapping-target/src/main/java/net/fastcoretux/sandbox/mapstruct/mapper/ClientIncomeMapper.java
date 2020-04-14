package net.fastcoretux.sandbox.mapstruct.mapper;

import net.fastcoretux.sandbox.mapstruct.domain.Client;
import net.fastcoretux.sandbox.mapstruct.to.ClientTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface ClientIncomeMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    // expecting if any source property is null so target value will be preserved
    @Mapping(source = "income.amount.value", target = "financial.income")
    @Mapping(source = "income.type", target = "financial.incomeType")
    void map(ClientTO source, @MappingTarget Client target);

    // STATIC
    static ClientIncomeMapper t() {
        return new ClientIncomeMapperImpl();
    }

}
