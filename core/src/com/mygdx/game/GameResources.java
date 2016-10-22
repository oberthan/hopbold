package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by Søren on 22-10-2016.
 */

public class GameResources {
    private final static String defaultThemeFolder = "default";

    private String themeFolder;

    protected GameResources() {
        this.themeFolder = defaultThemeFolder;
    }

    protected GameResources(String themeBaseFolder) {
        this.themeFolder = themeBaseFolder;
    }

    // Looks for asset in theme folder. If not found, the asset is loaded from the default folder instead.
    protected FileHandle findAsset(String fileName) {
        FileHandle themeHandle = Gdx.files.internal(themeFolder +"/"+fileName);
        if (themeHandle.exists()) return themeHandle;
        return Gdx.files.internal(defaultThemeFolder+"/"+fileName);
    }
}

