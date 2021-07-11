package it.sisal.camel.springbootcamel.processors;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProcessMessage implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class);
        log.info("IN - [{}]", body);
        if(null == body) {
            exchange.getIn().setBody("Test body", String.class);
            return;
        }
        exchange.getIn().setBody(body.concat("-Test body"), String.class);
        log.info("OUT - [{}]", exchange.getIn().getBody(String.class));
    }

}
