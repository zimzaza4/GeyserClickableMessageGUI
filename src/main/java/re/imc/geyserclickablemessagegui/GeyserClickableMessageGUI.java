package re.imc.geyserclickablemessagegui;

import com.github.steveice10.packetlib.event.session.SessionListener;
import org.geysermc.event.subscribe.Subscribe;
import org.geysermc.geyser.GeyserImpl;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCommandsEvent;
import org.geysermc.geyser.api.event.lifecycle.GeyserPostInitializeEvent;
import org.geysermc.geyser.api.extension.Extension;
import org.geysermc.geyser.session.GeyserSession;
import re.imc.geyserclickablemessagegui.command.ClickCommand;
import re.imc.geyserclickablemessagegui.listener.ChatPacketSessionListener;

import java.util.concurrent.TimeUnit;

public class GeyserClickableMessageGUI implements Extension {

    @Subscribe
    public void onPostInitialize(GeyserPostInitializeEvent e) {

        GeyserImpl.getInstance().getScheduledThread().scheduleAtFixedRate(() -> {
            for (GeyserSession session : GeyserImpl.getInstance().getSessionManager().getAllSessions()) {
                boolean canAdd = true;
                if (session.getDownstream() == null) {
                    continue;
                }
                for (SessionListener listener : session.getDownstream().getListeners()) {
                    if (listener instanceof ChatPacketSessionListener) {
                        canAdd = false;
                        break;
                    }
                }
                if (canAdd) {
                    logger().info("add listener");
                    session.getDownstream().addListener(new ChatPacketSessionListener(session));
                }
            }
        }, 2, 2, TimeUnit.SECONDS);
        // :P
    }
    @Subscribe
    public void onDefineCommands(GeyserDefineCommandsEvent event) {
        GeyserImpl.getInstance().commandManager().registerBuiltInCommand(new ClickCommand());
    }

}
