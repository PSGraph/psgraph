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
import java.util.Map;
import org.psgraph.graph.Edge;
import org.psgraph.graph.Vertex;
import org.psgraph.graph.search.visitor.EdgeVisitor;
import org.psgraph.graph.search.visitor.SearchDataVisitor;
import org.psgraph.graph.search.visitor.VertexVisitor;

/**
 * Fundamental definitions for search algorithms.
 *
 * @author Wilson de Carvalho
 */
public interface GraphSearch<V extends Vertex, E extends Edge<V>> {

  /**
   * Perform the graph search in all of the graph's vertices.
   *
   * @return A map with the search data containing all the graph's vertices.
   */
  Map<V, SearchData<V>> search();

  /**
   * Perform the graph search in all of the graph's vertices.
   *
   * @param visitor The visitor used to control the search. In case it returns false, the
   * topological search will stop at that point.
   * @return A map with the search data containing all visited vertices.
   */
  Map<V, SearchData<V>> search(VertexVisitor<V> visitor);

  /**
   * Perform the graph search in all of the graph's vertices.
   *
   * @param visitor The visitor used to control the search. In case it returns false, the
   * topological search will stop at that point.
   * @return A collection containing all visited edges.
   */
  Collection<E> search(EdgeVisitor<V, E> visitor);

  /**
   * Perform the graph search starting in the informed vertex.
   *
   * @param s Start vertex for the search.
   * @return A map with the search data containing all the graph's vertices (including those not
   * reached by this search).
   */
  Map<V, SearchData<V>> search(V s);

  /**
   * Perform the graph search starting in the informed vertex.
   *
   * @param s Start vertex for the search.
   * @param visitor The visitor used to control the search. In case it returns false, the
   * topological search will stop at that point.
   * @return A map with the search data containing all visited vertices.
   */
  Map<V, SearchData<V>> search(V s, VertexVisitor<V> visitor);

  /**
   * Perform the graph search starting in the informed vertex.
   *
   * @param s Start vertex for the search.
   * @param visitor The visitor used to control the search. In case it returns false, the
   * topological search will stop at that point.
   * @return A collection containing all visited edges.
   */
  Collection<E> search(V s, EdgeVisitor<V, E> visitor);

  /**
   * Perform the graph search in all of the graph's vertices.
   *
   * @param visitor The visitor used to control the search. In case it returns false, the
   * topological search will stop at that point.
   * @return A map with the search data containing all visited vertices.
   */
  Map<V, SearchData<V>> search(SearchDataVisitor<V> visitor);

  /**
   * Perform the graph search starting in the informed vertex.
   *
   * @param s Start vertex for the search.
   * @param visitor The visitor used to control the search. In case it returns false, the
   * topological search will stop at that point.
   * @return A map with the search data containing all visited vertices.
   */
  Map<V, SearchData<V>> search(V s, SearchDataVisitor<V> visitor);
}
