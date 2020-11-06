### Joinpoint
Well defined point during execution
 - method call
 - class init
 - obj init

### Advice
 - method that will execute on a given Joinpoint event

### Pointcut
 - collection of Joinpoints
 - collection of all Methods invocations that will be advised

### Aspect
 - combination of Advice and Pointcuts encapsulated in a Class

### Weaving
 - the process of inserting the aspects into app code

### Target
 - the object that will have its method advised

### Introduction
 - process by which you can modify the structure of an object by introducing additional methods or fields to it