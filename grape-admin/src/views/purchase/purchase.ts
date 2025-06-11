export interface dataInt {
	id: number | null //条码id
	productId: number | null
	storeId: number | null
	number: string
	name: string
	barcode: string
	specs: string
	quantity: number | null
	unitId: string
	unitPrice: number | null
	expectPurchasePrice: number | null
	amount: number | null
	projectId?: number | null
	inStoreId?: number | null
	stockCount?: number
}

export interface DocumentDetail {
  img: string
  productName: string
  productNumber: string
  barcode: string
  storeName: string
  quantity: number
  unitName: string
  unitPrice: number
  amount: number
}

export interface AccountDetail {
	accountName: string
	amount: number
}