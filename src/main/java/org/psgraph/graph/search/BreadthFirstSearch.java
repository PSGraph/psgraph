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
import org.psgraph.graph.Vertex;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Breadth First Search (BFS) algorithm.
 *
 * @author Wilson de Carvalho
 */
public class BreadthFirstSearch<V extends Vertex, E extends Edge<V>> implements GraphSearch<V, E> {

  /**
   * @inheritDoc
   */
  @Override
  public Set<V> execute(V startVertex) {
    throw new NotImplementedException();
  }

  /**
   * @inheritDoc
   */
  @Override
  public Set<V> execute(V startVertex, VertexVisitor<V> visitor) {
    throw new NotImplementedException();
  }

  /**
   * @inheritDoc
   */
  @Override
  public Set<V> execute(V startVertex, EdgeVisitor<V, E> visitor) {
    throw new NotImplementedException();
  }

  /**
   * @inheritDoc
   */
  @Override
  public Set<V> execute(V startVertex, VertexEdgeVisitor<V, E> visitor) {
    throw new NotImplementedException();
  }
}
