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

package org.psgraph.graph.sparse;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.psgraph.graph.Edge;
import org.psgraph.graph.EdgeType;
import org.psgraph.graph.Vertex;

/**
 * @author Wilson de Carvalho
 */
public class MapBasedGraph<V extends Vertex, E extends Edge<V>> implements
    org.psgraph.graph.Graph<V, E> {

  private final Map<V, Map<V, E>> graph;

  public MapBasedGraph() {
    this.graph = new HashMap<>();
  }

  public MapBasedGraph(Collection<E> edges) {
    this();
    edges.forEach(e -> addEdge(e));
  }

  /**
   * @inheritDoc
   */
  @Override
  public void addEdge(E edge) {
    if (edge.getEdgeType() == EdgeType.Directed) {
      graph.putIfAbsent(edge.getSource(), new HashMap<>());
      graph.get(edge.getSource()).put(edge.getTarget(), edge);
    } else {
      graph.putIfAbsent(edge.getSource(), new HashMap<>());
      graph.putIfAbsent(edge.getTarget(), new HashMap<>());
      graph.get(edge.getSource()).put(edge.getTarget(), edge);
      graph.get(edge.getTarget()).put(edge.getSource(), edge);
    }
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
        if (map.isEmpty()) {
          graph.remove(from);
        }
        if (edge.getEdgeType() == EdgeType.Undirected) {
          removeEdge(to, from);
        }
      }
    }
  }

  /**
   * @inheritDoc
   */
  @Override
  public Collection<E> getEdges(V vertex) {
    return graph.getOrDefault(vertex, new HashMap<>()).values();
  }

  /**
   * @inheritDoc
   */
  @Override
  public Set<Set<E>> getStronglyConnectedComponents(V startVertex) {
    return null;
  }
}
