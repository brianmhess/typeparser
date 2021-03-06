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

import java.math.BigDecimal;

// BigDecimal parser
public class BigDecimalParser extends AbstractParser<BigDecimal> {
    public BigDecimal parseIt(String toparse) throws NumberFormatException {
        if (null == toparse)
            return null;
        return new BigDecimal(toparse);
    }

    public String formatIt(Object o) {
        BigDecimal v = (BigDecimal)o;
        return v.toString();
    }
}
