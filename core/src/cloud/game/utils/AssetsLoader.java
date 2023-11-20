package my.game.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class AssetsLoader
{
    public final AssetManager assetManager = new AssetManager();

    public AssetsLoader() {}

    public TiledMap loadMap(String tmxPath) {
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load(tmxPath, TiledMap.class);
        assetManager.finishLoading();
        return assetManager.get(tmxPath, TiledMap.class);
    }
}
