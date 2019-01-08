package hessian.typeparser;

import com.datastax.driver.core.UserType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.util.*;

public class AnyParser {
    private static Map<Type, Parser> pmap = initPmap();

    public static <T> T parse(String instr, Type t) throws ParseException {
        if (null == instr)
            return null;
        return (T)getParser(t).parse(instr);
    }

    public static String format(Object o) throws ParseException {
        if (null == o) {
            return format(o, String.class);
        }
        return format(o, o.getClass());
    }

    public static String format(Object o, Type t) throws ParseException {
        return getParser(t).format(o);
    }

    public static String formatQuoted(Object o) throws ParseException {
        return formatQuoted(o, o.getClass());
    }

    public static String formatQuoted(Object o, Type t) throws ParseException {
        return getParser(t).format(o);
    }

    private static Map<Type, Parser> initPmap() {
        pmap = new HashMap<>();
        pmap.put(BigDecimal.class, new BigDecimalParser());
        pmap.put(BigInteger.class, new BigIntegerParser());
        pmap.put(Boolean.class, new BooleanParser());
        pmap.put(ByteBuffer.class, new ByteBufferParser());
        pmap.put(Date.class, new DateParser());
        pmap.put(Double.class, new DoubleParser());
        pmap.put(Float.class, new FloatParser());
        pmap.put(InetAddress.class, new InetAddressParser());
        pmap.put(Integer.class, new IntegerParser());
        pmap.put(Long.class, new LongParser());
        pmap.put(String.class, new StringParser());
        pmap.put(UUID.class, new UUIDParser());

        return pmap;
    }

    private static Parser getParser(Type t) throws ParseException {
        Parser parser;
        if (t instanceof Collection) {
            if (t instanceof List) {
                Parser listParser = getParser(((ParameterizedType)t).getActualTypeArguments()[0]);
                if (null == listParser) {
                    throw new ParseException("List data type not recognized ("
                            + t.getClass().getTypeParameters()[0]
                            + ")", 0);
                }
                parser = new ListParser(listParser, ',', '[', ']');
            }
            else if (t instanceof Set) {
                Parser setParser = getParser(((ParameterizedType)t).getActualTypeArguments()[0]);
                if (null == setParser) {
                    throw new ParseException("Set data type not recognized ("
                            + t.getClass().getTypeParameters()[0]
                            + ")", 0);
                }
                parser = new SetParser(setParser, ',', '{', '}');
            }
            else if (t instanceof Map) {
                Parser keyParser = getParser(((ParameterizedType)t).getActualTypeArguments()[0]);
                if (null == keyParser) {
                    throw new ParseException("Map key data type not recognized ("
                            + t.getClass().getTypeParameters()[0]
                            + ")", 0);
                }
                Parser valueParser = getParser(((ParameterizedType)t).getActualTypeArguments()[1]);
                if (null == valueParser) {
                    throw new ParseException("Map value data type not recognized ("
                            + t.getClass().getTypeParameters()[1]
                            + ")", 0);
                }
                parser = new MapParser(keyParser, valueParser, ',', '{', '}', ':');
            }
            else {
                throw new ParseException("Collection data type not recognized ("
                        + t.toString() + ")", 0);
            }
        }
        else if (t instanceof UserType) {
            parser = getUdtParser((UserType)t);
        }
        else {
            parser = pmap.get(t);
            if (null == parser) {
                throw new ParseException("Column data type not recognized (" + t.toString() + ")", 0);
            }
        }
        return parser;
    }

    private static Parser getUdtParser(UserType ut) throws ParseException {
        throw new ParseException("UDTs not yet supported", 0);
    }
}
