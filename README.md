# Vetstoria Interview Assignment
### Overview

Declarative approach of rate limiting control over the Spring endpoints. 
`throttle` method helps you to limit the number of endpoint method calls per `java.util.concurrent.TimeUnit`
for a particular user, IP address.


### Examples

#### Defaults (Remote IP)
The following rate limit configuration allows 1 method calls per SECOND for each unique `HttpServletRequest#getRemoteAddr()`.

```java
 final boolean isLimitExceed = rateLimitService.throttle(request.getRemoteAddr(), TimeUnit.MINUTES,100);
if(!isLimitExceed){
    throw new RateLimitException();
}

```

### How to build
```java
mvn clean package 
```