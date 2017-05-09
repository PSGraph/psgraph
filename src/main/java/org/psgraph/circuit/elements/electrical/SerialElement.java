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

package org.psgraph.circuit.elements.electrical;

/**
 * Defines a serial circuit element.
 *
 * @author Wilson de Carvalho
 */
public interface SerialElement extends CircuitElement {

  /**
   * A reversible element allows power flow to be reversed from its default configuration.
   */
  void setReversible(boolean reversible);

  /**
   * A reversible element allows power flow to be reversed from its default configuration.
   */
  boolean isReversible();
}
