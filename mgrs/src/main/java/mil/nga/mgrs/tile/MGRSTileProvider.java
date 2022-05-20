package mil.nga.mgrs.tile;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileProvider;

import java.util.Collection;

import mil.nga.mgrs.MGRS;
import mil.nga.mgrs.grid.GridType;
import mil.nga.mgrs.grid.style.Grids;

/**
 * MGRS Tile Provider
 *
 * @author wnewman
 * @author osbornb
 */
public class MGRSTileProvider implements TileProvider {

    /**
     * Tile width
     */
    private int tileWidth;

    /**
     * Tile height
     */
    private int tileHeight;

    /**
     * Grids
     */
    private Grids grids;

    /**
     * Create a tile provider with all grids
     *
     * @param tileWidth  tile width
     * @param tileHeight tile height
     */
    public static MGRSTileProvider create(int tileWidth, int tileHeight) {
        return new MGRSTileProvider(tileWidth, tileHeight);
    }

    /**
     * Create a tile provider with grid types
     *
     * @param tileWidth  tile width
     * @param tileHeight tile height
     * @param types      grids types to enable
     */
    public static MGRSTileProvider create(int tileWidth, int tileHeight, GridType... types) {
        return new MGRSTileProvider(tileWidth, tileHeight, types);
    }

    /**
     * Create a tile provider with grid types
     *
     * @param tileWidth  tile width
     * @param tileHeight tile height
     * @param types      grids types to enable
     */
    public static MGRSTileProvider create(int tileWidth, int tileHeight, Collection<GridType> types) {
        return new MGRSTileProvider(tileWidth, tileHeight, types);
    }

    /**
     * Create a tile provider with grids
     *
     * @param tileWidth  tile width
     * @param tileHeight tile height
     * @param grids      grids
     */
    public static MGRSTileProvider create(int tileWidth, int tileHeight, Grids grids) {
        return new MGRSTileProvider(tileWidth, tileHeight, grids);
    }

    /**
     * Create a tile provider with Grid Zone Designator grids
     *
     * @param tileWidth  tile width
     * @param tileHeight tile height
     */
    public static MGRSTileProvider createGZD(int tileWidth, int tileHeight) {
        return new MGRSTileProvider(tileWidth, tileHeight, Grids.createGZD());
    }

    /**
     * Constructor
     *
     * @param tileWidth  tile width
     * @param tileHeight tile height
     */
    public MGRSTileProvider(int tileWidth, int tileHeight) {
        this(tileWidth, tileHeight, Grids.create());
    }

    /**
     * Constructor
     *
     * @param tileWidth  tile width
     * @param tileHeight tile height
     * @param types      grids types to enable
     */
    public MGRSTileProvider(int tileWidth, int tileHeight, GridType... types) {
        this(tileWidth, tileHeight, Grids.create(types));
    }

    /**
     * Constructor
     *
     * @param tileWidth  tile width
     * @param tileHeight tile height
     * @param types      grids types to enable
     */
    public MGRSTileProvider(int tileWidth, int tileHeight, Collection<GridType> types) {
        this(tileWidth, tileHeight, Grids.create(types));
    }

    /**
     * Constructor
     *
     * @param tileWidth  tile width
     * @param tileHeight tile height
     * @param grids      grids
     */
    public MGRSTileProvider(int tileWidth, int tileHeight, Grids grids) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.grids = grids;
    }

    /**
     * Get the tile width
     *
     * @return tile width
     */
    public int getTileWidth() {
        return tileWidth;
    }

    /**
     * Set the tile width
     *
     * @param tileWidth tile width
     */
    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    /**
     * Get the tile height
     *
     * @return tile height
     */
    public int getTileHeight() {
        return tileHeight;
    }

    /**
     * Set the tile height
     *
     * @param tileHeight tile height
     */
    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    /**
     * Get the grids
     *
     * @return grids
     */
    public Grids getGrids() {
        return grids;
    }

    /**
     * Set the grids
     *
     * @param grids grids
     */
    public void setGrids(Grids grids) {
        this.grids = grids;
    }

    /**
     * Get the Military Grid Reference System coordinate for the location in one
     * meter precision
     *
     * @param latLng location
     * @return MGRS coordinate
     */
    public String getCoordinate(LatLng latLng) {
        return grids.getCoordinate(TileUtils.toPoint(latLng));
    }

    /**
     * Get the Military Grid Reference System coordinate for the location in the
     * zoom level precision
     *
     * @param latLng location
     * @param zoom   zoom level precision
     * @return MGRS coordinate
     */
    public String getCoordinate(LatLng latLng, int zoom) {
        return grids.getCoordinate(TileUtils.toPoint(latLng), zoom);
    }

    /**
     * Get the Military Grid Reference System coordinate for the location in the
     * grid type precision
     *
     * @param latLng location
     * @param type   grid type precision
     * @return MGRS coordinate
     */
    public String getCoordinate(LatLng latLng, GridType type) {
        return grids.getCoordinate(TileUtils.toPoint(latLng), type);
    }

    /**
     * Get the Military Grid Reference System for the location
     *
     * @param latLng location
     * @return MGRS
     */
    public MGRS getMGRS(LatLng latLng) {
        return grids.getMGRS(TileUtils.toPoint(latLng));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tile getTile(int x, int y, int zoom) {
        return TileUtils.toTile(drawTile(x, y, zoom));
    }

    /**
     * Draw the tile
     *
     * @param x    x coordinate
     * @param y    y coordinate
     * @param zoom zoom level
     * @return bitmap
     */
    private Bitmap drawTile(int x, int y, int zoom) {
        return grids.drawTile(tileWidth, tileHeight, x, y, zoom);
    }

}
