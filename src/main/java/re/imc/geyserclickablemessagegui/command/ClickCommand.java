package re.imc.geyserclickablemessagegui.command;

import org.geysermc.geyser.command.GeyserCommand;
import org.geysermc.geyser.command.GeyserCommandSource;
import org.geysermc.geyser.session.GeyserSession;
import org.jetbrains.annotations.Nullable;
import re.imc.geyserclickablemessagegui.form.ClickableMessageForm;

public class ClickCommand extends GeyserCommand {
    public ClickCommand() {
        super("click", "show clickable message gui", "gcmg.command.click");
    }

    @Override
    public void execute(@Nullable GeyserSession geyserSession, GeyserCommandSource geyserCommandSource, String[] strings) {
        ClickableMessageForm.send(geyserSession);
    }

    @Override
    public boolean isBedrockOnly() {
        return true;
    }
}
