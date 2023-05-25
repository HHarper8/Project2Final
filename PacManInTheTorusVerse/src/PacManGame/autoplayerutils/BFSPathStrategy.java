package src.PacManGame.autoplayerutils;

import ch.aplu.jgamegrid.Location;
import src.PacManGame.Game;
import src.PacManGame.PacActor;
import src.PacManGame.Portal;


import java.util.ArrayList;


public class BFSPathStrategy implements IPathStrategy {

    public BFSPathStrategy() {
    }

    @Override
    public Location findNextMoveLocation(PacActor pacActor) {
        return null;
    }

    /** findPathToTarget() uses a modified version of breadth first search to automatically move
     * pac man to the closest pill
     * @param pacActor
     * @return
     */
    @Override
    public ArrayList<Location> findPathToTarget(PacActor pacActor) {
        Location target = pacActor.findTargetLocation();
        Game thisGame = pacActor.getGame();

        // List of visited locations
        ArrayList<LocationNode> visitedLocations = new ArrayList<LocationNode>();

        // Result path
        ArrayList<Location> results = new ArrayList<Location>();

        // initialise the queue
        ArrayList<LocationNode> queue = new ArrayList<LocationNode>();
        LocationNode root = new LocationNode(pacActor.getLocation(), null);
        queue.add(root);

        LocationNode node;

        while (!queue.isEmpty()){
            node = queue.remove(0);

            // mark this location as visited
            visitedLocations.add(node);

            // have we found the target?
            if (target.equals(node.getLocation())) {
                // yes so find the very next location that led to this step
                LocationNode curr = node;
                results.add(curr.getLocation());

                while(curr.getParent() != null) {
                    results.add(curr.getLocation());
                    curr = curr.getParent();
                }


                return results;
            }


            Location otherPortalLocation = null;
            Portal thisPortal = (Portal)thisGame.getOneActorAt(node.getLocation(), Portal.class);
            // if the location is a portal, look at the otherPortal
            boolean visitedOtherPortal = false;
            if (thisPortal != null) {

                otherPortalLocation = thisPortal.getOtherPortalLocation();

                for(LocationNode visited: visitedLocations) {
                    if ((visited.getLocation().x == otherPortalLocation.x) && (visited.getLocation().y == otherPortalLocation.y)) {

                        visitedOtherPortal = true;
                        break;
                    }
                }

                // if we haven't visited the otherPortal then we use this other location as the root node instead
                if (!visitedOtherPortal) {
                    LocationNode parent = node.getParent();
                    node = new LocationNode(otherPortalLocation, node.getParent());
                }

            }

            // now, enqueue the node's children
            ArrayList<Location> possibleNextLocations = pacActor.getPossibleNextLocationsAt(node.getLocation());
            boolean visitedChild = false;
            for (Location possibleNext: possibleNextLocations) {
                // if child is visited, then don't add to the queue
                visitedChild = false;
                for(LocationNode visited: visitedLocations) {
                    if ((visited.getLocation().x == possibleNext.x) && (visited.getLocation().y == possibleNext.y)) {
                        System.out.println("THE OTHER PORTAL'S LOCATION IS VISITED");
                        visitedChild = true;
                        break;
                    }
                }

                // based on whether child was visited, determining whether to make a new node
                if (!visitedChild) {
                    LocationNode newNode = new LocationNode(possibleNext, node);
                    queue.add(newNode);
                }
            }


        }

        // CANNOT FIND A SOLUTION
        return null;
    }



}
