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

import android.content.res.ColorStateList;
import android.graphics.Typeface;

interface IPrint {

    /**
     * Sets the icon text from resources.
     *
     * @attr ref R.styleable#PrintView_iconText
     * @see #setIconText(CharSequence)
     * @see #getIconText()
     */
    void setIconText(int resId);

    /**
     * Sets the icon text.
     *
     * @attr ref R.styleable#PrintView_iconText
     * @see #setIconText(int)
     * @see #getIconText()
     */
    void setIconText(CharSequence text);

    /**
     * Return the icon text, or null if icon text is not set.
     *
     * @attr ref R.styleable#PrintView_iconText
     * @see #setIconText(int)
     * @see #setIconText(CharSequence)
     */
    CharSequence getIconText();

    /**
     * Sets the icon color from resources.
     *
     * @attr ref R.styleable#PrintView_iconColor
     * @see #setIconColor(android.content.res.ColorStateList)
     * @see #getIconColor()
     */
    void setIconColor(int resId);

    /**
     * Sets the icon color.
     *
     * @attr ref R.styleable#PrintView_iconColor
     * @see #setIconColor(int)
     * @see #getIconColor()
     */
    void setIconColor(ColorStateList colors);

    /**
     * Return the icon colors for the different states (normal, selected, focused).
     *
     * @attr ref R.styleable#PrintView_iconColor
     * @see #setIconColor(ColorStateList)
     * @see #setIconColor(int)
     */
    ColorStateList getIconColor();

    /**
     * Sets the icon size from resources.
     *
     * @attr ref R.styleable#PrintView_iconSize
     * @see #setIconSize(int, float)
     * @see #getIconSize()
     */
    void setIconSize(int resId);

    /**
     * Sets the icon size to a given unit and value. See {@link android.util.TypedValue}
     * for the possible dimension units.
     *
     * @param unit The desired dimension unit.
     * @param size The desired size in the given units.
     * @attr ref R.styleable#PrintView_iconSize
     * @see #setIconSize(int)
     * @see #getIconSize()
     */
    void setIconSize(int unit, float size);

    /**
     * Return the icon size (in pixels).
     *
     * @attr ref R.styleable#PrintView_iconSize
     * @see #setIconSize(int)
     * @see #setIconSize(int, float)
     */
    int getIconSize();

    /**
     * Sets the iconic font from assets.
     *
     * @param path The file name of the font in the assets directory, e.g. "fonts/iconic-font.ttf".
     * @attr ref R.styleable#PrintView_iconFont
     * @see #setIconFont(android.graphics.Typeface)
     * @see #getIconFont()
     */
    void setIconFont(String path);

    /**
     * Sets the iconic font.
     *
     * @attr ref R.styleable#PrintView_iconFont
     * @see #setIconFont(String)
     * @see #getIconFont()
     */
    void setIconFont(Typeface font);

    /**
     * Return the iconic font.
     *
     * @attr ref R.styleable#PrintView_iconFont
     * @see #setIconFont(String)
     * @see #setIconFont(android.graphics.Typeface)
     */
    Typeface getIconFont();

}