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

package org.psgraph.graph;

import java.util.Collection;
import java.util.Set;

/**
 * Fundamental definitions for graph implementations.
 *
 * @author Wilson de Carvalho
 */
public interface Graph<V extends Vertex, E extends Edge<V>> {

  /**
   * Gets the edge that is connected to the informed vertices.
   */
  E getEdge(V from, V to);

  /**
   * Gets all the edges connected to a given vertex v.
   */
  Collection<E> getEdges(V v);

  /**
   * Gets all the vertices in this graph.
   */
  Set<V> getVertices();

  /**
   * Gets all the edges in this graph.
   */
  Set<E> getEdges();

  /**
   * Gets the adjacent vertices of a given vertex.
   */
  Collection<V> getAdjacentVertices(V v);

  /**
   * Gets the successor of a given vertex.
   */
  Set<V> getSuccessors(V v);
}