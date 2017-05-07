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

package org.psgraph.graph.mutable;

import org.psgraph.graph.Edge;
import org.psgraph.graph.Vertex;

/**
 * Fundamental definitions for mutable graph implementations.
 *
 * @author Wilson de Carvalho
 */
public interface Graph<V extends Vertex, E extends Edge<V>> extends org.psgraph.graph.Graph<V, E> {

  /**
   * Adds a given vertex to this graph.
   */
  void addVertex(V v);

  /**
   * Removes a given vertex from this graph and all the edges in which it is connected.
   */
  void removeVertex(V v);

  /**
   * Adds a given edge to this graph. If the edge is an instance of DiEdge it will be created as a
   * source->target edge, otherwise it will be considered an undirected edge.
   */
  void addEdge(E edge);

  /**
   * Removes a given edge from this graph.
   */
  void removeEdge(E edge);

  /**
   * Removes the edge that is connected to the informed vertices.
   */
  void removeEdge(V from, V to);
}