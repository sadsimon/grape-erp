package net.grape.framework.mybatis.interceptor;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.parser.ASTNodeAccessImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StringValue extends ASTNodeAccessImpl implements Expression {

    private String value = "";
    private String prefix = null;

    public static final List<String> ALLOWED_PREFIXES = Arrays.asList("N", "U", "E", "R", "B", "RB", "_utf8");

    public StringValue() {
        // empty constructor
    }

    public StringValue(String escapedValue) {
        // removing "'" at the start and at the end
        if (escapedValue.startsWith("'") && escapedValue.endsWith("'")) {
            value = escapedValue.substring(1, escapedValue.length() - 1);
            return;
        }

        if (escapedValue.length() > 2) {
            for (String p : ALLOWED_PREFIXES) {
                if (escapedValue.length() > p.length() && escapedValue.substring(0, p.length()).equalsIgnoreCase(p)
                        && escapedValue.charAt(p.length()) == '\'') {
                    this.prefix = p;
                    value = escapedValue.substring(p.length() + 1, escapedValue.length() - 1);
                    return;
                }
            }
        }

        value = escapedValue;
    }

    public String getValue() {
        return value;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getNotExcapedValue() {
        StringBuilder buffer = new StringBuilder(value);
        int index = 0;
        int deletesNum = 0;
        while ((index = value.indexOf("''", index)) != -1) {
            buffer.deleteCharAt(index - deletesNum);
            index += 2;
            deletesNum++;
        }
        return buffer.toString();
    }

    public void setValue(String string) {
        value = string;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void accept(ExpressionVisitor expressionVisitor) {
//        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return (prefix != null ? prefix : "") + value;
    }

    public StringValue withPrefix(String prefix) {
        this.setPrefix(prefix);
        return this;
    }

    public StringValue withValue(String value) {
        this.setValue(value);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StringValue that = (StringValue) o;
        return Objects.equals(value, that.value) && Objects.equals(prefix, that.prefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, prefix);
    }

}
