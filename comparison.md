# Java Web Evolution: A Technical Comparison

This document compares the four demonstration projects, highlighting the evolution of configuration, boilerplate code, and developer experience.

## Overview

| Project | Technology | Configuration Style | Primary Components | View Technology |
| :--- | :--- | :--- | :--- | :--- |
| `demo-servlet` | Jakarta EE 10 Servlet 6.0 | XML (`web.xml`) | `HttpServlet` | JSP (Expression Language) |
| `demo-spring-xml` | Spring MVC 6.x | XML (Spring Beans) | `@Controller` | JSP (View Resolver) |
| `demo-spring-annot` | Spring MVC 6.x | Java-based (Annotations) | `@Configuration`, `@Controller` | JSP |
| `demo-spring-boot` | Spring Boot 3.x | Auto-configuration | `@SpringBootApplication` | Thymeleaf |

---

## 1. JSP & Servlet (`demo-servlet`)
**Era: Late 90s - Early 00s**

- **Configuration**: Heavily dependent on `WEB-INF/web.xml` for mapping every servlet and filter.
- **Boilerplate**: High. Developers must manually parse request parameters, handle session management, and manage the request-response lifecycle.
- **Complexity**: Low for small apps, but becomes unmanageable as the number of servlets grows.
- **Verdict**: The foundation of Java web development, but too verbose for modern applications.

## 2. Spring MVC XML-based (`demo-spring-xml`)
**Era: Mid 2000s**

- **Configuration**: Split between `web.xml` and one or more Spring XML files (e.g., `spring-servlet.xml`).
- **Boilerplate**: Moderate. The introduction of `@Controller` removed the need for manual parameter parsing, but XML bean definitions can become massive.
- **Developer Experience**: Improved due to Dependency Injection and decoupled views.
- **Verdict**: A huge leap forward in architecture, but "XML Hell" was a real pain point.

## 3. Spring MVC Annotation-based (`demo-spring-annot`)
**Era: Late 2000s - Early 2010s**

- **Configuration**: Pure Java. `web.xml` is replaced by `WebApplicationInitializer`.
- **Boilerplate**: Low. Most configuration is handled by `@Configuration` and `@EnableWebMvc`.
- **Developer Experience**: Type-safe configuration, easier refactoring, and better IDE support.
- **Verdict**: The beginning of modern Java web development. This is where Spring became significantly more developer-friendly.

## 4. Spring Boot (`demo-spring-boot`)
**Era: 2014 - Present**

- **Configuration**: Minimal. Auto-configuration detects dependencies (like Web and Thymeleaf) and configures them automatically.
- **Boilerplate**: Near Zero. No need for `web.xml`, view resolvers, or complex initializers.
- **Developer Experience**: Exceptional. "Opinionated" defaults, embedded servers (Tomcat/Jetty), and rapid startup.
- **Verdict**: The industry standard. Focuses on business logic rather than infrastructure configuration.

---

## Data Persistence Evolution

The way Java applications interact with databases has evolved from manual connection management to fully automated abstraction layers.

| Project | Data Access Strategy | Complexity | Key Features |
| :--- | :--- | :--- | :--- |
| `demo-servlet` | **Raw JDBC** | High | Manual `Connection`, `PreparedStatement`, `ResultSet`, and Exception handling. |
| `demo-spring-xml` | **JdbcTemplate** | Moderate | Spring manages connections; developer provides SQL and RowMappers. |
| `demo-spring-annot` | **Spring Data JPA** | Low (Config High) | Interface-based repositories; handles CRUD automatically via Hibernate (Manual Java Config). |
| `demo-spring-boot` | **Spring Boot Data JPA** | Near Zero | Zero-config persistence; auto-detects H2 and configures EntityManagerFactory automatically. |

### Technical Shift
1. **Manual Control**: In `demo-servlet`, you write every line of SQL and mapping.
2. **Template Relief**: `JdbcTemplate` in `demo-spring-xml` removes resource management boilerplate but keeps you in control of SQL.
3. **ORM Power**: `demo-spring-annot` shifts to Object-Relational Mapping (ORM), treating database rows as Java Objects.
4. **Auto-Configuration**: `demo-spring-boot` eliminates the need for `DataSource` and `EntityManagerFactory` bean setup, allowing you to start coding the Repository immediately.

---

## Security Evolution

Spring Security (formerly Acegi Security) has evolved from complex manual filter chains to highly declarative Java configuration.

| Project | Security Strategy | Configuration Location | Key Concept |
| :--- | :--- | :--- | :--- |
| `demo-servlet` | **Manual Integration** | `web.xml` + Java Config | Manual registration of `DelegatingFilterProxy` to bridge Servlet/Spring worlds. |
| `demo-spring-xml` | **XML Security Namespace** | `security-config.xml` | Declarative definitions using `<security:http>` and `<security:intercept-url>`. |
| `demo-spring-annot` | **Java Configuration** | `SecurityConfig.java` | Programmatic configuration using `SecurityFilterChain` bean. |
| `demo-spring-boot` | **Auto-Configured Security** | `SecurityConfig.java` (+ Starters) | Default security active just by adding `spring-boot-starter-security`. |

### Technical Shift
1. **The Bridge**: In the Servlet-only world, you must manually "mount" Spring Security into the container's filter chain via `DelegatingFilterProxy`.
2. **Namespace Power**: The XML era introduced powerful custom schemas (`<security:*>`) that condensed complex filter configurations into readable XML.
3. **Lambda-Based Config**: Modern Spring Security (Annotations & Boot) uses a builder pattern with lambdas, making the configuration type-safe and more readable than XML.
4. **Default-On**: Spring Boot takes a "secure by default" approach—once the starter is added, everything is locked down until you explicitly grant access.


---

## Aesthetic Summary
All projects in this demonstration share the same **Premium Design System** (`modern-ui.css`), proving that even "legacy" technologies like Servlets can look modern with a good UI layer. However, the ease with which these UIs are integrated varies significantly, with Spring Boot offering the most seamless experience via Thymeleaf's natural templating.
