/*
 * Copyright (c) 2015 by k3b.
 *
 * This file is part of AndroFotoFinder.
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>
 */
 
package de.k3b.android.osmdroid;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import org.osmdroid.api.*;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;

import java.util.List;

/**
 * A marker containing IGeoPoint, unique id and data. In a geographic map it will be shown as an icon.
 *
 * Created by k3b on 17.07.2015.
 */
public abstract class ClickableIconOverlay<DataType> extends IconOverlay {
    protected int mId = 0;
    private DataType mData = null;

    /**
     * save to be called in non-gui-thread
     */
    protected ClickableIconOverlay() {
        super();
    }

    /**
     * @return true if click was handeled.
     */
    abstract protected boolean onMarkerClicked(MapView mapView, int markerId, IGeoPoint makerPosition, DataType markerData);

    /** used to recycle this */
    public ClickableIconOverlay set(int id, IGeoPoint position, Drawable icon, DataType data) {
        set(position, icon);
        mId = id;
        mData = data;
        return this;
    }

    /**
     * From org.osmdroid.bonuspack.overlays.Marker#hitTest
     * @return true, if this marker was taped.
     */
    protected boolean hitTest(final MotionEvent event, final MapView mapView){
        final Projection pj = mapView.getProjection();

        // sometime at higher zoomlevels pj is null
        if ((mPosition == null) || (mPositionPixels == null) || (pj == null)) return false;

        pj.toPixels(mPosition, mPositionPixels);
        final Rect screenRect = pj.getIntrinsicScreenRect();
        int x = -mPositionPixels.x + screenRect.left + (int) event.getX();
        int y = -mPositionPixels.y + screenRect.top + (int) event.getY();
        boolean hit = mIcon.getBounds().contains(x, y);
        return hit;
    }

    /**
     * @return true: tap handeled. No following overlay/map should handle the event.
     * false: tap not handeled. A following overlay/map should handle the event.
     */
    @Override public boolean onSingleTapConfirmed(final MotionEvent event, final MapView mapView) {
        boolean touched = hitTest(event, mapView);
        if (touched) {
            return onMarkerClicked(mapView, mId, mPosition, mData);
        } else {
            return super.onSingleTapConfirmed(event, mapView);
        }
    }

    /**
     * By default does nothing ({@code return false}). If you handled the Event, return {@code true}
     * , otherwise return {@code false}. If you returned {@code true} none of the following Overlays
     * or the underlying {@link MapView} has the chance to handle this event.
     */
    public boolean onLongPress(final MotionEvent event, final MapView mapView) {
        boolean touched = hitTest(event, mapView);
        if (touched) {
            return onMarkerLongPress(mapView, mId, mPosition, mData);
        } else {
            return super.onLongPress(event, mapView);
        }
    }

    protected boolean onMarkerLongPress(MapView mapView, int markerId, IGeoPoint geoPosition, Object data) {
        return false;
    }

    public static ClickableIconOverlay find(List<ClickableIconOverlay> list, int id) {
        for (ClickableIconOverlay item : list) {
            if ((item != null) && (item.mId == id)) return item;
        }
        return null;
    }

    public int getID() {
        return mId;
    }

    public DataType getData() {
        return mData;
    }
}
