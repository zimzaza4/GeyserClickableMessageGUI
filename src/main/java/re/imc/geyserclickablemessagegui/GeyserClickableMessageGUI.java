package re.imc.geyserclickablemessagegui;

import com.github.steveice10.mc.protocol.packet.ingame.clientbound.ClientboundSystemChatPacket;
import com.github.steveice10.packetlib.Session;
import com.github.steveice10.packetlib.event.session.*;
import com.github.steveice10.packetlib.packet.Packet;
import net.kyori.adventure.text.Component;
import org.geysermc.event.subscribe.Subscribe;
import org.geysermc.geyser.GeyserImpl;
import org.geysermc.geyser.api.event.connection.ConnectionEvent;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCommandsEvent;
import org.geysermc.geyser.api.event.lifecycle.GeyserPostInitializeEvent;
import org.geysermc.geyser.api.extension.Extension;
import org.geysermc.geyser.session.GeyserSession;
import re.imc.geyserclickablemessagegui.command.ClickCommand;
import re.imc.geyserclickablemessagegui.message.BedrockClickableMessage;

public class GeyserClickableMessageGUI implements Extension {

    @Subscribe
    public void onDefineCommands(GeyserDefineCommandsEvent event) {
        GeyserImpl.getInstance().commandManager().registerBuiltInCommand(new ClickCommand());
    }

    @Subscribe
    public void onJoin(ConnectionEvent event) {

        if (event.connection() instanceof GeyserSession geyserSession) {
            geyserSession.getDownstream().addListener(new SessionListener() {
                @Override
                public void packetReceived(Session session, Packet packet) {
                    if (packet instanceof ClientboundSystemChatPacket chatPacket) {
                        BedrockClickableMessage.addMessage(geyserSession, chatPacket.getContent());
                    }
                }

                @Override
                public void packetSending(PacketSendingEvent packetSendingEvent) {

                }

                @Override
                public void packetSent(Session session, Packet packet) {

                }

                @Override
                public void packetError(PacketErrorEvent packetErrorEvent) {

                }

                @Override
                public void connected(ConnectedEvent connectedEvent) {

                }

                @Override
                public void disconnecting(DisconnectingEvent disconnectingEvent) {

                }

                @Override
                public void disconnected(DisconnectedEvent disconnectedEvent) {

                }
            });
        }
    }
}
