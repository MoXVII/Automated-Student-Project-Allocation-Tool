


# Automated Student Project Allocation Tool

This is a project aims to use the assistance of evolutionary algorithm to be able to allocate each student from a given dataset to a university project (from a second dataset) whilst factoring in their suitability to each project. This is akin to the [Assignment Problem](https://en.wikipedia.org/wiki/Assignment_problem)

# Variants of the tool

As of right now, there are two versions of the tool. The IDE version runs from any IDE (tested on Eclipse 4.7 STS on MacOSX) by simply executing the run command. Alternatively, the second version is in the form of a Spring Web Application and uses a model-view-controller architecture to manage the allocation process and persists the final results using a MySQL back-end database. This version of the tool is run using terminal by navigating to the location you have stored the StuProjectAllocation folder and executing the following:



```
gradle bootrun
```
This will initiallise and boot the application. Next you will want to open a browser and navigate to:

```
https://localhost:8090/
```

## Applications Built Using

* [Gradle](https://gradle.org) - Dependency Management.
* [Spring MVC](https://spring.io) - Web Framework.
* [Java 8](https://docs.oracle.com/javase/8/docs/api/) - Main language used for both versions 
* [MySQL](https://www.mysql.com) - Persistence & Backend.
* [Bootstrap 3](http://getbootstrap.com/docs/3.3/) - User Interface & Visuals. 


## Outstanding

- [ ] Resolve issues with front-end and re-iterave allocation aspect of evolutionary algorithm (WEB TOOL ONLY).
- [ ] Incorporate a quanification of how good this allocation is relative to other allocations made (using total absolute fitness).