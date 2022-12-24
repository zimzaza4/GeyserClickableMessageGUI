package re.imc.geyserclickablemessagegui.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import org.geysermc.geyser.session.GeyserSession;

import java.util.*;

public class BedrockClickableMessage {
    public static final Map<GeyserSession, List<BedrockClickableMessage>> playerMessages = new HashMap<>();
    private String value;
    private ClickEvent.Action action;
    private TextComponent msg;

    public static void addMessage(GeyserSession session, Component message) {
        List<Component> textComponents = new ArrayList<>();

        if (message.children().size() > 0) {
            textComponents = message.children();
        } else {
            textComponents.add(message);
        }

        for (Component component : textComponents) {

            ClickEvent clickEvent = component.clickEvent();

            BedrockClickableMessage clickableMessage = new BedrockClickableMessage();
            if (clickEvent == null) {
                continue;
            }

            if (clickEvent.action() == ClickEvent.Action.RUN_COMMAND || clickEvent.action() == ClickEvent.Action.SUGGEST_COMMAND) {
                if (component instanceof TextComponent) {
                    clickableMessage.msg = (TextComponent) component;
                    clickableMessage.value = clickEvent.value();
                    clickableMessage.action = clickEvent.action();

                    List<BedrockClickableMessage> messages;
                    if (playerMessages.containsKey(session)) {
                        messages = playerMessages.get(session);

                        if (messages.size() >= 25) {
                            messages.remove(0);
                        }

                        messages.add(clickableMessage);

                    } else {
                        messages = new ArrayList<>();
                        messages.add(clickableMessage);
                        playerMessages.put(session, messages);

                    }
                }
            }
        }
    }

    public static List<BedrockClickableMessage> getMessages(GeyserSession session) {
        if (playerMessages.containsKey(session)) {
            return new ArrayList<>(playerMessages.get(session));
        }
        return new ArrayList<>();
    }

    public String getValue() {
        if (value != null) {
            if (!value.startsWith("/")) {
                return value;
            }
            return value.substring(1);
        }
        return "";
    }

    public TextComponent getMsg() {
        return msg;
    }

    public ClickEvent.Action getAction() {
        return action;
    }
}

