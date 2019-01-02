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

import java.util.Locale;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.ParseException;

// General number parser
// This is useful as it can take care of Locales for us
// That means comma as a decimal separator, etc.
public class NumberParser extends AbstractParser<Number> {
    protected NumberFormat nf;
    public NumberParser() {
        this(null);
    }
    
    public NumberParser(Locale locale) {
        if (null == locale)
            locale = Locale.ENGLISH;
        nf = NumberFormat.getInstance(locale);
    }
    
    // Need this method for the subclasses
    public Number parseIt(String toparse) throws ParseException {
        if ((null == toparse) || (0 == toparse.length()))
            return null;
        return nf.parse(toparse);
    }

    public String formatIt(Object o) {
        return nf.format(o);
    }
}
