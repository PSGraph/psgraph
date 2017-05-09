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

package org.psgraph.circuit.elements;

/**
 * A switchable element that can be used to interrupt the current flow from its terminals or to be
 * grounded.
 *
 * @author Wilson de Carvalho
 */
public interface Switchable {

  /**
   * Gets the current status of this switchable element.
   */
  SwitchStatus getStatus();

  /**
   * Sets a new status for this switchable element.
   */
  void setStatus(SwitchStatus status);
}
