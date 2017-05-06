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

import org.psgraph.graph.Edge;
import org.psgraph.graph.Vertex;

/**
 * Interface created to allow code injection in search algorithms through lambda expressions.
 *
 * @author Wilson de Carvalho
 */
@FunctionalInterface
public interface EdgeVisitor<V extends Vertex, E extends Edge<V>> {

  /**
   * Visits an edge.
   *
   * @param edge The edge being visited.
   * @return False if the topological search must stop in the current element. True otherwise.
   */
  boolean visit(E edge);
}
