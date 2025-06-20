package com.momosensei.all_things_tinkers.modifiers.modifiers;

import com.momosensei.all_things_tinkers.modifiers.ATTmodifier;
import com.momosensei.all_things_tinkers.register.ATTModifiers;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PotionExtension extends ATTmodifier {
    public PotionExtension() {
        MinecraftForge.EVENT_BUS.addListener(this::AddMobEffect);
    }
    private static final List<MobEffect> BENEFICIAL_EFFECTS = new ArrayList<>(List.of(
            MobEffects.LUCK, MobEffects.DIG_SPEED, MobEffects.FIRE_RESISTANCE, MobEffects.MOVEMENT_SPEED,
            MobEffects.DAMAGE_RESISTANCE, MobEffects.JUMP,MobEffects.DAMAGE_BOOST, MobEffects.HEAL,
            MobEffects.REGENERATION,MobEffects.WATER_BREATHING,MobEffects.INVISIBILITY, MobEffects.NIGHT_VISION,
            MobEffects.HEALTH_BOOST,MobEffects.ABSORPTION,MobEffects.SATURATION, MobEffects.SLOW_FALLING,
            MobEffects.CONDUIT_POWER,MobEffects.DOLPHINS_GRACE
    ));
    public static List<MobEffect> getBeneficialEffectsByCopy(){
        return new ArrayList<>(BENEFICIAL_EFFECTS);
    }

    public void AddMobEffect(MobEffectEvent.Added event) {
        if (event.getEntity() instanceof Player player) {
            int a = getAllModifierlevel(player, ATTModifiers.potionextension.getId());
            if (a>0){
                var instance=event.getEffectInstance();
                var effect = instance.getEffect();
                for (MobEffect beneficialeffect : getBeneficialEffectsByCopy()) {
                    instance.duration = instance.mapDuration(b -> {
                        if (instance.isInfiniteDuration() || effect.isInstantenous() || effect != beneficialeffect) return b;
                        return (int) (b * (1f + 0.1f * a));
                    });
                }
            }
        }
    }
}