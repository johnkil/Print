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

import java.util.HashMap;

/**
 * A helper loading {@link android.graphics.Typeface} avoiding the leak of the font when loaded
 * by multiple calls to {@link android.graphics.Typeface#createFromAsset(android.content.res.AssetManager, String)}
 * on pre-ICS versions.
 */
class TypefaceManager {

    /**
     * The cached typefaces.
     */
    private static final HashMap<String, Typeface> sTypefaces = new HashMap<String, Typeface>();

    /**
     * Load a typeface from the specified font data.
     *
     * @param assets The application's asset manager.
     * @param path   The file name of the font data in the assets directory.
     */
    static Typeface load(AssetManager assets, String path) {
        synchronized (sTypefaces) {
            Typeface typeface;
            if (sTypefaces.containsKey(path)) {
                typeface = sTypefaces.get(path);
            } else {
                typeface = Typeface.createFromAsset(assets, path);
                sTypefaces.put(path, typeface);
            }
            return typeface;
        }
    }

    private TypefaceManager() {
    }

}