export interface DocumentDetail {
	id: number | null //条码id
	productId: number | null
	storeId: number | null
	specs: string
	productName: string
	productNumber: string
	barcode: string
	storeName?: string
	quantity: number | null
	unitId: string
	unitName?: string
	unitPrice?: number
	expectPurchasePrice?: number | null
	amount?: number
	taxRate?: number | null
	taxAmount?: number | null
	finalAmount?: number | null
	remark?: string
	projectId?: number | null
	inStoreId?: number | null
	stockCount?: number
	img?: string
}

export interface AccountDetail {
	accountName: string
	amount: number
}