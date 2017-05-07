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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.psgraph.graph.Edge;
import org.psgraph.graph.Graph;
import org.psgraph.graph.Vertex;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Depth First Search (DFS) algorithm.
 *
 * @author Wilson de Carvalho
 */
public class DepthFirstSearch<V extends Vertex, E extends Edge<V>> implements GraphSearch<V, E> {

  private final Graph<V, E> graph;

  public DepthFirstSearch(Graph<V, E> graph) {
    this.graph = graph;
  }

  @Override
  public Collection<SearchData<V>> search() {
    return search((VertexVisitor<V>) v -> true);
  }

  @Override
  public Collection<SearchData<V>> search(VertexVisitor<V> visitor) {
    Map<V, SearchData<V>> searchData = initSearchData();
    for (V v : graph.getVertices()) {
      if (searchData.get(v).getColor() == VertexColor.White) {
        dfsVertex(v, u -> true, searchData);
      }
    }
    return searchData.values();
  }

  @Override
  public Collection<E> search(EdgeVisitor<V, E> visitor) {
    Set<E> ret = new HashSet<>();
    Map<V, SearchData<V>> searchData = initSearchData();
    for (V v : graph.getVertices()) {
      if (searchData.get(v).getColor() == VertexColor.White) {
        ret.addAll(dfsEdge(v, u -> true, searchData));
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
    return dfsVertex(s, v -> true, searchData);
  }

  /**
   * @inheritDoc
   */
  @Override
  public Collection<SearchData<V>> search(V s, VertexVisitor<V> visitor) {
    Map<V, SearchData<V>> searchData = initSearchData();
    return dfsVertex(s, visitor, searchData);
  }

  /**
   * Executes the dfs algorithm for a given start vertex <b>s</b>. <b>Cost: Theta(V+E)</b>
   */
  private Collection<SearchData<V>> dfsVertex(V s, VertexVisitor<V> visitor,
      Map<V, SearchData<V>> searchData) {
    int time = 0;
    dfsVertexVisit(s, searchData, time, visitor);
    return searchData.values();
  }

  /**
   * Visits a given vertex and triggers the visitation of the adjacent non-visited vertices.
   */
  private void dfsVertexVisit(V u, Map<V, SearchData<V>> searchData, int time,
      VertexVisitor<V> visitor) {
    time += 1;
    SearchData<V> uData = searchData.get(u);
    uData.setDepth(time);
    if (visitor.visit(u)) {
      uData.setColor(VertexColor.Gray);
      for (V v : graph.getAdjacentVertices(u)) {
        SearchData<V> vData = searchData.get(v);
        if (vData.getColor() == VertexColor.White) {
          dfsVertexVisit(v, searchData, time, visitor);
        }
      }
    }
    time += 1;
    uData.setColor(VertexColor.Black);
    uData.setTime(time);
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
    throw new NotImplementedException();
  }


  /**
   * Executes the dfs algorithm for a given start vertex <b>s</b>. <b>Cost: Theta(V+E)</b>
   */
  private Set<E> dfsEdge(V s, EdgeVisitor<V, E> visitor,
      Map<V, SearchData<V>> searchData) {
    Set<E> ret = new HashSet<>();
    int time = 0;
    dfsEdgeVisit(s, searchData, time, visitor, ret);
    return ret;
  }

  /**
   * Visits a given vertex and triggers the visitation of the adjacent non-visited vertices.
   */
  private void dfsEdgeVisit(V u, Map<V, SearchData<V>> searchData, int time,
      EdgeVisitor<V, E> visitor, Set<E> edgesVisited) {
    time += 1;
    SearchData<V> uData = searchData.get(u);
    uData.setDepth(time);
    uData.setColor(VertexColor.Gray);
    for (V v : graph.getAdjacentVertices(u)) {
      E e = graph.getEdge(u, v);
      if (visitor.visit(e)) {
        edgesVisited.add(e);
        SearchData<V> vData = searchData.get(v);
        if (vData.getColor() == VertexColor.White) {
          dfsEdgeVisit(v, searchData, time, visitor, edgesVisited);
        }
      }
    }
    time += 1;
    uData.setColor(VertexColor.Black);
    uData.setTime(time);
  }
}
