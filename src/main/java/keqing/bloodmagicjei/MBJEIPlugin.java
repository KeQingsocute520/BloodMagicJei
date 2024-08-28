package keqing.bloodmagicjei;


import WayofTime.bloodmagic.meteor.Meteor;
import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static WayofTime.bloodmagic.meteor.MeteorRegistry.meteorMap;
import static keqing.bloodmagicjei.BMJ.MODID;

@JEIPlugin
public class MBJEIPlugin implements IModPlugin {
    public static IJeiHelpers jeiHelper;
    public static IGuiHelper guiHelper;
    public MBJEIPlugin() {
    }
    @Override
    public void registerCategories( IRecipeCategoryRegistration registry) {
        guiHelper = registry.getJeiHelpers().getGuiHelper();

        registry.addRecipeCategories(new MeteorsCategory(registry.getJeiHelpers().getGuiHelper()));

    }
    @Override
    public void register(@Nonnull IModRegistry registry) {
        String Meteors = MODID + ":" + "meteors";
        List<MeteorsRecipeJEI> MeteorsInfo = new ArrayList<>();

        for (Meteor meteor : meteorMap.values()) {
            // 将每个Meteor的components列表添加到allComponents列表中
            MeteorsInfo.add(new MeteorsRecipeJEI(meteor.getCatalystStack(),meteor.getComponents(),meteor.getExplosionStrength(),meteor.getRadius(), meteor.getMaxWeight(), meteor.getVersion(),meteor.getCost()));
        }

        registry.addRecipes(MeteorsInfo, Meteors);
    }
}
