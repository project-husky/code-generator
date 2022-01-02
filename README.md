# husky-codegenerator

This is a code generator for the Husky library, helping to maintain part of the code. It's made of two parts:

- An enum generator for some common classes to the infrastructure. More details in the
- [wiki](https://github.com/project-husky/code-generator/wiki/) and
  in `org.husky.codegenerator.InfrastructureCodeGenerator`.
- An enum generator for the common value sets of the Swiss EPR. More details in the
  [wiki](https://github.com/project-husky/code-generator/wiki/Swiss-EPR-code-generator) and
  in `org.husky.codegenerator.SwissEprCodeGenerator`.
- A class and enum generator for the CDA-CH-EMED project. More details in the
  [wiki](https://github.com/project-husky/code-generator/wiki/CDA-CH-EMED-code-generator) and
  in `org.husky.codegenerator.CdaChEmedCodeGenerator`.

## Building

When cloning the project, some classes will be missing (package `org.husky.codegenerator.cda.antlr`). They are generated
when compiling the Maven package (see the `antlr4-maven-plugin` plugin).

## License

This code is made available under the terms of the Eclipse Public License v1.0 in the
[github project](https://github.com/project-husky/code-generator). There, you also find a list of the contributors and
the license information. This project has been developed further and modified by the joined working group Husky on the
basis of the eHealth Connector opensource project from June 28, 2021, whereas medshare GmbH is the initial and main
contributor/author of the eHealth Connector.

All other accompanying materials are made available under the terms of the Creative Commons license
Attribution-ShareAlike 3.0 Switzerland (CC BY-SA 3.0 CH)
see https://creativecommons.org/licenses/by-sa/3.0/ch/ (Ausschliesslicher Gerichtsstand ist Bern, Schweiz). The
exclusive place of jurisdiction is Bern, Switzerland for the Creative Commons license.
