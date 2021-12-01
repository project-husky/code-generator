# husky-codegenerator

This is a code generator for the Husky library, helping to maintain part of the code. It's made of two parts:

- An enum generator for some common classes to the infrastructure. More details in the
- [wiki](https://github.com/project-husky/code-generator/wiki/) and in `org.husky.codegenerator.InfrastructureCodeGenerator`.
- An enum generator for the common value sets of the Swiss EPR. More details in the
[wiki](https://github.com/project-husky/code-generator/wiki/Swiss-EPR-code-generator) and in `org.husky.codegenerator.SwissEprCodeGenerator`.
- A class and enum generator for the CDA-CH-EMED project. More details in the
[wiki](https://github.com/project-husky/code-generator/wiki/CDA-CH-EMED-code-generator) and in `org.husky.codegenerator.CdaChEmedCodeGenerator`.

## Building

When cloning the project, some classes will be missing (package `org.husky.codegenerator.cda.antlr`).
They are generated when compiling the Maven package (see the `antlr4-maven-plugin` plugin).