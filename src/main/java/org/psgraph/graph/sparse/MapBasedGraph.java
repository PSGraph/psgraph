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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.psgraph.graph.Edge;
import org.psgraph.graph.EdgeType;
import org.psgraph.graph.Node;

/**
 * @author Wilson de Carvalho
 */
public class MapBasedGraph<N extends Node, E extends Edge<N>> implements
    org.psgraph.graph.Graph<N, E> {

  private final Map<N, Set<N>> nodes;
  private final Map<N, Set<E>> edgesByNode;

  public MapBasedGraph() {
    this.nodes = new HashMap<>();
    this.edgesByNode = new HashMap<>();
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
      nodes.putIfAbsent(edge.getSource(), new HashSet<>());
      nodes.get(edge.getSource()).add(edge.getTarget());
    } else {
      nodes.putIfAbsent(edge.getSource(), new HashSet<>());
      nodes.putIfAbsent(edge.getTarget(), new HashSet<>());
      nodes.get(edge.getSource()).add(edge.getTarget());
      nodes.get(edge.getTarget()).add(edge.getSource());
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
  public void removeEdge(N from, N to) {
    Set<N> nodes = this.nodes.get(from);
    if (nodes != null) {
      nodes.remove(to);
      if (nodes.isEmpty()) {
        this.nodes.remove(from);
      }
    }
  }

  /**
   * @inheritDoc
   */
  @Override
  public Set<E> getEdges(Node n) {
    return edgesByNode.get(n);
  }

  /**
   * @inheritDoc
   */
  @Override
  public Set<E> getSourceEdges(N n) {
    Set<E> edges = edgesByNode.get(n);
    if (edges != null) {
      return edges.stream().filter(e -> e.getEdgeType() == EdgeType.Directed)
          .filter(e -> e.getTarget().equals(n)).collect(
              Collectors.toSet());
    } else {
      return null;
    }
  }

  /**
   * @inheritDoc
   */
  @Override
  public Set<E> getTargetEdges(N n) {
    Set<E> edges = edgesByNode.get(n);
    if (edges != null) {
      return edges.stream().filter(e -> e.getEdgeType() == EdgeType.Directed)
          .filter(e -> e.getSource().equals(n)).collect(
              Collectors.toSet());
    } else {
      return null;
    }
  }
}
