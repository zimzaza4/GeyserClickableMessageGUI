package re.imc.geyserclickablemessagegui.form;

import org.geysermc.cumulus.form.CustomForm;
import org.geysermc.geyser.session.GeyserSession;

public class CommandForm {
    public static void send(GeyserSession session, String suggestCommand) {
        CustomForm.Builder form = CustomForm.builder().title("Command");
        form.input("", "help", suggestCommand);
        form.validResultHandler(response -> {


        });

    }
}
