### Config Server
```java
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    // ...
}
```

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-config-server</artifactId>
</dependency>
```
#### Application properties
```properties
server.port=8888
spring.application.name=SPRING-CLOULD-CONFIG-SERVER

spring.cloud.config.server.git.uri=file:///C:/Users/sujith.manchala/Desktop/config-server-repo

spring.cloud.config.server.git.clone-on-start=true

management.endpoint.serviceregistry.enabled=false
```
#### config-client.properties
```properties
limit-server.max : 999
limit-server.min : 111
```
#### config-client-dev.properties
```properties
limit-server.max : 9
limit-server.min : 11
```
#### config-client-prod.properties
```properties
limit-server.max : 324324
limit-server.min : 131
```

---

### Config Server
```
spring.application.name=config-client
server.port=8081

spring.profiles.active=dev

#spring.cloud.config.uri= http://localhost:8888


#	A bootstrap file (properties or yaml) is not needed for the Spring Boot Config Data method of import via spring.config.import.
# spring.config.import=optional:configserver:http://localhost:8888

spring.config.import=configserver:http://localhost:8888

#spring.cloud.config.enabled=true
```
```java
package com.micro.limitserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
@RefreshScope
@ConfigurationProperties(prefix =  "limit-server")
public class LimitConfig {
	private int max;
	private int min;
}
```
```java
package com.micro.limitserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.limitserver.config.LimitConfig;
import com.micro.limitserver.model.LimitDetails;

@RefreshScope
@RestController
@RequestMapping("/limits")
public class LimitController {

	@Autowired
	private LimitConfig config;
	
	@GetMapping("/")
	public LimitDetails getDetails() {
		return new LimitDetails(config.getMax(), config.getMin());
	}
}
```


