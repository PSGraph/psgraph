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
 * An identifiable object have an unique id that distinguishes it from other objects of the same
 * class. Thus, ids may be repeated as long as we are dealing with different objects.
 *
 * @author Wilson de Carvalho
 */
public interface Identifiable<T> {

  /**
   * Gets the current id of this identifiable.
   */
  T getId();
}
