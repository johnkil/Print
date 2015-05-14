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
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;
import android.util.Log;
import android.util.TypedValue;

/**
 * Drawable for displaying icons from iconic fonts.
 *
 * @author Evgeny Shishkin
 */
public class PrintDrawable extends Drawable implements IPrint {

    /**
     * Fluent API for creating {@link PrintDrawable} instances.
     */
    public static class Builder {
        private final Context context;

        private CharSequence iconText;
        private ColorStateList iconColor;
        private Typeface iconFont;
        private int iconSize;

        private boolean inEditMode = false;

        /**
         * Start building a new {@link PrintDrawable} instance.
         */
        public Builder(Context context) {
            this.context = context;
        }

        public Builder iconText(@StringRes int resId) {
            return iconText(context.getString(resId));
        }

        public Builder iconText(CharSequence text) {
            iconText = text;
            return this;
        }

        public Builder iconIntText(@IntegerRes int intRes) {
            iconText = new String(Character.toChars(context.getResources().getInteger(intRes)));
            return this;
        }

        public Builder iconColor(@ColorRes int resId) {
            return iconColor(context.getResources().getColorStateList(resId));
        }

        public Builder iconColor(ColorStateList colors) {
            if (colors == null) {
                throw new IllegalArgumentException("Color must not be null.");
            }
            iconColor = colors;
            return this;
        }

        public Builder iconFont(String fontAssetPath) {
            return iconFont(TypefaceManager.load(context.getAssets(), fontAssetPath));
        }

        public Builder iconFont(Typeface font) {
            if (font == null) {
                throw new IllegalArgumentException("Font must not be null.");
            }
            iconFont = font;
            return this;
        }

        public Builder iconSize(@DimenRes int resId) {
            iconSize = context.getResources().getDimensionPixelSize(resId);
            return this;
        }

        public Builder iconSize(int unit, float size) {
            iconSize = (int) TypedValue.applyDimension(unit, size, context.getResources().getDisplayMetrics());
            return this;
        }

        Builder inEditMode(boolean inEditMode) {
            this.inEditMode = inEditMode;
            return this;
        }

        /**
         * Create the {@link PrintDrawable} instance.
         */
        public PrintDrawable build() {
            if (iconFont == null) {
                PrintConfig config = PrintConfig.get();
                if (config.isFontSet()) {
                    iconFont = config.getFont();
                } else {
                    Log.w("Print", "The iconic font is not set.");
                }
            }

            return new PrintDrawable(context, iconText, iconColor, iconFont, iconSize, inEditMode);
        }
    }

    private final Context mContext;
    private final Paint mPaint;
    private final Path mPath;
    private final RectF mPathBounds;

    private CharSequence mIconText;
    private ColorStateList mIconColor;
    private Typeface mIconFont;
    private int mIconSize;

    private int mCurIconColor;

    private boolean mInEditMode;

    private PrintDrawable(Context context, CharSequence iconText,
                          ColorStateList iconColor, Typeface iconFont, int iconSize, boolean inEditMode) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setFlags(mPaint.getFlags() | Paint.ANTI_ALIAS_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
        mPath = new Path();
        mPathBounds = new RectF();

        mIconText = iconText;
        mIconColor = iconColor;
        mIconFont = iconFont;
        mIconSize = iconSize;

        mInEditMode = inEditMode;

        mPaint.setTextSize(mIconSize);
        mPaint.setTypeface(mIconFont);
        updateIconColors();
    }

    @Override
    public void setIconText(int resId) {
        setIconText(mContext.getText(resId));
    }

    @Override
    public void setIconIntText(@IntegerRes int resId) {
        setIconText(new String(Character.toChars(mContext.getResources().getInteger(resId))));
    }

    @Override
    public void setIconText(CharSequence iconText) {
        mIconText = iconText;
        invalidateSelf();
    }

    @Override
    public CharSequence getIconText() {
        return mIconText;
    }

    @Override
    public void setIconColor(int resId) {
        setIconColor(mContext.getResources().getColorStateList(resId));
    }

    @Override
    public void setIconColor(ColorStateList colors) {
        if (colors == null) {
            throw new IllegalArgumentException("Color must not be null.");
        }
        mIconColor = colors;
        updateIconColors();
        invalidateSelf();
    }

    @Override
    public ColorStateList getIconColor() {
        return mIconColor;
    }

    @Override
    public void setIconFont(String path) {
        setIconFont(TypefaceManager.load(mContext.getAssets(), path));
    }

    @Override
    public void setIconFont(Typeface font) {
        if (font == null) {
            throw new IllegalArgumentException("Font must not be null.");
        }
        mIconFont = font;
        mPaint.setTypeface(mIconFont);
        invalidateSelf();
    }

    @Override
    public Typeface getIconFont() {
        return mIconFont;
    }

    @Override
    public void setIconSize(int resId) {
        setIconSize(TypedValue.COMPLEX_UNIT_PX,
                mContext.getResources().getDimensionPixelSize(resId));
    }

    @Override
    public void setIconSize(int unit, float size) {
        mIconSize = (int) TypedValue.applyDimension(
                unit, size, mContext.getResources().getDisplayMetrics());
        mPaint.setTextSize(mIconSize);
        invalidateSelf();
    }

    @Override
    public int getIconSize() {
        return mIconSize;
    }

    @Override
    public boolean isStateful() {
        return true;
    }

    @Override
    protected boolean onStateChange(int[] state) {
        if (mIconColor != null && mIconColor.isStateful()) {
            updateIconColors();
            invalidateSelf();
        }
        return super.onStateChange(state);
    }

    private void updateIconColors() {
        int color = mIconColor.getColorForState(getState(), 0);
        if (color != mCurIconColor) {
            mCurIconColor = color;
            mPaint.setColor(mCurIconColor);
        }
    }

    @Override
    public int getIntrinsicHeight() {
        return mIconSize;
    }

    @Override
    public int getIntrinsicWidth() {
        return mIconSize;
    }

    @Override
    public void draw(Canvas canvas) {
        if (mIconText != null && !mInEditMode) {
            final Rect bounds = getBounds();

            mPaint.getTextPath(mIconText.toString(), 0, mIconText.length(), 0, bounds.height(), mPath);
            mPath.computeBounds(mPathBounds, true);
            offsetIcon(bounds);

            mPath.close();
            canvas.drawPath(mPath, mPaint);
        }
    }

    private void offsetIcon(Rect bounds) {
        float startX = bounds.centerX() - (mPathBounds.width() / 2);
        float offsetX = startX - mPathBounds.left;

        float startY = bounds.centerY() - (mPathBounds.height() / 2);
        float offsetY = startY - (mPathBounds.top);

        mPath.offset(offsetX, offsetY);
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