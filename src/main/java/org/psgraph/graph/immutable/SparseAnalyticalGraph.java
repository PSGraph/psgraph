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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.psgraph.graph.AnalyticalGraph;
import org.psgraph.graph.Edge;
import org.psgraph.graph.EdgeType;
import org.psgraph.graph.SparseRepresentation;
import org.psgraph.graph.Vertex;

/**
 * The sparse analytical graph is a specialization of the sparse graph. It allows more elaborated
 * operations in graphs, like predecessors' analysis without searching the graph. For this reason,
 * it presents a higher memory cost which is typically 2 times of the underlying data structure
 * compared to its super class.
 *
 * This immutable graph does not allow inclusion or removal of new vertices or edges after the
 * instantiation of the class. Thus, it may be used safely by multiple threads. *
 *
 * @author Wilson de Carvalho
 * @see org.psgraph.graph.immutable.SparseGraph
 */
public class SparseAnalyticalGraph<V extends Vertex, E extends Edge<V>> extends SparseGraph<V, E>
    implements AnalyticalGraph<V, E> {

  protected final Map<V, Set<V>> predecessors;

  public SparseAnalyticalGraph(SparseRepresentation sparseRepresentation, Collection<E> edges) {
    super(sparseRepresentation, edges);
    this.predecessors = new HashMap<>();
  }

  public SparseAnalyticalGraph(Collection<E> edges) {
    this(SparseRepresentation.Hash, edges);
  }

  /**
   * Adds a given edge to this graph. If the edge is an instance of DiEdge it will be created as a
   * source->target edge, otherwise it will be considered an undirected edge.
   */
  protected void addEdge(E edge) {
    super.addEdge(edge);
    V source = edge.getSource();
    V target = edge.getTarget();
    predecessors.putIfAbsent(target, new HashSet<>());
    predecessors.get(target).add(source);
    if (edge.getEdgeType() == EdgeType.Undirected) {
      predecessors.putIfAbsent(source, new HashSet<>());
      predecessors.get(source).add(target);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<V> getPredecessors(V v) {
    if (predecessors.containsKey(v)) {
      return predecessors.get(v);
    }
    return null;
  }
}