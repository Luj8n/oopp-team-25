package client.utils;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/**
 * A websocket client.
 */
public class WebSocketClient {
    private WebSocketSession session;
    private String url;
    private Runnable onUpdate;

    /**
     * Creates a websocket client.
     *
     * @param url      the websocket server address
     * @param onUpdate this is run when an 'update' message is received
     */
    public WebSocketClient(String url, Runnable onUpdate) {
        this.url = url;
        this.onUpdate = onUpdate;
        connect();
    }

    /**
     * Tries to [re]connect to a websocket server.
     *
     * @return whether connection was successful
     */
    public boolean connect() {
        if (session != null) {
            // shouldn't be able to connect to a server if there already is a session
            return false;
        }
        var handler = new MyWebSocketHandler();
        try {
            session = new StandardWebSocketClient().execute(handler, url).get();
            return session != null;
        } catch (Exception e) {
            System.err.println("Could not connect to websocket server: " + e);
            return false;
        }
    }

    private class MyWebSocketHandler extends AbstractWebSocketHandler {
        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
            System.err.println("Websocket connection was closed. Status: " + status);
            session = null;
            // TODO: check status of session in the long polling loop
            // if it is null, try to reconnect
        }

        @Override
        public void handleTextMessage(WebSocketSession session, TextMessage message) {
            String payload = message.getPayload();
            System.err.println("Got a message from ws. Payload: " + payload);
            if ("update".equals(payload)) {
                if (onUpdate != null) {
                    onUpdate.run();
                }
            }
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setOnUpdate(Runnable onUpdate) {
        this.onUpdate = onUpdate;
    }
}