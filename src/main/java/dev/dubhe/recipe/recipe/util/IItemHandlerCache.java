package dev.dubhe.recipe.recipe.util;

import net.neoforged.neoforge.items.IItemHandler;

public interface IItemHandlerCache {
    IItemHandler getInput();

    IItemHandler getOutput();
}
