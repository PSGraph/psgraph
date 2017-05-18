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

import java.util.Map;
import java.util.stream.Collectors;
import org.psgraph.graph.Vertex;

/**
 * Code that can be reused among different search classes but are not worth being inherited.
 *
 * @author Wilson de Carvalho.
 */
public class SearchUtil {

  /**
   * Filter visited vertices (black colored).
   */
  public <V extends Vertex> Map<V, SearchData<V>> filterVisitedVertices(
      Map<V, SearchData<V>> map) {
    return map.entrySet().stream().filter(v -> v.getValue().getColor() == VertexColor.Black)
        .collect(
            Collectors.toMap(v -> v.getKey(), v -> v.getValue()));
  }

}
