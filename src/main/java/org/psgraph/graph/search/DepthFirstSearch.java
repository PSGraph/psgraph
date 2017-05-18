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

/**
 * Depth First Search (DFS) algorithm. <b>Cost: Theta(V+E)</b>
 *
 * @author Wilson de Carvalho
 */
public class DepthFirstSearch<V extends Vertex, E extends Edge<V>> extends GraphSearchImpl<V, E> {

  public DepthFirstSearch(Graph<V, E> graph) {
    super(graph);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <T> void vertexSearch(V s, Visitor<T> visitor,
      Map<V, SearchData<V>> searchData) {
    int time = 0;
    dfsVisit(s, searchData, time, visitor);
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
   * {@inheritDoc}
   */
  @Override
  protected Set<E> edgeSearch(V s, EdgeVisitor<V, E> visitor, Map<V, SearchData<V>> searchData) {
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
