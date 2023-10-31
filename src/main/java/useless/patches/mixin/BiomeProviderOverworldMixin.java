package useless.patches.mixin;

import net.minecraft.core.world.biome.provider.BiomeProvider;
import net.minecraft.core.world.biome.provider.BiomeProviderOverworld;
import net.minecraft.core.world.noise.PerlinSimplexNoise;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BiomeProviderOverworld.class, remap = false)
public abstract class BiomeProviderOverworldMixin extends BiomeProvider {
	@Shadow
	private double temperatureFuzzPercentage;

	@Shadow
	@Final
	private PerlinSimplexNoise fuzzinessNoise;

	@Shadow
	@Final
	private PerlinSimplexNoise temperatureNoise;

	@Shadow
	private double temperatureXScale;

	@Shadow
	private double temperatureZScale;

	@Shadow
	private double temperatureExponent;

	@Shadow
	private double fuzzinessXScale;

	@Shadow
	private double fuzzinessZScale;

	@Shadow
	private double fuzzinessExponent;

	@Inject(method = "getTemperatures([DIIII)[D", at = @At("HEAD"), cancellable = true)
	private void coolWorld(double[] temperatures, int x, int z, int xSize, int zSize, CallbackInfoReturnable<double[]> cir){
		if (temperatures == null || temperatures.length < xSize * zSize) {
			temperatures = new double[xSize * zSize];
		}
		double[] tnResult = this.temperatureNoise.getValue(null, x, z, xSize, zSize, this.temperatureXScale, this.temperatureZScale, this.temperatureExponent);
		double[] fnResult = this.fuzzinessNoise.getValue(null, x, z, xSize, zSize, this.fuzzinessXScale, this.fuzzinessZScale, this.fuzzinessExponent);
		for (int dx = 0; dx < xSize; ++dx) {
			for (int dz = 0; dz < zSize; ++dz) {
				double fuzziness = fnResult[dx * zSize + dz] * 1.1 + 0.5;
				double fuzzPctg = this.temperatureFuzzPercentage;
				double valPctg = 1.0 - fuzzPctg;
				double temperature = (tnResult[dx * zSize + dz] * 0.15 + 0.5) * valPctg + fuzziness * fuzzPctg;
				if ((temperature = 1.0 - (1.0 - temperature) * (1.0 - temperature)) < 0.0) {
					temperature = 0.0;
				}
				if (temperature > 1.0) {
					temperature = 1.0;
				}
				temperatures[dx * zSize + dz] = temperature;
			}
		}
		cir.setReturnValue(temperatures);
	}
}
