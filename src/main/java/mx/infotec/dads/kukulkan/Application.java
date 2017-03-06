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

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import mx.infotec.dads.kukulkan.engine.domain.core.DataStore;
import mx.infotec.dads.kukulkan.engine.domain.core.DataStoreType;
import mx.infotec.dads.kukulkan.engine.domain.core.Rule;
import mx.infotec.dads.kukulkan.engine.domain.core.RuleType;
import mx.infotec.dads.kukulkan.engine.repository.DataStoreRepository;
import mx.infotec.dads.kukulkan.engine.repository.DataStoreTypeRepository;
import mx.infotec.dads.kukulkan.engine.repository.RuleRepository;
import mx.infotec.dads.kukulkan.engine.repository.RuleTypeRepository;

/**
 * 
 * @author Daniel Cortes Pichardo
 * @since 1.0.0
 * @version 1.0.0
 */

@SpringBootApplication
@EnableMongoRepositories
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(final DataStoreRepository repository, final DataStoreTypeRepository dsRepository,
            final RuleRepository ruleRepository, final RuleTypeRepository ruleTypeRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... arg0) throws Exception {
                repository.deleteAll();
                dsRepository.deleteAll();
                ruleRepository.deleteAll();
                ruleTypeRepository.deleteAll();
                DataStoreType dst = new DataStoreType();
                dst.setDescription("Data Store for JDBC connector");
                dst.setName("jdbc");
                dst = dsRepository.save(dst);
                DataStore ds = new DataStore();
                ds.setDataStoreType(dst);
                ds.setDriverClass("com.mysql.jdbc.Driver");
                ds.setPassword("temporal123");
                ds.setTableTypes("TABLE,VIEW");
                ds.setUrl("jdbc:mysql://localhost/zonacero?autoReconnect=true");
                ds.setUsername("developer");
                repository.save(ds);
                // RulesTypes
                RuleType singularRuleType = new RuleType();
                singularRuleType
                        .setDescription("regla que aplica para palabras convertir palabras de plural a singular");
                singularRuleType.setName("singular");
                RuleType plurarlRuleType = new RuleType();
                plurarlRuleType
                        .setDescription("regla que aplica para palabras convertir palabras de singular a plural");
                plurarlRuleType.setName("plural");
                singularRuleType = ruleTypeRepository.save(singularRuleType);
                ruleTypeRepository.save(plurarlRuleType);
                // Rules Repositories
                Rule r1 = new Rule();
                r1.setExpression("os$");
                r1.setReplacement("o");
                r1.setRuleType(singularRuleType);
                Rule r2 = new Rule();
                r2.setExpression("es$");
                r2.setReplacement("");
                r2.setRuleType(singularRuleType);
                ruleRepository.save(r1);
                ruleRepository.save(r2);
            }
        };
    }

}
