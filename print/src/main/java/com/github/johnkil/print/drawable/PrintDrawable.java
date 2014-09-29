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

package com.github.johnkil.print.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.github.johnkil.print.Print;

/**
 * Drawable for displaying icons from iconic fonts.
 *
 * @author Evgeny Shishkin
 */
public class PrintDrawable extends Drawable {

    private final Paint mPaint;

    private String mIcon;
    private int mIconSize;
    private int mIconColor;
    private int mPadding;

    private Rect mPaddingBounds;

    public PrintDrawable() {
        mPaint = new Paint();
        Print.applyFont(mPaint);
        mPaddingBounds = new Rect();
    }

    /**
     * Set the icon.
     *
     * @param icon The string representation of icon.
     * @return The current PrintDrawable instance.
     */
    public PrintDrawable icon(String icon) {
        mIcon = icon;
        invalidateSelf();
        return this;
    }

    /**
     * Set a color of icon.
     *
     * @param color The color, usually from android.graphics.Color or 0xFF012345.
     * @return The current PrintDrawable instance.
     */
    public PrintDrawable iconColor(int color) {
        mIconColor = color;
        mPaint.setColor(color);
        invalidateSelf();
        return this;
    }

    /**
     * Set a size of icon.
     *
     * @param size The size in pixels (px).
     * @return The current PrintDrawable instance.
     */
    public PrintDrawable iconSize(int size) {
        mIconSize = size;
        mPaint.setTextSize(size);
        invalidateSelf();
        return this;
    }

    /**
     * Set a padding of the drawable.
     *
     * @param padding the padding in pixels
     * @return The current PrintDrawable instance.
     */
    public PrintDrawable padding(int padding) {
        mPadding = padding;
        return this;
    }

    @Override
    public int getIntrinsicHeight() {
        int height = mIconSize + 2 * mPadding;
        return height;
    }

    @Override
    public int getIntrinsicWidth() {
        int width = mIconSize + 2 * mPadding;
        return width;
    }

    @Override
    public void draw(Canvas canvas) {
        float x = mPadding;
        float y = getBounds().height() - mPadding;
        canvas.drawText(mIcon, x, y, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

}