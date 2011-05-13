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

package slash.navigation.simple;

import slash.common.io.CompactCalendar;
import slash.common.io.Transfer;
import slash.navigation.base.*;

import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads and writes Opel Navi 600/900 (.poi) files.
 * <p/>
 * Format: 9.614394,52.769282,"Zoo Hodenhagen","Serengeti Safaripark","+(49) 5164531"
 *
 * @author Christian Pesch
 */

public class OpelNaviFormat extends SimpleLineBasedFormat<SimpleRoute> {
    protected static final Logger log = Logger.getLogger(OpelNaviFormat.class.getName());

    private static final char SEPARATOR = ',';
    private static final char QUOTE = '"';

    private static final Pattern LINE_PATTERN = Pattern.
            compile(BEGIN_OF_LINE +
                    WHITE_SPACE + "(" + POSITION + ")" + WHITE_SPACE + SEPARATOR +
                    WHITE_SPACE + "(" + POSITION + ")" + WHITE_SPACE + SEPARATOR +
                    WHITE_SPACE + QUOTE + "([^" + QUOTE + "]*)" + QUOTE + WHITE_SPACE + SEPARATOR +
                    WHITE_SPACE + QUOTE + "([^" + QUOTE + "]*)" + QUOTE + WHITE_SPACE + SEPARATOR +
                    WHITE_SPACE + QUOTE + "([^" + QUOTE + "]*)" + QUOTE + WHITE_SPACE +
                    END_OF_LINE);

    public String getName() {
        return "Opel Navi 600/900 (*" + getExtension() + ")";
    }

    public String getExtension() {
        return ".poi";
    }

    @SuppressWarnings("unchecked")
    public <P extends BaseNavigationPosition> SimpleRoute createRoute(RouteCharacteristics characteristics, String name, List<P> positions) {
        return new Wgs84Route(this, characteristics, (List<Wgs84Position>) positions);
    }

    protected boolean isValidLine(String line) {
        if (line == null)
            return false;
        Matcher matcher = LINE_PATTERN.matcher(line);
        return matcher.matches();
    }

    protected boolean isPosition(String line) {
        Matcher matcher = LINE_PATTERN.matcher(line);
        return matcher.matches();
    }

    protected Wgs84Position parsePosition(String line, CompactCalendar startDate) {
        Matcher lineMatcher = LINE_PATTERN.matcher(line);
        if (!lineMatcher.matches())
            throw new IllegalArgumentException("'" + line + "' does not match");
        Double longitude = Transfer.parseDouble(lineMatcher.group(1));
        Double latitude = Transfer.parseDouble(lineMatcher.group(2));
        String name = Transfer.trim(lineMatcher.group(3));
        String extra = Transfer.trim(lineMatcher.group(4));
        String phone = Transfer.trim(lineMatcher.group(5));

        String comment = name;
        if (extra != null)
            comment += ";" + extra;
        if (phone != null)
            comment += ";" + phone;

        Wgs84Position position = new Wgs84Position(longitude, latitude, null, null, null, comment);
        position.setStartDate(startDate);
        return position;
    }

    private String formatComment(String string, int maximumLength) {
        string = Transfer.escape(string, SEPARATOR, ';');
        return string != null ? string.substring(0, Math.min(string.length(), maximumLength)) : null;
    }

    protected void writePosition(Wgs84Position position, PrintWriter writer, int index, boolean firstPosition) {
        String longitude = Transfer.formatDoubleAsString(position.getLongitude(), 6);
        String latitude = Transfer.formatDoubleAsString(position.getLatitude(), 6);

        String[] strings = position.getComment().split(";");
        String comment = strings.length > 0 ? formatComment(strings[0], 60) : "";
        String extra = strings.length > 1 ? formatComment(strings[1], 60) : "";
        String phone = strings.length > 2 ? formatComment(strings[2], 30) : "";

        writer.println(longitude + SEPARATOR + latitude + SEPARATOR +
                QUOTE + comment + QUOTE + SEPARATOR +
                QUOTE + extra + QUOTE + SEPARATOR +
                QUOTE + phone + QUOTE);
    }
}