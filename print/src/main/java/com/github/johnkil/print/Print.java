/*
 * Copyright 2014 Evgeny Shishkin
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
import android.graphics.Paint;
import android.graphics.Typeface;
import android.widget.TextView;

import java.io.File;
import java.util.HashMap;

/**
 * @author Evgeny Shishkin
 */
public class Print {
    /**
     * The default typeface (for backward compatibility)
     */
    private static Typeface sDefaultTypeface;

    /**
     * A cache of all managed typefaces
     */
    private static HashMap<String, Typeface> sTypefaces = new HashMap<String, Typeface>();

    /**
     * Initialize an iconic font and add it to the cache. This will use the file name (without extension) as a reference name for the font.
     *
     * @param assets        The application's asset manager.
     * @param fontAssetPath The path of the font data in the assets directory.
     */
    public static void initFont(AssetManager assets, String fontAssetPath) {
        // Extract font file name (without extension) from path
        File file = new File(fontAssetPath);
        String fName = file.getName();
        int pos = fName.lastIndexOf(".");

        if (pos > 0) {
            fName = fName.substring(0, pos);
        }

        initFont(assets, fontAssetPath, fName);
    }

    /**
     * Initialize an iconic font and add it to the cache with the specified name.
     *
     * @param assets        The application's asset manager.
     * @param fontAssetPath The path of the font data in the assets directory.
     * @param fontName      The name of the font that will be used in reference to this font.
     */
    public static void initFont(AssetManager assets, String fontAssetPath, String fontName) {
        if(fontName == null)
        {
            throw new IllegalArgumentException("Font name cannot be null");
        }

        Typeface tf = Typeface.createFromAsset(assets, fontAssetPath);
        if (tf == null) {
            throw new IllegalArgumentException("Could not initialize font " + fontAssetPath);
        }

        sTypefaces.put(fontName.toLowerCase(), tf);

        // Set default typeface (if none was set before)
        if (sDefaultTypeface == null) {
            sDefaultTypeface = tf;
        }
    }

    /**
     * @return true if iconic font is initialized.
     */
    public static boolean isFontInit() {
        return sTypefaces.size() > 0;
    }

    /**
     * Returns the first font that was initialized.
     *
     * @return iconic font.
     * @deprecated Use {@code getFont(String)} to get the font by name
     */
    @Deprecated
    public static Typeface getFont() {
        return getFont(null);
    }

    /**
     * @return iconic font.
     */
    public static Typeface getFont(String fontName) {
        if (isFontInit()) {
            if (fontName == null) {
                return sDefaultTypeface;
            }

            Typeface tf = sTypefaces.get(fontName.toLowerCase());
            if(tf != null) {
                return tf;
            }
            else
            {
                throw new IllegalArgumentException("Font " + fontName + " was not found. Did you initialize it properly?");
            }
        } else {
            throw new IllegalStateException("No font is initialized");
        }
    }

    /**
     * Apply the default iconic font for TextView.
     *
     * @param view TextView.
     */
    public static void applyFont(TextView view) {
        applyFont(view, null);
    }

    /**
     * Apply the specified iconic font for TextView.
     *
     * @param view     The TextView.
     * @param fontName The font file name (without extension).
     */
    public static void applyFont(TextView view, String fontName) {
        Typeface tf = getFont(fontName);

        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        view.setTypeface(tf);
    }

    /**
     * Apply the default iconic font for Paint.
     *
     * @param paint Paint.
     */
    public static void applyFont(Paint paint) {
        applyFont(paint, null);
    }

    /**
     * Apply the specified iconic font for Paint.
     *
     * @param paint    Paint.
     * @param fontName The font file name (without extension).
     */
    public static void applyFont(Paint paint, String fontName) {
        Typeface tf = getFont(fontName);

        paint.setSubpixelText(true);
        paint.setAntiAlias(true);
        paint.setTypeface(tf);
    }

}