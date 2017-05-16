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
import org.psgraph.graph.search.visitor.EdgeVisitor;
import org.psgraph.graph.search.visitor.SearchDataVisitor;
import org.psgraph.graph.search.visitor.VertexVisitor;
import org.psgraph.graph.search.visitor.Visitor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Depth First Search (DFS) algorithm. <b>Cost: Theta(V+E)</b>
 *
 * @author Wilson de Carvalho
 */
public class DepthFirstSearch<V extends Vertex, E extends Edge<V>> implements GraphSearch<V, E> {

  private final Graph<V, E> graph;

  public DepthFirstSearch(Graph<V, E> graph) {
    this.graph = graph;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<V, SearchData<V>> search() {
    return search((VertexVisitor<V>) v -> true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<V, SearchData<V>> search(VertexVisitor<V> visitor) {
    Map<V, SearchData<V>> searchData = initSearchData();
    for (V v : graph.getVertices()) {
      if (searchData.get(v).getColor() == VertexColor.White) {
        dfsVertex(v, u -> true, searchData);
      }
    }
    return searchData;
  }

  /**
   * {@inheritDoc}
   */
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
   * {@inheritDoc}
   */
  @Override
  public Map<V, SearchData<V>> search(V s) {
    Map<V, SearchData<V>> searchData = initSearchData();
    return dfsVertex(s, v -> true, searchData);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<V, SearchData<V>> search(V s, VertexVisitor<V> visitor) {
    Map<V, SearchData<V>> searchData = initSearchData();
    return dfsVertex(s, visitor, searchData);
  }

  /**
   * Executes the dfs algorithm for a given start vertex <b>s</b>. <b>Cost: Theta(V+E)</b>
   */
  private Map<V, SearchData<V>> dfsVertex(V s, VertexVisitor<V> visitor,
      Map<V, SearchData<V>> searchData) {
    int time = 0;
    dfsVisit(s, searchData, time, visitor);
    return searchData;
  }

  /**
   * Visits a given vertex and triggers the visitation of the adjacent non-visited vertices.
   */
  private <T> void dfsVisit(V u, Map<V, SearchData<V>> searchData, int time,
      Visitor<T> visitor) {
    time += 1;
    SearchDataImpl<V> uData = (SearchDataImpl<V>) searchData.get(u);
    uData.setDepth(time);
    uData.setColor(VertexColor.Gray);
    for (V v : graph.getAdjacentVertices(u)) {
      SearchDataImpl<V> vData = (SearchDataImpl<V>) searchData.get(v);
      if (vData.getColor() == VertexColor.White) {
        uData.addSucessors(v);
        vData.setPredecessor(u);
      }
    }
    boolean ok = visitor instanceof VertexVisitor ? ((VertexVisitor<V>) visitor).visit(u)
        : ((SearchDataVisitor<V>) visitor).visit(uData);
    if (ok) {
      for (V v : uData.getSucessors()) {
        dfsVisit(v, searchData, time, visitor);
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
      ret.put(vertex, new SearchDataImpl<>(vertex));
    }
    return ret;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<E> search(V s, EdgeVisitor<V, E> visitor) {
    throw new NotImplementedException();
  }

  @Override
  public Map<V, SearchData<V>> search(SearchDataVisitor<V> visitor) {
    return null;
  }

  @Override
  public Map<V, SearchData<V>> search(V s, SearchDataVisitor<V> visitor) {
    return null;
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
    SearchDataImpl<V> uData = (SearchDataImpl<V>) searchData.get(u);
    uData.setDepth(time);
    uData.setColor(VertexColor.Gray);
    for (V v : graph.getAdjacentVertices(u)) {
      E e = graph.getEdge(u, v);
      if (!edgesVisited.contains(e) && visitor.visit(e)) {
        edgesVisited.add(e);
        SearchData<V> vData = searchData.get(v);
        if (vData.getColor() == VertexColor.White) {
          uData.addSucessors(v);
          dfsEdgeVisit(v, searchData, time, visitor, edgesVisited);
        }
      }
    }
    time += 1;
    uData.setColor(VertexColor.Black);
    uData.setTime(time);
  }
}
