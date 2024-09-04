package ru.nuthatch.organization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nuthatch.baseentity.entity.BaseIndividual;
import ru.nuthatch.baseentity.entity.BaseIndividualEntrepreneur;

@Configuration
public class OrgConfiguration {

    @Bean
    public BaseIndividual individual() {
        return new BaseIndividual();
    }

    @Bean
    public BaseIndividualEntrepreneur individualEntrepreneur() {
        return new BaseIndividualEntrepreneur();
    }
}
