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
 * Breadth First Search (BFS) algorithm. <b>Cost: Theta(V+E)</b>
 *
 * @author Wilson de Carvalho
 */
public class BreadthFirstSearch<V extends Vertex, E extends Edge<V>> extends
    GraphSearchImpl<V, E> {

  public BreadthFirstSearch(Graph<V, E> graph) {
    super(graph);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <T> void vertexSearch(V s, Visitor<T> visitor,
      Map<V, SearchData<V>> searchData) {
    ArrayDeque<V> queue = new ArrayDeque<>();
    VertexVisitor<V> vertexVisitor =
        visitor instanceof VertexVisitor ? ((VertexVisitor<V>) visitor) : null;
    SearchDataVisitor<V> searchDataVisitor =
        visitor instanceof SearchDataVisitor ? ((SearchDataVisitor<V>) visitor) : null;
    queue.push(s);
    while (!queue.isEmpty()) {
      V u = queue.pop();
      SearchDataImpl<V> uData = (SearchDataImpl<V>) searchData.get(u);
      for (V v : graph.getAdjacentVertices(u)) {
        SearchDataImpl<V> vData = (SearchDataImpl<V>) searchData.get(v);
        if (vData.getColor() == VertexColor.White) {
          uData.addSucessors(v);
          vData.setColor(VertexColor.Gray);
          vData.setDepth(uData.getDepth() + 1);
          vData.setPredecessor(u);
        }
      }
      boolean ok = false;
      if (vertexVisitor != null) {
        ok = vertexVisitor.visit(u);
      }
      if (searchDataVisitor != null) {
        ok = searchDataVisitor.visit(uData);
      }
      if (ok) {
        for (V v : uData.getSucessors()) {
          queue.push(v);
        }
      }
      uData.setColor(VertexColor.Black);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Set<E> edgeSearch(V s, EdgeVisitor<V, E> visitor, Map<V, SearchData<V>> searchData) {
    Set<E> ret = new HashSet<>();
    ArrayDeque<V> queue = new ArrayDeque<>();
    queue.push(s);
    while (!queue.isEmpty()) {
      V u = queue.pop();
      SearchDataImpl<V> uData = (SearchDataImpl<V>) searchData.get(u);
      Collection<V> adjU = graph.getAdjacentVertices(u);
      for (V v : adjU) {
        E e = graph.getEdge(u, v);
        if (visitor.visit(e)) {
          ret.add(e);
          SearchDataImpl<V> vData = (SearchDataImpl<V>) searchData.get(v);
          if (vData.getColor() == VertexColor.White) {
            uData.addSucessors(v);
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
