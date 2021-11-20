# mower in Kotlin

![workflow](https://github.com/javintx/mower-kt/actions/workflows/gradle.yml/badge.svg)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=javintx_mower-kt&metric=alert_status)](https://sonarcloud.io/dashboard?id=javintx_mower)
[![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=javintx_mower-kt&metric=coverage)](https://sonarcloud.io/component_measures/metric/coverage/list?id=javintx_mower)
[![SonarCloud Bugs](https://sonarcloud.io/api/project_badges/measure?project=javintx_mower-kt&metric=bugs)](https://sonarcloud.io/component_measures/metric/reliability_rating/list?id=javintx_mower)
[![SonarCloud Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=javintx_mower-kt&metric=vulnerabilities)](https://sonarcloud.io/component_measures/metric/security_rating/list?id=javintx_mower)
[![SonarCloud Code Smells](https://sonarcloud.io/api/project_badges/measure?project=javintx_mower-kt&metric=code_smells)](https://sonarcloud.io/component_measures?id=javintx_mower&metric=Maintainability&view=list)

## The mower challenge

The mower only have 3 commands that controls the mower movements:

- "L": Spin the mower to left.
- "R": Spin the mower to right.
- "M": Move the mower forward.

The mower has an initial position in the plateau represented by coordinates (X, Y). Assume that the square directly
North from (X, Y) is (X, Y + 1)

And the mower could face to one of the cardinal points:

- "N": North
- "E": East
- "S": South
- "W": West

## Assumptions

- The coordinates are always positive numbers.
- The mower executes the command and then verify if it has any problem.
    + When mower move outside the plateau, the mower stops and does not occupy any coordinate in the plateau.
    + When mower crashed with any previous mower, the mower stops and occupy the same coordinate as crashed mower.
- The application is command line interactive. The user experience is guided through messages.

## Architecture

- Hexagonal architecture implemented using Test-driven development
  ([TDD](https://en.wikipedia.org/wiki/Test-driven_development)) in Java 11.
- JUnit5 with Mockito is used for unit tests.
    + mock-maker-inline extension used for final classes.
- The mower application has been done respecting the [SOLID](https://en.wikipedia.org/wiki/SOLID)
  and [KISS](https://en.wikipedia.org/wiki/KISS_principle) principles and applying Clean Code as far as possible.

### Core hexagon

Hexagon with the domain and the use case logic of the mower application.

### App hexagon

Hexagon with the application logic of the mower application. It contains the java main method.

# Requirements

- It is necessary to have Docker installed to execute the docker image generated.

---

# HOW-TO

## Build the project

In the `rootDirectory` execute:

> gradlew clean build

## Execute tests

In the `rootDirectory` execute:

> gradlew clean test

## Execute the application

In the `rootDirectory/build/libs` execute:

> java -jar mower-1.0.0.jar

---

# Docker

## Generate Docker image

To generate a Docker image with "mower" name after build the project, execute the next command:

> docker build -t mower

## Run Docker image

To run the Docker image "mower", execute the next command:

> docker run -i -t mower

The -i and -t parameters provides input using your keyboard while the outputs is logged to your terminal.

---

# User manual

After execute the application, the user will be guided through console:

- First, it is necessary to insert the plateau dimensions by insert the width, and the height (represented by a positive
  numbers).
- Secondly, it is necessary to insert the mower coordinates (represented by a positive numbers) and orientation
  (represented by one of the four cardinal points: 'N', 'E', 'S', W').
    + A mower can only be valid if their coordinates are inside the plateau and there is no previous mower in the same
      coordinates.
- In third place, it is necessary to insert the mower commands (represented by the letters: 'L', 'R' or 'M').

After the commands inserted, the mower executes them and prints their final position if is inside the plateau.

- At the end, it is necessary to insert 'N'/'n'' to continue adding mowers and commands, or 'Y'/'y' to exiting the
  application.

---

# WHERE-TO

## Find the test reports

After build the project:

> {MODULE_NAME}/build/report/tests/test/index.html

Where "MODULE_NAME" could be each module of the mower application.

## Find the build reports

After build the project, you can find the html files that contains the build reports.

> {MODULE_NAME}/build/report/checkstyle/main.html
> {MODULE_NAME}/build/report/checkstyle/test.html
>
> {MODULE_NAME}/build/report/pmd/main.html
> {MODULE_NAME}/build/report/pmd/test.html
>
> {MODULE_NAME}/build/report/spotbugs/main.html
> {MODULE_NAME}/build/report/spotbugs/test.html
>
> {MODULE_NAME}/build/report/jacoco/test/index.html

Where "MODULE_NAME" could be each module of the mower application.

--- 

# TO-DO

- MowerApp: Find another way to inject for test purposes.
- Add e2e tests
