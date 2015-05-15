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
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;

interface IPrint {

    /**
     * Sets the icon text from resources.
     *
     * @see #setIconText(CharSequence)
     * @see #getIconText()
     */
    void setIconTextRes(@StringRes int resId);

    /**
     * Sets the icon text based on char code from integer resources. Fixed issue of support
     * UTF-16 chars (see <a href="https://github.com/johnkil/Print/issues/11">issue #11</a>)
     *
     * @see #setIconCode(int)
     * @see #getIconText()
     */
    void setIconCodeRes(@IntegerRes int resId);

    /**
     * Sets the icon text based on char code. Fixed issue of support
     * UTF-16 chars (see <a href="https://github.com/johnkil/Print/issues/11">issue #11</a>)
     *
     * @see #setIconCodeRes(int)
     * @see #getIconText()
     */
    void setIconCode(int code);

    /**
     * Sets the icon text.
     *
     * @see #setIconTextRes(int)
     * @see #getIconText()
     */
    void setIconText(CharSequence text);

    /**
     * Return the icon text, or null if icon text is not set.
     *
     * @see #setIconTextRes(int)
     * @see #setIconText(CharSequence)
     */
    CharSequence getIconText();

    /**
     * Sets the icon color from resources.
     *
     * @see #setIconColor(int)
     * @see #setIconColor(ColorStateList)
     * @see #getIconColor()
     */
    void setIconColorRes(@ColorRes int resId);

    /**
     * Sets the icon color for all the states (normal, selected, focused) to be this color.
     *
     * @see #setIconColorRes(int)
     * @see #setIconColor(ColorStateList)
     * @see #getIconColor()
     */
    void setIconColor(int color);

    /**
     * Sets the icon color.
     *
     * @see #setIconColorRes(int)
     * @see #setIconColor(int)
     * @see #getIconColor()
     */
    void setIconColor(ColorStateList colors);

    /**
     * Return the icon colors for the different states (normal, selected, focused).
     *
     * @see #setIconColorRes(int)
     * @see #setIconColor(int)
     * @see #setIconColor(ColorStateList)
     */
    ColorStateList getIconColor();

    /**
     * Sets the icon size from resources.
     *
     * @see #setIconSizeDp(float)
     * @see #setIconSize(int, float)
     * @see #getIconSize()
     */
    void setIconSizeRes(@DimenRes int resId);

    /**
     * Sets the icon size to the given value, interpreted as "device independent pixels" units.
     *
     * @param size The device independent pixels size.
     * @see #setIconSizeRes(int)
     * @see #setIconSize(int, float)
     * @see #getIconSize()
     */
    void setIconSizeDp(float size);

    /**
     * Sets the icon size to a given unit and value. See {@link android.util.TypedValue}
     * for the possible dimension units.
     *
     * @param unit The desired dimension unit.
     * @param size The desired size in the given units.
     * @see #setIconSizeRes(int)
     * @see #setIconSizeDp(float)
     * @see #getIconSize()
     */
    void setIconSize(int unit, float size);

    /**
     * Return the icon size (in pixels).
     *
     * @see #setIconSizeRes(int)
     * @see #setIconSizeDp(float)
     * @see #setIconSize(int, float)
     */
    int getIconSize();

    /**
     * Sets the iconic font from assets.
     *
     * @param path The file name of the font in the assets directory, e.g. "fonts/iconic-font.ttf".
     * @see #setIconFont(Typeface)
     * @see #getIconFont()
     */
    void setIconFont(String path);

    /**
     * Sets the iconic font.
     *
     * @see #setIconFont(String)
     * @see #getIconFont()
     */
    void setIconFont(Typeface font);

    /**
     * Return the iconic font.
     *
     * @see #setIconFont(String)
     * @see #setIconFont(Typeface)
     */
    Typeface getIconFont();

}