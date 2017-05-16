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

import java.util.LinkedHashSet;
import java.util.Set;
import org.psgraph.graph.Vertex;

/**
 * Stores the search data for graph searching algorithms.
 *
 * @author Wilson de Carvalho
 */
class SearchDataImpl<V extends Vertex> implements SearchData<V> {

  private V vertex = null;
  private VertexColor color = VertexColor.White;
  private int time = 0;
  private int depth = 0;
  private V predecessor = null;
  private Set<V> sucessors = new LinkedHashSet<>();

  public SearchDataImpl(V vertex) {
    this.vertex = vertex;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public V getVertex() {
    return vertex;
  }

  public void setVertex(V vertex) {
    this.vertex = vertex;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public VertexColor getColor() {
    return color;
  }

  public void setColor(VertexColor color) {
    this.color = color;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getDepth() {
    return depth;
  }

  public void setDepth(int depth) {
    this.depth = depth;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public V getPredecessor() {
    return predecessor;
  }

  public void setPredecessor(V predecessor) {
    this.predecessor = predecessor;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<V> getSucessors() {
    return sucessors;
  }

  public void addSucessors(V sucessor) {
    this.sucessors.add(sucessor);
  }
}
