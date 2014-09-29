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

/**
 * @author Evgeny Shishkin
 */
public class Print {

    private static Typeface sTypeface;

    /**
     * Initialize the iconic font.
     *
     * @param assets        The application's asset manager.
     * @param fontAssetPath The file name of the font data in the assets directory.
     */
    public static void initFont(AssetManager assets, String fontAssetPath) {
        sTypeface = Typeface.createFromAsset(assets, fontAssetPath);
        if (sTypeface == null) {
            throw new IllegalArgumentException("Error font initializing");
        }
    }

    /**
     * @return true if iconic font is initialized.
     */
    public static boolean isFontInit() {
        return sTypeface != null;
    }

    /**
     * @return iconic font.
     */
    public static Typeface getFont() {
        return sTypeface;
    }

    /**
     * Apply iconic font for TextView.
     *
     * @param view TextView.
     */
    public static void applyFont(TextView view) {
        if (isFontInit()) {
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            view.setTypeface(sTypeface);
        } else {
            throw new IllegalStateException("Font is not initialized");
        }
    }

    /**
     * Apply iconic font for Paint.
     *
     * @param paint Paint.
     */
    public static void applyFont(Paint paint) {
        if (isFontInit()) {
            paint.setSubpixelText(true);
            paint.setAntiAlias(true);
            paint.setTypeface(sTypeface);
        } else {
            throw new IllegalStateException("Font is not initialized");
        }
    }

}