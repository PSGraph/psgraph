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
import org.psgraph.graph.Edge;
import org.psgraph.graph.Vertex;

/**
 * Fundamental definitions for search algorithms.
 *
 * @author Wilson de Carvalho
 */
public interface GraphSearch<V extends Vertex, E extends Edge<V>> {

  /**
   * Perform the graph search starting in the informed vertex.
   *
   * @param startVertex Start vertex for the search.
   * @return The set of vertexes visited.
   */
  Set<V> execute(V startVertex);

  /**
   * Perform the graph search starting in the informed vertex.
   *
   * @param startVertex Start vertex for the search.
   * @param visitor The vertex visitor used to control the search.
   * @return The set of vertexes visited.
   */
  Set<V> execute(V startVertex, VertexVisitor<V> visitor);

  /**
   * Perform the graph search starting in the informed vertex.
   *
   * @param startVertex Start vertex for the search.
   * @param visitor The edge visitor used to control the search.
   * @return The set of vertexes visited.
   */
  Set<V> execute(V startVertex, EdgeVisitor<V, E> visitor);

  /**
   * Perform the graph search starting in the informed vertex.
   *
   * @param startVertex Start vertex for the search.
   * @param visitor The vertex-edge visitor used to control the search.
   * @return The set of vertexes visited.
   */
  Set<V> execute(V startVertex, VertexEdgeVisitor<V, E> visitor);
}
