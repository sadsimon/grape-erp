export interface DocumentInt {
	id: number | null //商品id
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
}

export interface DocumentDetailInt {
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

export interface AccountDetailInt {
	accountName: string
	amount: number
}