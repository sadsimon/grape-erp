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
	unitPrice?: number | null
	expectPurchasePrice?: number | null
	amount?: number | null
	taxRate?: number | null
	taxAmount?: number | null
	finalAmount?: number | null
	remark?: string | null
	projectId?: number | null
	inStoreId?: number | null
	stockCount?: number | null
	img?: string | null
}

export interface AccountDetail {
	accountName: string
	amount: number
}

import { useContactunitsApi } from '@/api/product/contactunits'

interface AdvanceData {
  advanceIn: number
  advanceOut: number
}

export async function getContactunitsAdvanceIn(id: number) : Promise<number> {
  const { advanceIn } = await getContactunitsAdvance(id)
  return advanceIn
}

export async function getContactunitsAdvanceOut(id: number) : Promise<number> {
  const { advanceOut } = await getContactunitsAdvance(id)
  return advanceOut
}

async function  getContactunitsAdvance(id: number): Promise<AdvanceData> {
  const res = await useContactunitsApi(id)
  return {
      advanceIn: res.data.advanceIn,
      advanceOut: res.data.advanceOut
    }
}