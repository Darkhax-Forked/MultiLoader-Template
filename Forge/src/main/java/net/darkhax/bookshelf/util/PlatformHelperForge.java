package net.darkhax.bookshelf.util;

import net.darkhax.bookshelf.lib.PhysicalSide;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.util.thread.EffectiveSide;

import java.nio.file.Path;

public class PlatformHelperForge implements IPlatformHelper {

    @Override
    public Path getGamePath() {

        return FMLPaths.GAMEDIR.get();
    }

    @Override
    public Path getConfigPath() {

        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FMLLoader.isProduction();
    }

    @Override
    public PhysicalSide getPhysicalSide() {

        return FMLEnvironment.dist.isClient() ? PhysicalSide.CLIENT : PhysicalSide.SERVER;
    }

    @Override
    public PhysicalSide getEffectiveSide() {

        return EffectiveSide.get().isClient() ? PhysicalSide.CLIENT : PhysicalSide.SERVER;
    }
}