package ru.nuthatch.baseentity;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import ru.nuthatch.baseentity.entity.*;

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

    @Bean
    public BaseSro baseSro() {
        return new BaseSro();
    }

    @Bean
    public BaseRepresentative baseRepresentative() {
        return new BaseRepresentative();
    }

}
