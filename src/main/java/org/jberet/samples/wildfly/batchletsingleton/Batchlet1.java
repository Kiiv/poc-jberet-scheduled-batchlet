/*
 * Copyright (c) 2020 Red Hat, Inc. and/or its affiliates.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.jberet.samples.wildfly.batchletsingleton;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class Batchlet1 extends AbstractBatchlet {
  private static final Logger LOGGER = Logger.getLogger(Batchlet1.class.getPackage().getName());

  @Inject
  private StepContext stepContext;

  @Inject
  private BatchletSingletonBean batchletSingletonBean;

  @Override
  public String process() {
    final boolean stopThisBatchlet = batchletSingletonBean.getStop();

    // if stop this bathlet, return exit status STOP
    // else do more processing to complete this batchlet, and return exit status COMPLETE
    final String exitStatus = stopThisBatchlet ? "STOP" : "COMPLETE";

    LOGGER.log(Level.INFO, "Batchlet process() of {0}, exit status: {1}",
        new String[] { stepContext.getStepName(), exitStatus });
    return exitStatus;
  }

}
