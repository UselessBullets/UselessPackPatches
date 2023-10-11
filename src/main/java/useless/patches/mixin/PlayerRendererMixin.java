package useless.patches.mixin;

import net.minecraft.client.render.ImageParser;
import net.minecraft.client.render.entity.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = PlayerRenderer.class, remap = false)
public class PlayerRendererMixin {
	@Redirect(method = "renderSpecials(Lnet/minecraft/core/entity/player/EntityPlayer;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/PlayerRenderer;loadDownloadableTexture(Ljava/lang/String;Ljava/lang/String;Lnet/minecraft/client/render/ImageParser;)Z",ordinal = 0))
	private boolean customCape(PlayerRenderer instance, String url, String local, ImageParser imageParser){
		String username = url.substring(url.lastIndexOf('=')+1);
		if (instance.loadDownloadableTexture("https://raw.githubusercontent.com/UselessBullets/UselessBTAModPack/main/Capes/" + username + ".png", local, imageParser)){
			return true;
		}
		return instance.loadDownloadableTexture(url, local, imageParser);
	}
}
