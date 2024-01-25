package useless.patches.mixin.Patches;

import net.minecraft.core.data.registry.Registry;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = RecipeSymbol.class, remap = false)
public class RecipeSymbolMixin {
	@Redirect(method = "resolve()Ljava/util/List;", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/data/registry/Registry;getItem(Ljava/lang/String;)Ljava/lang/Object;"))
	private <T> T fixCrash(Registry<List<ItemStack>> instance, String key){
		List<ItemStack> result = instance.getItem(key);
		if (result == null){
			result = new ArrayList<>();
		}
		return (T) result;
	}
}
