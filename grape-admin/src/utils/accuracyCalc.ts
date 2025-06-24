import Big from 'big.js'

export interface CalcChain {
    add(value: string | number | null | undefined): this
    sub(value: string | number | null | undefined): this
    mul(value: string | number | null | undefined): this
    div(value: string | number | null | undefined): this
    toFixed(): string     // 保留两位小数的字符串
    toNumber(): number     // 转换为JavaScript数字
    valueOf(): InstanceType<typeof Big>
}

/**
 * 将任意值转换为Big.js可用的数值
 * 规则：
 * 1. null/undefined -> 0
 * 2. number -> 直接转换（注意浮点数精度）
 * 3. string -> 字符串转换（推荐）
 */
function toBigValue(value: string | number | null | undefined): string {
    if (value == null) return '0';
    return typeof value === 'number' ? value.toString() : value;
}

export function calcChain(initialValue: string | number | null | undefined): CalcChain {
    let big = new Big(toBigValue(initialValue)); // 使用 let 允许重新赋值

    return {
        add(value) { 
            big = big.plus(toBigValue(value))
            return this; 
        },
        sub(value) { 
            big = big.minus(toBigValue(value))
            return this; 
        },
        mul(value) { 
            big = big.times(toBigValue(value))
            return this; 
        },
        div(value) { 
            const val = toBigValue(value);
            if (new Big(val).eq(0)) throw new Error('Division by zero');
            big = big.div(val)
            return this; 
        },
        toFixed(decimals = 2) { 
            return big.toFixed(decimals)
        },
        toNumber(decimals = 2) {
			return Number(big.toFixed(decimals)) 
			},
        valueOf() { 
            return big; 
        }
    };
}
