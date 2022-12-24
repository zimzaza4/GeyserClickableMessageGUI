package re.imc.geyserclickablemessagegui.listener;

import com.github.steveice10.mc.protocol.packet.ingame.clientbound.ClientboundSystemChatPacket;
import com.github.steveice10.packetlib.Session;
import com.github.steveice10.packetlib.event.session.*;
import com.github.steveice10.packetlib.packet.Packet;
import org.geysermc.geyser.session.GeyserSession;
import re.imc.geyserclickablemessagegui.message.BedrockClickableMessage;

public class ChatPacketSessionListener implements SessionListener {

    private final GeyserSession geyserSession;

    public ChatPacketSessionListener(GeyserSession geyserSession) {
        this.geyserSession = geyserSession;
    }

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
}
