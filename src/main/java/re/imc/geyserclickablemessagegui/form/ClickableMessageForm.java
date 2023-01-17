package re.imc.geyserclickablemessagegui.form;


import org.geysermc.cumulus.form.SimpleForm;
import org.geysermc.geyser.platform.bungeecord.shaded.net.kyori.adventure.text.event.ClickEvent;
import org.geysermc.geyser.platform.bungeecord.shaded.net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.geysermc.geyser.session.GeyserSession;
import re.imc.geyserclickablemessagegui.message.BedrockClickableMessage;

import java.util.Collections;
import java.util.List;

public class ClickableMessageForm {
    public static void send(GeyserSession session) {

        List<BedrockClickableMessage> msgs = BedrockClickableMessage.getMessages(session);

        Collections.reverse(msgs);


        SimpleForm.Builder builder = SimpleForm.builder().title("");
        for (BedrockClickableMessage message : msgs) {
            String text = LegacyComponentSerializer.legacyAmpersand().serialize(message.getMsg());
            builder.button(text.replace("&", "§") + "\n" + "§7" + message.getAction());

        }
        builder.validResultHandler((response)-> {


                int buttonId = response.clickedButtonId();
                BedrockClickableMessage msg = msgs.get(buttonId);
                if (msg.getAction() == ClickEvent.Action.RUN_COMMAND) {
                    session.sendCommand(msg.getValue());
                } else if (msg.getAction() == ClickEvent.Action.SUGGEST_COMMAND) {
                    session.sendMessage("Suggest:" + msg.getValue());

            }
        });
        session.sendForm(builder);
    }
}
