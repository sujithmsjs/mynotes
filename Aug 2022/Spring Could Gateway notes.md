
#
# Spring Could GATEWAY
#

```yaml
logging:
  level:
    reactor:
      netty: INFO
    org:
      springframework:
        cloud:
          gateway: TRACE
spring:
  cloud:
    gateway:
      httpclient:
        wiretap: true
      httpserver:
        wiretap: true
```


### LoggingFilter


```java
public class LoggingFilter implements GlobalFilter {
    Log log = LogFactory.getLog(getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Set<URI> uris = exchange.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, Collections.emptySet());
        String originalUri = (uris.isEmpty()) ? "Unknown" : uris.iterator().next().toString();
        Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
        URI routeUri = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
        log.info("Incoming request " + originalUri + " is routed to id: " + route.getId()
                + ", uri:" + routeUri);
        return chain.filter(exchange);
    }
}
```

```yml
logging:
    level:
        reactor:
            netty: INFO
        org:
            springframework:
                cloud:
                    gateway: INFO
```

# Running the evict task with compensationTime 0ms
# Current renewal threshold is : 5
# The replication of task
# EurekaServiceRegistry        : Registering application GATE-WAY with eureka with status UP



---
## GETTING VALUES FROM APPLICATION.PROPERTIES
[Environment Variables](https://www.baeldung.com/spring-boot-yaml-vs-properties)

[YAMLSyntax](https://docs.ansible.com/ansible/latest/reference_appendices/YAMLSyntax.html)

[GATE-WAY Routes Setting](https://cloud.spring.io/spring-cloud-gateway/reference/html/)

spring.cloud.gateway.enabled=false

Route: The basic building block of the gateway. It is defined by an ID, a destination URI, a collection of predicates, and a collection of filters. A route is matched if the aggregate predicate is true.


```yml
spring:
  cloud:
    gateway:
      routes:
      - id: myRoute
        uri: lb://service
        predicates:
        - Path=/service/**
```


```yml
- id: demo2
        # uri: http://localhost:8081/
		uri: http://localhost:8081
        predicates: 
        - Path=/hello/**
```

uri + Path = should be working uri


http://localhost:7865/hello
http://localhost:8081/hello

http://localhost:7865/hello/
http://localhost:8081/hello/

http://localhost:7865/hello/demo2

http://localhost:8081 + /hello/demo2

Example:-
{http://localhost:8081}{/hello/}demo3
uri: http://localhost:8081
predicates: 
- Path=/hello/**

```yml
- id: todo-management
	uri: lb://TODO-MANAGEMENT
    predicates:
    - Path=/hello  
```

SWAGGER:

http://localhost:8081/v3/api-docs
http://localhost:8082/v3/api-docs


{http://localhost:8081}/todos-api-docs

