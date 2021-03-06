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

import java.util.HashSet;
import java.util.Set;

/**
 * @author Wilson de Carvalho
 */
public class SubstationInfluence {

  private final Set<Feeder> feeders;

  public SubstationInfluence() {
    this.feeders = new HashSet<>();
  }
}
