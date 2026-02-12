# Seven Things - Lift Framework Demo Application

A feature-rich demonstration application showcasing seven key capabilities of the **Lift web framework**, a powerful and expressive web development framework for Scala.

## Overview

"Seven Things" is an interactive web application that illustrates core Lift framework features through practical, working examples. Each feature includes live demonstrations and accompanying explanations to help developers understand how to leverage these capabilities in their own applications.

## Featured Demonstrations

The application showcases the following seven Lift features:

1. **Lazy Loading** - Efficiently load page content on-demand to improve initial page load times and reduce bandwidth usage
2. **Parallel Rendering** - Render multiple page sections concurrently for improved performance
3. **Comet & AJAX** - Real-time server push notifications and asynchronous client-server communication
4. **Wiring** - Functional reactive programming patterns for managing dynamic data flow
5. **Designer Friendly Templates** - Create clean, semantic HTML templates that designers and developers can work with collaboratively
6. **Wizard** - Build multi-step forms and workflows with state management
7. **Security** - Implement Content Security Policy and other security best practices

## Technology Stack

- **Language**: Scala 2.13.18
- **Framework**: Lift Web Framework 4.0.0-M4
- **Build System**: SBT (Scala Build Tool) with sbt-war plugin
- **Server**: Jetty 11.0.25 (embedded for testing and demo)
- **Testing**: Specs2 4.21.0 with JUnit
- **Database**: H2 (for demonstration purposes)
- **Logging**: Logback
- **JavaScript**: jQuery 3.7.1

## Building and Running

### Prerequisites

- Java 11 or later (required for Jetty 11)
- SBT (Scala Build Tool)

### Quick Start

```bash
# Run the application with SBT
sbt

# In the SBT prompt, run:
> warStart

# The application will be available at http://localhost:8080
```

Alternatively, you can use the included `sbt` script directly:

```bash
sbt warStart
```

### Running Tests

```bash
sbt test
```

## Internationalization

The application includes full internationalization support for:

- English (default)
- Simplified Chinese (中文)
- Traditional Chinese (繁體中文)

Language switching is handled automatically based on browser settings.

## Project Structure

```
src/main/
├── scala/
│   ├── bootstrap/liftweb/          # Application bootstrap configuration
│   └── net/liftweb/seventhings/
│       ├── snippet/                # Lift snippets (view logic components)
│       ├── comet/                  # Comet actors for real-time updates
│       └── model/                  # Data models (if any)
├── webapp/                         # HTML templates and static assets
└── resources/
    ├── i18n/                       # Internationalization properties files
    └── props/                      # Logback configuration
```

## Key Snippets

- **MyWizard.scala** - Multi-step form wizard implementation
- **Chat.scala** - Real-time chat using Comet (server push)
- **ChatIn.scala** - Chat input handling
- **FetchAd.scala** - AJAX-based content fetching
- **LongTime.scala** - Long-running operation demonstration
- **InvoiceWiring.scala** - Functional reactive wiring example
- **ShowCode.scala** - Source code display with syntax highlighting

## Recent Updates

- Upgraded to Scala 2.13.18 and Lift 4.0.0-M4 for modern language features and Jakarta Servlet 6.1 support
- Migrated from xsbt-web-plugin to sbt-war 5.1.1 build plugin
- Updated to Jetty 11.0.25 with Jakarta Servlet API
- Replaced lift-jquery-module with jQuery 3.7.1 WebJar (now bundled in lift-webkit)
- Fixed deprecated Scala 2.13 procedure syntax throughout codebase
- Implemented Content Security Policy (CSP) for enhanced security
- Added Logback logging to prevent disk space issues
- Migrated from class-based selectors to `data-lift` attribute-based selectors
- Full UTF-8 support
- HTML5 rendering enabled

## Browser Support

The application is tested and works with all modern browsers:

- Chrome/Chromium
- Firefox
- Safari
- Edge

## Learning Resources

This application serves as both a tutorial and reference implementation for Lift web developers. Each demo page includes:

- Interactive examples
- Live demonstrations
- Code snippets showing how to implement the feature

## License

This project is part of the Lift web framework community projects.

## Additional Information

For more information about the Lift web framework, visit:
- [Lift Web Framework Official Site](https://www.liftweb.net/)
- [Lift Framework Documentation](https://accessing-and-modifying-lift-properties.lift.rocks/)

## Contributing

This is a demonstration project maintained as part of the Lift framework ecosystem. Contributions and improvements are welcome!
