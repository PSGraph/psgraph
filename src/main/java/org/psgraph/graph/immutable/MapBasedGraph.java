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

package org.psgraph.graph.immutable;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.psgraph.graph.Edge;
import org.psgraph.graph.EdgeType;
import org.psgraph.graph.Graph;
import org.psgraph.graph.Vertex;

/**
 * @author Wilson de Carvalho
 */
public class MapBasedGraph<V extends Vertex, E extends Edge<V>> implements
    Graph<V, E> {

  protected final Map<V, Map<V, E>> graph;
  protected final Set<V> vertices;
  protected final Set<E> edges;

  public MapBasedGraph() {
    this.graph = new HashMap<>();
    this.vertices = new HashSet<>();
    this.edges = new HashSet<>();
  }

  public MapBasedGraph(Collection<E> edges) {
    this();
    edges.forEach(e -> addEdge(e));
  }

  @Override
  public E getEdge(V from, V to) {
    return graph.getOrDefault(from, new HashMap<>()).get(to);
  }

  /**
   * Adds a given edge to this graph. If the edge is an instance of DiEdge it will be created as a
   * source->target edge, otherwise it will be considered an undirected edge.
   */
  protected void addEdge(E edge) {
    if (edge.getEdgeType() == EdgeType.Directed) {
      graph.putIfAbsent(edge.getSource(), new HashMap<>());
      graph.get(edge.getSource()).put(edge.getTarget(), edge);
    } else {
      graph.putIfAbsent(edge.getSource(), new HashMap<>());
      graph.putIfAbsent(edge.getTarget(), new HashMap<>());
      graph.get(edge.getSource()).put(edge.getTarget(), edge);
      graph.get(edge.getTarget()).put(edge.getSource(), edge);
    }
    edges.add(edge);
  }

  /**
   * @inheritDoc
   */
  @Override
  public Collection<E> getEdges(V v) {
    return graph.getOrDefault(v, new HashMap<>()).values();
  }

  /**
   * @inheritDoc
   */
  @Override
  public Set<V> getVertices() {
    return Collections.unmodifiableSet(this.vertices);
  }

  /**
   * @inheritDoc
   */
  @Override
  public Set<E> getEdges() {
    return Collections.unmodifiableSet(this.edges);
  }

  /**
   * @inheritDoc
   */
  @Override
  public Set<V> getAdjacentVertices(V v) {
    return graph.getOrDefault(v, new HashMap<>()).keySet();
  }
}
