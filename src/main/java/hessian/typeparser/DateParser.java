/*
 * Copyright 2015 Brian Hess
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hessian.typeparser;

import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

// Date parser - takes a format string
public class DateParser extends AbstractParser<Date> {
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    public DateParser() {
        this(null);
    }

    public DateParser(String inFormatString) {
        if (null != inFormatString)
            format = new SimpleDateFormat(inFormatString, Locale.ENGLISH);
    }
    
    public Date parseIt(String toparse) throws ParseException {
        if (null == toparse)
            return null;
        return format.parse(toparse);
    }

    public String formatIt(Object o) {
        Date v = (Date)o;
    if (v == null)
        return null;
        return format.format(v);
    }
}
