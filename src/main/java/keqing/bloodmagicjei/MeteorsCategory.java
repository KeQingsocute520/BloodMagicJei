package keqing.bloodmagicjei;


import WayofTime.bloodmagic.util.helper.TextHelper;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static keqing.bloodmagicjei.BMJ.MODID;

public class MeteorsCategory implements IRecipeCategory<MeteorsRecipeJEI> {
    @Nonnull
    private final IDrawable background;
    public float explosionStrength;
    public int radius;
    public int maxWeight;
    public int version;
    public int cost = 1000000;
    public MeteorsCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createBlankDrawable(144, 120);
    }

    @Nonnull
    public String getUid() {
        return MODID+":meteors";
    }

    @Nonnull
    public String getTitle() {
        return TextHelper.localize("bmj.meteors.jei");
    }

    @Nonnull
    public IDrawable getBackground() {
        return this.background;
    }

    @Nullable
    public IDrawable getIcon() {
        return null;
    }

    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull MeteorsRecipeJEI recipeWrapper, @Nonnull IIngredients ingredients) {
        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
        //Inputs

        itemStackGroup.init(0, true, 144/2-9, 0);


        if(recipeWrapper.getOutputCount()<=12)
        {
            for(int i = 0; i < recipeWrapper.getOutputCount(); ++i) {
                int yPos = 18 + 18*(i/4);
                int xPos = 36 + i % 4 * 18;
                itemStackGroup.init(i + 1, false, new ItemStackTextRenderer(recipeWrapper.oreWeight.get(i)*100), xPos + 1, yPos + 1, 16, 16, 0, 0);

            }
        }else if(recipeWrapper.getOutputCount()<=18)
        {
            for(int i = 0; i < recipeWrapper.getOutputCount(); ++i) {
                int yPos = 18 + 18*(i/6);
                int xPos = 18 + i % 6 * 18;
                itemStackGroup.init(i + 1, false, new ItemStackTextRenderer(recipeWrapper.oreWeight.get(i)*100), xPos + 1, yPos + 1, 16, 16, 0, 0);

            }
        }
        else {
            for (int i = 0; i < recipeWrapper.getOutputCount(); ++i) {
                int yPos = 18 + 18 * (i / 8);
                int xPos = i % 8 * 18;
                itemStackGroup.init(i + 1, false, new ItemStackTextRenderer(recipeWrapper.oreWeight.get(i) * 100), xPos + 1, yPos + 1, 16, 16, 0, 0);

            }
        }
        itemStackGroup.set(ingredients);

        this.explosionStrength=recipeWrapper.getExplosionStrength();
        this.radius=recipeWrapper.getRadius();
        this.maxWeight=recipeWrapper.getMaxWeight();
        this.version=recipeWrapper.getVersion();
        this.cost=recipeWrapper.getCost();
    }
    public void drawExtras(Minecraft minecraft) {
        String explosionStrength = I18n.format("bmj.meteors.explosionStrength", this.explosionStrength);
        minecraft.fontRenderer.drawString(explosionStrength, 0, 75, 0x111111);

        String radius = I18n.format("bmj.meteors.radius", this.radius);
        minecraft.fontRenderer.drawString(radius, 0, 85, 0x111111);

        String maxWeight = I18n.format("bmj.meteors.maxWeight", this.maxWeight);
        minecraft.fontRenderer.drawString(maxWeight, 0, 95, 0x111111);

        String version = I18n.format("bmj.meteors.version", this.version);
        minecraft.fontRenderer.drawString(version, 0, 105, 0x111111);

        String cost = I18n.format("bmj.meteors.cost", this.cost);
        minecraft.fontRenderer.drawString(cost, 0, 115, 0x111111);
    }
    @Nonnull
    public String getModName() {
        return MODID;
    }
}
