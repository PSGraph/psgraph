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

package org.psgraph.circuit.elements.logical;

import org.psgraph.circuit.elements.Identifiable;
import org.psgraph.circuit.elements.electrical.CircuitElement;

/**
 * A feeder is a logical element that is used to associate physical elements.
 *
 * @author Wilson de Carvalho
 * @type T The class type of the feeder's id.
 */
public class Feeder<T> implements Identifiable<T>, LogicalElement {

  /**
   * Reference element that is used to indicate where this feeder starts.
   */
  private CircuitElement elementRef;
  /**
   * The original source element that is used to indicate the steady configuration for a given
   * feeder.
   */
  private CircuitElement elementSource;

  private final T id;

  public Feeder(T id, CircuitElement elementRef, CircuitElement elementSource) {
    this.id = id;
    this.elementRef = elementRef;
    this.elementSource = elementSource;
  }

  public Feeder(T id, CircuitElement elementRef) {
    this(id, elementRef, null);
  }

  /**
   * @inheritDoc
   */
  @Override
  public T getId() {
    return this.id;
  }
}
