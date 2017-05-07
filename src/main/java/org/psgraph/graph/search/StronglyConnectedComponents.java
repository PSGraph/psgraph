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
import org.psgraph.graph.Graph;
import org.psgraph.graph.Vertex;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Strongly connected components algorithm as described by  Tarjan, R. E. (1972) in "Depth-first
 * search and linear graph algorithms".
 *
 * @author Wilson de Carvalho
 */
public class StronglyConnectedComponents<V extends Vertex, E extends Edge<V>> {

  private final Graph<V, E> graph;

  public StronglyConnectedComponents(Graph<V, E> graph) {
    this.graph = graph;
  }

  /**
   * Gets a set of strongly connected components (scc) with a search performed from the start node
   * provided.
   *
   * @param startVertex Start vertex for the search.
   * @return A set in which each internal set corresponds to a scc.
   */
  Set<Set<V>> scc(V startVertex) {
    throw new NotImplementedException();
  }
}
