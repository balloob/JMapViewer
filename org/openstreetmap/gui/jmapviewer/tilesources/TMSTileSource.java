package org.openstreetmap.gui.jmapviewer.tilesources;


public class TMSTileSource extends AbstractOsmTileSource {
    private int maxZoom;

    public TMSTileSource(String name, String url, int maxZoom) {
        super(name, url);
        this.maxZoom = maxZoom;
    }

    @Override
    public int getMaxZoom() {
        return (maxZoom == 0) ? super.getMaxZoom() : maxZoom;
    }

    public TileUpdate getTileUpdate() {
        return TileUpdate.IfNoneMatch;
    }
}
