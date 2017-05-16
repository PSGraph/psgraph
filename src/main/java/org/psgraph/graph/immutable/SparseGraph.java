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
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.psgraph.graph.Edge;
import org.psgraph.graph.EdgeType;
import org.psgraph.graph.Graph;
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
 * This immutable graph does not allow inclusion or removal of new vertices or edges after the
 * instantiation of the class. Thus, it may be used safely by multiple threads.
 *
 * @author Wilson de Carvalho
 */
public class SparseGraph<V extends Vertex, E extends Edge<V>> implements Graph<V, E> {

  protected final Map<V, Map<V, E>> graph;
  protected final Set<E> edges;
  protected final SparseRepresentation sparseRepresentation;

  public SparseGraph(SparseRepresentation sparseRepresentation, Collection<E> edges) {
    this.sparseRepresentation = sparseRepresentation;
    this.graph = newMap();
    this.edges = newSet();
    edges.forEach(e -> addEdge(e));
  }

  public SparseGraph(Collection<E> edges) {
    this(SparseRepresentation.Hash, edges);
  }

  /**
   * Builds a Set for the specified graph representation.
   */
  protected <T> Set<T> newSet() {
    if (sparseRepresentation == SparseRepresentation.Hash) {
      return new HashSet<>();
    } else {
      return new LinkedHashSet<>();
    }
  }

  /**
   * Builds a Set for the specified graph representation.
   */
  protected <T1, T2> Map<T1, T2> newMap() {
    if (sparseRepresentation == SparseRepresentation.Hash) {
      return new HashMap<>();
    } else {
      return new LinkedHashMap<>();
    }
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
    // We always add both vertices in order to allow the "getVertices" call to return existing
    // vertices. This approach avoids creating another set to store vertices or iterating over all
    // the graph to search for them.
    V source = edge.getSource();
    V target = edge.getTarget();
    graph.putIfAbsent(source, newMap());
    graph.putIfAbsent(target, newMap());
    graph.get(source).put(target, edge);
    if (edge.getEdgeType() == EdgeType.Undirected) {
      graph.get(target).put(source, edge);
    }
    edges.add(edge);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<E> getEdges(V v) {
    return graph.getOrDefault(v, new HashMap<>()).values();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<V> getVertices() {
    return Collections.unmodifiableSet(this.graph.keySet());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<E> getEdges() {
    return Collections.unmodifiableSet(this.edges);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<V> getAdjacentVertices(V v) {
    return Collections.unmodifiableSet(graph.getOrDefault(v, new HashMap<>()).keySet());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<V> getSuccessors(V v) {
    if (graph.containsKey(v)) {
      return graph.get(v).keySet();
    } else {
      return new HashSet<>();
    }
  }
}