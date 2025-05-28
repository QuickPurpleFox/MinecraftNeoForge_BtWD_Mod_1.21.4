package net.chinifox.beyondthewesterndeep.item;

import net.chinifox.beyondthewesterndeep.BeyondTheWesternDeep;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BeyondTheWesternDeep.MOD_ID);

    public static final DeferredItem<Item> SILVER = ITEMS.registerItem("silver", Item::new, new Item.Properties());
    public static final DeferredItem<Item> SILVER_COIN = ITEMS.registerItem("silver_coin", Item::new, new Item.Properties());

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
