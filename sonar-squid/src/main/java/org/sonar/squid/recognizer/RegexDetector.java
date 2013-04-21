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

package org.sonar.squid.recognizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDetector extends Detector {

  private Pattern regex;

  public RegexDetector(String regex, double probability) {
    super(probability);
    this.regex = Pattern.compile(regex);
  }

  @Override
  public int scan(String line) {
    Matcher matcher = regex.matcher(line);
    int matchers = 0;
    while (matcher.find()) {
      matchers++;
    }
    return matchers;
  }
}
