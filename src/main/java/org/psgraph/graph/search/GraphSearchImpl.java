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

/**
 * Fundamental definitions for search algorithms.
 *
 * @author Wilson de Carvalho
 */
public abstract class GraphSearchImpl<V extends Vertex, E extends Edge<V>> implements
    GraphSearch<V, E> {

  protected final Graph<V, E> graph;

  public GraphSearchImpl(Graph<V, E> graph) {
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
    return search((Visitor<V>) visitor);
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
        ret.addAll(edgeSearch(v, u -> true, searchData));
      }
    }
    return ret;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<V, SearchData<V>> search(V s) {
    return search(s, (Visitor<V> v) -> true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<V, SearchData<V>> search(V s, VertexVisitor<V> visitor) {
    return search(s, (Visitor<V>) visitor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<E> search(V s, EdgeVisitor<V, E> visitor) {
    Map<V, SearchData<V>> searchData = initSearchData();
    return edgeSearch(s, visitor, searchData);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<V, SearchData<V>> search(SearchDataVisitor<V> visitor) {
    return search((Visitor<SearchData<V>>) visitor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<V, SearchData<V>> search(V s, SearchDataVisitor<V> visitor) {
    return search(s, (Visitor<SearchData<V>>) visitor);
  }

  /**
   * Search for all vertices in the graph using a VertexVisitor or a SearchDataVisitor.
   */
  private <T> Map<V, SearchData<V>> search(Visitor<T> visitor) {
    Map<V, SearchData<V>> searchData = initSearchData();
    for (V v : graph.getVertices()) {
      if (searchData.get(v).getColor() == VertexColor.White) {
        vertexSearch(v, visitor, searchData);
      }
    }
    return searchData;
  }

  /**
   * Search starting in a vertex s using a VertexVisitor or a SearchDataVisitor.
   */
  private <T> Map<V, SearchData<V>> search(V s, Visitor<T> visitor) {
    Map<V, SearchData<V>> searchData = initSearchData();
    vertexSearch(s, visitor, searchData);
    return (new SearchUtil()).filterVisitedVertices(searchData);
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
   * Executes the search algorithm for a given start vertex <b>s</b>.
   *
   * @param s Start vertex.
   * @param visitor The vertex visitor used to control the search. In case the visitor returns false
   * for a given vertex, the topological search will stop at that point.
   * @param searchData The map that will be used to control visited vertices and its data.
   */
  protected abstract <T> void vertexSearch(V s, Visitor<T> visitor,
      Map<V, SearchData<V>> searchData);

  /**
   * Executes the search algorithm for a given start vertex <b>s</b>.
   *
   * @param s Start vertex.
   * @param visitor The edge visitor used to control the search. In case the visitor returns false
   * for a given edge, the topological search will stop at that point.
   * @param searchData The map that will be used to control visited vertices and its data.
   * @return A collection with the edges visited during the search.
   */
  protected abstract Set<E> edgeSearch(V s, EdgeVisitor<V, E> visitor,
      Map<V, SearchData<V>> searchData);
}
