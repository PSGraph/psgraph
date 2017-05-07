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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.psgraph.graph.EdgeType;
import org.psgraph.graph.Vertex;

/**
 * @author Wilson de Carvalho
 */
public class MapBasedGraph<V extends Vertex, E extends Edge<V>> extends
    org.psgraph.graph.immutable.MapBasedGraph<V, E> implements Graph<V, E> {

  public MapBasedGraph(Collection<E> edges) {
    super(edges);
  }

  /**
   * @inheritDoc
   */
  @Override
  public void addVertex(V v) {
    graph.putIfAbsent(v, new HashMap<>());
  }

  /**
   * @inheritDoc
   */
  @Override
  public void removeVertex(V v) {
    // Must also remove this vertex from all other edges
    Map<V, E> map = graph.get(v);
    if (map != null) {
      for (V u : map.keySet()) {
        graph.get(u).remove(v);
      }
    }
    graph.remove(v);
  }

  /**
   * @inheritDoc
   */
  @Override
  public void addEdge(E edge) {
    super.addEdge(edge);
  }

  /**
   * @inheritDoc
   */
  @Override
  public void removeEdge(E edge) {
    if (edge.getEdgeType() == EdgeType.Directed) {
      this.removeEdge(edge.getSource(), edge.getTarget());
    } else {
      this.removeEdge(edge.getSource(), edge.getTarget());
      this.removeEdge(edge.getTarget(), edge.getSource());
    }
    edges.remove(edge);
  }

  /**
   * @inheritDoc
   */
  @Override
  public void removeEdge(V from, V to) {
    Map<V, E> map = graph.get(from);
    if (map != null) {
      if (map.containsKey(to)) {
        E edge = map.get(to);
        map.remove(from);
        edges.remove(edge);
        if (map.isEmpty()) {
          graph.remove(from);
        }
        if (edge.getEdgeType() == EdgeType.Undirected) {
          removeEdge(to, from);
        }
      }
    }
  }
}
