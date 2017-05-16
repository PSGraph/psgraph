/**
 * <pre>
 *   _ __  ___  __  _ __    _    _ __  _  _
 *  |  _ \/  / /  ||  __\  / \  |  _ \| || |
 *  |  __/\  \/  __| \ \  / - \ |  __/| -- |
 *  |_|   /__/|___||_|\_\|_| |_||_|   |_||_|
 *
 *  Power Systems' Graph
 * </pre>
 */

package org.psgraph.graph.search;

import java.util.Set;
import org.psgraph.graph.Vertex;

/**
 * Stores the search data for graph searching algorithms.
 *
 * @author Wilson de Carvalho
 */
public interface SearchData<V extends Vertex> {

  /**
   * Gets the current vertex.
   */
  V getVertex();

  /**
   * Gets the color of the current vertex in the search.
   */
  VertexColor getColor();

  /**
   * Gets the time (starting in 0) that measures how many vertices have been visited until this
   * vertex was reached.
   */
  int getTime();

  /**
   * Gets the depth of the current vertex in the search.
   */
  int getDepth();

  /**
   * Gets the predecessor of the current vertex in this search.
   */
  V getPredecessor();

  /**
   * Gets the successors (vertices that will be reached next) of the current vertex in this search.
   */
  Set<V> getSucessors();
}
