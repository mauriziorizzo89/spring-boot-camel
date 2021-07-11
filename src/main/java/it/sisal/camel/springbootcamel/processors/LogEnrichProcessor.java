package it.sisal.camel.springbootcamel.processors;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class LogEnrichProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class);
        Set<Map.Entry<String, Object>> entries = exchange.getIn().getHeaders().entrySet();
        entries.forEach(stringObjectEntry -> log.info("IN ENRICH - [{}]", stringObjectEntry));
    }

}
