package com.mcjinmouren.extrabotany.common.effect.MobEffects;

import com.mcjinmouren.extrabotany.common.effect.ExtraBotanyEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import java.util.Objects;

public class Reflect extends MobEffect {
    public Reflect() {
        super(MobEffectCategory.NEUTRAL, 0X4169E1);
    }

    public static void onLivingAttacked(LivingAttackEvent event) {
        LivingEntity living = event.getEntity();
        DamageSource source = event.getSource();
        Entity attacker = source.getDirectEntity();
        if (living instanceof Player player && player.hasEffect(ExtraBotanyEffects.reflect)) {
            int levels = Objects.requireNonNull(living.getEffect(ExtraBotanyEffects.reflect)).getAmplifier();
            float dmg = event.getAmount() / Math.max(1, 6 - levels);
            if (attacker != null) {
                Level level = living.level();
                attacker.hurt(level.damageSources().magic(), dmg);
            }
        }
    }
}
