package dev.dubhe.recipe.init;

import dev.dubhe.recipe.InWorldRecipeSystem;
import dev.dubhe.recipe.recipe.IRecipeOutcome;
import dev.dubhe.recipe.recipe.IRecipePredicate;
import dev.dubhe.recipe.recipe.IRecipeTrigger;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = InWorldRecipeSystem.MOD_ID)
public class ModRegistries {
    public static final ResourceKey<Registry<IRecipeTrigger>> TRIGGER_KEY = ResourceKey.createRegistryKey(
        InWorldRecipeSystem.of("trigger")
    );
    public static final Registry<IRecipeTrigger> TRIGGER_REGISTRY = new RegistryBuilder<>(TRIGGER_KEY)
        .sync(true)
        .maxId(512)
        .create();

    public static final ResourceKey<Registry<IRecipeOutcome.Type<?>>> OUTCOME_KEY = ResourceKey.createRegistryKey(
        InWorldRecipeSystem.of("outcome")
    );
    public static final Registry<IRecipeOutcome.Type<?>> OUTCOME_TYPE_REGISTRY = new RegistryBuilder<>(OUTCOME_KEY)
        .sync(true)
        .maxId(512)
        .create();

    public static final ResourceKey<Registry<IRecipePredicate.Type<?>>> PREDICATE_KEY = ResourceKey.createRegistryKey(
        InWorldRecipeSystem.of("predicate")
    );
    public static final Registry<IRecipePredicate.Type<?>> PREDICATE_TYPE_REGISTRY = new RegistryBuilder<>(PREDICATE_KEY)
        .sync(true)
        .maxId(512)
        .create();

    @SubscribeEvent
    public static void registerRegistries(@NotNull NewRegistryEvent event) {
        event.register(TRIGGER_REGISTRY);
        event.register(OUTCOME_TYPE_REGISTRY);
        event.register(PREDICATE_TYPE_REGISTRY);
    }
}
