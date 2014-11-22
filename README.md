# VRaptor Ebean Plugin

![Build status](https://secure.travis-ci.org/dtelaroli/vraptor-ebean.png)

VRaptor Ebean Plugin provides support to use with Ebean 4.

# How to install?

You only need to copy the jar to your classpath. VRaptor will register plugin when 
your application starts without any configurations. Downloads are available in 
downloads area or in Maven Repository:

	<dependency>
	  <groupId>br.com.caelum.vraptor</groupId>
	  <artifactId>vraptor-ebean</artifactId>
	  <version>4.0.0-SNAPSHOT</version> <!-- or the latest version -->
	</dependency>

# Transactional Control

The default behavior is that each request will have a transaction available.

If you want, you can enable a decorator to change this behavior. 

When enabled, it will open transactions only for methods with `@Transactional` annotation. 

To do that you just need to add the follow content into your project's `beans.xml`:

```xml
<decorators>
    <class>br.com.caelum.vraptor.ebean.TransactionDecorator</class>
</decorators>
```
