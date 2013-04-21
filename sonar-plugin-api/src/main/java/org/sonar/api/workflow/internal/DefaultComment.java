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
package org.sonar.api.workflow.internal;

import com.google.common.annotations.Beta;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.sonar.api.workflow.Comment;

/**
 * @since 3.1
 */
@Beta
public final class DefaultComment implements Comment {
  private String markdownText;
  private Long userId;

  DefaultComment() {
  }

  public String getMarkdownText() {
    return markdownText;
  }

  public DefaultComment setMarkdownText(String s) {
    this.markdownText = s;
    return this;
  }

  public Long getUserId() {
    return userId;
  }

  public DefaultComment setUserId(Long l) {
    this.userId = l;
    return this;
  }

  @Override
  public String toString() {
    return new ReflectionToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).toString();
  }
}
