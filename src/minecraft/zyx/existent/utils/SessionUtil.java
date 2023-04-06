package zyx.existent.utils;

import java.net.Proxy;

import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class SessionUtil {
	private final YggdrasilUserAuthentication authentication;
	Minecraft mc = Minecraft.getMinecraft();

    private SessionUtil() {
        this.authentication = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
    }
    public final Session session() {
        try {
            authentication.logIn();
            GameProfile profile = authentication.getSelectedProfile();
            return new Session("LovelyJS", profile.getId().toString(), authentication.getAuthenticatedToken(), "MOJANG");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final boolean login() {
        Session session = session();
        if (session == null) {
            return false;
        }

        mc.session = session;
        return true;
    }
}