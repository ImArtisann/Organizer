import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.example.chatListener;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.Collections;

public class Main {

    public static String token = "Place token Here";
    public static void main(String[] args) throws LoginException, IOException {
        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();

        jda.addEventListener(new chatListener());
    }
}