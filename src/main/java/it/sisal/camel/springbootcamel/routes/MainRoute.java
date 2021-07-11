package it.sisal.camel.springbootcamel.routes;

import it.sisal.camel.springbootcamel.aggregator.InternalAggregationStrategy;
import it.sisal.camel.springbootcamel.constant.ProcessorConstant;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MainRoute extends RouteBuilder {

    private InternalAggregationStrategy internalAggregationStrategy;

    public MainRoute(InternalAggregationStrategy internalAggregationStrategy) {
        this.internalAggregationStrategy = internalAggregationStrategy;
    }

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
            .process();

        from("timer://mytimer?fixedRate=true&period=5000")
                .aggregate(internalAggregationStrategy)
                .constant(true)
                .completionTimeout(500L)
                .to("vm:out-route");

        from("vm:out-out")
                .process(ProcessorConstant.LOG_PROCESSOR)
                .stop();

        from("vm:resource")
                .process(ProcessorConstant.LOG_ENRICH_PROCESSOR)
                .stop();

    }

}
