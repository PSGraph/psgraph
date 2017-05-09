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
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.psgraph.graph.Edge;
import org.psgraph.graph.Graph;
import org.psgraph.graph.Vertex;

/**
 * Strongly connected components algorithm as described by Tarjan, R. E. (1972) in "Depth-first
 * search and linear graph algorithms".
 *
 * @author Wilson de Carvalho
 */
public class StronglyConnectedComponents<V extends Vertex, E extends Edge<V>> {

  private final Graph<V, E> graph;
  private int sccIndex;

  private class VertexData {

    int index = -1;
    int lowLink;
    boolean onStack = false;
  }

  public StronglyConnectedComponents(Graph<V, E> graph) {
    this.graph = graph;
  }

  /**
   * Gets a set of strongly connected components (scc) with a search performed from the start node
   * provided.
   *
   * @param s Start vertex for the search.
   * @return A set in which each internal set corresponds to a scc.
   */
  public Set<Set<V>> scc(V s) {
    return scc((new DepthFirstSearch<>(graph)).search(s));
  }

  /**
   * Gets a set of strongly connected components (scc) with a search performed from the start node
   * provided.
   *
   * @param searchData Start vertex for the search.
   * @return A set in which each internal set corresponds to a scc.
   */
  public Set<Set<V>> scc(Map<V, SearchData<V>> searchData) {
    return tarjan(searchData);
  }

  /**
   * Executes the tarjan algorithm. Code inspired on the pseudo code available at:
   * https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm
   */
  private Set<Set<V>> tarjan(Map<V, SearchData<V>> searchData) {
    Set<Set<V>> ret = new HashSet<>();
    Map<V, VertexData> sccMap = new HashMap<>();
    searchData.keySet().forEach(v -> sccMap.put(v, new VertexData()));
    Deque<V> S = new ArrayDeque<>();
    sccIndex = 0;
    for (Map.Entry<V, VertexData> entry : sccMap.entrySet()) {
      if (entry.getValue().index == -1) {
        ret.addAll(strongConnect(entry.getKey(), S, sccMap, searchData));
      }
    }
    return ret;
  }

  private Set<Set<V>> strongConnect(V vertexV, Deque<V> S,
      Map<V, VertexData> sccMap, final Map<V, SearchData<V>> searchData) {
    Set<Set<V>> ret = new HashSet<>();
    VertexData v = sccMap.get(vertexV);
    v.index = sccIndex;
    v.lowLink = ++sccIndex;
    S.push(vertexV);
    v.onStack = true;
    for (V vertexW : searchData.get(vertexV).getSucessors()) {
      VertexData w = sccMap.get(vertexW);
      if (w.index == -1) {
        ret.addAll(strongConnect(vertexW, S, sccMap, searchData));
        v.lowLink = v.lowLink < w.lowLink ? v.lowLink : w.lowLink;
      } else if (w.onStack) {
        v.lowLink = v.lowLink < w.index ? v.lowLink : w.index;
      }
    }
    if (v.lowLink == v.index) {
      Set<V> scc = new HashSet<>();
      V vertexW;
      do {
        vertexW = S.pop();
        sccMap.get(vertexW).onStack = false;
        scc.add(vertexW);
      } while (vertexW != vertexV);
      if (scc.size() > 1) {
        ret.add(Collections.unmodifiableSet(scc));
      }
    }
    return Collections.unmodifiableSet(ret);
  }
}
