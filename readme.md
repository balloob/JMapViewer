# JMapViewer with offline support
This is a fork of JMapViewer of OpenStreetMap with support added for offline maps.

Original JMapViewer can be found here: http://wiki.openstreetmap.org/wiki/JMapViewer

Useful for systems that are not connected to the internet.

This code is licensed under GPL.

## How to use
You can use the jMapViewer as you would normally do.
The difference with the original code base is the addition of the class OfflineOsmTileSource in the package org.openstreetmap.gui.jmapviewer.tilesources.

It requires 3 arguments: the directory where your files are stored, the minimum zoom level that you have stored and the maximum zoom level that you have stored.

Use it like this in Java:

    JMapViewer map = new JMapViewer();
    map.setTileSource(new OfflineOsmTileSource("file:///Users/paulus/Downloads/jTileDownloader/tiles/",1,9));

## Limitations
I hacked this together really really quickly. It is usuable but not for production releases.

Some rough edges:
 - given path is not validated if it exists
 - you are unable to set boundary latitude/longitude of the tiles that you have downloaded so it expects to be able to find tiles for the whole world offline.

## How to download the tiles
To download the Open Streetmap tiles to your computer you can use [jTileDownloader](http://wiki.openstreetmap.org/wiki/JTileDownloader).

Note that jTileDownloader 0.6 requires Java 7. It will not throw an error when an older JRE is used. As of this writing (August 2, 2012) Apple has not released Java 7 for OS X yet.



