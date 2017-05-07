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

/**
 * Base contract for immutable edge definition.
 *
 * @author Wilson de Carvalho
 */
public interface Edge<V extends Vertex> {

  /**
   * Get the source vertex for this edge.
   */
  V getSource();

  /**
   * Get the target vertex for this edge.
   */
  V getTarget();

  /**
   * Given a reference vertex, return the other vertex of this edge.
   *
   * @param v Reference vertex.
   * @return The other vertex connected to this edge.
   */
  V getOtherVertex(V v);

  /**
   * Get the edge type.
   */
  EdgeType getEdgeType();
}
