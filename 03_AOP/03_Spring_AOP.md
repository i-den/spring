### Core Architecture
 - based on proxies
 - Spring analyzes crosscuts for the beans in ApplicationContext and generates Proxy Beans

[PICTURES/SPRING_AOP.png]

 - ProxyFactory drives the main logic
 - ProxyFactory delegates call ot DefaultAopProxyFactory
 - DefaultAopProxyFactory delegates to Cglib2AopProxy or JdkDynamicAopProxy
 
### Types of Advices in Spring
 - only Method calls
 
##### Types
 - Before
 - After-Returning
 - After (finally)
   - if the advised method throws this still runs
 - Around
 - Throws
 - Introduction
 
 [PICTURES/AOP_classes.png]
 