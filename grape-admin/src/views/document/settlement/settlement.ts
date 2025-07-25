export interface documentDataInt {
	id: number | null //账户id
	documentCode: string
	documentType: string
	documentTime: string
	contactunitsName: string
	contactunitsCode: string
	realName: string
	remark: string
	finalAmount: number | null
	pendingAmount: number | null
	paymentAmount: number | null
	advanceIn?: number | null
	advanceOut?: number | null
	discountAmount: number | null
	sumAmount: number | null
}

/**
 * 单据支付详情
 */
export interface SettleDetailInt {
	key: number | null
	id: number | null //结算单据主键
	documentCode: string
	settleDocumentCode: String
	paymentAmount: number | null
	amountType: string
	advanceIn?: number | null
	advanceOut?: number | null
	discountAmount: number | null
}

/**
 * 结算单据支付账号详情
 */
export interface DocumentAccountDetailInt {
	key: number | null
	id: number | null //结算单据支付账号详情主键
	accountId: number | null
	accountCode: string
	accountName: string
	amount: number | null
	amountType: string
	remark: string
}