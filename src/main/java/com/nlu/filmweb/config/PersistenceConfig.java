package com.nlu.filmweb.config;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class PersistenceConfig {

    //...

//    @Bean
//    AuditorAware<String> auditorProvider() {
//        return new AuditorAware<String>() {
//            @Override
//            public Optional<String> getCurrentAuditor() {
//                UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//                    return Optional.of(userDetails.getUsername());
//            }
//        };
//    }

    //...

}