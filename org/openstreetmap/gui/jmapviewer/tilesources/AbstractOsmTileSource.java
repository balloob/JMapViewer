/**
 * 
 */
package org.openstreetmap.gui.jmapviewer.tilesources;

import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.TileSource;

public abstract class AbstractOsmTileSource implements TileSource {

    protected String name;
    protected String baseUrl;
    protected String attrImgUrl;

    public AbstractOsmTileSource(String name, String base_url) {
        this(name, base_url, null);
    }

    public AbstractOsmTileSource(String name, String base_url, String attr_img_url) {
        this.name = name;
        this.baseUrl = base_url;
        attrImgUrl = attr_img_url;
    }

    public String getName() {
        return name;
    }

    public int getMaxZoom() {
        return 18;
    }

    public int getMinZoom() {
        return 0;
    }

    public String getExtension() {
        return "png";
    }

    /**
     * @throws IOException when subclass cannot return the tile URL
     */
    public String getTilePath(int zoom, int tilex, int tiley) throws IOException {
        return "/" + zoom + "/" + tilex + "/" + tiley + "." + getExtension();
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getTileUrl(int zoom, int tilex, int tiley) throws IOException {
        return this.getBaseUrl() + getTilePath(zoom, tilex, tiley);
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getTileType() {
        return "png";
    }

    public int getTileSize() {
        return 256;
    }

    public Image getAttributionImage() {
        if (attrImgUrl != null)
            return new ImageIcon(attrImgUrl).getImage();
        else
            return null;
    }

    public boolean requiresAttribution() {
        return true;
    }

    public String getAttributionText(int zoom, Coordinate topLeft, Coordinate botRight) {
        return "© OpenStreetMap contributors, CC-BY-SA ";
    }

    public String getAttributionLinkURL() {
        return "http://openstreetmap.org/";
    }

    public String getTermsOfUseURL() {
        return "http://www.openstreetmap.org/copyright";
    }

    public double latToTileY(double lat, int zoom) {
        double l = lat / 180 * Math.PI;
        double pf = Math.log(Math.tan(l) + (1 / Math.cos(l)));
        return Math.pow(2.0, zoom - 1) * (Math.PI - pf) / Math.PI;
    }

    public double lonToTileX(double lon, int zoom) {
        return Math.pow(2.0, zoom - 3) * (lon + 180.0) / 45.0;
    }

    public double tileYToLat(int y, int zoom) {
        return Math.atan(Math.sinh(Math.PI - (Math.PI * y / Math.pow(2.0, zoom - 1)))) * 180 / Math.PI;
    }

    public double tileXToLon(int x, int zoom) {
        return x * 45.0 / Math.pow(2.0, zoom - 3) - 180.0;
    }
}