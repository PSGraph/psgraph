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
 * Like in regular graph theory, an edge is composed of two nodes (1 and 2).
 *
 * @author Wilson de Carvalho
 */
public interface Edge<N extends Node> {

  /**
   * Get the source node for this edge.
   */
  N getSource();

  /**
   * Set the source node for this edge.
   */
  void setSource(N n);

  /**
   * Get the target node for this edge.
   */
  N getTarget();

  /**
   * Set the target node for this edge.
   */
  void setTarget(N n);

  /**
   * Given a reference node, return the other node of this edge.
   *
   * @param n Reference node.
   * @return The other node connected to this edge.
   */
  N getOtherNode(N n);

  /**
   * Get the edge type.
   */
  EdgeType getEdgeType();

  /**
   * Set the edge type.
   */
  void setEdgeType(EdgeType edgeType);
}
