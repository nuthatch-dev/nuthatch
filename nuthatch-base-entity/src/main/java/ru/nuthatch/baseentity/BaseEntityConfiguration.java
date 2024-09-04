package ru.nuthatch.baseentity;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import ru.nuthatch.baseentity.entity.BaseIndividual;
import ru.nuthatch.baseentity.entity.BaseIndividualEntrepreneur;
import ru.nuthatch.baseentity.entity.BaseLegalEntity;

@AutoConfiguration
public class BaseEntityConfiguration {

    @Bean
    public BaseIndividual baseIndividual() {
        return new BaseIndividual();
    }

    @Bean
    public BaseIndividualEntrepreneur baseIndividualEntrepreneur() {
        return new BaseIndividualEntrepreneur();
    }

    @Bean
    public BaseLegalEntity baseLegalEntity() {
        return new BaseLegalEntity();
    }

}
