# typeparser
General type parser for Java types.

`AnyParser` will parse Strings into DataTypes specified:

``` 
Double d = AnyParser.parse("321.098", Double.class);
String s = AnyParser.parse("Hello World", String.class);
InetAddress a = AnyParser.parse("192.168.0.1", InetAddress.class);
```

There is a formatter, too:
```
System.out.println(AnyParser.format(new String("Hello World"));
System.out.println(AnyParser.format(new Double(321.098));
System.out.println(AnyParser.format(new Date());
```

There are type-specific parsers, too.
``` 
DoubleParser dp = new DoubleParser();
Double d = dp.parse("321.098");
```

The list of parser types is:
* BigDecimal
* BigInteger
* Boolean
* ByteBuffer
* Date (default format is "yyyy-MM-dd HH:mm:ss")
* Double
* Fload
* InetAddress
* Integer
* Long
* Number
* String
* UUID

