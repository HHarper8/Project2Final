package src.gamevalidator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LevelFileReader {

    private String fileName;
    private int gameWidth = 20;
    private int gameHeight = 11;

    private List<Point> pacLocations = new ArrayList<>();
    private List<Point> pathLocations = new ArrayList<>();
    private List<Point> wallLocations = new ArrayList<>();
    private List<Point> pillLocations = new ArrayList<>();
    private List<Point> goldLocations = new ArrayList<>();
    private List<Point> iceLocations = new ArrayList<>();
    private List<Point> portalWhiteLocations = new ArrayList<>();
    private List<Point> portalYellowLocations = new ArrayList<>();
    private List<Point> portalDarkGrayLocations = new ArrayList<>();
    private List<Point> portalDarkGoldLocations = new ArrayList<>();
    private List<Point> trollLocations = new ArrayList<>();
    private List<Point> TX5Locations = new ArrayList<>();


    protected LevelFileReader(File file) {
        readFile(file);
    }

    private void readFile(File file) {

        // Save filename
        fileName = file.getName();
        System.out.println("###DEBUG (line 43, LevelFileReader)### The file being read is: " + fileName);

        // Read all objects from xml file
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(file);

            // Initialise board coordinate counters
            int row = 1;
            int col = 1;

            // get size
            NodeList sizes = doc.getElementsByTagName("size");
            Node sizeNode = sizes.item(0);
            Element size = (Element) sizeNode;
            gameWidth = Integer.parseInt(size.getElementsByTagName("width").item(0).getTextContent());
            gameHeight = Integer.parseInt(size.getElementsByTagName("height").item(0).getTextContent());

            // get rows
            NodeList rows = doc.getElementsByTagName("row");

            for (int m = 0; m < rows.getLength(); m++) {
                NodeList cellNodes = rows.item(m).getChildNodes();
                for (int n = 0; n < cellNodes.getLength(); n++) {
                    Point coord = new Point(col, row);
                    Node cellNode = cellNodes.item(n);
                    if (cellNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element cellElem = (Element) cellNode;

                        if (cellElem.getTextContent().equals("PacTile")) {
                            pacLocations.add(coord);
                            System.out.println("###DEBUG (line 82, LFR)### PacTile coords: " + coord);
                        }
                        else if (cellElem.getTextContent().equals("PathTile")) {
                            pathLocations.add(coord);
                        }
                        else if (cellElem.getTextContent().equals("WallTile")) {
                            wallLocations.add(coord);
                        }
                        else if (cellElem.getTextContent().equals("PillTile")) {
                            pillLocations.add(coord);
                        }
                        else if (cellElem.getTextContent().equals("GoldTile")) {
                            goldLocations.add(coord);
                        }
                        else if (cellElem.getTextContent().equals("IceTile")) {
                            iceLocations.add(coord);
                        }
                        else if (cellElem.getTextContent().equals("PortalWhiteTile")) {
                            portalWhiteLocations.add(coord);
                        }
                        else if (cellElem.getTextContent().equals("PortalYellowTile")) {
                            portalYellowLocations.add(coord);
                        }
                        else if (cellElem.getTextContent().equals("PortalDarkGrayTile")) {
                            portalDarkGrayLocations.add(coord);
                        }
                        else if (cellElem.getTextContent().equals("PortalDarkGoldTile")) {
                            portalDarkGoldLocations.add(coord);
                        }
                        else if (cellElem.getTextContent().equals("TrollTile")) {
                            trollLocations.add(coord);
                        }
                        else if (cellElem.getTextContent().equals("TX5Tile")) {
                            TX5Locations.add(coord);
                        }
                        col++;
                    }
                }
                row++;
                col = 1;

            }

        } catch (Exception e) {
            // PRINT TO LOG FILE
            e.printStackTrace();
        }
    }

    protected String getFileName() {
        return fileName;
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    protected List<Point> getPacLocations() {
        return pacLocations;
    }

    protected List<Point> getPathLocations() {
        return pathLocations;
    }

    protected List<Point> getWallLocations() {
        return wallLocations;
    }

    protected List<Point> getPillLocations() {
        return pillLocations;
    }

    protected List<Point> getGoldLocations() {
        return goldLocations;
    }

    protected List<Point> getIceLocations() {
        return iceLocations;
    }

    protected List<Point> getPortalWhiteLocations() {
        return portalWhiteLocations;
    }

    protected List<Point> getPortalYellowLocations() {
        return portalYellowLocations;
    }

    protected List<Point> getPortalDarkGrayLocations() {
        return portalDarkGrayLocations;
    }

    protected List<Point> getPortalDarkGoldLocations() {
        return portalDarkGoldLocations;
    }

    protected List<Point> getTrollLocations() {
        return trollLocations;
    }

    protected List<Point> getTX5Locations() {
        return TX5Locations;
    }
}
