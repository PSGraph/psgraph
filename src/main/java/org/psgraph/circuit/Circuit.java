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

package org.psgraph.circuit;

import java.util.HashSet;
import java.util.Set;
import org.psgraph.circuit.elements.electrical.CircuitElement;

/**
 * @author Wilson de Carvalho
 */
public class Circuit {

  private final Set<CircuitElement> elementSet;

  public Circuit() {
    this.elementSet = new HashSet<>();
  }
}
