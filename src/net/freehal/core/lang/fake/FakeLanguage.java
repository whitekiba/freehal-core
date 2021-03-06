/*******************************************************************************
 * Copyright (c) 2006 - 2012 Tobias Schulz and Contributors.
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/gpl.html>.
 ******************************************************************************/
package net.freehal.core.lang.fake;

import net.freehal.core.lang.LanguageSpecific;
import net.freehal.core.lang.Language;

public class FakeLanguage implements Language {

	private static final String code = "en";

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public boolean isCode(String otherCode) {
		return code.equals(otherCode);
	}

	public static void initializeDefaults() {
		LanguageSpecific.add(null, FakeGrammar.class);
		LanguageSpecific.add(null, FakeParser.class);
		LanguageSpecific.add(null, FakeTagger.class);
		LanguageSpecific.add(null, FakeWording.class);
	}
}
