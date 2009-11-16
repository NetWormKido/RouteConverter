/*
    This file is part of RouteConverter.

    RouteConverter is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    RouteConverter is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with RouteConverter; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

    Copyright (C) 2007 Christian Pesch. All Rights Reserved.
*/

package slash.navigation.ovl;

import slash.navigation.IniFileSection;
import slash.navigation.Wgs84Position;
import slash.common.io.Transfer;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a section in a Top50 OVL ASCII (.ovl) file,
 * excluding the positions.
 *
 * @author Christian Pesch
 */

class OvlSection extends IniFileSection {
    private static final Pattern EASY_GPS_PATTERN = Pattern.compile(".*?([-\\d\\.]+).*?([-\\d\\.]+).*?");

    static final String GROUP = "Group";
    static final String POSITION_COUNT = "Punkte";
    static final String X_POSITION = "XKoord";
    static final String Y_POSITION = "YKoord";
    static final String TEXT = "Text";

    public OvlSection(String title) {
        super(title);
    }

    Integer getGroup() {
        return getInteger(GROUP);
    }

    String getText() {
        return get(TEXT);
    }

    void setText(String text) {
        put(TEXT, text);
    }

    int getPositionCount() {
        Integer positionCount = getInteger(POSITION_COUNT);
        if (positionCount == null) {
            if (get(X_POSITION) != null && get(Y_POSITION) != null)
                positionCount = 1;
        }
        return positionCount != null ? positionCount : 0;
    }

    Wgs84Position getPosition(int index) {
        Double x, y;
        String indexKey = getPositionCount() > 1 ? Integer.toString(index) : "";
        String xValue = Transfer.trim(get(X_POSITION + indexKey));
        String yValue = Transfer.trim(get(Y_POSITION + indexKey));
        String comment = getPositionCount() == 1 ? Transfer.trim(getText()) : null;
        // for the strange format of EasyGPS
        if (yValue == null && xValue != null) {
            Matcher matcher = EASY_GPS_PATTERN.matcher(xValue);
            if (matcher.matches()) {
                xValue = Transfer.trim(matcher.group(1));
                yValue = Transfer.trim(matcher.group(2));
            }
        }
        x = Transfer.parseDouble(xValue);
        y = Transfer.parseDouble(yValue);
        return new Wgs84Position(x, y, null, null, null, comment);
    }

    void removePositions() {
        for (String key : new HashSet<String>(keySet())) {
            if (key.startsWith(X_POSITION) || key.startsWith(Y_POSITION))
                remove(key);
        }
        remove(GROUP);
        remove(TEXT);
        remove(POSITION_COUNT);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OvlSection section = (OvlSection) o;

        return nameValues.equals(section.nameValues) && title.equals(section.title);
    }

    public int hashCode() {
        int result;
        result = nameValues.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }
}