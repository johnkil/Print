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

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;

class PrintViewUtils {

    /**
     * Initialization of icon for print views.
     *
     * @param context    The Context the view is running in, through which it can access the current
     *                   theme, resources, etc.
     * @param attrs      The attributes of the XML tag that is inflating the view.
     * @param inEditMode Indicates whether this View is currently in edit mode.
     * @return The icon to display.
     */
    static PrintDrawable initIcon(Context context, AttributeSet attrs, boolean inEditMode) {
        PrintDrawable.Builder iconBuilder = new PrintDrawable.Builder(context);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PrintView);

            if (a.hasValue(R.styleable.PrintView_iconText)) {
                String iconText = a.getString(R.styleable.PrintView_iconText);
                iconBuilder.iconText(iconText);
            }

            if (a.hasValue(R.styleable.PrintView_iconCode)) {
                int iconCode = a.getInteger(R.styleable.PrintView_iconCode, 0);
                iconBuilder.iconCode(iconCode);
            }

            if (!inEditMode && a.hasValue(R.styleable.PrintView_iconFont)) {
                String iconFontPath = a.getString(R.styleable.PrintView_iconFont);
                iconBuilder.iconFont(TypefaceManager.load(context.getAssets(), iconFontPath));
            }

            if (a.hasValue(R.styleable.PrintView_iconColor)) {
                ColorStateList iconColor = a.getColorStateList(R.styleable.PrintView_iconColor);
                iconBuilder.iconColor(iconColor);
            }

            int iconSize = a.getDimensionPixelSize(R.styleable.PrintView_iconSize, 0);
            iconBuilder.iconSize(TypedValue.COMPLEX_UNIT_PX, iconSize);

            iconBuilder.inEditMode(inEditMode);

            a.recycle();
        }

        return iconBuilder.build();
    }

    private PrintViewUtils() {
    }

}