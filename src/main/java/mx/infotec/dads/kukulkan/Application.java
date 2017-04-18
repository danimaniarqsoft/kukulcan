/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2016 Daniel Cortes Pichardo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package mx.infotec.dads.kukulkan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import mx.infotec.dads.kukulkan.engine.domain.core.DataStore;
import mx.infotec.dads.kukulkan.engine.domain.core.DataStoreType;
import mx.infotec.dads.kukulkan.engine.domain.core.RuleType;
import mx.infotec.dads.kukulkan.engine.repository.DataStoreRepository;
import mx.infotec.dads.kukulkan.engine.repository.DataStoreTypeRepository;
import mx.infotec.dads.kukulkan.engine.repository.RuleRepository;
import mx.infotec.dads.kukulkan.engine.repository.RuleTypeRepository;
import mx.infotec.dads.kukulkan.util.EntitiesFactory;

/**
 * 
 * @author Daniel Cortes Pichardo
 * @since 1.0.0
 * @version 1.0.0
 */

@SpringBootApplication
@EnableMongoRepositories
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Bean
    CommandLineRunner init(final DataStoreRepository repository, final DataStoreTypeRepository dsRepository,
            final RuleRepository ruleRepository, final RuleTypeRepository ruleTypeRepository) {
        return commandLineRunner -> {
            repository.deleteAll();
            dsRepository.deleteAll();
            ruleRepository.deleteAll();
            ruleTypeRepository.deleteAll();
            DataStoreType dst = EntitiesFactory.createDefaultDataStoreType();
            dst = dsRepository.save(dst);
            DataStore dsValuApp = EntitiesFactory.createDefaultDataStore(dst);
            repository.save(dsValuApp);
            RuleType singularRuleType = ruleTypeRepository.save(EntitiesFactory.createDefaultSingularRuleType());
            ruleTypeRepository.save(EntitiesFactory.createDefaultPluralRuleType());
            ruleRepository.save(EntitiesFactory.createOsRule(singularRuleType));
            ruleRepository.save(EntitiesFactory.createEsRule(singularRuleType));
        };
    }

    /**
     * For more information about cors you can use
     * https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
     * 
     * @return WebMvcConfigurer
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost");
        config.addAllowedOrigin("http://consumer.dads.infotec.mx");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    public static void main(String[] args) {
        LOGGER.info("Running Kukulkan...");
        SpringApplication.run(Application.class, args);
    }
}
