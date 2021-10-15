package net.darkhax.bookshelf.mixin.effect;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MobEffectInstance.class)
public interface AccessorMobEffectInstance {

    @Accessor("hiddenEffect")
    MobEffectInstance bookshelf$getHiddenEffect();

    @Accessor("hiddenEffect")
    void bookshelf$setHiddenEffect(EffectInstance hiddenEffect);
}
