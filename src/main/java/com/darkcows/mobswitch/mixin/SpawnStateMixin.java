package com.darkcows.mobswitch.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.darkcows.mobswitch.MobSwitch;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.NaturalSpawner;

@Mixin(NaturalSpawner.SpawnState.class)
public abstract class SpawnStateMixin {

    @ModifyVariable(method = "canSpawnForCategoryGlobal", at = @At("STORE"))
    private int injected(final int maxMobCount, final MobCategory mobCategory) {

        // for monsters specifically, if the mob switch is enabled, set the global cap to 0
        if (!mobCategory.isFriendly() && MobSwitch.MOBSWITCH_ENABLED) return 0;

        // for non-persistant mobs, double the global cap (prevents filling up the global mob cap)
        // this may mess with bat switches
        if (!mobCategory.isPersistent()) return maxMobCount * 2;
        
        return maxMobCount;
    }
}