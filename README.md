# Software Architecture Compliance Checking - Gradle Plugin

## Usage

Download the plugin and deploy it to your local repository.

    ./gradlew -b ./buildSrc/build.gradle publishToMavenLocal

Add the following to your build script.

```groovy
buildscript {
    repositories {
        mavenCentral()
        mavenLocal()
    }
    dependencies {
        classpath 'fr.ubordeaux.ddd:architecture-compliance:0.1.0'
    }
}

apply plugin: fr.ubordeaux.ddd.plugin.CompliancePlugin
```

You can now configure extensions and run tasks.

    ./gradlew compliance

Since no dependency is needed between the plugin and your project, you can apply it from any build script. It can also be applied without deployment using the `build.gradle` file.

## Extensions

Configure the following and add it to your build script. An example is available in the `build.gradle` file.

```groovy
compliance {
    // Configures plugin.
    description {
        // Configures description (optional).
    }
    project {
        // Configures project.
    }
    syntax {
        // Configures syntax (optional).
        classes {
            // Configures class-level syntax (optional).
        }
        fields {
            // Configures field-level syntax (optional).
        }
        methods {
            // Configures method-level syntax (optional).
        }
        packages {
            // Configures package-level syntax (optional).
        }
    }
}
```

### Compliance

| Parameter | Description              | Optional | Default |
| --------- | ------------------------ | -------- | ------- |
| `rules`   | Array of rule names.     | True     | Empty   |
| `fixes`   | Boolean to enable fixes. | True     | False   |

Will assert rules (if some are specified) on the architecture which follow the syntax.

#### Description

| Parameter      | Description                                                                       | Optional | Default |
| -------------- | --------------------------------------------------------------------------------- | -------- | ------- |
| `paths`        | Array of absolute paths to description files.                                     | True     | Empty   |
| `fromPackages` | Boolean to specify the description format between from classes and from packages. | True     | False   |
| `asXml`        | Boolean to specify the files format between JSON and XML.                         | True     | False   |

Will analyze the description of files (if some are specified) which follow description and files formats. Architecture can be exported as description using the application [here](https://github.com/GlobyBdx/architecture-export).

#### Project

| Parameter       | Description               | Optional | Default       |
| --------------- | ------------------------- | -------- | ------------- |
| `directoryPath` | Absolute path to project. | False    |               |
| `folderPath`    | Relative path to folder.  | True     | src/main/java |
| `packages`      | Array of package names.   | True     | Empty         |

Will analyze the bytecode of all packages (if none are specified) which reside in the folder of the project.

#### Syntax

Default annotations can be retrieved. Build the project and generate the JAR library.

```bash
./gradlew build
jar cvf ddd-annotations.jar -C ./bin/default/ ./fr/ubordeaux/ddd/annotations/
```

##### Classes

| Parameter        | Description                                                                                           |
| ---------------- | ----------------------------------------------------------------------------------------------------- |
| `aggregate`      | Syntax for aggregates. <br/> (optional, default: fr.ubordeaux.ddd.annotations.classes.Aggregate)      |
| `entity`         | Syntax for entities. <br/> (optional, default: fr.ubordeaux.ddd.annotations.classes.Entity)           |
| `factory`        | Syntax for factories. <br/> (optional, default: fr.ubordeaux.ddd.annotations.classes.Factory)         |
| `repository`     | Syntax for repositories. <br/> (optional, default: fr.ubordeaux.ddd.annotations.classes.Repository)   |
| `service`        | Syntax for services. <br/> (optional, default: fr.ubordeaux.ddd.annotations.classes.Service)          |
| `valueObject`    | Syntax for value objects. <br/> (optional, default: fr.ubordeaux.ddd.annotations.classes.ValueObject) |
| `areAnnotations` | Boolean to specify the format between character sequences and annotations. (optional, default: True)  |

Will assert rules on classes annotated with the annotation or containing the character sequence.

Examples of character sequences: Aggregate ; Entity ; Factory ; Repository ; Service ; ValueObject

##### Fields

| Parameter        | Description                                                                                          |
| ---------------- | ---------------------------------------------------------------------------------------------------- |
| `entityId`       | Syntax for entity IDs. <br/> (optional, default: fr.ubordeaux.ddd.annotations.fields.EntityID)       |
| `immutable`      | Syntax for immutables. <br/> (optional, default: fr.ubordeaux.ddd.annotations.fields.Immutable)      |
| `areAnnotations` | Boolean to specify the format between character patterns and annotations. (optional, default: True)  |

Will assert rules on fields annotated with the annotation or matching the character pattern (regex).

Examples of character patterns: [a-zA-Z]*Id ; [a-zA-Z]*Imm

##### Methods

| Parameter        | Description                                                                                         |
| ---------------- | --------------------------------------------------------------------------------------------------- |
| `getter`         | Syntax for getters. <br/> (optional, default: fr.ubordeaux.ddd.annotations.methods.Getter)          |
| `setter`         | Syntax for setters. <br/> (optional, default: fr.ubordeaux.ddd.annotations.methods.Setter)          |
| `areAnnotations` | Boolean to specify the format between character patterns and annotations. (optional, default: True) |

Will assert rules on methods annotated with the annotation or matching the character pattern (regex).

Examples of character patterns: get[a-zA-Z]* ; set[a-zA-Z]*

##### Packages

| Parameter        | Description                                                                                                       |
| ---------------- | ----------------------------------------------------------------------------------------------------------------- |
| `anticorruption` | Syntax for anticorruption layers. <br/> (optional, default: fr.ubordeaux.ddd.annotations.packages.Anticorruption) |
| `application`    | Syntax for application layers. <br/> (optional, default: fr.ubordeaux.ddd.annotations.packages.Application)       |
| `domain`         | Syntax for domain layers. <br/> (optional, default: fr.ubordeaux.ddd.annotations.packages.Domain)                 |
| `infrastructure` | Syntax for infrastructure layers. <br/> (optional, default: fr.ubordeaux.ddd.annotations.packages.Infrastructure) |
| `presentation`   | Syntax for presentation layers. <br/> (optional, default: fr.ubordeaux.ddd.annotations.packages.Presentation)     |
| `areAnnotations` | Boolean to specify the format between names and annotations. (optional, default: True)                            |

Will assert rules on packages and sub-packages of packages annotated with the annotation (`package-info.java`) or named with the name.

Examples of names: anticorruption ; application ; domain ; infrastructure ; presentation

## Tasks

| Task          | Description                                                                |
| ------------- | -------------------------------------------------------------------------- |
| `syntax`      | Configures syntax.                                                         |
| `description` | Configures description and imports metadata using given syntax.            |
| `project`     | Configures project and imports metadata from given bytecode.               |
| `conformance` | Checks conformance between imported description and project metadata.      |
| `compliance`  | Checks compliance of imported metadata to given architectural constraints. |
