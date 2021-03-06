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

package org.psgraph.graph.search.visitor;

/**
 * Base interface for grouping different visitors.
 *
 * @author Wilson de Carvalho
 */
public interface Visitor<T> {

  /**
   * Visits a vertex.
   *
   * @param element The element being visited.
   * @return False if the topological search must stop in the current element. True otherwise.
   */
  boolean visit(T element);
}
