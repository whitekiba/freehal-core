/*******************************************************************************
 * Copyright (c) 2006 - 2012 Tobias Schulz and Contributors.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl.html>.
 ******************************************************************************/
package net.freehal.core.database;

import net.freehal.core.util.FreehalFile;
import java.util.List;
import java.util.Set;

import net.freehal.core.xml.Word;
import net.freehal.core.xml.XmlFact;

public interface Database {

	public Set<XmlFact> findFacts(XmlFact xfact);

	public Set<XmlFact> findFacts(List<Word> words);

	public Set<XmlFact> findFacts(Word word);

	public void updateCache();

	public void updateCache(FreehalFile filename);

}