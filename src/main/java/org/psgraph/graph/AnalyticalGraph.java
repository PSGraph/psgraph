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
 * Fundamental definitions for analytical graph implementations.
 *
 * @author Wilson de Carvalho
 */
public interface AnalyticalGraph<V extends Vertex, E extends Edge<V>> extends Graph<V, E> {

  /**
   * Gets the predecessor of a given vertex.
   */
  Set<V> getPredecessors(V v);
}