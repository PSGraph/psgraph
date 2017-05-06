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
public interface Graph<N extends Vertex, E extends Edge<N>> {

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
  Collection<E> getEdges(N node);

  /**
   * Gets a set of strongly connected components (scc) with a search performed from the start node
   * provided.
   *
   * @param startNode The start node for the search.
   * @return A set in which each internal set corresponds to a scc.
   */
  Set<Set<E>> getStronglyConnectedComponents(N startNode);
}
