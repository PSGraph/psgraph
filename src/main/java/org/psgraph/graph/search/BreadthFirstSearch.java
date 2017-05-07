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

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.psgraph.graph.Edge;
import org.psgraph.graph.Graph;
import org.psgraph.graph.Vertex;

/**
 * Breadth First Search (BFS) algorithm.
 *
 * @author Wilson de Carvalho
 */
public class BreadthFirstSearch<V extends Vertex, E extends Edge<V>> implements GraphSearch<V, E> {

  private final Graph<V, E> graph;

  public BreadthFirstSearch(Graph<V, E> graph) {
    this.graph = graph;
  }

  /**
   * @inheritDoc
   */
  @Override
  public Collection<SearchData<V>> search() {
    return search((VertexVisitor<V>) v -> true);
  }

  /**
   * @inheritDoc
   */
  @Override
  public Collection<SearchData<V>> search(VertexVisitor<V> visitor) {
    Map<V, SearchData<V>> searchData = initSearchData();
    for (V v : graph.getVertices()) {
      if (searchData.get(v).getColor() == VertexColor.White) {
        bfsVertex(v, u -> true, searchData);
      }
    }
    return searchData.values();
  }

  /**
   * @inheritDoc
   */
  @Override
  public Set<E> search(EdgeVisitor<V, E> visitor) {
    Set<E> ret = new HashSet<>();
    Map<V, SearchData<V>> searchData = initSearchData();
    for (V v : graph.getVertices()) {
      if (searchData.get(v).getColor() == VertexColor.White) {
        ret.addAll(bfsEdge(v, u -> true, searchData));
      }
    }
    return ret;
  }

  /**
   * @inheritDoc
   */
  @Override
  public Collection<SearchData<V>> search(V s) {
    Map<V, SearchData<V>> searchData = initSearchData();
    return bfsVertex(s, v -> true, searchData);
  }

  /**
   * @inheritDoc
   */
  @Override
  public Collection<SearchData<V>> search(V s, VertexVisitor<V> visitor) {
    Map<V, SearchData<V>> searchData = initSearchData();
    return bfsVertex(s, visitor, searchData);
  }

  /**
   * Executes the dfs algorithm for a given start vertex <b>s</b>. <b>Cost: Theta(V+E)</b>
   */
  private Collection<SearchData<V>> bfsVertex(V s, Visitor<V> visitor,
      Map<V, SearchData<V>> searchData) {
    ArrayDeque<V> queue = new ArrayDeque<>();
    queue.push(s);
    while (!queue.isEmpty()) {
      V u = queue.pop();
      if (visitor.visit(u)) {
        SearchData<V> uData = searchData.get(u);
        for (V v : graph.getAdjacentVertices(u)) {
          SearchData<V> vData = searchData.get(v);
          if (vData.getColor() == VertexColor.White) {
            vData.setColor(VertexColor.Gray);
            vData.setDepth(uData.getDepth() + 1);
            vData.setPredecessor(u);
            queue.push(v);
          }
        }
        uData.setColor(VertexColor.Black);
      }
    }
    return searchData.values();
  }

  /**
   * Initializes a map with all vertices to store the search data.
   */
  private Map<V, SearchData<V>> initSearchData() {
    Map<V, SearchData<V>> ret = new HashMap<>();
    for (V vertex : graph.getVertices()) {
      ret.put(vertex, new SearchData<>(vertex));
    }
    return ret;
  }

  /**
   * @inheritDoc
   */
  @Override
  public Set<E> search(V s, EdgeVisitor<V, E> visitor) {
    Map<V, SearchData<V>> searchData = initSearchData();
    return bfsEdge(s, visitor, searchData);
  }

  /**
   * Executes the bfs algorithm for a given start vertex <b>s</b>. <b>Cost: Theta(V+E)</b>
   *
   * @param s Start vertex.
   * @param visitor The edge visitor used to control the search. In case the visitor returns false
   * for a given edge, the topological search will stop at that point.
   * @param searchData A m
   * @return A collection with the edges visited during the search.
   */
  private Set<E> bfsEdge(V s, EdgeVisitor<V, E> visitor, Map<V, SearchData<V>> searchData) {
    Set<E> ret = new HashSet<>();
    ArrayDeque<V> queue = new ArrayDeque<>();
    queue.push(s);
    while (!queue.isEmpty()) {
      V u = queue.pop();
      SearchData<V> uData = searchData.get(u);
      Collection<V> adjU = graph.getAdjacentVertices(u);
      for (V v : adjU) {
        E e = graph.getEdge(u, v);
        if (visitor.visit(e)) {
          ret.add(e);
          SearchData<V> vData = searchData.get(v);
          if (vData.getColor() == VertexColor.White) {
            vData.setColor(VertexColor.Gray);
            vData.setDepth(uData.getDepth() + 1);
            vData.setPredecessor(u);
            queue.push(v);
          }
        }
      }
      uData.setColor(VertexColor.Black);
    }
    return ret;
  }
}
