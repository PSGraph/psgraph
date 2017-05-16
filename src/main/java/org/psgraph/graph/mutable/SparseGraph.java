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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.psgraph.graph.EdgeType;
import org.psgraph.graph.SparseRepresentation;
import org.psgraph.graph.Vertex;

/**
 * A sparse graph, as the name suggests, allows sparse graph representation. It uses a HashMap
 * (default) or a LinkedHashMap to represent the graph internally. The choice between HashMap or
 * LinkedHashMap implies in differences in performance for big graphs. A graph backed by a
 * LinkedHashMap will be faster when iterating through its vertices or edges due to the nature of
 * this data structure. Conversely, the LinkedHashMap is slower when additions and removals are
 * performed in vertices and edges. When the underlying data structure is not informed, the graph
 * will be backed by a HashMap.
 *
 * This mutable graph can may have vertices and edges removed or inserted after the class is
 * instantiated. It is not thread safe.
 *
 * @author Wilson de Carvalho
 */
public class SparseGraph<V extends Vertex, E extends Edge<V>> extends
    org.psgraph.graph.immutable.SparseGraph<V, E> implements Graph<V, E> {

  public SparseGraph() {
    this(SparseRepresentation.Hash);
  }

  public SparseGraph(SparseRepresentation sparseRepresentation) {
    super(new ArrayList<>());
  }

  public SparseGraph(Collection<E> edges) {
    super(edges);
  }

  public SparseGraph(SparseRepresentation sparseRepresentation, Collection<E> edges) {
    super(sparseRepresentation, edges);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addVertex(V v) {
    graph.putIfAbsent(v, newMap());
  }

  /**
   * {@inheritDoc}
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
   * {@inheritDoc}
   */
  @Override
  public void addEdge(E edge) {
    super.addEdge(edge);
  }

  /**
   * {@inheritDoc}
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
   * {@inheritDoc}
   */
  @Override
  public void removeEdge(V from, V to) {
    Map<V, E> map = graph.get(from);
    if (map != null) {
      if (map.containsKey(to)) {
        E edge = map.get(to);
        map.remove(from);
        edges.remove(edge);
        if (edge.getEdgeType() == EdgeType.Undirected) {
          removeEdge(to, from);
        }
      }
    }
  }
}
