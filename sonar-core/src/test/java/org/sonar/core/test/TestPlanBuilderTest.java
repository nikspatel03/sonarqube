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
package org.sonar.core.test;

import org.junit.Test;
import org.sonar.api.component.mock.MockSourceFile;
import org.sonar.api.test.MutableTestPlan;
import org.sonar.core.component.ComponentVertex;
import org.sonar.core.component.ScanGraph;

import static org.fest.assertions.Assertions.assertThat;

public class TestPlanBuilderTest {
  @Test
  public void test_path() {

    ScanGraph graph = ScanGraph.create();
    TestPlanPerspectiveLoader loader = new TestPlanPerspectiveLoader();
    TestPlanBuilder builder = new TestPlanBuilder(graph, loader);

    assertThat(builder.path().getElements()).isNotEmpty();
  }

  @Test
  public void should_not_load_missing_perspective() {
    ScanGraph graph = ScanGraph.create();
    TestPlanPerspectiveLoader loader = new TestPlanPerspectiveLoader();
    TestPlanBuilder builder = new TestPlanBuilder(graph, loader);
    ComponentVertex file = graph.addComponent(MockSourceFile.createMain("org.foo.Bar"));

    assertThat(builder.getPerspectiveLoader().load(file)).isNull();
  }

  @Test
  public void should_create_perspective() {
    ScanGraph graph = ScanGraph.create();
    TestPlanPerspectiveLoader loader = new TestPlanPerspectiveLoader();
    TestPlanBuilder builder = new TestPlanBuilder(graph, loader);
    ComponentVertex file = graph.addComponent(MockSourceFile.createMain("org.foo.Bar"));

    MutableTestPlan plan = builder.create(file);
    assertThat(plan).isNotNull();
    assertThat(plan.component()).isSameAs(file);
    assertThat(builder.getPerspectiveLoader().load(file)).isSameAs(plan);
  }
}
