# Resilience4j-POC
**Introduction to Resilience4j**

After Netflix in Nov 2018 announced that Hystrix will be under maintenance mode and no further new developemnt will be done,  in Dec 2018 Spring Cloud Hystrix project is deprecated.So new applications should not use this project. Instead Resilience4j is a new option for Spring developers to implement the circuit breaker pattern.

Resilience4j is also more lightweight compared to Hystrix as it has the Vavr library as its only dependency. Netflix Hystrix, by contrast, has a dependency on Archaius which has several other external library dependencies such as Guava and Apache Commons. 

Resilience4j comes with features like Rate Limiter, Retry and Bulkhead along with Circuit Breaker pattern. Works well with spring boot and using micrometer libraries, it can emit metrics for monitoring.


Resilience4j works well with spring boot and using micrometer libraries, it can emit metrics for monitoring.
There is no replacement introduced by Spring for Hystrix Dashboard so users need to use prometheus or NewRelic for monitoring.

```
The Resilience4j Aspects order is following:
Retry ( CircuitBreaker ( RateLimiter ( Bulkhead ( Function ) ) ) )
If you need a different order, you can use the functional chaining style instead of the spring annotations style.
```


For more visit https://resilience4j.readme.io/docs


In this repository

* EmployeeService :- Service that exposes end-points for CRUD Operation on employees.

* EmployeeManagement :- Consumer of EmployeeService , it uses Resiliency4J library and is a Reactive Client created using CompletableFuture of java.

Both these above are separate gradle projects , need to build and run separately. The Configurations for Resilinecy4J Retry,BulkHead,CircuitBreaker,RateLimiter is present in application.yml file.

This project also have monitoring enabled using Micrometer which exposes metric endpoints to Prometheus and Grafana Dashboard 

Prometheus Server runs @ http://localhost:9090/
Grafana runs @ http://localhost:3000/

import the dashboard provided in the repository under Grafana folder , backed by prometheus datasource. This dashboard shows the Circuitbreaker States ,Bulk Head status
