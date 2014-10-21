/*
 * Copyright (C) 2014 Evgeny Shishkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.johnkil.print;

import android.content.res.AssetManager;
import android.graphics.Typeface;

public class PrintConfig {

    private static PrintConfig sInstance;

    /**
     * Define the default iconic font.
     *
     * @param assets          The application's asset manager.
     * @param defaultFontPath The file name of the font in the assets directory,
     *                        e.g. "fonts/iconic-font.ttf".
     * @see #initDefault(Typeface)
     */
    public static void initDefault(AssetManager assets, String defaultFontPath) {
        Typeface defaultFont = TypefaceManager.load(assets, defaultFontPath);
        initDefault(defaultFont);
    }

    /**
     * Define the default iconic font.
     *
     * @see #initDefault(AssetManager, String)
     */
    public static void initDefault(Typeface defaultFont) {
        sInstance = new PrintConfig(defaultFont);
    }

    static PrintConfig get() {
        if (sInstance == null)
            sInstance = new PrintConfig();
        return sInstance;
    }


    private final Typeface mFont;
    private final boolean mIsFontSet;

    private PrintConfig() {
        this(null);
    }

    private PrintConfig(Typeface defaultFont) {
        mFont = defaultFont;
        mIsFontSet = defaultFont != null;
    }

    /**
     * @return the default font.
     */
    Typeface getFont() {
        return mFont;
    }

    /**
     * @return true if default font is set.
     */
    boolean isFontSet() {
        return mIsFontSet;
    }

}