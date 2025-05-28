package net.chinifox.beyondthewesterndeep.block;

import net.chinifox.beyondthewesterndeep.BeyondTheWesternDeep;
import net.chinifox.beyondthewesterndeep.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * Klasa odpowiedzialna za rejestrowanie niestandardowych bloków w modzie BeyondTheWesternDeep.
 */
public class ModBlocks
{
    // Główny rejestr bloków, powiązany z MOD_ID
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BeyondTheWesternDeep.MOD_ID);

    // Blok: ruda srebra (silver_ore)
    public static final DeferredBlock<Block> SILVER_ORE = registerBlock("silver_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f) // wytrzymałość bloku (jak długo się kopie)
                    .requiresCorrectToolForDrops() // wymaga odpowiedniego narzędzia do wydobycia
                    .sound(SoundType.STONE) // dźwięk taki jak kamień
                    .mapColor(MapColor.STONE) // kolor na mapie
                    .instrument(NoteBlockInstrument.BASEDRUM) // dźwięk z noteblocka po ustawieniu bloku
            )
    );

    /**
     * Rejestruje blok oraz odpowiadający mu BlockItem.
     *
     * @param name  nazwa bloku (np. "silver_ore")
     * @param block funkcja tworząca instancję bloku
     * @return zarejestrowany DeferredBlock
     */
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block)
    {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn); // automatycznie rejestruje też item do stawiania bloku
        return toReturn;
    }

    /**
     * Rejestruje BlockItem — przedmiot reprezentujący blok w ekwipunku.
     *
     * @param name  ta sama nazwa co blok
     * @param block blok, dla którego tworzony jest item
     */
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    /**
     * Rejestruje wszystkie bloki w odpowiednim momencie cyklu ładowania moda.
     *
     * @param eventBus event bus moda
     */
    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
