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

package org.sonar.core.source;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class DecorationDataHolderTest {

  private static final String SAMPLE_SYNTAX_HIGHLIGHTING_RULES = "0,8,k;0,52,cppd;54,67,a;69,75,k;106,130,cppd;114,130,k;";
  private static final String SAMPLE_SYMBOLS_REFERENCES = "80,85,80,90,140;";

  private DecorationDataHolder decorationDataHolder;

  @Before
  public void setUpHighlightingContext() {
    decorationDataHolder = new DecorationDataHolder();
    decorationDataHolder.loadSyntaxHighlightingData(SAMPLE_SYNTAX_HIGHLIGHTING_RULES);
    decorationDataHolder.loadSymbolReferences(SAMPLE_SYMBOLS_REFERENCES);
  }

  @Test
  public void should_extract_lower_bounds_from_serialized_rules() throws Exception {

    List<TagEntry> openingTagsEntries = decorationDataHolder.getOpeningTagsEntries();

    assertThat(openingTagsEntries.get(0)).isEqualTo(new TagEntry(0, "k"));
    assertThat(openingTagsEntries.get(1)).isEqualTo(new TagEntry(0, "cppd"));
    assertThat(openingTagsEntries.get(2)).isEqualTo(new TagEntry(54, "a"));
    assertThat(openingTagsEntries.get(3)).isEqualTo(new TagEntry(69, "k"));
    assertThat(openingTagsEntries.get(4)).isEqualTo(new TagEntry(80, "symbol-80 highlightable"));
    assertThat(openingTagsEntries.get(5)).isEqualTo(new TagEntry(90, "symbol-80 highlightable"));
    assertThat(openingTagsEntries.get(6)).isEqualTo(new TagEntry(106, "cppd"));
    assertThat(openingTagsEntries.get(7)).isEqualTo(new TagEntry(114, "k"));
    assertThat(openingTagsEntries.get(8)).isEqualTo(new TagEntry(140, "symbol-80 highlightable"));
  }

  @Test
  public void should_extract_upper_bounds_from_serialized_rules() throws Exception {

    List<Integer> offsets = decorationDataHolder.getClosingTagsOffsets();

    assertThat(offsets.get(0)).isEqualTo(8);
    assertThat(offsets.get(1)).isEqualTo(52);
    assertThat(offsets.get(2)).isEqualTo(67);
    assertThat(offsets.get(3)).isEqualTo(75);
    assertThat(offsets.get(4)).isEqualTo(85);
    assertThat(offsets.get(5)).isEqualTo(95);
    assertThat(offsets.get(6)).isEqualTo(130);
    assertThat(offsets.get(7)).isEqualTo(130);
    assertThat(offsets.get(8)).isEqualTo(145);
  }
}
