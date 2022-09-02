# gate-way-server


### File Structure
```pre
+ gate-way-server\ 
	+ \src\main\java\com\suji\gateway
		|---  GateWayServerApplication.java
	+ \src\main\java\com\suji\gateway\config
		|---  ApiGatewayConfig.java
```
### Index
```pre
1. src\main\java\com\suji\gateway\GateWayServerApplication.java
2. src\main\java\com\suji\gateway\config\ApiGatewayConfig.java

```

---

### 1. GateWayServerApplication.java

#### src\main\java\com\suji\gateway\GateWayServerApplication.java

```java

package com.suji.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GateWayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayServerApplication.class, args);
	}

}

```

---

### 2. ApiGatewayConfig.java

#### src\main\java\com\suji\gateway\config\ApiGatewayConfig.java

```java

package com.suji.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

		return builder.routes()

				.route(p -> p.path("/check/**")
						.filters(f -> f.rewritePath("/check/(?<segment>.*)", "/hello/${segment}"))
						.uri("lb://TODO-MANAGEMENT"))

				
				.route(p -> p.path("/duck/**")
						.filters(f -> f.rewritePath("/duck/(?<segment>.*)", "/?va=j&t=hb&q=${segment}"))
						.uri("https://duckduckgo.com"))

				
				.build();
	}

}

```

---

