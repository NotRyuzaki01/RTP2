package me.not_ryuzaki.rTP2;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Random;

public class TeleportUtils {
    private static final int RADIUS = 25000;
    private static final Random random = new Random();

    public static Location findSafeLocation(World world) {
        int attempts = 0;

        while (attempts < 100) {
            attempts++;

            int x = random.nextInt(RADIUS * 2) - RADIUS;
            int z = random.nextInt(RADIUS * 2) - RADIUS;

            // Load chunk to make sure terrain is generated
            Chunk chunk = world.getChunkAt(x >> 4, z >> 4);
            if (!chunk.isLoaded()) chunk.load(true);

            if (world.getEnvironment() == World.Environment.NETHER) {
                // Nether: scan downward to avoid roof
                for (int y = 120; y > 30; y--) {
                    Block ground = world.getBlockAt(x, y, z);
                    Block above1 = ground.getRelative(0, 1, 0);
                    Block above2 = ground.getRelative(0, 2, 0);

                    if (isSafeGround(ground.getType()) &&
                            isAir(above1.getType()) &&
                            isAir(above2.getType())) {
                        return new Location(world, x + 0.5, y + 1, z + 0.5);
                    }
                }
            } else if (world.getEnvironment() == World.Environment.NORMAL) {
                // Overworld: always use the surface and ensure it's safe
                int surfaceY = world.getHighestBlockYAt(x, z);

                // Check if we're in water (ocean/lake)
                Block surfaceBlock = world.getBlockAt(x, surfaceY, z);
                if (surfaceBlock.getType() == Material.WATER) {
                    continue; // Skip water locations
                }

                // Check the blocks above the surface
                Block ground = world.getBlockAt(x, surfaceY, z);
                Block above1 = ground.getRelative(0, 1, 0);
                Block above2 = ground.getRelative(0, 2, 0);

                if (isSafeGround(ground.getType()) &&
                        isAir(above1.getType()) &&
                        isAir(above2.getType())) {
                    return new Location(world, x + 0.5, surfaceY + 1, z + 0.5);
                }
            } else {
                // End: use the original scanning logic
                int maxY = world.getMaxHeight();
                int surfaceY = world.getHighestBlockYAt(x, z);

                for (int y = surfaceY; y > world.getMinHeight(); y--) {
                    Block ground = world.getBlockAt(x, y, z);
                    Block above1 = ground.getRelative(0, 1, 0);
                    Block above2 = ground.getRelative(0, 2, 0);

                    if (isSafeGround(ground.getType()) &&
                            isAir(above1.getType()) &&
                            isAir(above2.getType())) {
                        return new Location(world, x + 0.5, y + 1, z + 0.5);
                    }
                }
            }
        }

        return null; // No safe location found after 100 tries
    }

    private static boolean isAir(Material mat) {
        return mat == Material.AIR || mat == Material.CAVE_AIR || mat == Material.VOID_AIR;
    }

    private static boolean isSafeGround(Material mat) {
        if (!mat.isSolid()) return false;
        return switch (mat) {
            case WATER, LAVA, FIRE, BEDROCK,
                 CAMPFIRE, SWEET_BERRY_BUSH,
                 MAGMA_BLOCK, CACTUS, POWDER_SNOW -> false;
            default -> true;
        };
    }
}