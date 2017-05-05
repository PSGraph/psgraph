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

import java.util.Set;

/**
 * @author Wilson de Carvalho
 */
public interface Graph<N extends Node, E extends Edge<N>> {

  /**
   * Adds a given edge to this nodes. If the edge is an instance of DiEdge it will be created as a
   * source->target edge, otherwise it will be considered an undirected edge.
   */
  void addEdge(E edge);

  /**
   * Removes a given edge.
   */
  void removeEdge(E edge);

  /**
   * Removes the edge that is connected to the informed nodes.
   */
  void removeEdge(N from, N to);

  /**
   * Get all the edges connected to a given node n.
   */
  Set<E> getEdges(Node n);

  /**
   * Get all edges that are source of a given node n.
   */
  Set<E> getSourceEdges(N n);

  /**
   * Get all edges that are target of a given node n.
   */
  Set<E> getTargetEdges(N n);
}
