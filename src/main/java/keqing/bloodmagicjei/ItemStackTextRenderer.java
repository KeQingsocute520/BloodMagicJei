package keqing.bloodmagicjei;

import mezz.jei.plugins.vanilla.ingredients.item.ItemStackRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ItemStackTextRenderer extends ItemStackRenderer {
    private final int chanceBase;

    public ItemStackTextRenderer(int chanceBase) {
        this.chanceBase = chanceBase;
    }

    public void render(Minecraft minecraft, int xPosition, int yPosition,ItemStack ingredient) {
        super.render(minecraft, xPosition, yPosition, ingredient);
        if (this.chanceBase >= 0) {
            GlStateManager.disableBlend();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5, 0.5, 1.0);
            GlStateManager.translate(0.0F, 0.0F, 160.0F);
            String s = this.chanceBase / 100 + "%";


            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
            fontRenderer.drawStringWithShadow(s, (float)((xPosition + 6) * 2 - fontRenderer.getStringWidth(s) + 19), (float)((yPosition + 1) * 2), 16776960);
            GlStateManager.popMatrix();
            GlStateManager.enableBlend();
        }
    }
}