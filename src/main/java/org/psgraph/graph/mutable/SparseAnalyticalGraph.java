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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.psgraph.graph.AnalyticalGraph;
import org.psgraph.graph.EdgeType;
import org.psgraph.graph.SparseRepresentation;
import org.psgraph.graph.Vertex;

/**
 * The sparse analytical graph is a specialization of the sparse graph. It allows more elaborated
 * operations in graphs, like predecessors' analysis without searching the graph. For this reason,
 * it presents a higher memory cost which is typically 2 times of the underlying data structure
 * compared to its super class.
 *
 * This mutable graph can may have vertices and edges removed or inserted after the class is
 * instantiated. It is not thread safe.
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
   * {@inheritDoc}
   */
  @Override
  public void addEdge(E edge) {
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