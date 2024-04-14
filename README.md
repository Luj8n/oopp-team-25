# OOPP Project

## Server

Start the server while also resetting and seeding the database:

```bash
./gradlew bootrun --args='--seed'
```

Start it without modifying the database:

```bash
./gradlew bootrun
```

## Client

- Start the client with gradle:

```bash
./gradlew run
```

---

- Compile the client into a fat jar file:

```bash
./gradlew client:jar
```

- Execute the jar with java:

```bash
java -jar client/build/libs/client-0.0.1-SNAPSHOT.jar
```

Note: it will try to find the `config.properties` file in user's current directory.

## Testing

To test everything the Gitlab pipeline does:

- Start the server (required for client tests)

```bash
./gradlew bootrun --args='--seed'
```

- Run all checks and tests

```
./gradlew build
```

- To actually see the TestFx running in real time go to build.gradle (:client) and uncomment lines 20 to 24:
```bash
    systemProperty 'testfx.robot', 'glass'
    systemProperty 'testfx.headless', true
    systemProperty 'prism.order', 'sw'
    systemProperty 'prism.text', 't2k'
    systemProperty 'java.awt.headless', true
```
