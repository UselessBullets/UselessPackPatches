package useless.patches.mixin.Patches;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.chunk.provider.ChunkProviderClient;
import net.minecraft.core.net.packet.Packet56RequestChunk;
import net.minecraft.core.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.Map;

@Mixin(value = ChunkProviderClient.class, remap = false)
public class ChunkProviderClientMixin {
	@Shadow
	private Map chunkMapping;

	@Inject(method = "checkForMissingChunks()V", at = @At("HEAD"))
	private void checkForMissingChunks(CallbackInfo ci){
		for (Chunk chunk : (Collection<Chunk>)chunkMapping.values())
        {
            if (!chunk.receivedFromServer)
            {
                if (chunk.ticksUnreceived > 100)
                {
                    Minecraft.getMinecraft(this).getSendQueue().addToSendQueue(new Packet56RequestChunk(chunk.xPosition, chunk.zPosition));
                    System.out.println("Re-requesting chunk at " + chunk.xPosition + " " + chunk.zPosition);
                    chunk.ticksUnreceived = 0;
                }
                chunk.ticksUnreceived++;
            }
        }
	}
}
