package net.alexandra.atlas.atlas_combat.item;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

import java.util.UUID;

enum WeaponType {
    SWORD,
    AXE,
    PICKAXE,
    HOE,
    SHOVEL,
    TRIDENT;

    protected static final UUID BASE_ATTACK_DAMAGE_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    protected static final UUID BASE_ATTACK_SPEED_UUID = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
    protected static final UUID BASE_ATTACK_REACH_UUID = UUID.fromString("26cb07a3-209d-4110-8e10-1010243614c8");

    private WeaponType() {
    }

    public void addCombatAttributes(Tier var1, ImmutableMultimap.Builder<Attribute, AttributeModifier> var2) {
        float var3 = this.getSpeed(var1);
        float var4 = this.getDamage(var1);
        float var5 = this.getReach(var1);
        var2.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)var4, AttributeModifier.Operation.ADDITION));
        var2.put(Attributes.getAttribute("generic.attack_speed"), new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)var3, AttributeModifier.Operation.ADDITION));
        if (var5 != 0.0F) {
            var2.put(Attributes.getAttribute("generic.attack_reach"), new AttributeModifier(BASE_ATTACK_REACH_UUID, "Weapon modifier", (double)var5, AttributeModifier.Operation.ADDITION));
        }

    }

    public float getDamage(Tier var1) {
        switch (this) {
            case SWORD:
                return var1.getAttackDamageBonus() + 2.0F;
            case AXE:
                return var1.getAttackDamageBonus() + 3.0F;
            case PICKAXE:
                return var1.getAttackDamageBonus() + 1.0F;
            case HOE:
                if (var1 != Tiers.IRON && var1 != Tiers.DIAMOND) {
                    if (var1 == Tiers.NETHERITE) {
                        return 2.0F;
                    }

                    return 0.0F;
                }

                return 1.0F;
            case SHOVEL:
                return var1.getAttackDamageBonus();
            case TRIDENT:
                return 5.0F;
            default:
                return 0.0F;
        }
    }

    public float getSpeed(Tier var1) {
        switch (this) {
            case SWORD:
                return 0.5F;
            case AXE:
            case SHOVEL:
            case TRIDENT:
                return -0.5F;
            case PICKAXE:
                return 0.0F;
            case HOE:
                if (var1 == Tiers.WOOD) {
                    return -0.5F;
                } else if (var1 == Tiers.IRON) {
                    return 0.5F;
                } else if (var1 == Tiers.DIAMOND) {
                    return 1.0F;
                } else if (var1 == Tiers.GOLD) {
                    return 1.0F;
                } else {
                    if (var1 == Tiers.NETHERITE) {
                        return 1.0F;
                    }

                    return 0.0F;
                }
            default:
                return 0.0F;
        }
    }

    public float getReach(Tier var1) {
        switch (this) {
            case SWORD:
                return 0.5F;
            case AXE:
            case PICKAXE:
            case SHOVEL:
                return 0.0F;
            case HOE:
            case TRIDENT:
                return 1.0F;
            default:
                return 0.0F;
        }
    }
}
