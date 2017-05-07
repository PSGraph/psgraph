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

package org.psgraph.graph.exception;

/**
 * Base exception for graph operations.
 *
 * @author Wilson de Carvalho
 */
public class GraphException extends Exception {

  public GraphException() {
    super();
  }

  public GraphException(String msg) {
    super(msg);
  }

  public GraphException(Exception e) {
    super(e);
  }

  public GraphException(String msg, Exception e) {
    super(msg, e);
  }
}
