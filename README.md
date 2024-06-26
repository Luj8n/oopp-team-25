# OOPP Project

---

## Rubrics at the bottom of the file

---

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

Note: the `config.properties` has to be unchanged (codes should be equal to `MLzTfy2P,fDabK7JT,kHUI38hj`).

- Run all checks and tests

```
./gradlew build
```

- To actually see the TestFX running in real time go to build.gradle (:client) and comment lines 20 to 24:

```bash
    systemProperty 'testfx.robot', 'glass'
    systemProperty 'testfx.headless', true
    systemProperty 'prism.order', 'sw'
    systemProperty 'prism.text', 't2k'
    systemProperty 'java.awt.headless', true
```

---

## Rubric items

- Websockets:
  - [MyWebSocketHandler](server/src/main/java/server/component/MyWebSocketHandler.java)
  - [UpdateInterceptor](server/src/main/java/server/component/UpdateInterceptor.java)
  - [WebSocketConfig](server/src/main/java/server/component/WebSocketConfig.java)
  - [WebSocketClient](client/src/main/java/client/utils/WebSocketClient.java)
- Long-polling
  - [StatusController](server/src/main/java/server/api/StatusController.java)
  - [ServerUtils::serverOnline](client/src/main/java/client/utils/ServerUtils.java)
  - [ServerUtils::serverOnline](client/src/main/java/client/utils/ServerUtils.java)
  - [MainCtrl::initialize](client/src/main/java/client/scenes/MainCtrl.java)
- Keyboard shortcuts (Ctrl+Z)
  - Undo for everything except admin operations (creating/deleting events)
- Navigation (tab, space, enter)
  - All screens (expect Home screen) and popups can be exited with Esc
  - Mostly everything can be selected with Tab, Shift+Tab, Enter
