package dev.dubhe.recipe;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = InWorldRecipeSystem.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class InWorldRecipeSystemConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.IntValue MAXIMUM_PROCESSING_EFFICIENCY = BUILDER
        .comment("The maximum processing efficiency of the recipe.")
        .defineInRange("maximum_processing_efficiency", 64, 1, 256);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static int maxProcessingEfficiency = 64;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        maxProcessingEfficiency = MAXIMUM_PROCESSING_EFFICIENCY.get();
    }
}
