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
package org.sonar.duplications.statement.matcher;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.sonar.duplications.token.Token;
import org.sonar.duplications.token.TokenQueue;

import com.google.common.collect.Lists;

public class ForgetLastTokenMatcherTest {

  @Test
  public void shouldMatch() {
    TokenQueue tokenQueue = spy(new TokenQueue());
    Token token = new Token("a", 0, 0);
    List<Token> output = Lists.newArrayList(token);
    ForgetLastTokenMatcher matcher = new ForgetLastTokenMatcher();

    assertThat(matcher.matchToken(tokenQueue, output), is(true));
    assertThat(output.size(), is(0));
    verify(tokenQueue).pushForward(eq(Collections.singletonList(token)));
  }

}
