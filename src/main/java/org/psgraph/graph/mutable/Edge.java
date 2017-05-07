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

import org.psgraph.graph.EdgeType;
import org.psgraph.graph.Vertex;

/**
 * An immutable edge may have its properties changed.
 *
 * @author Wilson de Carvalho
 */
public interface Edge<V extends Vertex> extends org.psgraph.graph.Edge<V> {

  /**
   * Set the source vertex for this edge.
   */
  void setSource(V v);

  /**
   * Set the target vertex for this edge.
   */
  void setTarget(V v);

  /**
   * Set the edge type.
   */
  void setEdgeType(EdgeType edgeType);
}
