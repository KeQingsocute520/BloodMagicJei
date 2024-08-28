package keqing.bloodmagicjei;

import WayofTime.bloodmagic.meteor.MeteorComponent;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MeteorsRecipeJEI implements IRecipeWrapper {
    @Nonnull
    public final List<ItemStack> output;
    @Nonnull
    public final ItemStack input;
    public final List<String> oreNames;
    public final List<Integer> oreWeight;

    private final float explosionStrength;
    private final int radius;
    private final int maxWeight;
    public int version;
    public int cost;

    private final List<List<ItemStack>> groupedInputsAsItemStacks = new ArrayList<>();
    private final List<List<ItemStack>> groupedOutputsAsItemStacks = new ArrayList<>();

    public MeteorsRecipeJEI(ItemStack input, List<MeteorComponent> output,float explosionStrength,int radius,int maxWeight,int version,int cost) {
        this.explosionStrength=explosionStrength;
        this.radius=radius;
        this.maxWeight=maxWeight;
        this.version=version;
        this.cost=cost;

        this.groupedInputsAsItemStacks.add(Collections.singletonList(input));

        oreNames = output.stream()
                .map(MeteorComponent::getOreName)
                .collect(Collectors.toList());

        for (String oreName : oreNames) {
            this.groupedOutputsAsItemStacks.add(OreDictionary.getOres(oreName));
        }

        this.output = getItemStacksFromOreNames(oreNames);

        oreWeight = output.stream()
                .map(MeteorComponent::getWeight)
                .collect(Collectors.toList());

        this.input = input;
    }
    public static List<ItemStack> getItemStacksFromOreNames(List<String> oreNames) {
        List<ItemStack> itemStacks = new ArrayList<>();

        for (String oreName : oreNames) {
            Collection<ItemStack> ores = OreDictionary.getOres(oreName);
            if (ores != null) {
                itemStacks.addAll(ores);
            }
        }

        return itemStacks;
    }
    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, groupedInputsAsItemStacks);
        ingredients.setOutputLists(VanillaTypes.ITEM, groupedOutputsAsItemStacks);

    }
    public int getOutputCount() {
        return oreNames.size();
    }

    public float getExplosionStrength() {
        return this.explosionStrength;
    }

    public int getRadius() {
        return this.radius;
    }

    public int getMaxWeight() {
        return this.maxWeight;
    }

    public int getVersion() {
        return this.version;
    }

    public int getCost() {
        return this.cost;
    }


}