/*
 * SonarQube, open source software quality management tool.
 * Copyright (C) 2008-2012 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * SonarQube is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * SonarQube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Sonar; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.server.charts;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.ServerComponent;
import org.sonar.api.charts.Chart;

import java.util.Map;

public final class ChartFactory implements ServerComponent {
  private static final Logger LOG = LoggerFactory.getLogger(ChartFactory.class);
  private Map<String, Chart> chartsByKey = Maps.newHashMap();


  public ChartFactory(Chart[] charts) {
    for (Chart chart : charts) {
      if (chartsByKey.containsKey(chart.getKey())) {
        LOG.warn("Duplicated chart key:" + chart.getKey() + ". Existing chart: " + chartsByKey.get(chart.getKey()).getClass().getCanonicalName());

      } else {
        chartsByKey.put(chart.getKey(), chart);
      }
    }
  }

  public ChartFactory() {
    // DO NOT SUPPRESS : used by picocontainer if no charts
  }

  public Chart getChart(String key) {
    return chartsByKey.get(key);
  }
}
